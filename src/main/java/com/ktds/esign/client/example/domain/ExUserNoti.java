package com.ktds.esign.client.example.domain;

import com.ktds.esign.common.audit.BaseEntity;
import com.ktds.esign.common.enums.NotiDirectType;
import com.ktds.esign.common.enums.NotiType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "example_user_noti")
public class ExUserNoti extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = NotiDirectType.Converter.class)
    private NotiDirectType directionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_ex_user_noti_user_id"))
    private ExUser user;

    @Convert(converter = NotiType.Converter.class)
    private NotiType notiType;

    // utility method
    public void changeUser(ExUser user) {
        this.user = user;
        if (!user.getUserNotis().contains(this)) {
            user.getUserNotis().add(this);
        }
    }

    // create method
    public static ExUserNoti createExUserNoti(NotiType notiType, NotiDirectType directionType) {
        return ExUserNoti.builder()
                .notiType(notiType)
                .directionType(directionType)
                .build();
    }

}
