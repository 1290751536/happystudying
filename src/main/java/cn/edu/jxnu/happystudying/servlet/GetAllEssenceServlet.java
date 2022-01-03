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

@WebServlet(name = "GetAllEssenceServlet", value = "/getallessence.do")
public class GetAllEssenceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();

        String orderByType = request.getParameter("orderByType");

        ActivityService activityService = new ImplActivityService();
        QuestionService questionService = new ImplQuestionService();

        List<ActivityDomain> activityList = null;
        List<QuestionDomain> questionList = null;

        if (orderByType.equals("1")) {
            activityList = activityService.queryEssenceActivitySortByPublishTime();
            questionList = questionService.queryEssenceQuestionSortByPublishTime();
        } else {
            activityList = activityService.queryEssenceActivitySortByNumber();
            questionList = questionService.queryEssenceQuestionSortByNumber();
        }

        map.put("success", true);
        map.put("activityList", activityList);
        map.put("questionList", questionList);
        out.println(mapper.writeValueAsString(map));
    }
}
