package com.ktds.esign.client.example.controller;

import com.ktds.esign.client.example.payload.ExUserPledgeReq.SearchDto;
import com.ktds.esign.client.example.service.ExPledgeService;
import com.ktds.esign.common.response.ResponseDto;
import com.ktds.esign.common.utils.PaginationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("example")
public class ExPledgeController {

    private final ExPledgeService exPledgeService;
    private final PaginationUtil paginationUtil;

    @GetMapping("user/pledges")
    public ModelAndView list(ModelAndView mav) {
        mav.setViewName("views/example/user-pledge-list");
        return mav;
    }

    /**
     * Get Parameter Mapping Enum Domain convert 작동안함
     * Post @RequestBody Mapping 시에만 작동함
     *
     * @param searchDto
     * @param pageable
     * @return
     */
    @GetMapping("user/pledges/list")
    public ResponseDto<?> getUserPledgeList(SearchDto searchDto, Pageable pageable) {
        log.info("searchDto===========>{}", searchDto);
        log.info("pageable============>{}", pageable);
        Page page = exPledgeService.findUserPledgeList(searchDto, pageable);
        String paginationHtml = paginationUtil.getPagination(page);
        return ResponseDto.of(page, paginationHtml);
    }

    @GetMapping("user/pledges/detail")
    public ModelAndView detail(@RequestParam String userPledgeId, ModelAndView mav) {
        mav.setViewName("views/example/user-pledge-detail");
        mav.addObject("userPledgeId", userPledgeId);
        return mav;
    }

    @GetMapping("user/pledges/detail/{id}")
    public ResponseDto<?> getPledge(@PathVariable Long id) {
        log.info("getPledge===========>{}", id);
        return null;
    }

}
