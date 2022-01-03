$(function () {
    $.ajaxSettings.async = false
    $.getJSON('/user/search.do?key=' + encodeURI(getQueryVariable('q')), function (msg) {
        list = msg.activityDomainList.concat(msg.questionDomainList);
        paging(list);
    })
})

function paging(list) {
    $('#paging').show();
    console.log(list[0]);
    if (list.length == 0) {
        layer.alert('很抱歉没有该类数据');
        $('#content').html('');
        $('#paging').hide();
        return;
    }
    layui.use('laypage', function () {
        var laypage = layui.laypage;
        laypage.render({
            elem: 'paging',
            count: list.length,
            limit: 10,
            curr: 1,
            theme: '#009966',
            jump: function (obj, first) {
                // alert(this.curr);
                var replaceHTML = getPaging((this.curr - 1) * this.limit, this.limit, this.count, list)
                $('#content').html(replaceHTML);
            }
        })
    })
}

function getPaging(i, length, total, list) {
    var str = '';
    for (let j = 0; j < length && i + j < total; ++j) {
        if (list[i + j].aId != undefined) {
            str += insertActivity(list[i + j]);
        } else {
            str += insertQuestion(list[i + j]);
        }
    }
    return str;
}

function insertQuestion(question) {
    var str = '';
    var questionIdUrl = '/front-desk-management/html/question/detail.html?qId=' + question.qId;
    var iconUrl = '/front-desk-management/user/activity/' + question.qIcon;
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