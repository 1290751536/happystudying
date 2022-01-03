$(function () {
    var qId = getQueryVariable("qId");
    if (qId) {
        addViewNumber(qId);
        getQuestionInfo(qId);
        getQuestionRespInfo(qId);
        buildSubmit(qId);
    }
})

function addViewNumber(qId) {
    $.getJSON('/updatequestionviewnumber.do', {qId: qId, x: 1});
}

function getQuestionInfo(qId) {
    $.getJSON('/getquestion.do', {qId: qId}, function (msg) {
        if (msg.success) {
            var question = msg.question;
            // console.log(question);
            $('#title').html(question.qTitle);

            $('#head-icon').html('<span class="layui-btn layui-btn-xs jie-admin" id="del">删除</span>\n' +
                '\n' +
                '                        <span class="layui-btn layui-btn-xs jie-admin" id="set" field="stick" rank="1">置顶</span>\n' +
                '                        <!-- <span class="layui-btn layui-btn-xs jie-admin" type="set" field="stick" rank="0" style="background-color:#ccc;">取消置顶</span> -->\n' +
                '                    <span class="fly-list-nums">\n' +
                '            <a href="#comment"><i class="iconfont" title="回答">&#xe60c;</i> ' + question.qResponsesNumber + '</a>\n' +
                '            <i class="iconfont" title="人气">&#xe60b;</i> ' + question.qViewNumber + '');

            var iconUrl = '/front-desk-management/user/question/' + question.qIcon;
            var userIdUrl = '/front-desk-management/html/user/home.html?uId=' + question.qUserId;
            $('#author').html('<a class="fly-avatar" href="' + iconUrl + '">\n' +
                '                        <img src="' + iconUrl + '"\n' +
                '                             alt="贤心">\n' +
                '                    </a>\n' +
                '                    <div class="fly-detail-user">\n' +
                '                        <a href="' + userIdUrl + '" class="fly-link">\n' +
                '                            <cite>' + question.qUserName + '</cite>\n' +
                '                        </a>\n' +
                '                        <span>' + timeStamptoString(question.qPublishTime) + '</span>\n' +
                '                    </div>\n' +
                '                    <div class="detail-hits" id="LAY_jieAdmin" data-id="123">\n' +
                '<span style="padding-right: 10px; color: #FF7200">悬赏：' + question.qDiamondNumber + '钻石</span>' +
                '                        <span class="layui-btn layui-btn-xs jie-admin" type="edit"><a href="/front-desk-management/html/add.html?qId=' + question.qId + '">编辑此贴</a></span>\n' +
                '                    </div>');
            $('#content').html("<p>" + question.qDescription + "</p><input style='display: none' id='qId' value='" + question.qId + "' />");

        } else {
            layer.alert(msg.errMsg);
        }
    })
}


function getQuestionRespInfo(qId) {
    $.getJSON('/getquestionresp.do', {qId: qId}, function (msg) {
        if (msg.success) {
            var replaceHTML = '';
            var list = msg.questionRespDomainList;
            list.map(function (item) {
                replaceHTML += insertOneQuestionResp(item);
            })

            if (replaceHTML == '') {
                replaceHTML = '<li class="fly-none">快来消灭零回复吧</li>';
            }
            // console.log(list);
            // console.log(replaceHTML);
            $('#question-resp').html(replaceHTML);
        }
    })
}

function insertOneQuestionResp(questionResp) {
    var userImgUrl = '/front-desk-management/user/avatar/' + questionResp.rUserAvatar;
    var userIdUrl = '/front-desk-management/html/user/home.html?uId=' + questionResp.rUserId;
    var str = '';
    str += '<li data-id="' + questionResp.rId + '">\n' +
        '                        <a name="item-1111111111"></a>\n' +
        '                        <div class="detail-about detail-about-reply">\n' +
        '                            <a class="fly-avatar" href="">\n' +
        '                                <img src="' + userImgUrl + '"\n' +
        '                                     alt=" ">\n' +
        '                            </a>\n' +
        '                            <div class="fly-detail-user">\n' +
        '                                <a href="' + userIdUrl + '" class="fly-link">\n' +
        '                                    <cite>' + questionResp.rUserName + '</cite>\n' +
        '                                </a>\n' +
        '                            </div>\n' +
        '                            <div class="detail-hits">\n' +
        '                                <span>' + timeStamptoString(questionResp.rTime) + '</span>\n' +
        '                            </div>\n';
    if (questionResp.isAdopt == '1') {
        str += '<i class="iconfont icon-caina" title="最佳答案"></i>\n' +
            '                        </div>\n' +
            '                        <div class="detail-body jieda-body photos">\n' +
            '                            <p>' + questionResp.rContent + '</p>\n' +
            '                        </div>\n' +
            '                        <div class="jieda-reply">\n' +
            '              <span class="jieda-zan zanok" type="zan">\n' +
            '                <i class="iconfont icon-zan"></i>\n' +
            '                <em>66</em>\n' +
            '              </span>\n' +
            '                            <span type="reply">\n' +
            '                <i class="iconfont icon-svgmoban53"></i>\n' +
            '                回复\n' +
            '              </span>\n' +
            '                            <div class="jieda-admin">\n' +
            '                                <span type="edit">编辑</span>\n' +
            '                                <span type="del">删除</span>\n' +
            '                                <!-- <span class="jieda-accept" type="accept">采纳</span> -->\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                    </li>';
    } else {
        str += '                        </div>\n' +
            '                        <div class="detail-body jieda-body photos">\n' +
            '                            <p>' + questionResp.rContent + '</p>\n' +
            '                        </div>\n' +
            '                        <div class="jieda-reply">\n' +
            '              <span class="jieda-zan" type="zan">\n' +
            '                <i class="iconfont icon-zan"></i>\n' +
            '                <em>0</em>\n' +
            '              </span>\n' +
            '                            <span type="reply">\n' +
            '                <i class="iconfont icon-svgmoban53"></i>\n' +
            '                回复\n' +
            '              </span>\n' +
            '                            <div class="jieda-admin">\n' +
            '                                <span type="edit">编辑</span>\n' +
            '                                <span type="del">删除</span>\n' +
            '                                <span class="jieda-accept" type="accept" data-id="' + questionResp.rId + '">采纳</span>\n' +
            '                            </div>\n' +
            '                        </div>\n' +
            '                    </li>';
    }
    return str;
}

function buildSubmit() {
    $('#resp-question').on('click', function () {
        var respContent = change($('#L_content').val().trim());
        var qId = $('#qId').val().trim();
        var title = $('#title').html().trim();
        if (respContent == '') {
            layer.alert('回复内容不能为空！');
            return false;
        }
        $.getJSON('/user/creatquestionresp.do', {content: respContent, qId: qId, title: title}, function (msg) {
            if (msg.success) {
                $.getJSON('/updatequestionresponsenumber.do', {qId: qId, x: 1});
                $.getJSON('/insertquestionmsg.do', {qId: qId})
                alert('回复成功！');
                location.reload();
            } else {
                layer.alert('发布失败，' + msg.errMsg);
            }
        });
    })
}

function change(content) {
    content = content.replaceAll('<', '&lt');
    content = content.replaceAll('>', '&gt');
    content = content.replaceAll('[pre]', '<pre>');
    content = content.replaceAll('[/pre]', '</pre>');
    return content
}