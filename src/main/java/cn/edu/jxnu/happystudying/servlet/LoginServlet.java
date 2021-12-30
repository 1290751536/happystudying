package cn.edu.jxnu.happystudying.servlet;

import cn.edu.jxnu.happystudying.domain.UserDomain;
import cn.edu.jxnu.happystudying.service.UserService;
import cn.edu.jxnu.happystudying.service.impl.ImplUserService;
import cn.edu.jxnu.happystudying.util.Md5Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(name = "LoginServlet", value = "/user/login.do")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");

// 允许跨域访问的域名：若有端口需写全（协议+域名+端口），若没有端口末尾不用加'/'
        response.setHeader("Access-Control-Allow-Origin", "http://www.domain1.com");

// 允许前端带认证cookie：启用此项后，上面的域名不能为'*'，必须指定具体的域名，否则浏览器会提示
        response.setHeader("Access-Control-Allow-Credentials", "true");

// 提示OPTIONS预检时，后端需要设置的两个常用自定义头
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,X-Requested-With");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();

        String uNo = request.getParameter("uNo");
        String uPassword = request.getParameter("uPassword");
        String code = request.getParameter("code");

        if (isEmpty(code)) {
            failed("验证码不能为空", map, out, mapper);
            return;
        }
        if (!code.equalsIgnoreCase((String) request.getSession().getAttribute("code"))) {
            failed("验证码错误", map, out, mapper);
            return;
        }

        UserService userService = new ImplUserService();
        List<UserDomain> user = userService.login(uNo, Md5Utils.digest(uPassword));
        if (user == null || user.size() == 0) {
            failed("登录失败，请检查用户名或者密码是否正确", map, out, mapper);
            return;
        }

//        System.out.println(request.getParameter("remember"));
        // 由于跨域问题，自动登录暂时无法实现
        if (request.getParameter("remember").equals("true")) {
            Cookie cookieUserNo = new Cookie("uNo", uNo);
            Cookie cookieUserPassword = new Cookie("uPassword", Md5Utils.digest(uPassword));
            cookieUserNo.setMaxAge(60 * 60 * 24 * 10); // 10天免登录
            cookieUserPassword.setMaxAge(60 * 60 * 24 * 10);
            cookieUserNo.setPath("/");
            cookieUserPassword.setPath("/");
            response.addCookie(cookieUserNo);
            response.addCookie(cookieUserPassword);
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", user.get(0));
        map.put("success", true);
        out.println(mapper.writeValueAsString(map));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    boolean isEmpty(String val) {
        return val == null || val.equals("");
    }

    void failed(String errMsg, Map<String, Object> map, PrintWriter out, ObjectMapper mapper) throws JsonProcessingException {
        map.put("success", false);
        map.put("errMsg", errMsg);
        out.println(mapper.writeValueAsString(map));
    }
}
