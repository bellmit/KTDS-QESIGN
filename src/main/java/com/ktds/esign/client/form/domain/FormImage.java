package com.ktds.esign.client.form.domain;

import com.ktds.esign.common.audit.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@ToString(exclude = "form")
@EqualsAndHashCode(callSuper = false, of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_form_image")
public class FormImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String uploadPath;

    @Column(length = 100, nullable = false)
    private String imageName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_id", foreignKey = @ForeignKey(name = "fk_tb_form_image_form_id"))
    private Form form;

}
