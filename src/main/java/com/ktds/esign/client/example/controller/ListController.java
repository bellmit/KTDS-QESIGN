package com.ktds.esign.client.example.controller;

import com.ktds.esign.client.example.payload.ListReq.SearchDto;
import com.ktds.esign.client.example.service.ListService;
import com.ktds.esign.common.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("example/list")
public class ListController {

    private final ListService listService;

    @GetMapping
    public ModelAndView list(ModelAndView mav) {
        mav.setViewName("views/example/list");
        return mav;
    }

    /**
     * Get Parameter Mapping Enum Domain convert 작동안함
     * Post @RequestBody Mapping 시에만 작동함
     * @param searchDto
     * @param pageable
     * @return
     */
    @GetMapping(path = "userpledges")
    public ResponseDto<?> getUserPledgeList(SearchDto searchDto, @PageableDefault(size = 10) Pageable pageable) {
        log.info("@getUserPledges===============>{}", searchDto.getPledgeType());
        return ResponseDto.of(listService.findUserPledgeList(searchDto, pageable));
    }

}
