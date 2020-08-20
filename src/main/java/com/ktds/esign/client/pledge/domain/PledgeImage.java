package com.ktds.esign.client.pledge.domain;

import com.ktds.esign.common.audit.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false, exclude = "pledge")
@NoArgsConstructor
@Entity
@Table(name = "tb_pledge_image")
public class PledgeImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String imagePath;

    @Column(length = 100, nullable = false)
    private String imageName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pledge_id", foreignKey = @ForeignKey(name = "fk_tb_pledge_image_pledge_id"))
    private Pledge pledge;
}
