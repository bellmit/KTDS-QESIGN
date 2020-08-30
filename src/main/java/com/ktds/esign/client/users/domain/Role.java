package com.ktds.esign.client.users.domain;

import com.ktds.esign.common.audit.BaseEntity;
import com.ktds.esign.common.enums.RoleType;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@ToString
@EqualsAndHashCode(callSuper = false, of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_role")
public class Role extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    @Convert(converter = RoleType.Converter.class)
    private RoleType roleType;
}