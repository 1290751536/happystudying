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
                    var userUrl = '/front-desk-management/html/user/home.html?uId='+item.mUserId;
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
                            '              <a href="'+userUrl+'" target="_blank"><cite>' + item.mReplyUserName + '</cite></a>' + item.mMessageDescription;
                        if (item.mQuestionId != null) {
                            var questionUrl = '/front-desk-management/html/question/detail.html?qId=' + item.mQuestionId;
                            replaceHTML += '<a target="_blank" href="'+questionUrl+'"><cite>' + item.mQuestionTitle + '</cite></a>\n';
                        } else {
                            var activityUrl='/front-desk-management/html/activity/detail.html?aId=' + item.mBlogId;
                            replaceHTML += '<a target="_blank" href="'+activityUrl+'"><cite>' + item.mBlogTitle + '</cite></a>\n';
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