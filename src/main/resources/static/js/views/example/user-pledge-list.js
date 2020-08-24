const module = (function (global, $, _, moment, thisPage) {

    /***************************************************************************
     * @ 모듈 변수(상수) 선언
     **************************************************************************/
    const CTX = thisPage['ctxPath'];

    class Pledge {
        constructor(dateType, startDt, endDt, searchKey, pledgeType, pledgeName, reqUser, reqDetp, page, size) {
            this.dateType = dateType;
            this.startDt = startDt;
            this.endDt = endDt;
            this.searchKey = searchKey;
            this.pledgeType = pledgeType;
            this.pledgeName = pledgeName;
            this.reqUser = reqUser;
            this.reqDetp = reqDetp;
            this.page = page || 0;
            this.size = size || 10;
        }
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
        const contents = response['data'];
        const pledgeProgTypes = contents.filter(content => content['groupCode'] === 'PLEDGE_TYPE');
        let html = '<option value="">전체</option>';
        pledgeProgTypes.forEach(pledgeProgType => {
            html += '<option value="' + pledgeProgType['code'] + '">' + pledgeProgType['codeDesc'] + '</option>';
        });
        $("#pledgeType").html(html).selectpicker('refresh');
    }

    /**
     * request ajax
     */
    function getUserPledgeList(formData, pageNo) {
        formData.page = pageNo || 0;
        formData.size = $('#low-size').val() || 10;
        const param = decodeURI($.param(formData));

        $.ajaxRest($.reqGet(CTX + 'example/user/pledges/list')
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
        const currentLows = totalElements - (page * size);
        const $tbody = $('#tbody');
        const $listWrap = $('#list-wrap');
        const $total = $('#total');
        const $pagination = $('#pagination');

        // result reset
        $tbody.empty();

        if (!_.isEmpty(content)) {
            let html = '';
            content.forEach(function (v, i) {
                html += '<li>';
                html += '<ul class="list-group list-group-horizontal" data-id="' + v['id'] + '">';
                html += '<li class="d-inline-block w-5">' + (currentLows - i) + '</li>';
                html += '<li class="d-inline-block w-10 font-blue">' + getDescFromAcceptCode(v['pledgeAcceptType']) + '</li>';
                html += '<li class="d-inline-block w-10">' + getDescFromCode(v['pledgeType']) + '</li>';
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
        getUserPledgeList(new Pledge(), pageNo);
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
     * PledgeType Description
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

        // bootstrap selectpicker init
        $('.selectpicker').selectpicker();

        // low-size select event
        $('#low-size').on('change', function () {
            getUserPledgeList(new Pledge());
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
            // set search formData
            const formData = new Pledge();
            const searchType = $('#searchType').val();
            const searchKey = $('#searchKey').val();
            formData.searchType = searchType;
            formData.searchKey = searchKey;
            formData.pledgeType = $('#pledgeType').val();
            formData.dateType = $("input[type='radio']:checked").val();
            formData.startDt = moment($('#date-start').val(), 'YYYY-MM-DD').format("YYYY-MM-DD[T]HH:mm"); // java LocalDateTime format
            formData.endDt = moment($('#date-end').val(), 'YYYY-MM-DD').format("YYYY-MM-DD[T]HH:mm");

            if (searchType === 'pledgeName') {
                formData.pledgeName = searchKey;
            } else if (searchType === 'reqDept') {
                formData.reqDept = searchKey;
            } else if (searchType === 'reqUser') {
                formData.reqUser = searchKey;
            }
            console.log("formData========>", formData);

            // search form validation
            if (!validateSearchForm(formData)) return;

            // call search ajax function
            getUserPledgeList(formData)

        });

        // 상세
        $(document).on('click', '.pledge-name', function (e) {
            e.preventDefault();
            const userPledgeId = $(this).closest('ul').data('id');
            if (userPledgeId) {
                global.location.href = CTX + 'example/user/pledges/detail?userPledgeId=' + userPledgeId;
                ;
            }
        });


    }

    /***************************************************************************
     * @ 화면 로딩 시 최초로 실행할 함수 선언
     **************************************************************************/
    function moduleInitializr() {
        getCommonCodeList();
        getUserPledgeList(new Pledge());
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