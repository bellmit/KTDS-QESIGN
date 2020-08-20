package com.ktds.esign.client.user.domain;

import com.ktds.esign.common.audit.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@ToString(exclude = "departments")
@EqualsAndHashCode(callSuper = false, of = {"id", "cmpnId"})
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company", cascade = CascadeType.ALL)
    private final List<Department> departments = new ArrayList<>();

    // utility methods
    public void addDepartment(Department department) {
        this.departments.add(department);
        if (department.getCompany() != this) {
            department.changeCompany(this);
        }
    }

}
