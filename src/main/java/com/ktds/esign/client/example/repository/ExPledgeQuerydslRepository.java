package com.ktds.esign.client.example.repository;

import com.ktds.esign.client.example.domain.ExUserPledge;
import com.ktds.esign.client.example.payload.ExUserPledgeReq.SearchDto;
import com.ktds.esign.common.enums.PledgeAcceptType;
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
                        this.equalPledgeAcceptType(searchDto.getPledgeAcceptType()),
                        this.equalPledgeType(searchDto.getPledgeType()),
                        this.equalSearchType(searchDto),
                        this.betweenStartDtAndEndDt(searchDto.getDateType(), searchDto.getStartDt(), searchDto.getEndDt())
                )
                .orderBy(
                        exUserPledge.startDt.asc(),
                        exUserPledge.id.desc()
                ));
    }


    /**
     * Where Clause
     */
    // 선택 조건 검색 :searchType 값이 있으면 검색 조건 값이 있음
    private BooleanExpression equalSearchType(SearchDto searchDto) {
        if ("pledgeName".equals(searchDto.getSearchType())) {
            return this.containsPledgeName(searchDto.getPledgeName());
        } else if ("reqDept".equals(searchDto.getSearchType())) {
            return this.containsReqDept(searchDto.getReqDept());
        } else if ("reqUser".equals(searchDto.getSearchType())) {
            return this.containsReqUser(searchDto.getReqUser());
        } else if ("all".equals(searchDto.getSearchType())) {
            return exUserPledge.pledgeName.contains(searchDto.getPledgeName())
                    .or(exUserPledge.reqUser.contains(searchDto.getReqUser()))
                    .or(exUserPledge.reqDept.contains(searchDto.getReqDept()));
        } else {
            return null;
        }
    }

    // 서약 유형
    private BooleanExpression equalPledgeType(String pledgeType) {
        return StringUtils.hasText(pledgeType) ? exUserPledge.pledgeType.eq(PledgeType.getTypeFromCode(pledgeType)) : null;
    }

    // 사용자 서약 승인(accept) 진행 유형
    private BooleanExpression equalPledgeAcceptType(String pledgeAcceptType) {
        return StringUtils.hasText(pledgeAcceptType) ?
                exUserPledge.pledgeAcceptType.eq(PledgeAcceptType.getTypeFromCode(pledgeAcceptType))
                : exUserPledge.pledgeAcceptType.eq(PledgeAcceptType.getTypeFromCode("PROCEEDING"));
    }

    // 서약 명
    private BooleanExpression containsPledgeName(String pledgeName) {
        return StringUtils.hasText(pledgeName) ? exUserPledge.pledgeName.contains(pledgeName) : null;
    }

    // 요청자
    private BooleanExpression containsReqUser(String reqUser) {
        return StringUtils.hasText(reqUser) ? exUserPledge.reqUser.contains(reqUser) : null;
    }

    // 요청부서
    private BooleanExpression containsReqDept(String reqDept) {
        return StringUtils.hasText(reqDept) ? exUserPledge.reqDept.contains(reqDept) : null;
    }

    // 날짜 조건 검색 between
    private BooleanExpression betweenStartDtAndEndDt(String dateType, LocalDateTime startDt, LocalDateTime endDt) {
        if (StringUtils.hasText(dateType) && dateType.equals("startDt")) {
            return exUserPledge.startDt.between(startDt, endDt);
        } else if (StringUtils.hasText(dateType) && dateType.equals("endDt")) {
            return exUserPledge.endDt.between(startDt, endDt);
        } else {
            return exUserPledge.startDt.between(startDt, endDt); // 조건 없을 때 시작일 기준으로 조회
        }
    }

}
