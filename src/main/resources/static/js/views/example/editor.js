const module = (function (global, $, _, moment, thisPage) {

    /***************************************************************************
     * @ 모듈 변수(상수) 선언
     **************************************************************************/
    const CTX = thisPage['ctxPath'];

    /***************************************************************************
     * @ 모듈 함수 선언
     **************************************************************************/
    function initCKEditor() {

        CKEDITOR.replace('editor', {
            filebrowserImageUploadUrl: CTX + 'example/editor/upload/image',
            height: 400
        });

        CKEDITOR.on('dialogDefinition', function (ev) {
            // Take the dialog name and its definition from the event data.
            const dialogName = ev.data.name;
            const dialogDefinition = ev.data.definition;
            // Check if the definition is from the dialog we're
            // interested in (the 'image' dialog). This dialog name found using DevTools plugin
            if (dialogName === 'image') {
                // Remove the 'Link' and 'Advanced' tabs from the 'Image' dialog.
                dialogDefinition.removeContents('Link');    // 링크 탭 제거
                dialogDefinition.removeContents('advanced');  // 상세정보 탭 제거

                // Get a reference to the 'Image Info' tab.
                const infoTab = dialogDefinition.getContents('info');  // info탭을 제거하면 이미지 업로드가 안된다.
                // Remove unnecessary widgets/elements from the 'Image Info' tab.
                // infoTab.remove('txtHSpace'); //info 탭 내에 불필요한 엘레멘트들 제거
                // infoTab.remove('txtVSpace');
                // infoTab.remove('txtBorder');
                // infoTab.remove('txtWidth');
                // infoTab.remove('txtHeight');
                // infoTab.remove('ratioLock');
            }
        });

    }

    /***************************************************************************
     * @ jquery 이벤트 등록
     **************************************************************************/
    function moduleEventHandlers() {

        // post
        $('#editor_post').on('click', function() {
            // window.alert(CKEDITOR.instances.editor.getData());

            const title = $("#title").val();
            const content = CKEDITOR.instances.editor.getData();
            const images = [];

            const post = content;
            const $images = $(post).find('img');
            $images.each(function() {
                images.push($(this).attr('src').split("/").pop());
            });

            const data = {
                title: title,
                content: content,
                images: images
            }

            if (!data.title) {
                alert("title must not be empty!");
                return;
            }

            if (!data.content) {
                alert("content must not be empty!");
                return;
            }

            $.ajaxRest($.reqPost(CTX + "example/editor/save")
                .setData(data).build()).done(function (response) {
                console.log("response===>", response);
                window.alert("게시글이 저장되었습니다."); // error 처리는 error callback에서 일괄 처리
            });

        });

        // cancel
        $('#editor_cancel').on('click', function() {
            CKEDITOR.instances.editor.setData('');
        });

    }

    /***************************************************************************
     * @ 화면 로딩 시 최초로 실행할 함수 선언
     **************************************************************************/
    function moduleInitializr() {
        initCKEditor();

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