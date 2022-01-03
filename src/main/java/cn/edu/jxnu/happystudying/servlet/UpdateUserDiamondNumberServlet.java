package cn.edu.jxnu.happystudying.servlet;

import cn.edu.jxnu.happystudying.domain.UserDomain;
import cn.edu.jxnu.happystudying.service.UserService;
import cn.edu.jxnu.happystudying.service.impl.ImplUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateUserDiamondNumberServlet", value = "/user/updateuserdiamondnumber.do")
public class UpdateUserDiamondNumberServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new ImplUserService();
        UserDomain userDomain = (UserDomain) request.getSession().getAttribute("user");
        userService.updateUserDiamondNumber(userDomain.getuNo(), request.getParameter("remainDiamondNumber"));
        userDomain.setuDiamondNumber(request.getParameter("remainDiamondNumber"));
        request.getSession().setAttribute("user", userDomain);
    }
}
