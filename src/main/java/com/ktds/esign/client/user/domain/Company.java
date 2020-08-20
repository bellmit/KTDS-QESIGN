package com.ktds.esign.client.user.domain;

import com.ktds.esign.common.audit.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false, of = {"id", "cmpnId"})
@NoArgsConstructor
@Entity
@Table(name = "tb_company",
        uniqueConstraints =
        @UniqueConstraint(name = "tb_company_unique", columnNames = {"cmpnId"})
)
public class Company extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 75, nullable = false)
    private String cmpnId;

    @Column(length = 75, nullable = false)
    private String cmpnName;

    @Column(length = 75)
    private String displayOrder;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "company")
    private List<Department> departments = new ArrayList<>();

}
