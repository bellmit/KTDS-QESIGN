package com.ktds.esign.client.example.service;

import com.ktds.esign.client.example.domain.ExNoti;
import com.ktds.esign.client.example.domain.ExUser;
import com.ktds.esign.client.example.domain.ExUserNoti;
import com.ktds.esign.client.example.payload.ExUserNotiReq.CreateDto;
import com.ktds.esign.client.example.payload.ExUserNotiReq.ExUserNotiDto;
import com.ktds.esign.client.example.repository.ExNotiRepository;
import com.ktds.esign.client.example.repository.ExUserNotiRepository;
import com.ktds.esign.client.example.repository.ExUserRepository;
import com.ktds.esign.common.enums.NotiDirectType;
import com.ktds.esign.common.enums.NotiType;
import com.ktds.esign.common.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ExUserNotiService {
    private final ExUserRepository exUserRepository;
    private final ExUserNotiRepository exUserNotiRepository;
    private final ExNotiRepository exNotiRepository;

    /**
     * if exists delete and create
     * @param createDto
     * @throws UserNotFoundException
     */
    @Transactional
    public void createUserNoti(CreateDto createDto) throws UserNotFoundException {
        ExUser user = exUserRepository.findById("U123456789").orElseThrow(UserNotFoundException::new);
        List<ExUserNoti> exUserNotis = user.getUserNotis();

        if (!exUserNotis.isEmpty()) {
            user.getUserNotis().clear();
        }

        List<ExUserNotiDto> exUserNotiDtos = createDto.getUserNotis();
        for (ExUserNotiDto exUserNotiDto : exUserNotiDtos) {
            ExUserNoti exUserNoti = ExUserNoti.createExUserNoti(NotiType.getTypeFromCode(exUserNotiDto.getNotiType()),
                    NotiDirectType.getTypeFromCode(exUserNotiDto.getNotiDirection()));
            exUserNoti.changeUser(user);
            exUserNotis.add(exUserNoti);
        }

        exUserNotiRepository.saveAll(exUserNotis);
    }
}
