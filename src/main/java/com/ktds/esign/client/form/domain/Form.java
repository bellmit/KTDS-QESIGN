package com.ktds.esign.client.form.domain;

import com.ktds.esign.client.users.domain.User;
import com.ktds.esign.common.audit.BaseEntity;
import com.ktds.esign.common.enums.ContentsType;
import com.ktds.esign.common.enums.FormType;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@ToString(exclude = {"user", "formUsers"})
@EqualsAndHashCode(callSuper = false, of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_form")
public class Form extends BaseEntity {

    @Id
    @Column(name = "form_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String formName;

    @Column(length = 20, nullable = false, columnDefinition = "varchar(20) default 'PERSONAL'")
    @Convert(converter = FormType.Converter.class)
    private FormType formType;

    @Column(length = 20, nullable = false, columnDefinition = "varchar(20) default 'HTML'")
    @Convert(converter = ContentsType.Converter.class)
    private ContentsType contentsType;

    @Column(columnDefinition = "text")
    private String formContents;

    // 영상 저장 경로
    private String videoFilePath;

    // 영상 이름
    private String videoFileName;

    // 삭제 여부
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean deleteYn;

    // 사용 여부
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean useYn;

    // form 생성자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns(value = {
            @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            @JoinColumn(name = "company_id", referencedColumnName = "company_id")
    }, foreignKey = @ForeignKey(name = "fk_tb_form_user_pk"))
    private User user;

    // 공통 양식 사용자 지정
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "form", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<FormUser> formUsers = new ArrayList<>();

    // utility method
    public void addFormUser(FormUser formUser) {
        this.formUsers.add(formUser);
        if (formUser.getForm() != this) {
            formUser.changeForm(this);
        }
    }

}
