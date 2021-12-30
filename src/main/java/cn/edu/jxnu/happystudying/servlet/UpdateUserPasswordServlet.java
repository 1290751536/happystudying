package cn.edu.jxnu.happystudying.servlet;

import cn.edu.jxnu.happystudying.domain.UserDomain;
import cn.edu.jxnu.happystudying.service.UserService;
import cn.edu.jxnu.happystudying.service.impl.ImplUserService;
import cn.edu.jxnu.happystudying.util.Md5Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "UpdateUserPasswordServlet", value = "/user/updatepassword.do")
public class UpdateUserPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();

        String nowPassword = request.getParameter("nowPassword");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("rePassword");

        if (!password.equals(rePassword)) {
            failed("两次密码不一致请稍后再试", map, out, mapper);
            return;
        }
        if (password.equals(nowPassword)) {
            failed("密码和之前一致更新无效", map, out, mapper);
            return;
        }

        UserService userService = new ImplUserService();
        UserDomain userDomain = (UserDomain) request.getSession().getAttribute("user");
        userService.updeUserPassword(userDomain.getuNo(), Md5Utils.digest(password));
        request.getSession().removeAttribute("user"); //撤销登录
        map.put("success", true);
        out.println(mapper.writeValueAsString(map));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    void failed(String errMsg, Map<String, Object> map, PrintWriter out, ObjectMapper mapper) throws JsonProcessingException, JsonProcessingException {
        map.put("success", false);
        map.put("errMsg", errMsg);
        out.println(mapper.writeValueAsString(map));
    }
}
