package com.ktds.esign.code.domain;

import com.ktds.esign.common.audit.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@ToString(exclude = "codes")
@EqualsAndHashCode(callSuper = false, of = "groupCodeId")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_group_code")
public class GroupCode extends BaseEntity {

    @Id
    @Column(length = 20, nullable = false)
    private String groupCodeId;

    @Column(length = 50, nullable = false)
    private String groupDesc;

    @OneToMany(mappedBy="groupCode", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private final List<Code> codes = new ArrayList<>();

}
