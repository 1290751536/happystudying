package cn.edu.jxnu.happystudying.servlet;

import cn.edu.jxnu.happystudying.domain.BlogDomain;
import cn.edu.jxnu.happystudying.domain.UserDomain;
import cn.edu.jxnu.happystudying.service.BlogService;
import cn.edu.jxnu.happystudying.service.impl.ImplBlogService;
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

@WebServlet(name = "InsertBlogServlet", value = "/user/creatblog.do")
public class InsertBlogServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();

        try {

            BlogDomain blogDomain = new BlogDomain();
            UserDomain user = (UserDomain) request.getSession().getAttribute("user");
            blogDomain.setbUserId(user.getuId());
            blogDomain.setbUserName(user.getuName());
            blogDomain.setbActivityId(request.getParameter("aId"));
            blogDomain.setbPublishTime(new Date());
            blogDomain.setbContent(request.getParameter("content"));

            BlogService blogService = new ImplBlogService();
            String bId = blogService.insertBlog(blogDomain);
            blogDomain.setbId(bId);

            map.put("success", true);
            map.put("blogDomain", blogDomain);
        } catch (Exception e) {
            map.put("success", false);
        }
        out.println(mapper.writeValueAsString(map));

    }
}
