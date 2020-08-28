package com.ktds.esign.client.example.domain;

import com.ktds.esign.common.audit.BaseEntity;
import com.ktds.esign.common.enums.ContentsType;
import com.ktds.esign.common.enums.ProgsSttusType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter // mpastruct를 이용해 entity update시 setter 필요
@Entity
@ToString
@EqualsAndHashCode(callSuper = false, of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "example_user_pledge")
public class ExUserPledge extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 서약 진행 상태
    @Column(length = 20)
    @Convert(converter = ProgsSttusType.Converter.class)
    private ProgsSttusType progsSttusType;

    // 양식 컨텐츠 유형(HTML, VIDEO)
    @Column(length = 20)
    @Convert(converter = ContentsType.Converter.class)
    private ContentsType contentsType;

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
