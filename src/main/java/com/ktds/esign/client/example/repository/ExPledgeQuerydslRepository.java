package com.ktds.esign.client.example.repository;

import com.ktds.esign.client.example.domain.ExUserPledge;
import com.ktds.esign.client.example.payload.ExUserPledgeReq.SearchDto;
import com.ktds.esign.common.enums.PledgeType;
import com.ktds.esign.common.querydsl.Querydsl4RepositorySupport;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

import static com.ktds.esign.client.example.domain.QExUserPledge.exUserPledge;

@Repository
public class ExPledgeQuerydslRepository extends Querydsl4RepositorySupport {

    public ExPledgeQuerydslRepository() {
        super(ExUserPledge.class);
    }

    /**
     * select all user_pledges
     *
     * @return
     */
    public Page<ExUserPledge> selectUserPledgeList(SearchDto searchDto, Pageable pageable) {
        return applyPagination(pageable, contentQuery -> contentQuery
                .selectFrom(exUserPledge)
                .where(
                        this.equalPledgeType(searchDto.getPledgeType()),
                        this.containsPledgeName(searchDto.getPledgeName()),
                        this.equalReqUser(searchDto.getReqUser()),
                        this.equalReqDept(searchDto.getReqDept()),
                        this.betweenStartDtAndEndDt(searchDto.getDateType(), searchDto.getStartDt(), searchDto.getEndDt())
                )
                .orderBy(
                        exUserPledge.id.asc()
                        //,exUserPledge.endDt.asc()
                        //,exUserPledge.userPledgeStatus.desc()
                ));
    }


    /**
     * Where Clause
     */
    // 서약 유형
    private BooleanExpression equalPledgeType(String pledgeType) {
        return StringUtils.hasText(pledgeType) ? exUserPledge.pledgeType.eq(PledgeType.getTypeFromCode(pledgeType)) : null;
    }

    // 서약 명
    private BooleanExpression containsPledgeName(String pledgeName) {
        return StringUtils.hasText(pledgeName) ? exUserPledge.pledgeName.contains(pledgeName) : null;
    }

    // 요청자
    private BooleanExpression equalReqUser(String reqUser) {
        return StringUtils.hasText(reqUser) ? exUserPledge.reqUser.eq(reqUser) : null;
    }

    // 요청부서
    private BooleanExpression equalReqDept(String reqDept) {
        return StringUtils.hasText(reqDept) ? exUserPledge.reqDept.eq(reqDept) : null;
    }

    // 날짜 조건 검색 between
    private BooleanExpression betweenStartDtAndEndDt(String dateType, LocalDateTime startDt, LocalDateTime endDt) {
        if (StringUtils.hasText(dateType) && dateType.equals("startDt")) {
            return exUserPledge.startDt.between(startDt, endDt);
        } else if (StringUtils.hasText(dateType) && dateType.equals("endDt")) {
            return exUserPledge.endDt.between(startDt, endDt);
        }
        return null;
    }

}
