$(function () {
    getAll();


    $.getJSON('/user/getinfo.do', function (msg) {
        if (msg.user.isSubDivision == 0) {
            $('#new-activity').addClass("layui-hide");
        }
    })
})

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
        $.getJSON('/getallactivity.do', {orderByType: orderByType}, function (msg) {
            // alert(1 + ":" + rbtn);
            var list = fun1(msg.activityList, orderByType);
            paging(list);
        })
    } else if (lbtn == '进行中') {
        $.getJSON('/getallactivity.do', {orderByType: orderByType}, function (msg) {
            // alert(2 + ":" + rbtn);
            var list = fun2(msg.activityList);
            paging(list);
        })
    } else if (lbtn == '已结束') {
        $.getJSON('/getallactivity.do', {orderByType: orderByType}, function (msg) {
            // alert(3 + ":" + rbtn);
            var list = fun3(msg.activityList);
            paging(list);
        })
    } else if (lbtn == '精华') {
        $.getJSON('/getallessence.do', {orderByType: orderByType}, function (msg) {
            // alert(4 + ":" + rbtn);
            var list = msg.activityList;
            paging(list);
        })
    } else {
        layer.msg("点击错误！");
    }
}

function judgeMethod(activity, question, orderByType) {
    if (orderByType == '1') return parseInt(activity.aPublishTime) > parseInt(question.qPublishTime);
    return parseInt(activity.aViewNumber) + parseInt(activity.aResponsesNumber) > parseInt(question.qViewNumber) + parseInt(question.qResponsesNumber);
}

function fun1(activityList, orderByTpye) {
    return activityList;
}

function fun2(activityList) {
    var ret = [];
    var i = 0;
    var nowTime = (new Date).getTime();
    while (i < activityList.length) {
        if (nowTime > activityList[i].aBeginTime && nowTime < activityList[i].aEndTime) {
            ret.push(activityList[i]);
        }
        ++i;
    }
    return ret;
}

function fun3(activityList) {
    var ret = [];
    var i = 0;
    var nowTime = (new Date).getTime();
    while (i < activityList.length) {
        if (nowTime > activityList[i].aEndTime) {
            ret.push(activityList[i]);
        }
        ++i;
    }
    return ret;
}

function paging(list) {
    $('#paging').show();
    console.log(list);
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
                var replaceHTML = getPagingActivity((this.curr - 1) * this.limit, this.limit, this.count, list)
                $('#content').html(replaceHTML);
            }
        })
    })
}


function getPagingActivity(i, length, total, list) {
    var str = '';
    for (let j = 0; j < length && i + j < total; ++j) {
        str += insertActivity(list[i + j]);
    }
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