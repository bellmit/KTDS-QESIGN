package com.ktds.esign.client.users.domain;

import com.ktds.esign.common.audit.BaseEntity;
import com.ktds.esign.common.enums.NotiType;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@ToString
@EqualsAndHashCode(callSuper = false, of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_noti")
public class Noti extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    @Convert(converter = NotiType.Converter.class)
    private NotiType notiType;
}
