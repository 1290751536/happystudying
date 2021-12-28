$(function () {
    updateUserInfo();
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
                flag = true;
                replaceHTML += '                    <i class="layui-badge fly-badge-vip layui-hide-xs">教师</i>'
            }
            if (msg.user.isSubDivision == '1') {
                flag = true;
                replaceHTML += '                    <i class="layui-badge fly-badge-vip layui-hide-xs">分管</i>'
            }
            if (flag == true) {
                replaceHTML += '<i class="iconfont icon-renzheng layui-hide-xs"></i>'
            }
            replaceHTML += '<img src="/front-desk-management/user/avatar/' + msg.user.uAvatar + '">'
            $('#userInfo').html(replaceHTML)
        }
    })
}