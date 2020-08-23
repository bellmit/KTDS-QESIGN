const module = (function(global, $, _, moment, thisPage) {

    /***************************************************************************
     * @ 모듈 변수(상수) 선언
     **************************************************************************/
    const CTX = thisPage['ctxPath'];

    /***************************************************************************
     * @ 모듈 함수 선언
     **************************************************************************/

    /**
     * request ajax
     */
    function getUserPledgeList(page) {
        console.log("getUserPledgeList=========>", page);
        const size = $('#low-size').val();
        console.log("select size >>>>>", size);
        const data = {
            page: page || 0,
            size : size || 10,
            userPledgeStatus : 'COMPLETE',
            pledgeType : 'VIDEO'
        }

        const param = decodeURI($.param(data));
        console.log("@param===========>", param);

        // $.ajaxRest($.reqGet(CTX + 'example/list/userpledges?page=' + 1 + '&size=' + 10)
        $.ajaxRest($.reqGet(CTX + 'example/list/userpledges')
            .setData(param)
            .setCallback(renderUserPledgeTable)
            .build()
        ).done(function(response) {
          console.log("end==================>", response);
        });
    }

    /**
     * render table
     */
    function renderUserPledgeTable(response) {
        const content = response['data']['content'];
        const pagination = response['pagination'];
        const totalElements = response['data']['totalElements'];
        const page = response['data']['number'];
        const size = response['data']['size'];
        let currentLows = totalElements - ( page * size);
        console.log("currentLows====>", currentLows);

        if (!_.isEmpty(content)) {
            let html = '';
            _.each(content, function(v, i) {
                html += '<li>';
                html += '<ul class="list-group list-group-horizontal">';
                html += '<li class="d-inline-block w-5">' + (currentLows - i) + '</li>';
                html += '<li class="d-inline-block w-10 font-blue">' + v['userPledgeStatus'] + '</li>';
                html += '<li class="d-inline-block w-10">' + v['pledgeType'] + '</li>';
                html += '<li class="d-inline-block w-30 text-left">';
                html += '<a class="text-truncate" href="">' + v['pledgeName'] + '</a>';
                html += '</li>';
                html += '<li class="d-inline-block w-10">' + $.dateFormat(v['startDt'], 'D') + '</li>';
                html += '<li class="d-inline-block w-15">' + v['reqDept'] + '</li>';
                html += '<li class="d-inline-block w-10">' + v['reqUser'] + '</li>';
                html += '<li class="d-inline-block w-10">' + $.dateFormat(v['endDt'], 'D') + '</li>';
                html += '</ul>';
                html += '</li>';
            });

            $('#total').html(totalElements || 0);
            $('#tbody').html(html);
            $('#pagination').html(pagination).show();

        } else {
            $('#tbody').html('<li><ul><li class="d-inline-block w-100 font-red">요청된 서약이 없습니다.</li></ul></li>');
        }

        return response; // return promise

    }

    /**
     * 페이징 클릭 이벤트 처리
     */
    function pageMove(page) {
        getUserPledgeList(page);
    }

    /***************************************************************************
     * @ jquery 이벤트 등록
     **************************************************************************/
    function moduleEventHandlers() {

        // low-size select event
        $('#low-size').on('change',function() {
            getUserPledgeList();
        });


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
        pageMove : pageMove
    };


})(window, jQuery, _, moment, thisPage);