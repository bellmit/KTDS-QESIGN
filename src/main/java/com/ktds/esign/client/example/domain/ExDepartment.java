package com.ktds.esign.client.example.domain;

import com.ktds.esign.common.audit.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@ToString(exclude = {"parent", "children"})
@EqualsAndHashCode(callSuper = false, of = {"id", "deptId"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "example_department",
        uniqueConstraints =
        @UniqueConstraint(name = "ex_department_unique", columnNames = {"deptId"})
)
public class ExDepartment extends BaseEntity {

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
    @JoinColumn(name = "parent_dept_id", foreignKey = @ForeignKey(name = "fk_ex_department_parent_dept_id"))
    private ExDepartment parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    private final List<ExDepartment> children = new ArrayList<>();

}
