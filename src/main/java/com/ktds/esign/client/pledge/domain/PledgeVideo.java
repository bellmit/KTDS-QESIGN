package com.ktds.esign.client.pledge.domain;

import com.ktds.esign.common.audit.BaseEntity;
import lombok.*;

import javax.persistence.*;


@Builder
@Getter
@ToString(exclude = "pledge")
@EqualsAndHashCode(callSuper = false, of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_pledge_video")
public class PledgeVideo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String videoPath;

    @Column(length = 100, nullable = false)
    private String videoName;

    // 비디오 필수 재생 시간(초단위: 1분 ==> 60 )
    @Column(length = 5, columnDefinition = "integer default 0")
    private Integer watchTimes;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "pledgeVideo")
    private Pledge pledge;
}
