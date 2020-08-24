package com.ktds.esign.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaginationUtil {

    /** default page pagination block size : 10 **/
    private static final Integer PAGINATION_SIZE = 10;

    /** default page module namespace **/
    private static final String MODULE_NAMESPACE = "module";

    /**
     * 페이징 처리. js 파일의 module 명을 받아서 처리
     * @param page
     * @return
     */
    public String getPagination(Page page) {
        log.info("@page=====================>{}", page.getNumber());
        log.info("@size=====================>{}", page.getSize());
        log.info("@total====================>{}", page.getTotalElements());
        log.info("@curElm===================>{}", page.getNumberOfElements());
        int pageNo = page.getNumber() + 1; // front 1 base
        int totalPage = (int) Math.ceil((float) page.getTotalElements() / page.getSize());
        int pageGroup = (int) Math.ceil((float) pageNo / PAGINATION_SIZE);
        int next = pageGroup * PAGINATION_SIZE;
        int prev = next - (PAGINATION_SIZE - 1);
        int goNext;
        int goPrev;
        String module = MODULE_NAMESPACE;

        log.info("@pageNo=====================>{}", pageNo);
        log.info("@totalPage==================>{}", totalPage);
        log.info("@pageGroup==================>{}", pageGroup);
        log.info("@next=======================>{}", next);
        log.info("@prev=======================>{}", prev);

        StringBuilder paginationHtml = new StringBuilder();

        if (prev - 1 <= 0) {
            goPrev = 1;
            prev = 1;
        } else {
            goPrev = prev - 1;
        }

        if (next > totalPage) {
            goNext = totalPage;
            next = totalPage;
        } else {
            goNext = next + 1;
        }

        if (goNext > totalPage) {
            goNext = totalPage;
        }

        log.info("@goPrev=====================>{}", goPrev);
        log.info("@goNext=====================>{}", goNext);

        if (page.getTotalElements() > 0) {

            paginationHtml.append("<ul class=\"pagination justify-content-center\">");

            /** |<<| <| **/
            if (pageNo == 1) {
                // 맨앞[<<]
                paginationHtml.append("<li class=\"page-item\">");
                paginationHtml.append("<a class=\"page-link page-prev first\" href=\"#\" aria-label=\"Previous\" title=\"맨앞\" data-page=\"0\"></a>");
                paginationHtml.append("</li>");
                // 앞[<]
                paginationHtml.append("<li class=\"page-item\">");
                paginationHtml.append("<a class=\"page-link page-prev\" href=\"#\" aria-label=\"Previous\" title=\"앞\" data-page=\"");
                paginationHtml.append(goPrev -1);
                paginationHtml.append("\"></a>");
                paginationHtml.append("</li>");
            } else {
                // 맨앞[<<]
                paginationHtml.append("<li class=\"page-item\">");
                paginationHtml.append("<a class=\"page-link page-prev first\" href=\"#\" aria-label=\"Previous\" title=\"맨앞\" data-page=\"0\" ");
                paginationHtml.append("onclick=");
                paginationHtml.append(module);
                paginationHtml.append(".pageMove(0);return false;\"></a>");
                paginationHtml.append("</li>");
                // 앞[<]
                paginationHtml.append("<li class=\"page-item\">");
                paginationHtml.append("<a class=\"page-link page-prev\" href=\"#\" aria-label=\"Previous\" title=\"앞\" data-page=\"");
                paginationHtml.append(goPrev - 1); // backend 0 base
                paginationHtml.append("\" onclick=\"");
                paginationHtml.append(module);
                paginationHtml.append(".pageMove(");
                paginationHtml.append(goPrev - 1); // backend 0 base
                paginationHtml.append(");return false;\"></a>");
                paginationHtml.append("</li>");
            }

            /** |1|2|3|........|9|10| **/
            for (int pageIndex = prev; pageIndex <= next; pageIndex ++) {
                if (pageIndex == pageNo) {
                    paginationHtml.append("<li class=\"page-item active\">");
                    paginationHtml.append("<a class=\"page-link\" href=\"#\">");
                    paginationHtml.append(pageIndex);
                    paginationHtml.append("</a>");
                    paginationHtml.append("</li>");
                } else {
                    paginationHtml.append("<li class=\"page-item\">");
                    paginationHtml.append("<a class=\"page-link\" onclick=\"");
                    paginationHtml.append(module);
                    paginationHtml.append(".pageMove(");
                    paginationHtml.append(pageIndex - 1); // backend 0 base
                    paginationHtml.append(");return false;\" data-page=\"");
                    paginationHtml.append(pageIndex - 1); // backend 0 base
                    paginationHtml.append("\">");
                    paginationHtml.append(pageIndex);
                    paginationHtml.append("</a>");
                    paginationHtml.append("</li>");
                }
            }

            /** |> |>>| **/
            if (pageNo == totalPage) {
                // 뒤[<]
                paginationHtml.append("<li class=\"page-item\">");
                paginationHtml.append("<a class=\"page-link page-next\" href=\"#\" aria-label=\"Next\" title=\"뒤\" data-page=\"");
                paginationHtml.append(goNext - 1);
                paginationHtml.append("\"></a>");
                paginationHtml.append("</li>");
                // 맨뒤[<<]
                paginationHtml.append("<li class=\"page-item\">");
                paginationHtml.append("<a class=\"page-link page-next last\" href=\"#\" aria-label=\"Next\" title=\"맨뒤\" data-page=\"");
                paginationHtml.append(totalPage - 1);
                paginationHtml.append("></a>");
                paginationHtml.append("</li>");
            } else {
                // 뒤[<]
                paginationHtml.append("<li class=\"page-item\">");
                paginationHtml.append("<a class=\"page-link page-next\" href=\"#\" aria-label=\"Next\" title=\"뒤\" data-page=\"");
                paginationHtml.append(goNext - 1); // backend 0 base
                paginationHtml.append("\" onclick=\"");
                paginationHtml.append(module);
                paginationHtml.append(".pageMove(");
                paginationHtml.append(goNext - 1); // backend 0 base
                paginationHtml.append(");return false;\"></a>");
                paginationHtml.append("</li>");
                // 맨뒤[<<]
                paginationHtml.append("<li class=\"page-item\">");
                paginationHtml.append("<a class=\"page-link page-next last\" href=\"#\" aria-label=\"Next\" title=\"맨뒤\" data-page=\"");
                paginationHtml.append(totalPage - 1); // backend 0 base
                paginationHtml.append("\" onclick=\"");
                paginationHtml.append(module);
                paginationHtml.append(".pageMove(");
                paginationHtml.append(totalPage - 1); // backend 0 base
                paginationHtml.append(");return false;\"></a>");
                paginationHtml.append("</li>");
            }

            paginationHtml.append("</ul>");
        }

        log.info("@paginationHtml======>{}", paginationHtml.toString());
        return paginationHtml.toString();
    }

}
