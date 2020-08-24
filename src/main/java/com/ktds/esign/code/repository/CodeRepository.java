package com.ktds.esign.code.repository;

import com.ktds.esign.code.domain.CommonCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * 공통 코드 조회 서비스 리포
 */
public interface CodeRepository extends JpaRepository<CommonCode, Long> {

    @Query(value = "select c from CommonCode c order by c.groupCode asc, c.displayOrder asc")
    List<CommonCode> findCodeByOrderByGroupCode();

    @Query(value = "select c from CommonCode c where c.groupCode = :groupCode order by c.groupCode asc, c.displayOrder asc")
    List<CommonCode> findCodeByGroupCode(@Param("groupCode") String groupCode);

    @Query(value = "select c from CommonCode c where c.groupCode = :groupCode and c.code = :code order by c.groupCode asc, c.displayOrder asc")
    Optional<CommonCode> findCodeByGroupCodeAndCode(@Param("groupCode") String groupCode, @Param("code") String code);
}
