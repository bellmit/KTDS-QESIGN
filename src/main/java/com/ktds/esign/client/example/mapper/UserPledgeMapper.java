package com.ktds.esign.client.example.mapper;

import com.ktds.esign.client.example.domain.ExUserPledge;
import com.ktds.esign.client.example.payload.ExUserPledgeReq.CreateDto;
import com.ktds.esign.client.example.payload.ExUserPledgeReq.SearchDto;
import com.ktds.esign.client.example.payload.ExUserPledgeReq.UpdateDto;
import com.ktds.esign.client.example.payload.ExUserPledgeRes;
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

    @Mapping(target = "pledgeType", qualifiedBy = {RootEnumMapper.class, PledgeTypeMapper.class})
    @Mapping(target = "pledgeAcceptType", ignore = true)
    @Mapping(target = "id", ignore = true)
    ExUserPledge toEntity(SearchDto searchDto);

    @Mapping(target = "pledgeAcceptType", qualifiedBy = {RootEnumMapper.class, PledgeAcceptTypeMapper.class})
    @Mapping(target = "pledgeType", qualifiedBy = {RootEnumMapper.class, PledgeTypeMapper.class})
    @Mapping(target = "id", ignore = true)
    ExUserPledge createEntity(CreateDto createDto);

    @Mapping(target = "pledgeAcceptType", qualifiedBy = {RootEnumMapper.class, PledgeAcceptTypeMapper.class})
    @Mapping(target = "pledgeType", qualifiedBy = {RootEnumMapper.class, PledgeTypeMapper.class})
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "startDt", ignore = true)
    @Mapping(target = "endDt", ignore = true)
    @Mapping(target = "reqDept", ignore = true)
    @Mapping(target = "reqUser", ignore = true)
    void updateEntity(UpdateDto updateDto, @MappingTarget ExUserPledge exUserPledge);

    @Mapping(target = "pledgeAcceptType", qualifiedBy = {RootEnumMapper.class, PledgeAcceptCodeMapper.class})
    @Mapping(source = "pledgeAcceptType", target = "pledgeAcceptDesc", qualifiedBy = {RootEnumMapper.class, PledgeAcceptDescMapper.class})
    @Mapping(target = "pledgeType", qualifiedBy = {RootEnumMapper.class, PledgeCodeMapper.class})
    @Mapping(source = "pledgeType", target = "pledgeDesc", qualifiedBy = {RootEnumMapper.class, PledgeDescMapper.class})
    ExUserPledgeRes.FindDto toDto(ExUserPledge exUserPledge);

    List<FindDto> toDtoList(List<ExUserPledge> exUserPledge);

}
