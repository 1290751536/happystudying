package cn.edu.jxnu.happystudying.servlet;

import cn.edu.jxnu.happystudying.domain.UserDomain;
import cn.edu.jxnu.happystudying.service.UserService;
import cn.edu.jxnu.happystudying.service.impl.ImplUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Service;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "UpdateUserInfoServlet", value = "/user/updateuserinfo.do")
public class UpdateUserInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();

        UserDomain user = (UserDomain) request.getSession().getAttribute("user");
        String uNo = user.getuNo();
        String uEmail = request.getParameter("uEmail");
        String uName = request.getParameter("uName");
        String uAddress = request.getParameter("uAddress");
        String uSignature = request.getParameter("uSignature");
        String uSex = request.getParameter("uSex");

        UserService userService = new ImplUserService();
        if (uEmail != null) {
            userService.updateUserEmailByNo(uNo, uEmail);
            user.setuEmail(uEmail);
        }
        if (!isEmpty(uName)) {
            userService.updateUserNameByNo(uNo, uName);
            user.setuName(uName);
        } else {
            failed("名称不能为空", map, out, mapper);
            return;
        }
        if (!isEmpty(uAddress)) {
            userService.updateUserAddressByNo(uNo, uAddress);
            user.setuAddress(uAddress);
        } else {
            failed("地区不能为空", map, out, mapper);
            return;
        }
        if (!isEmpty(uSex)) {
            userService.setSex(uNo, uSex);
            user.setuSex(uSex);
        } else {
            failed("性别不能为空", map, out, mapper);
            return;
        }
        if (uSignature != null) {
            userService.updateUserSignatureByNo(uNo, uSignature);
            user.setuSignature(uSignature);
        }

        request.getSession().setAttribute("user", user);
        map.put("success", true);
        out.println(mapper.writeValueAsString(map));
    }

    boolean isEmpty(String str) {
        return str == null || str.equals("");
    }

    void failed(String errMsg, Map<String, Object> map, PrintWriter out, ObjectMapper mapper) throws JsonProcessingException {
        map.put("success", false);
        map.put("errMsg", errMsg);
        out.println(mapper.writeValueAsString(map));
    }
}
