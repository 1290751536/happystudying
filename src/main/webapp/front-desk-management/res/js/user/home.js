$(function () {
    var uId = getQueryVariable("uId");
    if (uId != false) {
        otherUserDetail(uId);
        getOtherUserQuestion(uId);
        getOtherUserQuestionResp(uId);
        return;
    }
    userDetail();
    getUserQuestion();
    getUserQuestionResp();
})

function userDetail() {
    $.getJSON('/user/getinfo.do', function (msg) {
        var replaceHTML = '';
        var user = msg.user;
        replaceHTML += '<img src="/front-desk-management/user/avatar/' + user.uAvatar + '" alt="贤心">';
        if (user.isSubDivision == '1') {
            replaceHTML += '<i class="iconfont icon-renzheng" title="分管认证"></i>';
        }
        replaceHTML += '<h1>\n' +
            '    ' + user.uName + '\n';

        if (user.uSex == '男') {
            replaceHTML += ' <i class="iconfont icon-nan"></i>';
        } else {
            replaceHTML += '<i class="iconfont icon-nv"></i> '
        }
        replaceHTML += '  </h1>';

        if (user.isPost == '1' && user.isSubDivision == '1') {
            replaceHTML += '<p style="padding: 10px 0; color: #5FB878;">认证信息：教师、分管</p>';
        } else if (user.isPost == '1') {
            replaceHTML += '<p style="padding: 10px 0; color: #5FB878;">认证信息：教师</p>'
        } else if (user.isSubDivision == '1') {
            replaceHTML += '<p style="padding: 10px 0; color: #5FB878;">认证信息：分管</p>'
        } else {
            replaceHTML += '<p style="padding: 10px 0; color: #5FB878;">认证信息：学生</p>'
        }
        replaceHTML += '<p class="fly-home-info">\n' +
            '    <span class="fly-list-fire layui-hide-xs" title="悬赏钻石"><i class="layui-icon layui-icon-fire\n' +
            '" style="font-size: 15px; color: red;">&#xe735;</i>  ' + user.uDiamondNumber + '钻石</span>\n' +
            '    <i class="iconfont icon-shijian"></i><span>' + timeStamptoString(user.uRegisterTime) + ' 加入</span>\n' +
            '    <i class="iconfont icon-chengshi"></i><span>来自' + user.uAddress + '</span>\n' +
            '  </p>\n' +
            '\n' +
            '  <p class="fly-home-sign">（' + user.uSignature + '）</p>'
        $('#user-detail').html(replaceHTML);

        $('#user-name-question').html(user.uName + ' 最近的提问')
        $('#user-name-questionresp').html(user.uName + ' 最近的回答')
    })
}

function otherUserDetail(uId) {
    $.getJSON('/user/getinfo.do', {uId, uId}, function (msg) {
        if (msg.success) {
            var replaceHTML = '';
            var user = msg.user;
            replaceHTML += '<img src="/front-desk-management/user/avatar/' + user.uAvatar + '" alt="贤心">';
            if (user.isSubDivision == '1') {
                replaceHTML += '<i class="iconfont icon-renzheng" title="分管认证"></i>';
            }
            replaceHTML += '<h1>\n' +
                '    ' + user.uName + '\n';

            if (user.uSex == '男') {
                replaceHTML += ' <i class="iconfont icon-nan"></i>';
            } else {
                replaceHTML += '<i class="iconfont icon-nv"></i> '
            }
            replaceHTML += '  </h1>';

            if (user.isPost == '1' && user.isSubDivision == '1') {
                replaceHTML += '<p style="padding: 10px 0; color: #5FB878;">认证信息：教师、分管</p>';
            } else if (user.isPost == '1') {
                replaceHTML += '<p style="padding: 10px 0; color: #5FB878;">认证信息：教师</p>'
            } else if (user.isSubDivision == '1') {
                replaceHTML += '<p style="padding: 10px 0; color: #5FB878;">认证信息：分管</p>'
            } else {
                replaceHTML += '<p style="padding: 10px 0; color: #5FB878;">认证信息：学生</p>'
            }
            replaceHTML += '<p class="fly-home-info">\n' +
                '    <span class="fly-list-fire layui-hide-xs" title="悬赏钻石"><i class="layui-icon layui-icon-fire\n' +
                '" style="font-size: 15px; color: red;">&#xe735;</i>  ' + user.uDiamondNumber + '钻石</span>\n' +
                '    <i class="iconfont icon-shijian"></i><span>' + timeStamptoString(user.uRegisterTime) + ' 加入</span>\n' +
                '    <i class="iconfont icon-chengshi"></i><span>来自' + user.uAddress + '</span>\n' +
                '  </p>\n' +
                '\n' +
                '  <p class="fly-home-sign">（' + user.uSignature + '）</p>'
            $('#user-detail').html(replaceHTML);

            $('#user-name-question').html(user.uName + ' 最近的提问')
            $('#user-name-questionresp').html(user.uName + ' 最近的回答')
        } else {
            alert(msg.errMsg);
            window.location.href = "/front-desk-management/html/user/home.html";
        }
    })
}

