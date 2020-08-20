package com.ktds.esign.code.domain;

import com.ktds.esign.common.audit.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false, of = {"id", "groupCode", "code"})
@NoArgsConstructor
@Entity
@Table(name = "tb_common_code")
public class CommonCode extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String groupCode;

    @Column(length = 50, nullable = false)
    private String groupDesc;

    @Column(length = 20, nullable = false)
    private String code;

    @Column(length = 50, nullable = false)
    private String codeDesc;

    @Column(length = 2)
    private String displayOrder;

}
