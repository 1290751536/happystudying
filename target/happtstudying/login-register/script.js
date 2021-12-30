$(function () {
    // 获取元素和点击操作
    const signInBtn = document.getElementById("signIn");
    const signUpBtn = document.getElementById("signUp");
    const fistForm = document.getElementById("form1");
    const secondForm = document.getElementById("form2");
    const container = document.querySelector(".container");

    signInBtn.addEventListener("click", () => {
        container.classList.remove("right-panel-active");
    });

    signUpBtn.addEventListener("click", () => {
        container.classList.add("right-panel-active");
    });

    fistForm.addEventListener("submit", (e) => e.preventDefault());
    secondForm.addEventListener("submit", (e) => e.preventDefault());

    $('#login').on('click', function () {
        var data = {};
        data['uNo'] = $('#login-userid').val();
        data['uPassword'] = $('#login-password').val();
        data['code'] = $('#login-code').val();
        data['remember'] = $('#remember').is(':checked');
        var url = "/user/login.do";
        $.ajax({
            url: url,
            data: data,
            type: 'POST',
            dataType: 'json',
            success: function (msg) {
                console.log(msg);
                // console.log($.cookie('uNo'))
                // alert('success');
                if (msg.success) {
                    // alert("登录成功");
                    window.location.href = "/front-desk-management/html/index.html";
                } else {
                    alert(msg.errMsg);
                    location.reload();
                }
            },
            error: function (msg) {
                console.log(msg)
                // if (msg.success) {
                //     // alert("登录成功");
                //     window.location.href = "/front-desk-management/html/index.html";
                // } else {
                //     alert(msg.errMsg);
                //     location.reload();
                // }
            }
        })
    })

    $('#register').on('click', function () {
        var data = {};
        if ($('#register-password').val() != $('#register-repassword').val()) {
            alert('两次密码不一致，请重新输入');
            return;
        }
        data['uNo'] = $('#register-userid').val();
        data['uPassword'] = $('#register-password').val();
        var url = "/user/register.do";
        $.ajax({
            url: url,
            data: data,
            type: 'POST',
            success: function (msg) {
                if (msg.success) {
                    alert("注册成功，正在前往登录界面");
                    location.reload();
                } else {
                    alert('注册失败：' + msg.errMsg);
                }
            }
        })
    })
})