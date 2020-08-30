package com.ktds.esign.client.form.domain;

import com.ktds.esign.client.users.domain.User;
import com.ktds.esign.common.audit.BaseEntity;
import lombok.*;

import javax.persistence.*;

/**
 * 공통 양식 사용자
 */
@Builder
@Getter
@ToString(exclude = {"form", "user"})
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_form_user")
public class FormUser extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_id", foreignKey = @ForeignKey(name = "fk_tb_form_user_form_id"))
    private Form form;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_tb_form_user_user_id"))
    private User user;

    // utility method
    public void changeForm(Form form) {
        this.form = form;
        if (!form.getFormUsers().contains(this)) {
            form.getFormUsers().add(this);
        }
    }

    // create method
    public static FormUser createFormUser(Form form, User user) {
        return FormUser.builder()
                .form(form)
                .user(user)
                .build();
    }

}
