package cn.edu.jxnu.happystudying.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LogOutServlet", value = "/user/logout.do")
public class LogOutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("user");

        // 通过持续时间为0删除Cookie
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            String uNo = null, uPassword = null;
            for (Cookie cookie : cookies) {
                if ("uNo".equals(cookie.getName()))
                    uNo = cookie.getValue();
                if ("uPassword".equals(cookie.getName()))
                    uPassword = cookie.getValue();
            }
            if (uNo != null && uPassword != null) {
                Cookie cookieId = new Cookie("uNo", null);
                cookieId.setMaxAge(0);
                cookieId.setPath("/");
                Cookie cookiePassword = new Cookie("uPassword", null);
                cookiePassword.setMaxAge(0);
                cookiePassword.setPath("/");
                response.addCookie(cookieId);
                response.addCookie(cookiePassword);
            }
        }
    }
}
