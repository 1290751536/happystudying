package cn.edu.jxnu.happystudying.servlet;

import cn.edu.jxnu.happystudying.domain.UserDomain;
import cn.edu.jxnu.happystudying.service.CheckInService;
import cn.edu.jxnu.happystudying.service.UserService;
import cn.edu.jxnu.happystudying.service.impl.ImplCheckInService;
import cn.edu.jxnu.happystudying.service.impl.ImplUserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "CheckInServlet", value = "/user/checkin.do")
public class CheckInServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();

        UserDomain userDomain = (UserDomain) request.getSession().getAttribute("user");
        CheckInService checkInService = new ImplCheckInService();
        checkInService.updateTodayData(userDomain.getuId(), new Date());

        String addDiamondNumber = request.getParameter("diamondNumber");
        userDomain.setuDiamondNumber(String.valueOf(Integer.parseInt(userDomain.getuDiamondNumber()) + Integer.parseInt(addDiamondNumber)));
        request.getSession().setAttribute("user", userDomain);

        UserService userService = new ImplUserService();
        userService.updateUserDiamondNumber(userDomain.getuNo(), userDomain.getuDiamondNumber());
//        System.out.println(userDomain);
    }
}
