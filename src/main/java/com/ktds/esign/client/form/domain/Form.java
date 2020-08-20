package com.ktds.esign.client.form.domain;

import com.ktds.esign.client.user.domain.User;
import com.ktds.esign.common.audit.BaseEntity;
import com.ktds.esign.common.enums.FormTypeCode;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(exclude = {"user", "forImages"})
@EqualsAndHashCode(callSuper = false, of = {"id", "formCode"})
@NoArgsConstructor
@Entity
@Table(name = "tb_form",
        uniqueConstraints =
        @UniqueConstraint(name = "tb_form_unique", columnNames = {"formCode"})
)
public class Form extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String formCode;

    @Column(length = 100, nullable = false)
    private String formName;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private FormTypeCode formType;

    @Column(columnDefinition = "text")
    private String formContent;

    // 이미지 사용 여부
    @Column(columnDefinition = "boolean default false", nullable = false)
    private boolean useImageYn;

    // 사용자 지정 여부
    @Column(columnDefinition = "boolean default false", nullable = false)
    private boolean specifyUserYn;

    // 삭제 여부
    @Column(columnDefinition = "boolean default false", nullable = false)
    private boolean deleteYn;

    // 사용 여부
    @Column(columnDefinition = "boolean default false", nullable = false)
    private boolean useYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_tb_form_user_id"))
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "form", cascade = CascadeType.ALL)
    private List<FormImage> forImages = new ArrayList<>();

}
