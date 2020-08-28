const module = (function (global, $, _, moment, moduleUI, thisPage) {

    /***************************************************************************
     * @ 모듈 변수(상수) 선언
     **************************************************************************/
    const CTX = thisPage['ctxPath'];
    const COMMON_CODE = thisPage['commonCode'];
    const userPledgeId = thisPage['userPledgeId'];

    /***************************************************************************
     * @ 모듈 함수 선언
     **************************************************************************/

    /**
     * create form data object
     */
    function createFormDataObject(contentsType, progsSttusType, pledgeName) {
        const formData = {};
        formData.contentsType = contentsType || null;
        formData.progsSttusType = progsSttusType || null;
        formData.pledgeName = pledgeName || null;
        return formData;
    }

    /**
     * render code selector
     */
    function renderCodeSelector() {
        const progsSttusSelectHtml = moduleUI.getSelectorFromGroupCode(COMMON_CODE, "PROGS_STTUS_TYPE");
        const contentsSelectHtml = moduleUI.getSelectorFromGroupCode(COMMON_CODE, "CONTENTS_TYPE");
        $("#progsSttusType").html(progsSttusSelectHtml).selectpicker('refresh');
        $("#contentsType").html(contentsSelectHtml).selectpicker('refresh');
    }


    // 서약 상세 조회
    function getUserPledgeDetail() {
        $.ajaxRest($.reqGet(CTX + 'example/user/pledges/detail/' + userPledgeId)
            .setCallback(renderUserPledgeDetail)
            .build()
        ).done(function (response) { // 추가 작업 시 사용
            console.log("end====>", response);
        });
    }

    // 서약 상세 화면 생성
    function renderUserPledgeDetail(response) {
        console.log("response============>", response);
        const content = response['data'];
        $('#pledgeName').val(content['pledgeName']);
        $('#reqDept').val(content['reqDept']);
        $('#reqUser').val(content['reqUser']);

        // $('#startDt').val($.dateFormat(content['startDt'], 'D'));
        // $('#endDt').val($.dateFormat(content['endDt'], 'D'));
        $('#date-start').datepicker('update', moment(content['startDt'], 'YYYY-MM-DD[T]HH:mm:ss').toDate());
        $('#date-end').datepicker('update', moment(content['endDt'], 'YYYY-MM-DD[T]HH:mm:ss').toDate());

        // set selectbox value by ajax value
        $('#progsSttusType option[value='+ content['progsSttusType'] +']').prop('selected', true);
        $('#progsSttusType').selectpicker('refresh');

        $('#contentsType option[value='+ content['contentsType'] +']').prop('selected', true);
        $('#contentsType').selectpicker('refresh');

        return response; // 추가 작업 시 사용
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


        // 수정 이벤트(reqPut): pathvariable로 id와 수정할 formData 넘겨줄것
        $('#update').on('click', function(e) {
            e.preventDefault();
            const contentsType = $('#contentsType').val();
            const progsSttusType = $('#progsSttusType').val();
            const pledgeName = $('#pledgeName').val();
            const formData = createFormDataObject(contentsType, progsSttusType, pledgeName);
            console.log("formData=========>", formData);
            $.ajaxRest($.reqPut(CTX + 'example/user/pledges/detail/' + userPledgeId)
                .setData(formData)
                .build()
            ).done(function (response) { // 추가 작업 시 사용
                console.log("update end====>", response);
                global.alert("서약 정보가 수정되었습니다.");
                global.location.href = CTX + 'example/user/pledges/view'; // 목록으로 이동
            });
        });

        // 삭제 이벤트(reqDel): pathvariable로 id를 넘겨줄 것
        $('#delete').on('click', function(e) {
            e.preventDefault();
            $.ajaxRest($.reqDel(CTX + 'example/user/pledges/detail/' + userPledgeId)
                .build()
            ).done(function (response) { // 추가 작업 시 사용
                console.log("delete end====>", response);
                global.alert("서약 정보가 삭제되었습니다.");
                global.location.href = CTX + 'example/user/pledges/view'; // 목록으로 이동
            });
        });

    }

    /***************************************************************************
     * @ 화면 로딩 시 최초로 실행할 함수 선언
     **************************************************************************/
    function moduleInitializr() {
        renderCodeSelector();
        getUserPledgeDetail();
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

})(window, jQuery, _, moment, moduleUI, thisPage);