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

import java.util.List;

@Mapper(config = MapStructMapperConfig.class, uses = {EnumFormatter.class})
public interface UserPledgeMapper {

    @Mapping(target = "userPledgeStatus", qualifiedBy = {RootMapper.class, UserPledgeStatusTypeMapper.class})
    @Mapping(target = "pledgeType", qualifiedBy = {RootMapper.class, PledgeTypeMapper.class})
    ExUserPledge toEntity(SearchDto searchDto);

    @Mapping(target = "userPledgeStatus", qualifiedBy = {RootMapper.class, UserPledgeStatusTypeMapper.class})
    @Mapping(target = "pledgeType", qualifiedBy = {RootMapper.class, PledgeTypeMapper.class})
    @Mapping(target = "id", ignore = true)
    ExUserPledge toEntity(CreateDto createDto);

    @Mapping(target = "userPledgeStatus", qualifiedBy = {RootMapper.class, UserPledgeStatusCodeMapper.class})
    @Mapping(target = "pledgeType", qualifiedBy = {RootMapper.class, PledgeCodeMapper.class})
    ExUserPledgeDto toDto(ExUserPledge exUserPledge);

    List<ExUserPledgeDto> toDtoList(List<ExUserPledge> exUserPledge);

}
