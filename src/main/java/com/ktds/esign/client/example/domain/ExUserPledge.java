package com.ktds.esign.client.example.domain;

import com.ktds.esign.common.enums.PledgeProgType;
import com.ktds.esign.common.enums.PledgeType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@Entity
@ToString
@EqualsAndHashCode(callSuper = false, of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "example_user_pledge")
public class ExUserPledge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 서약 작업 상태
    @Column(length = 20)
    @Convert(converter = PledgeProgType.Converter.class)
    private PledgeProgType pledgeProgType;

    // 서약 유형
    @Column(length = 20)
    @Convert(converter = PledgeType.Converter.class)
    private PledgeType pledgeType;

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
