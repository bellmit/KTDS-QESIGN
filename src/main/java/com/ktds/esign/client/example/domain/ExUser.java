package com.ktds.esign.client.example.domain;

import com.ktds.esign.common.audit.BaseEntity;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@ToString(exclude = {"company"})
@EqualsAndHashCode(callSuper = false, of = "userId")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "example_user")
public class ExUser extends BaseEntity {

    @Id
    @Column(name = "user_id", length = 75)
    private String userId;

    @Column(length = 75, nullable = false, unique = true)
    private String empNo;

    @Column(length = 75, nullable = false)
    private String empName;

    @Column(length = 75, nullable = false, unique = true)
    private String email;

    @Column(length = 75, nullable = false)
    private String phone;

    @Column(length = 75, nullable = false)
    private String deptId;

    @Column(length = 255)
    private String signFilePath;

    @Column(length = 255)
    private String signFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cmpn_id", foreignKey = @ForeignKey(name = "fk_ex_user_cmpn_id"))
    private ExCompany company;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ExUserNoti> userNotis = new ArrayList<>();

    // utility method
    public void addUserNoti(ExUserNoti userNoti) {
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
