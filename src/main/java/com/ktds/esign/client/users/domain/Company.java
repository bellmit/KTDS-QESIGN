package com.ktds.esign.client.users.domain;

import com.ktds.esign.common.audit.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@ToString
@EqualsAndHashCode(callSuper = false, of = "companyId")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_company")
public class Company extends BaseEntity {

    @Id
    @Column(length = 75)
    private String companyId;

    @Column(length = 75, nullable = false)
    private String companyName;

    @Column(length = 10)
    private String displayOrder;

}
