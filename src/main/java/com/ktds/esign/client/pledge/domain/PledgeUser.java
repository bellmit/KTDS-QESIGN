package com.ktds.esign.client.pledge.domain;

import com.ktds.esign.client.users.domain.User;
import com.ktds.esign.common.audit.BaseEntity;
import com.ktds.esign.common.enums.ProgsSttusType;
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
    @Column(name = "pledge_user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pledge_id", foreignKey = @ForeignKey(name = "fk_tb_pledge_user_pledge_id"))
    private Pledge pledge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns(value = {
            @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            @JoinColumn(name = "company_id", referencedColumnName = "company_id")
    }, foreignKey = @ForeignKey(name = "fk_tb_pledge_user_user_pk"))
    private User user;

    // 서명 진행 상태
    @Column(length = 20, nullable = false, columnDefinition = "varchar(20) default 'STANDBY'")
    @Convert(converter = ProgsSttusType.Converter.class)
    private ProgsSttusType progsSttus;

    // 서약 서명일
    private LocalDateTime pledgeDt;

    // 비디오 필요 재생 완료 여부
    @Column(columnDefinition = "boolean default false", nullable = false)
    private boolean videoPlayYn;

}
