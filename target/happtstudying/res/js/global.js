$(function () {
    updateUserInfo();
    logOut();
})

function updateUserInfo() {
    $.ajax({
        url: "/user/getinfo.do",
        type: "GET",
        dataType: "json",
        success: function (msg) {
            var replaceHTML = '';
            replaceHTML += '<cite class="layui-hide-xs">' + msg.user.uName + '</cite>\n';
            var flag = false;
            if (msg.user.isPost == '1') {
                replaceHTML += '<i class="layui-badge fly-badge-vip layui-hide-xs">教师</i>'
            }
            if (msg.user.isSubDivision == '1') {
                flag = true;
                replaceHTML += '<i class="layui-badge fly-badge-vip layui-hide-xs">分管</i>'
            }
            if (flag == true) {
                replaceHTML += '<i class="iconfont icon-renzheng layui-hide-xs" title="分管认证"></i>'
            }
            replaceHTML += '<img src="/front-desk-management/user/avatar/' + msg.user.uAvatar + '">'
            $('#userInfo').html(replaceHTML)
        }
    })
}

function logOut() {
    $('#logout').on('click', function () {
        $.ajax({
            url: '/user/logout.do',
            type: 'GET',
            success: function (msg) {
                // alert('登出成功');
                window.location.href = '/login-register/index.html';
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
}

function getQueryVariable(variable) {
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        if (pair[0] == variable) {
            return pair[1];
        }
    }
    return (false);
}
