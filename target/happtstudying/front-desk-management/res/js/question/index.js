$(function () {
    getAll();
})


function getAll() {
    $('.btn1').on('click', function () {
        if ($(this).hasClass('layui-this')) {
            $(this).removeClass('layui-this');
        } else {
            $(this).addClass('layui-this');
        }
        changeDiv();
    })

    $('.btn2').on('click', function () {
        $('#rDiv').find('.layui-this').removeClass('layui-this');
        $(this).addClass('layui-this');
        // console.log($('#rDiv').find('.layui-this').html());
        changeDiv();
    })
    $('#rDiv').find('.layui-this').click();
}

function changeDiv() {
    var lbtn = $('#lDiv').find('.layui-this:first').html().trim();
    var rbtn = $('#rDiv').find('.layui-this').html().trim();

    var orderByType = rbtn == '按最新' ? "1" : "2";
    console.log(lbtn);
    if (lbtn == '精华') {
        $.getJSON('/getallessence.do', {orderByType: orderByType}, function (msg) {
            // alert(4 + ":" + rbtn);
            var list = msg.questionList;
            paging(list);
        })

    } else {
        $.getJSON('/getall.do', {orderByType: orderByType}, function (msg) {
            var list = msg.questionList;
            paging(list);
        })
    }
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
                var replaceHTML = getPagingQuestion((this.curr - 1) * this.limit, this.limit, this.count, list)
                $('#content').html(replaceHTML);
            }
        })
    })
}


function getPagingQuestion(i, length, total, list) {
    var str = '';
    for (let j = 0; j < length && i + j < total; ++j) {
        str += insertQuestion(list[i + j]);
    }
    return str;
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