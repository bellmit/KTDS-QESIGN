package com.ktds.esign.code.mapper;

import com.ktds.esign.code.domain.CommonCode;
import com.ktds.esign.code.payload.CodeRes.CommonCodeDto;
import com.ktds.esign.config.MapStructMapperConfig;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapStructMapperConfig.class)
public interface CodeMapper {
    CommonCodeDto toDto(CommonCode commonCode);
    List<CommonCodeDto> toDtoList(List<CommonCode> commonCode);
}
