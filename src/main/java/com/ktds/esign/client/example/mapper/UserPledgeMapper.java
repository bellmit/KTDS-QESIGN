package com.ktds.esign.client.example.mapper;

import com.ktds.esign.client.example.domain.ExUserPledge;
import com.ktds.esign.client.example.payload.ExUserPledgeReq.CreateDto;
import com.ktds.esign.client.example.payload.ExUserPledgeReq.SearchDto;
import com.ktds.esign.client.example.payload.ExUserPledgeRes.ExUserPledgeDto;
import com.ktds.esign.common.annos.enums.*;
import com.ktds.esign.common.enums.formatter.EnumFormatter;
import com.ktds.esign.config.MapStructMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
    ExUserPledge toEntity(CreateDto createDto);

    @Mapping(target = "pledgeAcceptType", qualifiedBy = {RootEnumMapper.class, PledgeAcceptCodeMapper.class})
    @Mapping(target = "pledgeType", qualifiedBy = {RootEnumMapper.class, PledgeCodeMapper.class})
    ExUserPledgeDto toDto(ExUserPledge exUserPledge);

    List<ExUserPledgeDto> toDtoList(List<ExUserPledge> exUserPledge);

}
