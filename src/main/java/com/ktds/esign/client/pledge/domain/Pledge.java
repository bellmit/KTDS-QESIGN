package com.ktds.esign.client.pledge.domain;

import com.ktds.esign.client.form.domain.Form;
import com.ktds.esign.client.user.domain.User;
import com.ktds.esign.common.audit.BaseEntity;
import com.ktds.esign.common.enums.PledgeTypeCode;
import com.ktds.esign.common.enums.PledgeWorkStatusCode;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@ToString(exclude = {"form", "user", "pledgeImages", "pledgeUsers", "pledgeVideo"})
@EqualsAndHashCode(callSuper = false, of = {"id", "pledgeCode"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_pledge")
public class Pledge extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 4, nullable = false)
    private String pledgeCode;

    @Column(length = 60, nullable = false)
    private String pledgeName;

    @Column(columnDefinition = "text")
    private String pledgeDesc;

    // 서약 타입
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private PledgeTypeCode pledgeType;

    // 서약 작업 상태
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private PledgeWorkStatusCode pledgeWorkStatus;

    // 서약 개시일(요청일)
    private LocalDateTime startDt;

    // 서약 종료일(마감일)
    private LocalDateTime endDt;

    // 삭제 여부
    @Column(columnDefinition = "boolean default false", nullable = false)
    private String deleteYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_id", foreignKey = @ForeignKey(name = "fk_tb_pledge_form_id"))
    private Form form;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_tb_pledge_user_id"))
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pledge", cascade = CascadeType.ALL)
    private final List<PledgeImage> pledgeImages = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pledge", cascade = CascadeType.ALL)
    private final List<PledgeUser> pledgeUsers = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "video_id", foreignKey = @ForeignKey(name = "fk_tb_pledge_video_id"))
    private PledgeVideo pledgeVideo;

}
