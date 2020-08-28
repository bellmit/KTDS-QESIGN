const module = (function (global, $, _, moment, thisPage) {

    /***************************************************************************
     * @ 모듈 변수(상수) 선언
     **************************************************************************/
    const CTX = thisPage['ctxPath'];
    const COMMON_CODE = thisPage['commonCode'];

    /***************************************************************************
     * @ 모듈 함수 선언
     **************************************************************************/
    function createUserNotiObject() {
        const userNoti = {};
        userNoti.notiType = null;
        userNoti.notiDirection = null;
        return userNoti;
    }


    /***************************************************************************
     * @ jquery 이벤트 등록
     **************************************************************************/
    function moduleEventHandlers() {

        $("#save").on('click', function () {

            const formData = {};
            formData.userNotis = [];

            $.each($("input[name='send']:checked"), function () {
                console.log("send.checked====>", $(this).val());
                const userNoti = createUserNotiObject();
                userNoti.notiType = $(this).val();
                userNoti.notiDirection = 'SEND';
                formData.userNotis.push(userNoti);
            });

            $.each($("input[name='receive']:checked"), function () {
                console.log("receive.checked====>", $(this).val());
                const userNoti = createUserNotiObject();
                userNoti.notiType = $(this).val();
                userNoti.notiDirection = 'RECEIVE';
                formData.userNotis.push(userNoti);
            });

            console.log("@formData=======>", formData);

            $.ajaxRest($.reqPost(CTX + 'example/person-tool').setData(formData).build())
                .done(function (response) {
                    console.log("save response====>", response);
                });

        });

    }

    /***************************************************************************
     * @ 화면 로딩 시 최초로 실행할 함수 선언
     **************************************************************************/
    function moduleInitializr() {
        // 정의된_사용자_함수_중_화면_최초_로딩시_호출된_함수();

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
    return {};


})(window, jQuery, _, moment, thisPage);