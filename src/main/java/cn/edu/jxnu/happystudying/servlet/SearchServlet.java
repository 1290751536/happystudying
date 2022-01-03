package cn.edu.jxnu.happystudying.servlet;

import cn.edu.jxnu.happystudying.domain.ActivityDomain;
import cn.edu.jxnu.happystudying.domain.QuestionDomain;
import cn.edu.jxnu.happystudying.service.ActivityService;
import cn.edu.jxnu.happystudying.service.QuestionService;
import cn.edu.jxnu.happystudying.service.impl.ImplActivityService;
import cn.edu.jxnu.happystudying.service.impl.ImplQuestionService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "SearchServlet", value = "/user/search.do")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();
        String key = URLDecoder.decode(request.getParameter("key"), "UTF-8");
        System.out.println(key);
        ActivityService activityService = new ImplActivityService();
        QuestionService questionService = new ImplQuestionService();

        List<ActivityDomain> activityDomainList = activityService.queryByKey(key);
        List<QuestionDomain> questionDomainList = questionService.queryByKey(key);

        map.put("success", true);
        map.put("activityDomainList", activityDomainList);
        map.put("questionDomainList", questionDomainList);
        System.out.println(map);
        out.println(mapper.writeValueAsString(map));
    }
}
