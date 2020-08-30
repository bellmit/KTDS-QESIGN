package com.ktds.esign.client.example.mapper;

import com.ktds.esign.client.example.domain.ExCompany;
import com.ktds.esign.client.example.domain.ExUser;
import com.ktds.esign.client.example.domain.ExUserNoti;
import com.ktds.esign.client.example.payload.ExUserNotiRes;
import com.ktds.esign.client.example.payload.ExUserNotiRes.ExUserDto;
import com.ktds.esign.common.annos.enums.NotiCodeMapper;
import com.ktds.esign.common.annos.enums.DirectCodeMapper;
import com.ktds.esign.common.annos.enums.RootEnumMapper;
import com.ktds.esign.common.enums.formatter.EnumFormatter;
import com.ktds.esign.config.MapStructMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static com.ktds.esign.client.example.payload.ExUserNotiRes.*;

@Mapper(config = MapStructMapperConfig.class, uses = {EnumFormatter.class})
public interface PersonToolMapper {

    @Mapping(target = "notiDirectType", qualifiedBy = {RootEnumMapper.class, DirectCodeMapper.class})
    @Mapping(target = "notiType", qualifiedBy = {RootEnumMapper.class, NotiCodeMapper.class})
    ExUserNotiDto toDto(ExUserNoti exUserNoti);

    ExCompanyDto toDto(ExCompany exCompany);

    ExUserDto toDto(ExUser exUser);

    List<ExUserDto> toDtoList(List<ExUser> exUser);

}
