const module = (function (global, $, _, moment, thisPage) {

    /***************************************************************************
     * @ 모듈 변수(상수) 선언
     **************************************************************************/
    const CTX = thisPage['ctxPath'];
    let videoPlaySec = 0;

    /***************************************************************************
     * @ 모듈 함수 선언
     **************************************************************************/

    // local video
    // function getCurTime() {
    //     const vid = document.getElementById('video');
    //     window.alert("current play time is " + vid.currentTime);
    // }
    //
    // function setCurTime() {
    //     const vid = document.getElementById('video');
    //     vid.currentTime = 5; // seconds
    // }

    // remote video
    function getCurTime2() {
        const vid = document.getElementById('video2');
        window.alert("current play time is " + vid.currentTime);
    }

    function setCurTime2() {
        const vid = document.getElementById('video2');
        vid.currentTime = 5; // seconds
    }

    /***************************************************************************
     * @ jquery 이벤트 등록
     **************************************************************************/
    function moduleEventHandlers() {

        // $('#get').on('click', function (e) {
        //     e.preventDefault();
        //     getCurTime();
        // });
        //
        // $('#set').on('click', function (e) {
        //     e.preventDefault();
        //     setCurTime();
        // });

        $('#get2').on('click', function (e) {
            e.preventDefault();
            getCurTime2();
        });

        $('#set2').on('click', function (e) {
            e.preventDefault();
            setCurTime2();
        });

        // 새 동영상 불러오기
        $('#new-vod').on('click', function (e) {
            e.preventDefault();
            $('#pledge').hide();
            const videoName = $('#videoName').val();
            $.ajaxRest($.reqGet(CTX + 'example/video/request/' + videoName)
                .build()
            ).done(function (response) { // 추가 작업 시 사용
                console.log("end====>", response);
                videoPlaySec = response['data']['videoPlaySec'];
                let video = document.getElementById('video2');
                video.src = response['data']['videoUrl'];
                video.load();
                // video.play();
            });
        });

        // 사용자 임의로 동영상 스킵을 막기 위해서 video tag에
        // (style="pointer-events: none;") 적용
        $('#play').on('click', function () {
            $('#video2').get(0).play();
        });

        $('#pause').on('click', function () {
            $('#video2').get(0).pause();
        });

        $("#video2").on("timeupdate", function () {
            onTrackedVideoFrame(this.currentTime, this.duration);
        });

        // currentTime(현재 시간), duration(총 영상 시간)
        function onTrackedVideoFrame(currentTime, duration) {
            console.log("crrentTime===>", currentTime, "totalTime===>", duration);
            if (currentTime > videoPlaySec) {
                $('#end').html("필수 시청 시간 시청을 완료하였습니다.");
                $('#pledge').show();
            }
            // $("#current").text(currentTime); //Change #current to currentTime
            // $("#duration").text(duration)
        }

        $('#pledge').on('click', function (e) {
            e.preventDefault();
            $('.modal').modal("hide");
            $('#alertMessage').html('서약을 진행합니다.');
            $('#alertPop').modal({backdrop: 'static', keyboard: false});
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