const module = (function (global, $, _, moment, thisPage) {

    /***************************************************************************
     * @ 모듈 변수(상수) 선언
     **************************************************************************/
    const CTX = thisPage['ctxPath'];
    const formData = {
        dateType: null,
        startDt: null,
        endDt: null,
        pledgeType: null,
        pledgeName: null,
        reqUser: null,
        reqDetp: null,
        page: 0,
        size: 10
    }

    /***************************************************************************
     * @ 모듈 함수 선언
     **************************************************************************/

    /**
     * request code
     */
    function getCommonCodeList() {
        $.ajaxRest($.reqGet(CTX + 'commoncodes')
            .setCallback(renderCodeElements)
            .build()
        ).done(function (response) {
            console.log("end====>", response); // 추가 작업 시 사용
        });
    }

    /**
     * code render
     */
    function renderCodeElements(response) {
        console.log("code response=========>", response);
        const contents = response['data'];
        console.log("contents====>", contents);
        const pledgeProgTypes = contents.filter(content => content['groupCode'] === 'PLEDGE_TYPE');
        let html = '<option value="">전체</option>';
        pledgeProgTypes.forEach(pledgeProgType => {
            html += '<option value="' + pledgeProgType['code'] + '">' + pledgeProgType['codeDesc'] + '</option>';
        });
        console.log("pledgeProgTypes====>", pledgeProgTypes);
        $("#pledgeType").html(html).selectpicker('refresh');
    }

    /**
     * request ajax
     */
    function getUserPledgeList(formData, pageNo) {
        formData.page = pageNo || 0;
        formData.size = $('#low-size').val() || 10;
        console.log("@formData==========>", formData);

        const param = decodeURI($.param(formData));
        console.log("@param============>", param);

        // $.ajaxRest($.reqGet(CTX + 'example/list/userpledges?page=' + 1 + '&size=' + 10)
        $.ajaxRest($.reqGet(CTX + 'example/list/userpledges')
            .setData(param)
            .setCallback(renderUserPledgeTable)
            .build()
        ).done(function (response) {
            console.log("end====>", response); // 추가 작업 시 사용
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
        let currentLows = totalElements - (page * size);
        console.log("currentLows====>", currentLows);
        let $tbody = $('#tbody');
        let $listWrap = $('#list-wrap');
        let $total = $('#total');
        let $pagination = $('#pagination');

        // result reset
        $tbody.empty();

        if (!_.isEmpty(content)) {
            let html = '';
            content.forEach(function (v, i) {
                html += '<li>';
                html += '<ul class="list-group list-group-horizontal">';
                html += '<li class="d-inline-block w-5">' + (currentLows - i) + '</li>';
                html += '<li class="d-inline-block w-10 font-blue">' + v['pledgeProgType'] + '</li>';
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

            $listWrap.removeClass('list-none')
            $total.html(totalElements || 0);
            $tbody.html(html);
            $pagination.html(pagination).show();

        } else {
            $listWrap.addClass('list-none')
            $total.html(0);
            $tbody.hide();
            $pagination.html(pagination).hide();
        }

        return response; // return promise

    }

    /**
     * search form validation
     */
    function validateSearchForm(formData) {
        // search cond validation
        if (!formData.startDt) {
            window.alert('검색 시작일을 설정하세요.');
            return false;
        } else if (!formData.endDt) {
            window.alert('검색 종료일을 설정하세요.');
        } else if (formData.searchType && !formData.searchKey) {
            window.alert('검색 내용을 입력하세요.');
            $('#searchKey').focus();
            return false;
        }
        return true;
    }


    /**
     * 페이징 클릭 이벤트 처리
     */
    function pageMove(pageNo) {
        getUserPledgeList(formData, pageNo);
    }

    /***************************************************************************
     * @ jquery 이벤트 등록
     **************************************************************************/
    function moduleEventHandlers() {

        $('.selectpicker').selectpicker();

        // low-size select event
        $('#low-size').on('change', function () {
            getUserPledgeList(formData);
        });

        // datepicker
        // start date
        $('#date-start').datepicker({
            format: 'yyyy-mm-dd',
            language: 'ko',
            autoclose: true,
            todayHighlight: true,
        })
            .on('changeDate', function (selected) {
                let minDate = new Date(selected.date.valueOf());
                $('#date-end').datepicker('setStartDate', minDate);
            });

        // end date
        $('#date-end').datepicker({
            format: 'yyyy-mm-dd',
            language: 'ko',
            autoclose: true,
            todayHighlight: true,
        })
            .on('changeDate', function (selected) {
                let minDate = new Date(selected.date.valueOf());
                $('#date-start').datepicker('setEndDate', minDate);
            });

        $('#date-start, #date-end').datepicker('setDate', new Date());

        // 검색
        $('#search').on('click', function (e) {
            e.preventDefault();
            const pledgeType = $('#pledgeType').val();
            const searchType = $('#searchType').val();
            const dateType = $("input[type='radio']:checked").val();
            const startDt = $('#date-start').val()
            const endDt = $('#date-end').val()
            const searchKey = $('#searchKey').val();
            console.log("pledgeType=======>", pledgeType);
            console.log("searchType=======>", searchType);
            console.log("dateType=========>", dateType);
            console.log("startDt==========>", startDt);
            console.log("endDt============>", endDt);
            console.log("searchKey========>", searchKey);

            // set search formData (formData is Global Const)
            formData.pledgeType = pledgeType;
            formData.dateType = dateType;
            formData.startDt =  moment(startDt, 'YYYY-MM-DD').format("YYYY-MM-DD[T]HH:mm");
            formData.endDt =  moment(endDt, 'YYYY-MM-DD').format("YYYY-MM-DD[T]HH:mm");
            console.log("startDt@@@==========>", formData.startDt);
            console.log("endDt@@@==========>", formData.endDt);

            if (searchType === 'pledgeName') {
                formData.pledgeName = searchKey;
                formData.reqDept = null;
                formData.reqUser = null;
            } else if (searchType === 'reqDept') {
                formData.pledgeName = null;
                formData.reqDept = searchKey;
                formData.reqUser = null;
            } else if (searchType === 'reqUser') {
                formData.pledgeName = null;
                formData.reqDept = null;
                formData.reqUser = searchKey;
            }

            // search form validation
            if (!validateSearchForm(formData)) return;

            // call search ajax function
            getUserPledgeList(formData)

        });

    }

    /***************************************************************************
     * @ 화면 로딩 시 최초로 실행할 함수 선언
     **************************************************************************/
    function moduleInitializr() {
        getCommonCodeList();
        getUserPledgeList(formData);
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
        pageMove: pageMove
    };


})(window, jQuery, _, moment, thisPage);