package cn.edu.jxnu.happystudying.servlet;

import cn.edu.jxnu.happystudying.domain.BlogDomain;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "GetResponseBlogServlet", value = "/getresponseblog.do")
public class GetResponseBlogServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();


        String aId = request.getParameter("aId");
        BlogService blogService = new ImplBlogService();
        List<BlogDomain> blogDomainList = blogService.queryBlogByActivityId(aId);
        map.put("success", true);
        map.put("blogDomainList", blogDomainList);
        out.println(mapper.writeValueAsString(map));
    }
}
