/*****************************************************************************************************
 *    @QESIGN version 1.0
 *    @Description: UI 공통 스크립트
 *    @Author: 91218672
 *****************************************************************************************************/
(function(global, $, _, thisPage) {
    
    // context path
    const _CTX = thisPage['ctxPath'];
    
    /***************************************************************************
     * @ DOM Ready 실행
     **************************************************************************/
    $(function() {

        // GNB mouseover event
        $('.main-menu > li, .sub-menu').hover(function() {
            $('.sub-menu').show();
        }, function() {
            $('.sub-menu').hide();
        });

        // datepicker
        $('.datepicker-input').datepicker({
            format: "yyyy.mm.dd",
            language : "ko",
            autoclose : true,
            todayHighlight : true
        });

    });

})(window, jQuery, _, thisPage);