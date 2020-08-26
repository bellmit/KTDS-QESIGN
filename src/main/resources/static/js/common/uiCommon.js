/*****************************************************************************************************
 *    @QESIGN version 1.0
 *    @Description: UI 공통 스크립트
 *    @Author: 91218672
 *****************************************************************************************************/
const moduleUI = (function(global, $, _, thisPage) {
    
    // context path
    const CTX = thisPage['ctxPath'];

    /***************************************************************************
     * @ 모듈 함수 선언
     **************************************************************************/

    function getSelectorFromGroupCode(commonCode, groupCode) {
        const codeTypes = commonCode.filter(function (content) {
            return content['groupCode'] === groupCode;
        });
        let html = '<option value="">전체</option>';
        codeTypes.forEach(function (codeType) {
            html += '<option value="' + codeType['code'] + '">' + codeType['codeDesc'] + '</option>';
        });
        return html;
    }


    /***************************************************************************
     * @ jquery 이벤트 등록
     **************************************************************************/
    function moduleEventHandlers() {

        // GNB mouseover event
        $('.main-menu > li, .sub-menu').hover(function() {
            $(this).addClass('on');
            $('.sub-menu').show();
        }, function() {
            $(this).removeClass('on');
            $('.sub-menu').hide();
        });

    }

    /***************************************************************************
     * @ 화면 로딩 시 최초로 실행할 함수 선언
     **************************************************************************/
    function moduleInitializr() {

    }
    /***************************************************************************
     * @ DOM Ready 실행
     **************************************************************************/
    $(function () {
        moduleEventHandlers();
        moduleInitializr();
    });

    /***************************************************************************
     * @ 외부로 노출할 함수 선언
     **************************************************************************/
    return {
        getSelectorFromGroupCode: getSelectorFromGroupCode
    };

})(window, jQuery, _, thisPage);