function getUserQuestion() {
    $.getJSON('/user/getallquestion.do', function (msg) {
        if (msg.success) {
            var replaceHTML = '';
            msg.questionDomainList.map(function (item) {
                var questionIdUrl = '/front-desk-management/html/question/detail.html?qId=' + item.qId;
                replaceHTML += '<li>\n';
                if (item.isEssence == '1') {
                    replaceHTML += '            <span class="fly-jing">精</span>\n';
                }
                replaceHTML += '            <a href="' + questionIdUrl + '" class="jie-title"> ' + item.qTitle + '</a>\n' +
                    '            <i>' + timeStamptoString(item.qPublishTime) + '</i>\n' +
                    '            <em class="layui-hide-xs">' + item.qViewNumber + '阅   /  ' + item.qResponsesNumber + '答</em>\n' +
                    '          </li>'
            })
            if (replaceHTML == '') {
                replaceHTML = '<div class="fly-none" style="min-height: 50px; padding:30px 0; height:auto;"><i style="font-size:14px;">没有发表任何求解</i></div>';
            }
            $('#user-question').html(replaceHTML);
        }
    })
}

function getUserQuestionResp() {
    $.getJSON('/user/getallquestionresp.do', function (msg) {
        if (msg.success) {
            var replaceHTML = '';
            msg.questionRespDomainList.map(function (item) {
                var questionIdUrl = '/front-desk-management/html/question/detail.html?qId=' + item.rQuestionId;
                replaceHTML += '          <li>\n' +
                    '          <p>\n' +
                    '          <span>' + timeStamptoString(item.rTime) + '</span>\n' +
                    '          在<a href="' + questionIdUrl + '" target="_blank">' + item.rQuestionTitle + '</a>中回答：\n' +
                    '          </p>\n' +
                    '          <div class="home-dacontent">\n' + item.rContent +
                    '          </div>\n' +
                    '        </li>';
            })
            if (replaceHTML == '') {
                replaceHTML = '<div class="fly-none" style="min-height: 50px; padding:30px 0; height:auto;"><span>没有回答任何问题</span></div>';
            }
            $('#user-questionresp').html(replaceHTML);
        }
    })
}

function getOtherUserQuestion(uId) {
    $.getJSON('/user/getallquestion.do', {uId: uId}, function (msg) {
        if (msg.success) {
            var replaceHTML = '';
            msg.questionDomainList.map(function (item) {
                var questionIdUrl = '/front-desk-management/html/question/detail.html?qId=' + item.qId;
                replaceHTML += '<li>\n';
                if (item.isEssence == '1') {
                    replaceHTML += '            <span class="fly-jing">精</span>\n';
                }
                replaceHTML += '            <a href="' + questionIdUrl + '" class="jie-title"> ' + item.qTitle + '</a>\n' +
                    '            <i>' + timeStamptoString(item.qPublishTime) + '</i>\n' +
                    '            <em class="layui-hide-xs">' + item.qViewNumber + '阅   /  ' + item.qResponsesNumber + '答</em>\n' +
                    '          </li>'
            })
            if (replaceHTML == '') {
                replaceHTML = '<div class="fly-none" style="min-height: 50px; padding:30px 0; height:auto;"><i style="font-size:14px;">没有发表任何求解</i></div>';
            }
            $('#user-question').html(replaceHTML);
        }
    })
}

function getOtherUserQuestionResp(uId) {
    $.getJSON('/user/getallquestionresp.do', {uId: uId}, function (msg) {
            if (msg.success) {
                var replaceHTML = '';
                msg.questionRespDomainList.map(function (item) {
                    var questionIdUrl = '/front-desk-management/html/question/detail.html?qId=' + item.rQuestionId;
                    replaceHTML += '          <li>\n' +
                        '          <p>\n' +
                        '          <span>' + timeStamptoString(item.rTime) + '</span>\n' +
                        '          在<a href="' + questionIdUrl + '" target="_blank">' + item.rQuestionTitle + '</a>中回答：\n' +
                        '          </p>\n' +
                        '          <div class="home-dacontent">\n' + item.rContent +
                        '          </div>\n' +
                        '        </li>';
                })

                if (replaceHTML == '') {
                    replaceHTML = '<div class="fly-none" style="min-height: 50px; padding:30px 0; height:auto;"><span>没有回答任何问题</span></div>';
                }
                $('#user-questionresp').html(replaceHTML);
            }
        }
    )
}


function timeStamptoString(time) {

    var datetime = new Date();
    datetime.setTime(time);
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1;
    var date = datetime.getDate();
    // var hour = datetime.getHours();
    // if(hour<=9){
    //     hour="0"+hour;
    // }
    // var minute = datetime.getMinutes();
    // if(minute<=9){
    //     minute="0"+minute;
    // }
    // var second = datetime.getSeconds();
    // if(second<=9){
    //     second="0"+second;
    // }
    // var mseconds = datetime.getMilliseconds();
    return year + "-" + month + "-" + date;//+" "+hour+":"+minute+":"+second;//+"."+mseconds;
}