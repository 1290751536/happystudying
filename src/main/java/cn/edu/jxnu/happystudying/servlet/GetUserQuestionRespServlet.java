package cn.edu.jxnu.happystudying.servlet;

import cn.edu.jxnu.happystudying.domain.QuestionRespDomain;
import cn.edu.jxnu.happystudying.domain.UserDomain;
import cn.edu.jxnu.happystudying.service.QuestionRespService;
import cn.edu.jxnu.happystudying.service.impl.ImplQuestionRespService;
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

@WebServlet(name = "GetUserQuestionRespServlet", value = "/user/getallquestionresp.do")
public class GetUserQuestionRespServlet extends HttpServlet {
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

        QuestionRespService questionRespService = new ImplQuestionRespService();
        List<QuestionRespDomain> list = questionRespService.queryAllQuestionRespByUserId(uId);

        List<QuestionRespDomain> questionRespDomainList = list.subList(0, Math.min(5, list.size()));
        map.put("success", true);
        map.put("questionRespDomainList", questionRespDomainList);
        out.println(mapper.writeValueAsString(map));
    }
}
