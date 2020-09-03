const module = (function (global, $, _, moment, thisPage) {

    /***************************************************************************
     * @ 모듈 변수(상수) 선언
     **************************************************************************/
    const CTX = thisPage['ctxPath'];

    /***************************************************************************
     * @ 모듈 함수 선언
     **************************************************************************/


    /***************************************************************************
     * @ jquery 이벤트 등록
     **************************************************************************/
    function moduleEventHandlers() {

        // 영상 불러오기
        $('#videoFile').on('change', function () {
            console.log("loading video file.................");
            const file = this.files[0];
            const reader = new FileReader();
            reader.onload = function (e) {
                $('#preview').attr('src', e.target.result);
            }
            reader.readAsDataURL(file);
            console.log("filename===>", file.name);
            console.log("filetype===>", file.type);
            console.log("filesize===>", Math.round(file.size / 1024));
        });


        // 영상 업로드
        $('#save').on('click', function (e) {
            e.preventDefault();

            const formData = new FormData($('#videoUploadForm')[0]);
            formData.append("formName", $('#formName').val());
            formData.append("formDesc", $('#formDesc').val());
            formData.append("formType", "VIDEO");

            $.ajaxUpload($.reqPost(CTX + "example/video/upload")
                .setData(formData).build()).done(function (response) {
                console.log("response===>", response);
                $('.modal').modal("hide");
                $('#alertMessage').html('영상 양식이 저장되었습니다.');
                $('#alertPop').modal({backdrop: 'static', keyboard: false});
                $('#preview2').attr('src', response['data'])
                $('#savedVideo').show();

            });

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
        //pageMove : pageMove, // 페이징 링크 클릭 처리
    };


})(window, jQuery, _, moment, thisPage);