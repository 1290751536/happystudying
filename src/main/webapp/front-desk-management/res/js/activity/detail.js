$(function () {
    var aId = getQueryVariable("aId");
    if (aId) {
        addViewNumber(aId);
        getActivityInfo(aId);
        getBlogInfo(aId);
        buildSubmit();
    }

    $.getJSON('/user/getinfo.do', function (msg) {
        if (msg.user.isSubDivision == 0) {
            $('#new-activity').addClass("layui-hide");
        }
    })
})

function addViewNumber(aId) {
    $.getJSON('/updateactivityviewnumber.do', {aId: aId, x: 1});
}

function getActivityInfo(aId) {
    $.getJSON('/getactivity.do', {aId: aId}, function (msg) {
        if (msg.success) {
            var activity = msg.activity;

            $('#title').html(activity.aTitle);

            $('#head-icon').html('<span class="layui-btn layui-btn-xs jie-admin" id="del">删除</span>\n' +
                '\n' +
                '                        <span class="layui-btn layui-btn-xs jie-admin" id="set" field="stick" rank="1">置顶</span>\n' +
                '                        <!-- <span class="layui-btn layui-btn-xs jie-admin" type="set" field="stick" rank="0" style="background-color:#ccc;">取消置顶</span> -->\n' +
                '                    <span class="fly-list-nums">\n' +
                '            <a href="#comment"><i class="iconfont" title="回答">&#xe60c;</i> ' + activity.aResponsesNumber + '</a>\n' +
                '            <i class="iconfont" title="人气">&#xe60b;</i> ' + activity.aViewNumber + '');

            var iconUrl = '/front-desk-management/user/activity/' + activity.aIcon;
            var userIdUrl = '/front-desk-management/html/user/home.html?uId=' + activity.aUserId;
            $('#author').html('<a class="fly-avatar" href="' + iconUrl + '">\n' +
                '                        <img src="' + iconUrl + '"\n' +
                '                             alt="贤心">\n' +
                '                    </a>\n' +
                '                    <div class="fly-detail-user">\n' +
                '                        <a href="' + userIdUrl + '" class="fly-link">\n' +
                '                            <cite>' + activity.aUserName + '</cite>\n' +
                '                        </a>\n' +
                '                        <span>' + timeStamptoString(activity.aPublishTime) + '</span>\n' +
                '                    </div>\n' +
                '                    <div class="detail-hits" id="LAY_jieAdmin" data-id="123">\n' +
                '                        <span class="layui-btn layui-btn-xs jie-admin" type="edit"><a href="/front-desk-management/html/add.html?aId=' + activity.aId + '">编辑此贴</a></span>\n' +
                '                    </div>')

            $('#content').html("<p>" + activity.aDescription + "</p><input style='display: none' id='aId' value='" + activity.aId + "' />");
        } else {
            layer.alert(msg.errMsg);
        }
    })
}

function getBlogInfo(aId) {
    $.getJSON('/getresponseblog.do', {aId, aId}, function (msg) {
        if (msg.success) {
            var replaceHTML = '';
            var list = msg.blogDomainList;
            list.map(function (item) {
                replaceHTML += insertOneResponseBlog(item);
            })
            if (replaceHTML == '') {
                replaceHTML = '<li><p style="color: #01AAED">暂时没有用户发送过博文哦~</p></li>';
            }
            $('#response-blog').html(replaceHTML);
        }
    })
}

function insertOneResponseBlog(blog) {
    var blogIdUrl = '/front-desk-management/html/blog/detail.html?bId=' + blog.bId;
    var iconUrl = '/front-desk-management/user/blog/' + blog.bIcon;
    var userIdUrl = '/front-desk-management/html/user/home.html?uId=' + blog.bUserId;

    var str = '';
    str += '<li>\n' +
        '                        <a href="' + blogIdUrl + '" class="fly-avatar">\n' +
        '                            <img src="' + iconUrl + '"\n' +
        '                                 alt="贤心">\n' +
        '                        </a>\n' +
        '                        <h2>\n' +
        '                            <a class="layui-badge">博文</a>\n' +
        '                            <a href="' + blogIdUrl + '">' + blog.bTitle + '</a>\n' +
        '                        </h2>\n' +
        '                        <div class="fly-list-info">\n' +
        '                            <a href="' + userIdUrl + '" link>\n' +
        '                                <cite>' + blog.bUserName + '</cite>\n' +
        '                                <!--\n' +
        '                                <i class="iconfont icon-renzheng" title="认证信息：XXX"></i>\n' +
        '                                <i class="layui-badge fly-badge-vip">VIP3</i>\n' +
        '                                -->\n' +
        '                            </a>\n' +
        '                            <span>' + timeStamptoString(blog.bPublishTime) + '</span>\n' +
        '\n' +
        '                            <span class="fly-list-nums"><i class="iconfont icon-pinglun1" title="回答"></i> ' + blog.bResponsesNumber + '</span>\n' +
        '                        </div>\n' +
        '                        <div class="fly-list-badge">\n';
    if (blog.isEssence == '1') {
        str += '                            <span class="layui-badge layui-bg-red">精帖</span>\n';
    }

    str += '                        </div>\n' +
        '                    </li>';
    return str;
}

function buildSubmit() {
    $('#submit-blog').on('click', function () {
        var blogContent = $('#L_content').val().trim();
        var aId = $('#aId').val().trim();
        console.log(blogContent);
        if (blogContent == '') {
            layer.alert('博文内容不能为空！');
            return false;
        }
        $.getJSON('/user/creatblog.do', {content: blogContent, aId: aId}, function (msg) {
            if (msg.success) {
                $.getJSON('/updateactivityresponsenumber.do', {aId: aId, x: 1});
                alert('发布博文成功！');
                location.reload();
            } else {
                layer.alert('发布失败，' + msg.errMsg);
            }
        });
    })
}