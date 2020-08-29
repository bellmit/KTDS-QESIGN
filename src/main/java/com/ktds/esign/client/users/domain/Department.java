package com.ktds.esign.client.users.domain;

import com.ktds.esign.common.audit.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@ToString(exclude = {"parent", "children", "company"})
@EqualsAndHashCode(callSuper = false, of = {"id", "deptId"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_department",
        uniqueConstraints =
        @UniqueConstraint(name = "tb_department_unique", columnNames = {"deptId"})
)
public class Department extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 75, nullable = false)
    private String deptId;

    @Column(length = 75, nullable = false)
    private String deptName;

    @Column(length = 75)
    private String deptLevel;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_dept_id", foreignKey = @ForeignKey(name = "fk_tb_department_parent_dept_id"))
    private Department parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    private final List<Department> children = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cmpn_id", foreignKey = @ForeignKey(name = "fk_tb_department_cmpn_id"))
    private Company company;

    // utility methods
    public void changeCompany(Company company) {
        this.company = company;
        if (!company.getDepartments().contains(this)) {
            company.getDepartments().add(this);
        }
    }

}