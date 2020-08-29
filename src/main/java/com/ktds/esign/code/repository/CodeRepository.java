package com.ktds.esign.code.repository;

import com.ktds.esign.code.domain.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * 공통 코드 조회
 */
public interface CodeRepository extends JpaRepository<Code, String> {
    // 전체 코드 조회
    @Query(value = "select c from Code c left join fetch c.groupCode g order by g.groupCodeId asc, c.displayOrder asc")
    List<Code> findAllCodes();

    // 그룹 코드 아이디로 조회
    @Query(value = "select c from Code c left join fetch c.groupCode g where g.groupCodeId = :groupCodeId order by g.groupCodeId asc, c.displayOrder asc")
    List<Code> findCodesByGroupCodeId(@Param("groupCodeId") String groupCodeId);

    // 그룹 코드 아이디, 코드 아이디로 조회
    @Query(value = "select c from Code c left join fetch c.groupCode g where c.groupCode = :groupCodeId and c.codeId = :codeId order by g.groupCodeId asc, c.displayOrder asc")
    Optional<Code> findCodeByGroupCodeIdAndCodeId(@Param("groupCodeId") String groupCodeId, @Param("codeId") String codeId);
}
