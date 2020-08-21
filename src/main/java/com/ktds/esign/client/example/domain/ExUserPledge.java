package com.ktds.esign.client.example.domain;

import com.ktds.esign.common.enums.PledgeTypeCode;
import com.ktds.esign.common.enums.PledgeUserStatusCode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@ToString
@EqualsAndHashCode(callSuper = false, of = "id")
@NoArgsConstructor
@Table(name = "example_user_pledge")
public class ExUserPledge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 서약 작업 상태
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private PledgeUserStatusCode pledgeUserStatus;

    // 서약 유형
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private PledgeTypeCode pledgeType;

    // 서약서명
    @Column(length = 60, nullable = false)
    private String pledgeName;

    // 서약 요청일
    private LocalDateTime startDt;

    // 서약 마감일
    private LocalDateTime endDt;

    // 요청부서
    @Column(length = 20, nullable = false)
    private String reqDept;

    // 요청자
    @Column(length = 30, nullable = false)
    private String reqUser;


}
