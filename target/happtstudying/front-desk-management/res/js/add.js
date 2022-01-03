$(function () {
    var qId = getQueryVariable("qId");
    var aId = getQueryVariable("aId");
    replaceSelectType();
    replaceCollege();
    if (qId) {
        $.getJSON('/getquestion.do', {qId: qId}, function (msg) {
            if (msg.success) {
                console.log(msg.question);
                $('#select-type').val('0');
                $('#L_title').val(msg.question.qTitle);
                $('#select-college').val(msg.question.qCollege);
                $('#L_version').val(msg.question.qGradeLevel);
                $('#L_content').val(msg.question.qDescription);
                $('#diamond-number').val(msg.question.qDiamondNumber);
                $('#icon').attr('src', '/front-desk-management/user/question/' + msg.question.qIcon);
                reBuildQuestionSubmit();
            }
        })
    } else if (aId) {
        $('#select-type').val('99');
    } else {
        buildSubmit();
    }
})

function replaceSelectType() {
    $.getJSON('/user/getinfo.do', function (msg) {
        if (msg.success) {
            var replaceHTML = '';
            var user = msg.user;
            if (user.isSubDivision == '1') {
                replaceHTML += '<option></option> \n' +
                    '                      <option value="0">问答</option>\n' +
                    '                      <option value="99">活动</option>';
            } else {
                replaceHTML += '<option></option> \n' +
                    '                      <option value="0">问答</option>\n';
            }
            $('#select-type').html(replaceHTML);
        }
    })
}

function replaceCollege() {
    $.getJSON('/user/getallcollege.do', function (msg) {
        if (msg.success) {
            var replaceHTML = '<option></option>';
            var colleges = msg.colleges;
            colleges.map(function (item, index) {
                replaceHTML += '<option value="' + item.cName + '">' + item.cName + '</option>';
            })
            $('#select-college').html(replaceHTML);
        }
    })
}

function reBuildQuestionSubmit() {
    $('#submit').on('click', function () {
        var selectType = $('#select-type').val();
        if (selectType == '') {
            alert('请先选择发表类型');
            return false;
        }
        var data = {};
        data['title'] = $('#L_title').val().trim();
        data['college'] = $('#select-college').val().trim();
        data['gradeLevel'] = $('#L_version').val().trim();
        data['content'] = change($('#L_content').val().trim());

        if (data.title == '') {
            alert("标题不能为空！");
            return false;
        }

        var url = '';
        if (selectType == '0') {
            url = '/user/updatequestion.do';
            data['diamondNumber'] = $('#diamond-number').val().trim();
        } else {
            layer.alert('无法修改选择类型哦');
            return false;
        }
        data['qId'] = getQueryVariable("qId");
        $.ajax({
            async: false,
            url: url,
            type: 'GET',
            data: data,
            success: function (msg) {
                if (msg.success) {
                    var icon = $('#input-icon')[0].files[0];
                    if (icon != undefined) {
                        updateIcon(selectType, icon, msg.id);
                    }
                    alert('发布成功');
                    window.location.href = '/front-desk-management/html/question/detail.html?qId=' + getQueryVariable("qId");
                } else {
                    layer.msg('发布失败，' + msg.errMsg);
                }
            },
            error: function () {
                layer.msg('error');
            }
        })
    })
}

function reBuildActivitySubmit() {
    $('#submit').on('click', function () {
        layer.msg(3);
    })
}

function buildSubmit() {
    $('#submit').on('click', function () {
        var selectType = $('#select-type').val();
        if (selectType == '') {
            layer.msg('请先选择发表类型');
            return false;
        }
        var data = {};
        data['title'] = $('#L_title').val().trim();
        data['college'] = $('#select-college').val().trim();
        data['gradeLevel'] = $('#L_version').val().trim();
        data['content'] = change($('#L_content').val().trim());

        if (data.title == '') {
            layer.msg("标题不能为空！");
            return false;
        }

        var url = '';
        var flag = true, remainDiamondNumber = null;
        if (selectType == '0') {
            url = '/user/insertquestion.do';
            data['diamondNumber'] = $('#diamond-number').val().trim();
            $.ajax({
                async: false,
                url: '/user/getinfo.do',
                success: function (msg) {
                    if (msg.success) {
                        if (parseInt(msg.user.uDiamondNumber) < parseInt(data['diamondNumber'])) {
                            layer.alert("当前你的钻石数小于悬赏数，请重新选择！");
                            flag = false;
                        } else {
                            remainDiamondNumber = parseInt(msg.user.uDiamondNumber) - parseInt(data['diamondNumber'])
                        }
                    }
                }
            })
        } else {
            url = '/user/insertactivity.do';
            data['beginTime'] = $('#begin-time').val().trim();
            data['endTime'] = $('#end-time').val().trim();
            if (data.beginTime == '' || data.endTime == '') {
                layer.msg("起止时间为必填项！");
                return false;
            }
            var beginTime = new Date(data.beginTime);
            var endTime = new Date(data.endTime);
            if (beginTime.getTime() > endTime.getTime()) {
                layer.msg("开始时间大于结束时间啦！请检查~");
                return false;
            }
        }
        if (flag == true) {
            $.ajax({
                async: false,
                url: url,
                type: 'GET',
                data: data,
                success: function (msg) {
                    if (msg.success) {
                        var icon = $('#input-icon')[0].files[0];
                        if (icon != undefined) {
                            updateIcon(selectType, icon, msg.id);
                        }
                        alert('发布成功');
                        if (remainDiamondNumber != null) {
                            updateUserDiamondNumber(remainDiamondNumber);
                        }
                        if (url == '/user/insertquestion.do') {
                            window.location.href = '/front-desk-management/html/question/detail.html?qId=' + msg.id;
                        } else {
                            window.location.href = '/front-desk-management/html/activity/detail.html?aId=' + msg.id;
                        }
                        // location.reload();
                    } else {
                        layer.msg('发布失败，' + msg.errMsg);
                    }
                },
                error: function () {
                    layer.msg('error');
                }
            })
        }
    })
}

function updateIcon(selectType, icon, id) {
    var iconUrl = (selectType == '99') ? "/user/updateactivityicon.do" : "/user/updatequestionicon.do";
    var data = new FormData();
    data.append("icon", icon);
    data.append("id", id);
    // console.log(data);
    $.ajax({
        async: false,
        url: iconUrl,
        type: 'POST',
        data: data,
        contentType: false,
        processData: false,
        success: function (msg) {
            if (msg.success) {
                layer.msg('更新图标成功');
            } else {
                layer.msg('更新图标失败，' + msg.errMsg);
            }
        },
        error: function () {
            layer.msg('error');
        }
    })
}

function updateUserDiamondNumber(remainDiamondNumber) {
    $.getJSON('/user/updateuserdiamondnumber.do', {remainDiamondNumber: remainDiamondNumber});
}

function change(content) {
    content = content.replaceAll('<', '&lt');
    content = content.replaceAll('>', '&gt');
    content = content.replaceAll('[pre]', '<pre>');
    content = content.replaceAll('[/pre]', '</pre>');
    return content
}