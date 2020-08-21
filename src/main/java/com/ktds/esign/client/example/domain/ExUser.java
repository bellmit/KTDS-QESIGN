package com.ktds.esign.client.example.domain;

import com.ktds.esign.common.audit.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@ToString(exclude = {"department"})
@EqualsAndHashCode(callSuper = false, of = {"id", "empNo", "email"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "example_user",
        uniqueConstraints =
        @UniqueConstraint(name = "ex_user_unique", columnNames = {"empNo", "email"})
)
public class ExUser extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 75, nullable = false)
    private String empNo;

    @Column(length = 75, nullable = false)
    private String empName;

    @Column(length = 75)
    private String email;

    @Column(length = 75)
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id", foreignKey = @ForeignKey(name = "fk_ex_user_dept_id"))
    private ExDepartment department;

}
