package com.ktds.esign.code.mapper;

import com.ktds.esign.code.domain.Code;
import com.ktds.esign.code.payload.CodeRes.CodeDto;
import com.ktds.esign.config.MapStructMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapStructMapperConfig.class)
public interface CodeMapper {

    @Mapping(source = "code.groupCode.groupCodeId", target = "groupCodeId")
    CodeDto toDto(Code code);
    List<CodeDto> toDtoList(List<Code> codes);

}
