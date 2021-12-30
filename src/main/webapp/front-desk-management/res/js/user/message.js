$(function () {
    getAllMessage();
    buildSubmit();
})

function getAllMessage() {
    /*等活动和博文界面完善后还需要修改*/
    $.getJSON('/user/getallmsg.do', function (msg) {
        if (msg.success) {
            var list = msg.usermsgs;
            replaceHTML = '';
            if (list.length == 0) {
                replaceHTML = '<div class="fly-none">您暂时没有最新消息</div>';
            } else {
                replaceHTML += '<ul class="mine-msg">\n';
                list.map(function (item, index) {
                    if (item.tReplyUserId == '10000000') {
                        replaceHTML += '<li data-id="' + item.mId + '">\n' +
                            '            <blockquote class="layui-elem-quote">\n' +
                            '              系统消息：' + item.mMessageDescription + '\n' +
                            '            </blockquote>\n' +
                            '            <p><span>' + timeStamptoString(item.mResponseTime) + '</span><button class="layui-btn layui-btn-small layui-btn-danger fly-delete btn-del">删除</button></p>\n' +
                            '          </li>'
                    } else {
                        replaceHTML += '<li data-id="' + item.mId + '">\n' +
                            '            <blockquote class="layui-elem-quote">\n' +
                            '              <a href="/jump?username=Absolutely" target="_blank"><cite>' + item.mReplyUserName + '</cite></a>' + item.mMessageDescription;
                        if (item.mQuestionId != null) {
                            replaceHTML += '<a target="_blank" href="/jie/8153.html/page/0/#item-1489505778669"><cite>' + item.mQuestionTitle + '</cite></a>\n';
                        } else {
                            replaceHTML += '<a target="_blank" href="/jie/8153.html/page/0/#item-1489505778669"><cite>' + item.mBlogTitle + '</cite></a>\n';
                        }

                        replaceHTML += '            </blockquote>\n' +
                            '            <p><span>' + timeStamptoString(item.mResponseTime) + '</span><button class="layui-btn layui-btn-small layui-btn-danger fly-delete btn-del">删除</button></p>\n' +
                            '          </li>'
                    }

                })

                replaceHTML += '        </ul>';
            }
            $('#LAY_minemsg').html(replaceHTML);
        }
    })
}

function buildSubmit() {
    $('body').on('click', '.btn-del', function () {
        var mId = $(this).parent().parent().attr("data-id");
        $.getJSON('/user/deletemsg.do',{"mId":mId},function (msg) {
            if(msg.success){
                alert("删除成功");
                location.reload();
            }
        })
    })
}

function timeStamptoString(time) {

    var datetime = new Date();
    datetime.setTime(time);
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1;
    var date = datetime.getDate();
    var hour = datetime.getHours();
    if (hour <= 9) {
        hour = "0" + hour;
    }
    var minute = datetime.getMinutes();
    if (minute <= 9) {
        minute = "0" + minute;
    }
    var second = datetime.getSeconds();
    if (second <= 9) {
        second = "0" + second;
    }
    var mseconds = datetime.getMilliseconds();
    return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;//+"."+mseconds;
};