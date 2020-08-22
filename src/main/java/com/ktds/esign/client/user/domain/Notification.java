package com.ktds.esign.client.user.domain;

import com.ktds.esign.common.enums.NotiType;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_noti")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private NotiType notiType;
}
