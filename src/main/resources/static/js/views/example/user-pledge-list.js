const module = (function (global, $, _, moment, moduleUI, thisPage) {

    /***************************************************************************
     * @ 모듈 변수(상수) 선언
     **************************************************************************/
    const CTX = thisPage['ctxPath'];
    const COMMON_CODE = thisPage['commonCode'];
    let ACTIVE_TAB = 'proceeding-tab';

    /***************************************************************************
     * @ 모듈 함수 선언
     **************************************************************************/

    /**
     * create form data object
     */
    function createFormDataObject() {
        const formData = {};
        formData.dateType = null;
        formData.startDt = null;
        formData.endDt = null;
        formData.searchKey = null;
        formData.contentsType = null;
        formData.progsSttusType = null;
        formData.pledgeName = null;
        formData.reqDept = null;
        formData.reqUser = null;
        formData.page = 0;
        formData.size = 10;
        return formData;
    }

    /**
     * render code selector
     */
    function renderCodeSelector() {
        const contentsSelectHtml = moduleUI.getSelectorFromGroupCode(COMMON_CODE, "CONTENTS_TYPE");
        $("#contentsType").html(contentsSelectHtml).selectpicker('refresh');
        // $("#contentsType").val("VIDEO");
        // $("#contentsType").selectpicker('refresh');
    }

    /**
     * Result Count
     */
    function getUserPledgeResultCount(formData) {
        if (!formData) {
            formData = {};
            formData.startDt = moment($('#date-start').val(), 'YYYY-MM-DD').startOf("day").format("YYYY-MM-DD[T]HH:mm"); // java LocalDateTime format
            formData.endDt = moment($('#date-end').val(), 'YYYY-MM-DD').endOf("day").format("YYYY-MM-DD[T]HH:mm");
            formData.dateType = $("input[type='radio']:checked").val();
        }

        const param = decodeURI($.param(formData)); // serializing form data

        $.ajaxRest($.reqGet(CTX + 'example/user/pledges/count')
            .setData(param).build()
        ).done(function (response) {
            const content = response['data'];
            $('#pro-cnt').html("(" + content['proceedingCount'] + ")");
            $('#com-cnt').html("(" + content['completeCount'] + ")");
            $('#std-cnt').html("(" + content['standbyCount'] + ")");
        });
    }

    /**
     * request ajax
     */
    function getUserPledgeList(formData, pageNo) {
        formData.page = pageNo || 0;
        formData.size = $('#low-size').val() || 10;

        if (!formData.startDt || !formData.endDt) {
            formData.startDt = moment($('#date-start').val(), 'YYYY-MM-DD').startOf("day").format("YYYY-MM-DD[T]HH:mm"); // java LocalDateTime format
            formData.endDt = moment($('#date-end').val(), 'YYYY-MM-DD').endOf("day").format("YYYY-MM-DD[T]HH:mm");
        }

        if (ACTIVE_TAB === 'proceeding-tab') { // 최초 로딩 또는 선택 시
            formData.progsSttusType = 'ONGOING';
        } else if (ACTIVE_TAB === 'complete-tab') {
            formData.progsSttusType = 'COMPLETE';
        } else if (ACTIVE_TAB === 'standby-tab') {
            formData.progsSttusType = 'STANDBY';
        }

        const param = decodeURI($.param(formData));
        console.log("@@@param============>", param);

        $.ajaxRest($.reqGet(CTX + 'example/user/pledges/list')
            .setData(param)
            .setCallback(renderUserPledgeTable)
            .build()
        );
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
        const currentLows = totalElements - (page * size);
        let $tbody = null;
        let $listWrap = null;
        let $pagination = null;

        if (ACTIVE_TAB === 'proceeding-tab') {
            $tbody = $('#tbody');
            $listWrap = $('#list-wrap');
            $pagination = $('#pagination');
        } else if (ACTIVE_TAB === 'complete-tab') {
            $tbody = $('#tbody2');
            $listWrap = $('#list-wrap2');
            $pagination = $('#pagination2');
        } else if (ACTIVE_TAB === 'standby-tab') {
            $tbody = $('#tbody3');
            $listWrap = $('#list-wrap3');
            $pagination = $('#pagination3');
        } else { // proceeding-tab
            $tbody = $('#tbody');
            $listWrap = $('#list-wrap');
            $pagination = $('#pagination');
        }

        // clear table
        $tbody.empty();

        if (!_.isEmpty(content)) {
            let html = '';
            content.forEach(function (v, i) {
                html += '<li>';
                html += '<ul class="list-group list-group-horizontal" data-id="' + v['id'] + '">';
                html += '<li class="d-inline-block w-5">' + (currentLows - i) + '</li>';
                //html += '<li class="d-inline-block w-10 font-blue">' + getDescFromAcceptCode(v['progsSttusType']) + '</li>';
                html += '<li class="d-inline-block w-10 font-blue">' + v['progsSttusDesc'] + '</li>';
                //html += '<li class="d-inline-block w-10">' + getDescFromCode(v['contentsType']) + '</li>';
                html += '<li class="d-inline-block w-10">' + v['contentsDesc'] + '</li>';
                html += '<li class="d-inline-block w-30 text-left pledge-name">';
                html += '<a class="text-truncate" href="javascript:;">' + v['pledgeName'] + '</a>';
                html += '</li>';
                html += '<li class="d-inline-block w-10">' + $.dateFormat(v['startDt'], 'D') + '</li>';
                html += '<li class="d-inline-block w-15">' + v['reqDept'] + '</li>';
                html += '<li class="d-inline-block w-10">' + v['reqUser'] + '</li>';
                html += '<li class="d-inline-block w-10">' + $.dateFormat(v['endDt'], 'D') + '</li>';
                html += '</ul>';
                html += '</li>';
            });

            $listWrap.removeClass('list-none')
            $tbody.html(html);
            $pagination.html(pagination).show();
            $('#total').html("&nbsp;" +totalElements);

        } else {
            $listWrap.addClass('list-none')
            $tbody.hide();
            $pagination.html(pagination).hide();
            $('#total').html("&nbsp;0");
        }

        return response; // return promise

    }

    /**
     * search form validation
     */
    function validateSearchForm(formData) {
        console.log("validate===============>", formData);
        // search cond validation
        if (!formData.startDt) {
            global.alert('검색 시작일을 설정하세요.');
            return false;
        } else if (!formData.endDt) {
            global.alert('검색 종료일을 설정하세요.');
            return false;
        } else if (!formData.searchType) {
            global.alert('검색 조건을 선택하세요.');
            $('#searchType').focus();
            return false;
        } else if ((!formData.searchType || formData.searchType !== 'all') && !formData.searchKey) {
            global.alert('검색 내용을 입력하세요.');
            $('#searchKey').focus();
            return false;
        }
        return true;
    }


    /**
     * 페이징 클릭 이벤트 처리
     */
    function pageMove(pageNo) {
        getUserPledgeList(createFormDataObject(), pageNo);
    }

    /**
     * PledgeAcceptCode Description
     */
    function getDescFromAcceptCode(code) {
        return {
            STANDBY: '대기',
            PROCEEDING: '진행 중',
            COMPLETE: '완료'
        }[code];
    }

    /**
     * contentsType Description
     */
    function getDescFromCode(code) {
        return {
            HTML: '일반',
            VIDEO: '영상'
        }[code];
    }

    /***************************************************************************
     * @ jquery 이벤트 등록
     **************************************************************************/
    function moduleEventHandlers() {

        // low-size select event
        $('#low-size').on('change', function () {
            getUserPledgeList(createFormDataObject());
        });

        /**
         * Datepicker ####################################################################
         */
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
        /**
         * Datepicker ####################################################################
         */

        // 검색
        $('#search').on('click', function (e) {
            e.preventDefault();
            // set search formData
            const formData = createFormDataObject();
            const searchType = $('#searchType').val();
            const searchKey = $('#searchKey').val();
            formData.searchType = searchType;
            formData.searchKey = searchKey;
            formData.contentsType = $('#contentsType').val();
            formData.dateType = $("input[type='radio']:checked").val();
            formData.startDt = moment($('#date-start').val(), 'YYYY-MM-DD').startOf("day").format("YYYY-MM-DD[T]HH:mm"); // java LocalDateTime format
            formData.endDt = moment($('#date-end').val(), 'YYYY-MM-DD').endOf("day").format("YYYY-MM-DD[T]HH:mm");

            if (searchType === 'pledgeName') {
                formData.pledgeName = searchKey;
            } else if (searchType === 'reqDept') {
                formData.reqDept = searchKey;
            } else if (searchType === 'reqUser') {
                formData.reqUser = searchKey;
            } else if (searchType === 'all') {
                formData.pledgeName = searchKey;
                formData.reqDept = searchKey;
                formData.reqUser = searchKey;
            }

            // search form validation
            if (!validateSearchForm(formData)) return;

            // call search ajax function
            getUserPledgeList(formData)
            getUserPledgeResultCount(formData);

        });

        // 상세조회 화면으로 이동 query param not route param(path variable)
        $(document).on('click', '.pledge-name', function (e) {
            e.preventDefault();
            const userPledgeId = $(this).closest('ul').data('id');
            if (userPledgeId) {
                global.location.href = CTX + 'example/user/pledges/detail/view?userPledgeId=' + userPledgeId;
            }
        });

        // 탭 클릭 이벤트
        $(".nav-tabs a").on('show.bs.tab', function (e) {
            ACTIVE_TAB = $(e.target).attr('id'); // active tab
            getUserPledgeResultCount();
            getUserPledgeList(createFormDataObject())
        });

        // enter 조회
        $('#searchKey').on('keypress', function(e) {
            if (e.which === 13 || e.keyCode === 13) {
                $('#search').trigger('click');
                return false;
            }
            return true;
        });

    }

    /***************************************************************************
     * @ 화면 로딩 시 최초로 실행할 함수 선언
     **************************************************************************/
    function moduleInitializr() {
        renderCodeSelector();
        getUserPledgeResultCount();
        getUserPledgeList(createFormDataObject());
    }


    /***************************************************************************
     * @ DOM Ready 실행
     **************************************************************************/
    $(function () {
        moduleEventHandlers(); // always first
        moduleInitializr();
    });

    /***************************************************************************
     * @ 외부로 노출할 함수 선언
     **************************************************************************/
    return {
        pageMove: pageMove
    };


})(window, jQuery, _, moment, moduleUI, thisPage);