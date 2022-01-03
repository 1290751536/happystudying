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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "GetTopServlet", value = "/getTop.do")
public class GetTopServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();

        ActivityService activityService = new ImplActivityService();
        QuestionService questionService = new ImplQuestionService();

        List<ActivityDomain> activityList = activityService.queryTopActivity();
        List<QuestionDomain> questionList = questionService.queryTopQuestion();

        map.put("success", true);
        map.put("activityList", activityList);
        map.put("questionList", questionList);
        out.println(mapper.writeValueAsString(map));
    }
}
