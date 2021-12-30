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
        data['uEmail'] = $('#L_email').val();
        data['uName'] = $('#L_username').val();
        data['uAddress'] = $('#L_city').val();
        data['uSignature'] = $('#L_sign').val();
        if ($('#sex-man').checked == true) {
            data['uSex'] = '男';
        } else {
            data['uSex'] = '女';
        }
        console.log(data);
        $.getJSON('/user/updateuserinfo.do', data, function (msg) {
            if (msg.success) {
                alert('更新个人信息成功');
                location.reload();
            } else {
                alert('更新个人信息失败，' + msg.errMsg);
            }
        })
    })

    $('#btn-img').on('click', function () {
        var data = new FormData();
        data.append("uAvatar", $('#user-avatar')[0].files[0]);
        console.log(data);
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
                    alert('更新头像失败，' + msg.errMsg);
                }
            },
            error: function () {
                alert('error');
            }
        })
    })

    $('#btn-pw').on('click', function () {
        var data = {};
        data['nowPassword'] = $('#L_nowpass').val();
        data['password'] = $('#L_pass').val();
        data['rePassword'] = $('#L_repass').val();
        if(data.password != data.rePassword){
            alert('两次密码不一致，请检查后重新输入');
            return;
        }
        if(!checkPassword(data.password)){
            alert("密码长度应该在6-16位之间，并且只能包含字母数字下划线");
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
                    alert("密码更新失败，" + msg.errMsg);
                }
            }
        })
    })
}

function checkPassword(pw) {
    var reg = /^\w{6,16}$/;
    return reg.test(pw);
}