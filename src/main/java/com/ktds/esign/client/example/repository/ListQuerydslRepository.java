package com.ktds.esign.client.example.repository;

import com.ktds.esign.client.example.domain.ExUserPledge;
import com.ktds.esign.client.example.payload.ListReq.SearchDto;
import com.ktds.esign.common.querydsl.Querydsl4RepositorySupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import static com.ktds.esign.client.example.domain.QExUserPledge.exUserPledge;

@Repository
public class ListQuerydslRepository extends Querydsl4RepositorySupport {

    public ListQuerydslRepository() {
        super(ExUserPledge.class);
    }

    /**
     * select all user_pledges
     * @return
     */
    public Page<ExUserPledge> selectUserPledgeList(SearchDto searchDto, Pageable pageable) {
        return applyPagination(pageable, contentQuery -> contentQuery
                .selectFrom(exUserPledge)
                .orderBy(
                        exUserPledge.id.asc()
                        //,exUserPledge.endDt.asc()
                        //,exUserPledge.userPledgeStatus.desc()
                ));
    }

}
