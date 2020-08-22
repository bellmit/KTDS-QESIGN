package com.ktds.esign.client.example.mapper;

import com.ktds.esign.client.example.domain.ExUserPledge;
import com.ktds.esign.client.example.payload.ListReq.SearchDto;
import com.ktds.esign.client.example.payload.ListRes.ExUserPledgeDto;
import com.ktds.esign.common.annos.UserPledgeFomatter;
import com.ktds.esign.common.enums.format.EnumFomatter;
import com.ktds.esign.config.MapStructMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapStructMapperConfig.class, uses = { EnumFomatter.class })
public interface UserPledgeMapper {

    @Mapping(source = "userPledgeStatus", target = "userPledgeStatus", qualifiedBy = UserPledgeFomatter.class)
    ExUserPledge toEntity(SearchDto searchDto);

    ExUserPledgeDto toDto(ExUserPledge exUserPledge);

    List<ExUserPledgeDto> toDtoList(List<ExUserPledge> exUserPledge);

}
