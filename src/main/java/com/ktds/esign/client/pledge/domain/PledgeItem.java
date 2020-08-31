package com.ktds.esign.client.pledge.domain;

import com.ktds.esign.client.users.domain.User;
import com.ktds.esign.common.audit.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@ToString(exclude = {"pledge", "user"})
@EqualsAndHashCode(callSuper = false, of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_pledge_item")
public class PledgeItem extends BaseEntity {

    @Id
    @Column(name = "pledge_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pledge_id", foreignKey = @ForeignKey(name = "fk_tb_pledge_item_pledge_id"))
    private Pledge pledge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns(value = {
            @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            @JoinColumn(name = "company_id", referencedColumnName = "company_id")
    }, foreignKey = @ForeignKey(name = "fk_tb_pledge_user_user_pk"))
    private User user;

    @Column(length = 50, nullable = false)
    private String itemName;

    @Column(columnDefinition = "text", nullable = false)
    private String itemContent;

    @Column(length = 10, nullable = false)
    private String displayOrder;


}
