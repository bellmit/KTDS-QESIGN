package com.ktds.esign.client.example.mapper;

import com.ktds.esign.client.example.domain.ExUserPledge;
import com.ktds.esign.client.example.payload.ExUserPledgeReq.CreateDto;
import com.ktds.esign.client.example.payload.ExUserPledgeReq.SearchDto;
import com.ktds.esign.client.example.payload.ExUserPledgeReq.UpdateDto;
import com.ktds.esign.client.example.payload.ExUserPledgeRes.FindDto;
import com.ktds.esign.common.annos.enums.*;
import com.ktds.esign.common.enums.formatter.EnumFormatter;
import com.ktds.esign.config.MapStructMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(config = MapStructMapperConfig.class, uses = {EnumFormatter.class})
public interface UserPledgeMapper {

//    @Mapping(source = "startDt", target = "startDt", dateFormat = "yyyy/MM/dd HH:mm:ss")
//    @Mapping(source = "endDt", target = "endDt", dateFormat = "yyyy/MM/dd HH:mm:ss")
//    SearchDto updateDto(SearchDto searchDto, @MappingTarget SearchDto searchDt);

    @Mapping(target = "contentsType", qualifiedBy = {RootEnumMapper.class, ContentsTypeMapper.class})
    @Mapping(target = "progsSttusType", ignore = true)
    @Mapping(target = "id", ignore = true)
    ExUserPledge toEntity(SearchDto searchDto);

    @Mapping(target = "progsSttusType", qualifiedBy = {RootEnumMapper.class, ProgsSttusTypeMapper.class})
    @Mapping(target = "contentsType", qualifiedBy = {RootEnumMapper.class, ContentsTypeMapper.class})
    @Mapping(target = "id", ignore = true)
    ExUserPledge createEntity(CreateDto createDto);

    @Mapping(target = "progsSttusType", qualifiedBy = {RootEnumMapper.class, ProgsSttusTypeMapper.class})
    @Mapping(target = "contentsType", qualifiedBy = {RootEnumMapper.class, ContentsTypeMapper.class})
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "startDt", ignore = true)
    @Mapping(target = "endDt", ignore = true)
    @Mapping(target = "reqDept", ignore = true)
    @Mapping(target = "reqUser", ignore = true)
    void updateEntity(UpdateDto updateDto, @MappingTarget ExUserPledge exUserPledge);

    @Mapping(target = "progsSttusType", qualifiedBy = {RootEnumMapper.class, ProgsSttusCodeMapper.class})
    @Mapping(source = "progsSttusType", target = "progsSttusDesc", qualifiedBy = {RootEnumMapper.class, ProgsSttusDescMapper.class})
    @Mapping(target = "contentsType", qualifiedBy = {RootEnumMapper.class, ContentsCodeMapper.class})
    @Mapping(source = "contentsType", target = "contentsDesc", qualifiedBy = {RootEnumMapper.class, ContentsDescMapper.class})
    FindDto toDto(ExUserPledge exUserPledge);

    List<FindDto> toDtoList(List<ExUserPledge> exUserPledge);

}
