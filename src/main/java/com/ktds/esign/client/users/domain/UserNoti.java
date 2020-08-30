package com.ktds.esign.client.users.domain;

import com.ktds.esign.common.audit.BaseEntity;
import com.ktds.esign.common.enums.NotiDirectType;
import com.ktds.esign.common.enums.NotiType;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@ToString(exclude = "user")
@EqualsAndHashCode(callSuper = false, of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_user_noti")
public class UserNoti extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    @Convert(converter = NotiDirectType.Converter.class)
    private NotiDirectType notiDirectType;

    @Column(length = 20, nullable = false)
    @Convert(converter = NotiType.Converter.class)
    private NotiType notiType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_tb_user_noti_user_id"))
    private User user;

    // utility method
    public void changeUser(User user) {
        this.user = user;
        if (!user.getUserNotis().contains(this)) {
            user.getUserNotis().add(this);
        }
    }

    // create method
    public static UserNoti createUserNoti(NotiType notiType, NotiDirectType directionType) {
        return UserNoti.builder()
                .notiType(notiType)
                .notiDirectType(directionType)
                .build();
    }

}
