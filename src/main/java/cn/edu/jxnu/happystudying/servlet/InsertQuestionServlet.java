package cn.edu.jxnu.happystudying.servlet;

import cn.edu.jxnu.happystudying.domain.QuestionDomain;
import cn.edu.jxnu.happystudying.domain.UserDomain;
import cn.edu.jxnu.happystudying.service.QuestionService;
import cn.edu.jxnu.happystudying.service.impl.ImplQuestionService;
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

@WebServlet(name = "InsertQuestionServlet", value = "/user/insertquestion.do")
public class InsertQuestionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();

        UserDomain userDomain = (UserDomain) request.getSession().getAttribute("user");

        QuestionDomain questionDomain = new QuestionDomain();
        questionDomain.setqUserId(userDomain.getuId());
        questionDomain.setqUserName(userDomain.getuName());
        questionDomain.setqPublishTime(new Date());
        questionDomain.setqTitle(request.getParameter("title"));
        questionDomain.setqDescription(request.getParameter("content"));
        questionDomain.setqDiamondNumber(Integer.valueOf(request.getParameter("diamondNumber")));
        questionDomain.setqCollege(request.getParameter("college"));
        questionDomain.setqGradeLevel(request.getParameter("gradeLevel"));

        QuestionService questionService = new ImplQuestionService();
        String id = questionService.insertQuestion(questionDomain);
        questionDomain.setqId(id);

        map.put("success", true);
        map.put("id", id);
        map.put("questionDomain", questionDomain);
        out.println(mapper.writeValueAsString(map));
    }
}
