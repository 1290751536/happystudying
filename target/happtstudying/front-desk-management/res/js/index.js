$(function () {
    getTop(); // 更新置顶板块

    getAll(); // 更新综合板块

    getCheckIn(); // 更新签到板块
})

function getTop() {
    $.getJSON('/getTop.do', function (msg) {
        var replaceHTML = insertActivityAndQuestion(msg.activityList, msg.questionList);
        $('#top').html(replaceHTML);
    })
}

function getAll() {
    $('.btn1').on('click', function () {
        $('#lDiv').find('.layui-this:first').removeClass('layui-this');
        $(this).addClass('layui-this');
        changeDiv();
    })

    $('.btn2').on('click', function () {
        $('#rDiv').find('.layui-this').removeClass('layui-this');
        $(this).addClass('layui-this');
        // console.log($('#rDiv').find('.layui-this').html());
        changeDiv();
    })
    $('#lDiv').find('.layui-this:first').click();
}

function changeDiv() {
    var lbtn = $('#lDiv').find('.layui-this:first').html().trim();
    var rbtn = $('#rDiv').find('.layui-this').html().trim();

    var orderByType = rbtn == '按最新' ? "1" : "2";
    if (lbtn == '综合') {
        $.getJSON('/getall.do', {orderByType: orderByType}, function (msg) {
            var replaceHTML = '';
            // alert(1 + ":" + rbtn);
            replaceHTML = fun1(msg.activityList, msg.questionList, orderByType);
            $('#all').html(replaceHTML);
        })
    } else if (lbtn == '进行中') {
        $.getJSON('/getallactivity.do', {orderByType: orderByType}, function (msg) {
            var replaceHTML = '';
            // alert(2 + ":" + rbtn);
            replaceHTML = fun2(msg.activityList);
            $('#all').html(replaceHTML);
        })
    } else if (lbtn == '已结束') {
        $.getJSON('/getallactivity.do', {orderByType: orderByType}, function (msg) {
            var replaceHTML = '';
            // alert(3 + ":" + rbtn);
            replaceHTML = fun3(msg.activityList);
            $('#all').html(replaceHTML);
        })
    } else if (lbtn == '精华') {
        $.getJSON('/getallessence.do', {orderByType: orderByType}, function (msg) {
            var replaceHTML = '';
            // alert(4 + ":" + rbtn);
            replaceHTML = fun1(msg.activityList, msg.questionList, orderByType);
            $('#all').html(replaceHTML);
        })
    } else {
        layer.alert("点击错误！");
    }
}

function insertActivityAndQuestion(activityList, questionList) {
    var replaceHTML = '';
    var i = 0, j = 0;
    while (i < activityList.length || j < questionList.length) {
        if (i == activityList.length) {
            replaceHTML += insertQuestion(questionList[j++]);
        } else if (j == questionList.length) {
            replaceHTML += insertActivity(activityList[i++]);
        } else if (parseInt(activityList[i].aPublishTime) > parseInt(questionList[j].qPublishTime)) {
            replaceHTML += insertActivity(activityList[i++]);
        } else {
            replaceHTML += insertQuestion(questionList[j++]);
        }
    }
    return replaceHTML;
}

function judgeMethod(activity, question, orderByType) {
    if (orderByType == '1') return parseInt(activity.aPublishTime) > parseInt(question.qPublishTime);
    return parseInt(activity.aViewNumber) + parseInt(activity.aResponsesNumber) > parseInt(question.qViewNumber) + parseInt(question.qResponsesNumber);
}

function fun1(activityList, questionList, orderByTpye) {
    var replaceHTML = '';
    var i = 0, j = 0;
    var cnt = 0;
    while ((i < activityList.length || j < questionList.length) && cnt < 15) {
        if (i == activityList.length) {
            replaceHTML += insertQuestion(questionList[j++]);
        } else if (j == questionList.length) {
            replaceHTML += insertActivity(activityList[i++]);
        } else if (judgeMethod(activityList[i], questionList[j], orderByTpye)) {
            replaceHTML += insertActivity(activityList[i++]);
        } else {
            replaceHTML += insertQuestion(questionList[j++]);
        }
        ++cnt;
    }
    return replaceHTML;
}

function fun2(activityList) {
    var replaceHTML = '';
    var i = 0;
    var cnt = 0;
    var nowTime = (new Date).getTime();
    while (i < activityList.length && cnt < 15) {
        if (nowTime > activityList[i].aBeginTime && nowTime < activityList[i].aEndTime) {
            replaceHTML += insertActivity(activityList[i]);
            ++cnt;
        }
        ++i;
    }
    return replaceHTML;
}

function fun3(activityList) {
    var replaceHTML = '';
    var i = 0;
    var cnt = 0;
    var nowTime = (new Date).getTime();
    while (i < activityList.length && cnt < 15) {
        if (nowTime > activityList[i].aEndTime) {
            replaceHTML += insertActivity(activityList[i]);
            ++cnt;
        }
        ++i;
    }
    return replaceHTML;
}

