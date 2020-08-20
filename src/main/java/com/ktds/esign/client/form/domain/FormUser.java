package com.ktds.esign.client.form.domain;

import com.ktds.esign.client.user.domain.User;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false, exclude = {"form", "user"})
@NoArgsConstructor
@Entity
@Table(name = "tb_form_user")
public class FormUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_id", foreignKey = @ForeignKey(name = "fk_tb_form_user_form_id"))
    private Form form;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_tb_form_user_user_id"))
    private User user;

}