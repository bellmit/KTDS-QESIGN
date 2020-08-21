package com.ktds.esign.client.example.mapper;

import com.ktds.esign.client.example.domain.ExUserPledge;
import com.ktds.esign.client.example.payload.ListReq.ExUserPledgeDto;
import com.ktds.esign.config.MapStructMapperConfig;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapStructMapperConfig.class)
public interface UserPledgeMapper {

    ExUserPledgeDto toDto(ExUserPledge exUserPledge);

    List<ExUserPledgeDto> toDtoList(List<ExUserPledge> exUserPledge);

}
