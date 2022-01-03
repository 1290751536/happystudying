package cn.edu.jxnu.happystudying.servlet;

import cn.edu.jxnu.happystudying.domain.UserDomain;
import cn.edu.jxnu.happystudying.service.UserService;
import cn.edu.jxnu.happystudying.service.impl.ImplUserService;
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

@WebServlet(name = "GetUserInfoServlet", value = "/user/getinfo.do")
public class GetUserInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();

        String uId = request.getParameter("uId");
        UserDomain user = null;

        if (uId == null || uId.equals("")) {
            user = (UserDomain) request.getSession().getAttribute("user");
        } else {
            UserService userService = new ImplUserService();
            try {
                user = userService.queryUserById(uId).get(0);
            } catch (Exception e) {
                map.put("success", false);
                map.put("errMsg", "该用户不存在请重新尝试");
                return;
            }

        }


        map.put("success", true);
        map.put("user", user);
        out.println(mapper.writeValueAsString(map));
    }
}
