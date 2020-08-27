package com.ktds.esign.client.example.service;

import com.ktds.esign.client.example.domain.ExUserNoti;
import com.ktds.esign.client.example.payload.ExUserNotiReq.CreateDto;
import com.ktds.esign.client.example.payload.ExUserNotiReq.UserNotiDto;
import com.ktds.esign.client.example.repository.ExUserNotiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ExUserNotiService {
    private final ExUserNotiRepository exUserNotiRepository;


    @Transactional
    public void createUserNoti(CreateDto createDto) {
        List<UserNotiDto> userNotis = createDto.getUserNotis();
        userNotis.stream().forEach(un -> {
            ExUserNoti exUserNoti = ExUserNoti.builder().build();
                    // @todo


        });

    }
}
