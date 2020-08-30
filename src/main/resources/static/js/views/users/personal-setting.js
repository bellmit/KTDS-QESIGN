const module = (function (global, $, _, moment, thisPage) {

    /***************************************************************************
     * @ 모듈 변수(상수) 선언
     **************************************************************************/
    const CTX = thisPage['ctxPath'];
    const requstURL = thisPage['requestURL'];
    const serverName = thisPage['serverName'];
    const serverPort = thisPage['serverPort'];
    const ALLOWED_IMAGE_EXTS = ['jpg', 'jpeg', 'gif'];
    const MAX_IMAGE_SIZE = 10485760; // 10MB

    /***************************************************************************
     * @ jquery 이벤트 등록
     **************************************************************************/
    function moduleEventHandlers() {

        // config croppie
        let $targetImage = $('#targetImage').croppie({
            showZoomer: false,
            viewport: {
                width: 200,
                height: 100,
                type: 'square'
            },
            boundary: {
                width: 300,
                height: 300
            }
        });

        // load image on preview modal
        $('#selectImage').on('change', function () {
            const reader = new FileReader();
            reader.onload = function (event) {
                $targetImage.croppie('bind', {
                    url: event.target.result
                });
            }
            reader.readAsDataURL(this.files[0]);
            $('#cropModal').modal('show');
        });

        // save cropped image
        $('#saveCropImage').on('click', function () {
            const attachFile = document.getElementById("selectImage");
            let imageWidth = attachFile.clientWidth;
            let imageHeight = attachFile.clientHeight;
            console.log(">>>file>>>", attachFile);
            console.log(">>>imageWidth>>>>", imageWidth);
            console.log(">>>imageHeight>>>", imageHeight);

            // check upload file size
            if (attachFile.value && !validateUploadFileSize(attachFile)) {
               $('#selectImage').val('');
               return false;
            }

            // check upload file extension
            if (attachFile.value && !validateImageExtension(attachFile.value)) {
                $('#selectImage').val('');
                return false;
            }

            $targetImage.croppie('result', {
                type: 'blob',
                size: 'viewport'
            }).then(function (blob) {
                const formData = new FormData();
                formData.append("file", blob);
                formData.append("userId", "123456789");
                formData.append("filename", $('#selectImage')[0].files[0].name);
                $.ajaxUpload($.reqPost(CTX + "example/crop/upload/image")
                    .setData(formData).build()).done(function (response) {
                    console.log("response===>", response);
                    // window.alert("지정한 이미지가 저장되었습니다.");
                    const imageInfo = response['data'];
                    const imageName = imageInfo.substring(imageInfo.lastIndexOf('/') + 1);
                    const imagePath = imageInfo.substring(0, imageInfo.lastIndexOf('/') + 1);
                    console.log("imageName===>", imageName);
                    console.log("imagePath===>", imagePath);
                    $('#cropModal').modal('hide');
                    $('#selectImage').val('');
                    const urlCreator = window.URL || window.webkitURL;
                    const imageUrl = urlCreator.createObjectURL(blob);
                    $("#croppedImage").attr("src", imageUrl);
                    $(".img-area").show();
                    $('#userSign').val(response['data'])
                    $('#signFilePath').val(imagePath);
                    $('#signFileName').val(imageName);
                });
            });
        });

        $('.close-modal').on('click', function (e) {
            e.preventDefault();
            $('#selectImage').val('');
            $('#cropModal').modal('hide');
        });

        // save person setting
        $('#savePersonSetting').on('click', function (e) {
            e.preventDefault();

            const formData = {};
            formData.userNotis = [];
            formData.signFilePath = $('#signFilePath').val();
            formData.signFileName = $('#signFileName').val();

            $.each($("input[name='send']:checked"), function () {
                console.log("send.checked====>", $(this).val());
                const userNoti = createPersonalSetting();
                userNoti.notiType = $(this).val();
                userNoti.notiDirectType = 'SEND';
                formData.userNotis.push(userNoti);
            });

            $.each($("input[name='receive']:checked"), function () {
                console.log("receive.checked====>", $(this).val());
                const userNoti = createPersonalSetting();
                userNoti.notiType = $(this).val();
                userNoti.notiDirectType = 'RECEIVE';
                formData.userNotis.push(userNoti);
            });

            console.log("@@formData=======>", formData);
            if (!validatePersonalSettingForm(formData)) return;

            $.ajaxRest($.reqPost(CTX + 'example/person-tool').setData(formData).build())
                .done(function (response) {
                    console.log("save response====>", response);
                    // global.alert("개인 설정이 저장되었습니다.");
                    $('.modal').modal("hide");
                    $('#alertMessage').html('개인 설정이 저장되었습니다.');
                    $('#alertPop').modal({backdrop: 'static', keyboard: false});
                });

        });

    }

    /***************************************************************************
     * @ 모듈 함수 선언
     **************************************************************************/

    function createPersonalSetting() {
        const userNoti = {};
        userNoti.notiType = null;
        userNoti.notiDirectType = null;
        return userNoti;
    }

    /**
     * 개인 설정 정보 조회
     */
    /**
     * request ajax
     */
    function getPersonalSetting() {
        $.ajaxRest($.reqGet(CTX + 'example/person-tool')
            .setCallback(renderPersonalSetting)
            .build()
        );
    }

    /**
     * render 개인 세팅 화면
     */
    function renderPersonalSetting(response) {
        console.log("@renderPersonalSetting.response=====>>", response);
        const userNotis = response['data']['userNotis'];
        const userReceiveNotis = userNotis.filter(function (noti) {
            return noti.notiDirectType === 'RECEIVE'
        });

        // 내가 받은 서약
        userReceiveNotis.forEach(function (noti) {
            console.log("receive.noti==========>", noti);
            if (noti && noti.notiType === 'EMAIL') {
                $('input:checkbox[name=receive]').eq(0).prop('checked', true);
            } else if (noti && noti.notiType === 'PUSH') {
                $('input:checkbox[name=receive]').eq(1).prop('checked', true);
            } else if (noti && noti.notiType === 'SNS') {
                $('input:checkbox[name=receive]').eq(2).prop('checked', true);
            }
        });

        const userSendNotis = userNotis.filter(function (noti) {
            return noti.notiDirectType === 'SEND';
        });

        // 내가 요청한 서약
        userSendNotis.forEach(function (noti) {
            console.log("send.noti==========>", noti);
            if (noti && noti.notiType === 'EMAIL') {
                $('input:checkbox[name=send]').eq(0).prop('checked', true);
            } else if (noti && noti.notiType === 'PUSH') {
                $('input:checkbox[name=send]').eq(1).prop('checked', true);
            } else if (noti && noti.notiType === 'SNS') {
                $('input:checkbox[name=send]').eq(2).prop('checked', true);
            }
        });

        const signImagePath = response['data']['signFilePath'];
        const signImageName = response['data']['signFileName'];

        if (signImagePath && signImageName) {
            $('#croppedImage').attr("src", signImagePath + signImageName);
            $('.img-area').show();
        } else {
            $('#croppedImage').removeAttr("src");
            $('.img-area').hide();
        }

    }

    /**
     * 업로드 파일 확장자 검사
     */
    function validateImageExtension(file) {
        let valid = true;
        if (file) {
            let ext = file.split('.').reverse()[0].toLowerCase();
            if (($.inArray(ext.toLowerCase(), ALLOWED_IMAGE_EXTS) < 0)) {
                // global.alert('확장자가 [' + ext + ']인 파일은 업로드 불가능합니다. [gif, jpg]만 가능합니다.');
                $('.modal').modal("hide");
                $('#alertMessage').html("확장자가 [" + ext + "]인 파일은 업로드 불가능합니다.<br/>[gif, jpg]만 가능합니다.");
                $('#alertPop').modal({backdrop: 'static', keyboard: false});
                valid = false;
            }
        }
        return valid;
    }

    /**
     * 업로드 파일 사이즈 검사
     */
    function validateUploadFileSize(attachFile) {
        let valid = true;
        if (attachFile.files[0].size) {
            let fileSize = attachFile.files[0].size; // in bytes
            let imageSize = bytesToSize(fileSize);
            if (fileSize > MAX_IMAGE_SIZE) {
                // global.alert('이미지 사이즈가 10MB를 초과하였습니다. 현재 [' + imageSize + ']');
                $('.modal').modal("hide");
                $('#alertMessage').html("이미지 사이즈가 10MB를 초과하였습니다.<br/>현재 이미지의 사이즈는 [" + imageSize + "] 입니다.");
                $('#alertPop').modal({backdrop: 'static', keyboard: false});
                valid = false;
            }
        }
        return valid;
    }

    function bytesToSize(bytes) {
        const sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB'];
        if (bytes === 0) return '0 Byte';
        let i = parseInt(Math.floor(Math.log(bytes) / Math.log(1024)));
        return Math.round(bytes / Math.pow(1024, i), 2) + ' ' + sizes[i];
    }

    /**
     * form validation
     */
    function validatePersonalSettingForm(formData) {
        console.log("@formData===========>", formData);
        return true;
    }


    /***************************************************************************
     * @ 화면 로딩 시 최초로 실행할 함수 선언
     **************************************************************************/
    function moduleInitializr() {
        getPersonalSetting();
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