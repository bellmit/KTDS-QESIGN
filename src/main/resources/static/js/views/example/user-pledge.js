const module = (function (global, $, _, moment, thisPage) {

    /***************************************************************************
     * @ 모듈 변수(상수) 선언
     **************************************************************************/
    const CTX = thisPage['ctxPath'];
    const COMMON_CODE = thisPage['commonCode'];

    /***************************************************************************
     * @ 모듈 함수 선언
     **************************************************************************/

    /**
     * create form data object
     */
    function createFormDataObject(pledgeType, pledgeAcceptType, pledgeName, startDt, endDt, reqDept, reqUser) {
        const formData = {};
        formData.pledgeType = pledgeType || null;
        formData.pledgeAcceptType = pledgeAcceptType || null;
        formData.pledgeName = pledgeName || null;
        formData.startDt = startDt || null;
        formData.endDt = endDt || null;
        formData.reqDept = reqDept || null;
        formData.reqUser = reqUser || null;
        return formData;
    }

    /**
     * render code selector
     */
    function renderCodeSelector() {
        const acceptTypeSelectHtml = moduleUI.getSelectorFromGroupCode(COMMON_CODE, "PLEDGE_ACCEPT_TYPE");
        const typeSelectHtml = moduleUI.getSelectorFromGroupCode(COMMON_CODE, "PLEDGE_TYPE");
        $("#pledgeAcceptType").html(acceptTypeSelectHtml).selectpicker('refresh');
        $("#pledgeType").html(typeSelectHtml).selectpicker('refresh');
    }

    /**
     * create form validation
     */
    function validateCreateForm(formData) {
        if (!formData.pledgeType) {
            global.alert('서약 유형을 선택하세요.');
            $('#pledgeType').focus();
            return false;
        } else if (!formData.pledgeAcceptType) {
            global.alert('진행상태를 선택하세요.');
            $('#pledgeAcceptType').focus();
            return false;
        } else if (!formData.pledgeName) {
            global.alert('서약 업무명을 입력하세요.');
            $('#pledgeName').focus();
            return false;
        } else if (!formData.reqDept) {
            global.alert('요청 부서를 입력하세요.');
            $('#reqDept').focus();
            return false;
        } else if (!formData.reqUser) {
            global.alert('요청자를 입력하세요.');
            $('#reqUser').focus();
            return false;
        } else if (!formData.startDt) {
            global.alert('검색 시작일을 설정하세요.');
            $('#startDt').focus();
            return false;
        } else if (!formData.endDt) {
            global.alert('검색 종료일을 설정하세요.');
            $('#endDt').focus();
            return false;
        }

        return true;
    }


    /***************************************************************************
     * @ jquery 이벤트 등록
     **************************************************************************/
    function moduleEventHandlers() {

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

        // 생성 이벤트
        $('#create').on('click', function(e) {
            e.preventDefault();

            const pledgeType = $('#pledgeType').val();
            const pledgeAcceptType = $('#pledgeAcceptType').val();
            const pledgeName = $('#pledgeName').val().trim();
            const startDt = moment($('#date-start').val(), 'YYYY-MM-DD').format("YYYY-MM-DD[T]HH:mm"); // java LocalDateTime format
            const endDt = moment($('#date-end').val(), 'YYYY-MM-DD').format("YYYY-MM-DD[T]HH:mm");
            const reqDept = $('#reqDept').val();
            const reqUser = $('#reqUser').val();

            // create formData
            const formData = createFormDataObject(pledgeType, pledgeAcceptType, pledgeName, startDt, endDt, reqDept, reqUser);
            console.log("@formData=========>", formData);

            // create form validation
            if (!validateCreateForm(formData)) return;

            $.ajaxRest($.reqPost(CTX + 'example/user/pledge')
                .setData(formData)
                .build()
            ).done(function (response) { // 추가 작업 시 사용
                console.log("create end====>", response);
                global.alert("사용자 서약 정보가 생성되었습니다.");
                global.location.href = CTX + 'example/user/pledges'; // 목록으로 이동
            });
        });

    }

    /***************************************************************************
     * @ 화면 로딩 시 최초로 실행할 함수 선언
     **************************************************************************/
    function moduleInitializr() {
        renderCodeSelector();
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

    };


})(window, jQuery, _, moment, thisPage);