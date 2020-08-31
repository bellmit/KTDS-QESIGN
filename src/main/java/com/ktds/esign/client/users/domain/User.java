package com.ktds.esign.client.users.domain;

import com.ktds.esign.common.audit.BaseEntity;
import com.ktds.esign.common.enums.UserType;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@ToString(exclude = {"userNotis", "roles", "company"})
@EqualsAndHashCode(callSuper = false, of = "userId")
@NoArgsConstructor
@AllArgsConstructor
@IdClass(UserPk.class)
@Entity
@Table(name = "tb_user", uniqueConstraints =
@UniqueConstraint(name = "uk_tb_user_unique", columnNames = {"empNo", "email"})
)
public class User extends BaseEntity {

    @Id
    @Column(name = "user_id", length = 75)
    private String userId;

    @Id
    @Column(name = "company_id", length = 75)
    private String companyId;

    @Column(length = 75, nullable = false)
    private String username;

    @Column(length = 75)
    private String empNo;

    @Column(length = 75)
    private String deptId;

    @Column(length = 75)
    private String deptName;

    @Column(length = 75, nullable = false)
    private String email;

    @Column(length = 75)
    private String phone;

    // 서명 이미지 저장 경로
    private String signFilePath;

    // 서명 이미지 이름
    private String signFileName;

    // 협력사 여부
    @Column(length = 20, nullable = false, columnDefinition = "varchar(20) default 'STAFF'")
    @Convert(converter = UserType.Converter.class)
    private UserType userType;

    // 삭제 여부
    @Column(columnDefinition = "boolean default false", nullable = false)
    private String deleteYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_tb_use_company_id"))
    private Company company;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tb_user_role",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
                    @JoinColumn(name = "company_id", referencedColumnName = "company_id")
            },
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            foreignKey = @ForeignKey(name = "fk_tb_user_role_user_pk"),
            inverseForeignKey = @ForeignKey(name = "fk_tb_user_role_role_id")
    )
    private final Set<Role> roles = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<UserNoti> userNotis = new ArrayList<>();

    // utility method
    public void addUserNoti(UserNoti userNoti) {
        this.userNotis.add(userNoti);
        if (userNoti.getUser() != this) {
            userNoti.changeUser(this);
        }
    }

    // save user sign
    public void saveUserSign(String signFilePath, String signFileName) {
        if (StringUtils.isNotBlank(signFilePath) && StringUtils.isNoneBlank(signFileName)) {
            this.signFilePath = signFilePath;
            this.signFileName = signFileName;
        }
    }

}
