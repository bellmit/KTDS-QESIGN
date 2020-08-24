package com.ktds.esign.client.example.mapper;

import com.ktds.esign.client.example.domain.ExUserPledge;
import com.ktds.esign.client.example.payload.ListReq.CreateDto;
import com.ktds.esign.client.example.payload.ListReq.SearchDto;
import com.ktds.esign.client.example.payload.ListRes.ExUserPledgeDto;
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

//    @Mapping(target = "pledgeProgType", qualifiedBy = {RootEnumMapper.class, PledgeProgTypeMapper.class})
    @Mapping(target = "pledgeType", qualifiedBy = {RootEnumMapper.class, PledgeTypeMapper.class})
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pledgeProgType", ignore = true)
    ExUserPledge toEntity(SearchDto searchDto);

    @Mapping(target = "pledgeProgType", qualifiedBy = {RootEnumMapper.class, PledgeProgTypeMapper.class})
    @Mapping(target = "pledgeType", qualifiedBy = {RootEnumMapper.class, PledgeTypeMapper.class})
    @Mapping(target = "id", ignore = true)
    ExUserPledge toEntity(CreateDto createDto);

    @Mapping(target = "pledgeProgType", qualifiedBy = {RootEnumMapper.class, PledgeProgCodeMapper.class})
    @Mapping(target = "pledgeType", qualifiedBy = {RootEnumMapper.class, PledgeCodeMapper.class})
    ExUserPledgeDto toDto(ExUserPledge exUserPledge);

    List<ExUserPledgeDto> toDtoList(List<ExUserPledge> exUserPledge);

}
