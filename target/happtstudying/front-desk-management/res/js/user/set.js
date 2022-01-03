$(function () {
    fillingInFo();
    buildSubmit();
})

function fillingInFo() {
    $.getJSON('/user/getinfo.do', function (msg) {
        var user = msg.user;
        console.log(user);
        $('#L_email').val(user.uEmail);
        $('#L_username').val(user.uName);
        $('#L_city').val(user.uAddress);
        $('#L_sign').val(user.uSignature);
        if (user.uSex == '男') {
            $(':radio').get(0).checked = true;
        } else {
            $(':radio').get(1).checked = true;
        }
        document.getElementById('img-avatar').src = '/front-desk-management/user/avatar/' + user.uAvatar;
    })
}

function buildSubmit() {
    $('#btn-info').on('click', function () {
        var data = {};
        data['uEmail'] = $('#L_email').val().trim();
        data['uName'] = $('#L_username').val().trim();
        data['uAddress'] = $('#L_city').val().trim();
        data['uSignature'] = $('#L_sign').val().trim();
        if ($('#sex-man').checked == true) {
            data['uSex'] = '男';
        } else {
            data['uSex'] = '女';
        }
        // console.log(data);

        var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"); //正则表达式
        if (data.uEmail != '' && !reg.test(data.uEmail)) { //正则验证不通过，格式不对
            layer.msg("邮箱非法哦！");
            return false;
        }

        $.getJSON('/user/updateuserinfo.do', data, function (msg) {
            if (msg.success) {
                alert('更新个人信息成功');
                location.reload();
            } else {
                layer.msg('更新个人信息失败，' + msg.errMsg);
            }
        })
    })

    $('#btn-img').on('click', function () {
        var data = new FormData();
        data.append("uAvatar", $('#user-avatar')[0].files[0]);
        // console.log(data);
        $.ajax({
            url: '/user/updateavatar.do',
            type: 'POST',
            data: data,
            contentType: false,
            processData: false,
            success: function (msg) {
                if (msg.success) {
                    alert('更新头像成功');
                    location.reload();
                } else {
                    layer.msg('更新头像失败，' + msg.errMsg);
                }
            },
            error: function () {
                layer.msg('error');
            }
        })
    })

    $('#btn-pw').on('click', function () {
        var data = {};
        data['nowPassword'] = $('#L_nowpass').val();
        data['password'] = $('#L_pass').val();
        data['rePassword'] = $('#L_repass').val();
        if (data.password != data.rePassword) {
            layer.msg('两次密码不一致，请检查后重新输入');
            return;
        }
        if (!checkPassword(data.password)) {
            layer.msg("密码长度应该在6-16位之间，并且只能包含字母数字下划线");
            return;
        }
        $.ajax({
            url: '/user/updatepassword.do',
            data: data,
            type: 'POST',
            success: function (msg) {
                if (msg.success) {
                    alert('密码更新成功，请重新登录');
                    window.location.href = "/front-desk-management/html/index.html";
                } else {
                    layer.msg("密码更新失败，" + msg.errMsg);
                }
            }
        })
    })
}

function checkPassword(pw) {
    var reg = /^\w{6,16}$/;
    return reg.test(pw);
}