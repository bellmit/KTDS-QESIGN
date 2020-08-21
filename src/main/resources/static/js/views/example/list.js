const module = (function(global, $, _, moment, thisPage) {

    /***************************************************************************
     * @ 모듈 변수(상수) 선언
     **************************************************************************/
    const CTX = thisPage['ctxPath'];

    /***************************************************************************
     * @ 모듈 함수 선언
     **************************************************************************/
    function getUserPledgeList() {
        console.log("getUserPledgeList=============================================>");
        $.ajaxRest($.reqGet(CTX + 'example/list/userpledge').build()).done(function(response) {
            const data = response['data'];
            console.log("result=========>", data);
            renderUserPledgeTable(data, null);
        });
    }

    function renderUserPledgeTable(data, pagination) {
        // var pagination = response['data']['pagination'];

        if (!_.isEmpty(data)) {

            let html = '';

            _.each(data, function(v, k) {
                html += '<li>';
                html += '<ul class="list-group list-group-horizontal">';
                html += '<li class="d-inline-block w-5">10</li>';
                html += '<li class="d-inline-block w-10 font-blue">' + v['pledgeUserStatus'] + '</li>';
                html += '<li class="d-inline-block w-10">' + v['pledgeType'] + '</li>';
                html += '<li class="d-inline-block w-30 text-left">';
                html += '<a class="text-truncate" href="">' + v['pledgeName'] + '</a>';
                html += '</li>';
                html += '<li class="d-inline-block w-10">' + v['startDt'] + '</li>';
                html += '<li class="d-inline-block w-15">' + v['reqDept'] + '</li>';
                html += '<li class="d-inline-block w-10">' + v['reqUser'] + '</li>';
                html += '<li class="d-inline-block w-10 font-red">' + v['endDt'] + '</li>';
                html += '</ul>';
                html += '</li>';
            });

            $('#tbody').html(html);
            // $('#pagination').html(pagination).show();

        } else {
            // $('#resultList').html('<tr><td colspan="8">조회된 클러스터가 존재하지 않습니다.</td></tr>');
        }


    }

    /***************************************************************************
     * @ jquery 이벤트 등록
     **************************************************************************/
    function moduleEventHandlers() {



    }

    /***************************************************************************
     * @ 화면 로딩 시 최초로 실행할 함수 선언
     **************************************************************************/
    function moduleInitializr() {
        getUserPledgeList();
    }


    /***************************************************************************
     * @ DOM Ready 실행
     **************************************************************************/
    $(function() {
        moduleEventHandlers();
        moduleInitializr();
    });

    /***************************************************************************
     * @ 외부로 노출할 함수 선언
     **************************************************************************/
    return {
        //pageMove : pageMove, // 페이징 링크 클릭 처리
    };


})(window, jQuery, _, moment, thisPage);