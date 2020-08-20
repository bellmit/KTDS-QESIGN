package com.ktds.esign.client.pledge.domain;

import com.ktds.esign.client.user.domain.User;
import com.ktds.esign.common.audit.BaseEntity;
import com.ktds.esign.common.enums.PledgeUserStatusCode;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@ToString(exclude = {"pledge", "user"})
@EqualsAndHashCode(callSuper = false, of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_pledge_user")
public class PledgeUser extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pledge_id", foreignKey = @ForeignKey(name = "fk_tb_pledge_user_pledge_id"))
    private Pledge pledge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_tb_pledge_user_user_id"))
    private User user;

    // 서약 대상자의 서약 진행 상태
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private PledgeUserStatusCode pledgeUserStatus;

    // 서약 서명일
    private LocalDateTime pledgeDt;

    // 비디오 필요 재생 완료 여부
    @Column(columnDefinition = "boolean default false", nullable = false)
    private boolean playVideoYn;

}
