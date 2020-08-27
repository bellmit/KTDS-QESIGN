package com.ktds.esign.client.example.domain;

import com.ktds.esign.common.audit.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@ToString
@EqualsAndHashCode(callSuper = false, of = "cmpnId")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "example_company")
public class ExCompany extends BaseEntity {

    @Id
    @Column(name="cmpn_id", length = 75, nullable = false)
    private String cmpnId;

    @Column(length = 75, nullable = false)
    private String cmpnName;

    @Column(length = 75, nullable = false)
    private String diplayOrder;

}
