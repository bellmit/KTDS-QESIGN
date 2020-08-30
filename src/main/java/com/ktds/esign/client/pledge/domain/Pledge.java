package com.ktds.esign.client.pledge.domain;

import com.ktds.esign.client.form.domain.Form;
import com.ktds.esign.client.users.domain.User;
import com.ktds.esign.common.audit.BaseEntity;
import com.ktds.esign.common.enums.ProgsSttusType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@ToString(exclude = {"form", "user", "pledgeUsers"})
@EqualsAndHashCode(callSuper = false, of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_pledge")
public class Pledge extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 4, nullable = false)
    private String pledgeNo;

    @Column(length = 60, nullable = false)
    private String pledgeName;

    @Column(nullable = false, columnDefinition = "text")
    private String pledgeContents;

    // 비디오 재생 필요 시간(초단위)
    @Column(nullable = false, columnDefinition = "integer default 0")
    private Integer videoPlaySec;

    // 서약 작업 상태
    @Column(length = 20, nullable = false, columnDefinition = "varchar(20) default 'STANDBY'")
    @Convert(converter = ProgsSttusType.Converter.class)
    private ProgsSttusType progsSttusType;

    // 서약 개시일(요청일)
    @Column(nullable = false)
    private LocalDateTime startDt;

    // 서약 종료일(마감일)
    @Column(nullable = false)
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pledge", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<PledgeUser> pledgeUsers = new ArrayList<>();

}