function insertActivity(activity) {
    var str = '';
    var activityIdUrl = '/front-desk-management/html/activity/detail.html?aId=' + activity.aId;
    var iconUrl = '/front-desk-management/user/activity/' + activity.aIcon;
    var userIdUrl = '/front-desk-management/html/user/home.html?uId=' + activity.aUserId;
    str = '<li>\n' +
        '                        <a href="' + activityIdUrl + '" class="fly-avatar" target="_blank">\n' +
        '                            <img src="' + iconUrl + '"\n' +
        '                                 alt="贤心">\n' +
        '                        </a>\n' +
        '                        <h2>\n' +
        '                            <a class="layui-badge">活动</a>\n' +
        '                            <a href="' + activityIdUrl + '" target="_blank">' + activity.aTitle + '</a>\n' +
        '                        </h2>\n' +
        '                        <div class="fly-list-info">\n' +
        '                            <a href="' + userIdUrl + '" link target="_blank">\n' +
        '                                <cite>' + activity.aUserName + '</cite>\n' +
        '                                <!--                            黄V图标    <i class="iconfont icon-renzheng" title="认证信息：XXX"></i>-->\n' +
        '                                <!--                                <i class="layui-badge fly-badge-vip">VIP3</i>-->\n' +
        '                            </a>\n' +
        '                            <span>' + timeStamptoString(activity.aPublishTime) + '</span>\n' +
        '\n' + checkTime((new Date()).getTime(), activity.aBeginTime, activity.aEndTime) +
        '                            <span class="fly-list-nums"><i class="iconfont icon-pinglun1" title="回答"></i> ' + activity.aResponsesNumber + '</span>\n' +
        '                        </div>\n' +
        '                        <div class="fly-list-badge">\n';
    if (activity.isSticky == '1') {
        str += '                            <span class="layui-badge layui-bg-black">置顶</span>\n';
    }
    if (activity.isEssence == '1') {
        str += '<span class="layui-badge layui-bg-red">精帖</span>\n';
    }
    str += '                        </div>\n' +
        '                    </li>';
    return str;
}

function checkTime(now, begin, end) {
    if (now < begin) return "";
    if (now < end) return '<span class="layui-badge fly-badge-accept layui-hide-xs">进行中</span>\n';
    if (now > end) return '<span class="layui-badge fly-badge-accept layui-hide-xs">已结束</span>\n';
}

function insertQuestion(question) {
    var str = '';
    var questionIdUrl = '/front-desk-management/html/question/detail.html?qId=' + question.qId;
    var iconUrl = '/front-desk-management/user/question/' + question.qIcon;
    var userIdUrl = '/front-desk-management/html/user/home.html?uId=' + question.qUserId;

    str += '<li>\n' +
        '                        <a href="' + questionIdUrl + '" class="fly-avatar" target="_blank">\n' +
        '                            <img src="' + iconUrl + '"\n' +
        '                                 alt="贤心">\n' +
        '                        </a>\n' +
        '                        <h2>\n' +
        '                            <a class="layui-badge">问答</a>\n' +
        '                            <a href="' + questionIdUrl + '" target="_blank">' + question.qTitle + '</a>\n' +
        '                        </h2>\n' +
        '                        <div class="fly-list-info">\n' +
        '                            <a href="' + userIdUrl + '" link target="_blank">\n' +
        '                                <cite>' + question.qUserName + '</cite>\n' +
        '                                <!--\n' +
        '                                <i class="iconfont icon-renzheng" title="认证信息：XXX"></i>\n' +
        '                                <i class="layui-badge fly-badge-vip">VIP3</i>\n' +
        '                                -->\n' +
        '                            </a>\n' +
        '                            <span>' + timeStamptoString(question.qPublishTime) + '</span>\n' +
        '\n' + '<span class="fly-list-fire layui-hide-xs" title="悬赏钻石"><i class="layui-icon layui-icon-fire\n' +
        '" style="font-size: 15px; color: red;">&#xe735;</i>  ' + question.qDiamondNumber + '</span>' +
        '                            <!--<span class="layui-badge fly-badge-accept layui-hide-xs">已结</span>-->\n' +
        '                            <span class="fly-list-nums"><i class="iconfont icon-pinglun1" title="回答"></i> ' + question.qResponsesNumber + '</span>\n' +
        '                        </div>\n' +
        '                        <div class="fly-list-badge">\n';
    if (question.isSticky == '1') {
        str += '                            <span class="layui-badge layui-bg-black">置顶</span>\n';
    }
    if (question.isEssence == '1') {
        str += '<span class="layui-badge layui-bg-red">精帖</span>\n';
    }
    str += '                        </div>\n' +
        '                    </li>'

    return str;
}

function getCheckIn() {
    $.getJSON('/user/getcheckininfo.do', function (msg) {
        var day = parseInt(msg.day);
        var diamondNumber = parseInt(day / 5 + 1) * 5;
        $('#day').html(day);
        $('#diamond-number').html(diamondNumber);
        if (msg.todayIsCheckIn == false) {
            $('#LAY_signin').removeClass('layui-btn-disabled');
        }
    });
    $('#LAY_signin').on('click', function () {
        if ($(this).hasClass('layui-btn-disabled')) return;

        var data = {};
        data['diamondNumber'] = $('#diamond-number').html().trim();

        $.getJSON('/user/checkin.do', data)

        $(this).addClass('layui-btn-disabled');
    })
}