package cn.edu.jxnu.happystudying.filter;

import cn.edu.jxnu.happystudying.dao.UserDao;
import cn.edu.jxnu.happystudying.dao.impl.ImplUserDao;
import cn.edu.jxnu.happystudying.domain.UserDomain;
import com.mysql.cj.Session;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebFilter(filterName = "IsLoginFilter", value = "/front-desk-management/*")
public class IsLoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String uri = request.getRequestURI();
        System.out.println(uri);
        if (uri.endsWith("/login-register/index.html")) {
            chain.doFilter(req, resp);
        }
        if (uri.endsWith(".js") || uri.endsWith(".css") || uri.endsWith(".png") || uri.endsWith("jpg")) {
            chain.doFilter(req, resp);
        } else {
            HttpSession session = request.getSession();
            UserDomain user = (UserDomain) session.getAttribute("user");
            if (user != null) {
                chain.doFilter(req, resp);
            } else {
                String uNo = null, uPassword = null;
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if ("uNo".equals(cookie.getName()))
                            uNo = cookie.getValue();
                        if ("uPassword".equals(cookie.getName()))
                            uPassword = cookie.getValue();
                    }
                }
                if (uNo != null && uPassword != null) {
                    UserDao userDao = new ImplUserDao();
                    List<UserDomain> list = userDao.queryUser(uNo, uPassword);
                    if (list.size() > 0) {
                        session.setAttribute("user", list.get(0));
                        chain.doFilter(req, resp);
                    } else { // 用户篡改了错误的cookie
                        response.sendRedirect("/login-register/index.html");
                    }
                } else {
                    response.sendRedirect("/login-register/index.html");
                }
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
