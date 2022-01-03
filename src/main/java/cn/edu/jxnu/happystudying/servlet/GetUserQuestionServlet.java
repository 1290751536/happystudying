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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "GetUserQuestionServlet", value = "/user/getallquestion.do")
public class GetUserQuestionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();

        String uId = request.getParameter("uId");
        if (uId == null || uId.equals("")) {
            UserDomain userDomain = (UserDomain) request.getSession().getAttribute("user");
            uId = userDomain.getuId();
        }

        QuestionService questionService = new ImplQuestionService();
        List<QuestionDomain> questionDomainList = questionService.queryAllQuestionByUserId(uId);

        map.put("success", true);
        map.put("questionDomainList", questionDomainList);
        out.println(mapper.writeValueAsString(map));
    }
}
