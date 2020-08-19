package com.ktds.esign.client.user.domain;

import com.ktds.esign.common.audit.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_users",
        indexes = {@Index(name = "tb_users_index", columnList = "empNo, cmpnId")},
        uniqueConstraints =
        @UniqueConstraint(name = "tb_users_unique", columnNames = {"empNo", "cmpnId", "deptId", "email"})
)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 75, nullable = false)
    private String empNo;

    @Column(length = 75, nullable = false)
    private String empName;

    @Column(length = 75)
    private String email;

    @Column(length = 75)
    private String phone;

    @Column(length = 75, nullable = false)
    private String cmpnId;

    @Column(length = 75, nullable = false)
    private String cmpnName;

    @Column(length = 75)
    private String deptId;

    @Column(length = 75)
    private String deptName;

    @Column(length = 75)
    private String positionId;

    @Column(length = 75)
    private String positionName;

    // 서명 이미지 저장 경로
    @Column(length = 100)
    private String signFilePath;

    // 서명 이미지 이름
    @Column(length = 40)
    private String signFileName;

    // 협력사 여부
    @Column(columnDefinition = "boolean default false", nullable = false)
    private String isPartner;

    // 삭제 여부
    @Column(columnDefinition = "boolean default false", nullable = false)
    private String isDeleted;

}
