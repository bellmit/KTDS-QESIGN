package com.ktds.esign.client.example.domain;

import com.ktds.esign.common.audit.BaseEntity;
import com.ktds.esign.common.enums.DirectionType;
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

    @Convert(converter = DirectionType.Converter.class)
    private DirectionType directionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_ex_user_noti_user_id"))
    private ExUser user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "noti_id", foreignKey = @ForeignKey(name = "fk_ex_user_noti_noti_id"))
    private ExNoti noti;

    // utility method
    public void changeUser(ExUser user) {
        this.user = user;
        if (!user.getUserNotis().contains(this)) {
            user.getUserNotis().add(this);
        }
    }

}
