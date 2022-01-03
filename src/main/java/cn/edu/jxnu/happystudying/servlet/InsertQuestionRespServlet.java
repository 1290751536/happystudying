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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "InsertQuestionRespServlet", value = "/user/creatquestionresp.do")
public class InsertQuestionRespServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();

        String qId = request.getParameter("qId");
        String content = request.getParameter("content");
        String qTitle = request.getParameter("title");

        QuestionRespService questionRespService = new ImplQuestionRespService();
        QuestionRespDomain questionRespDomain = new QuestionRespDomain();

        UserDomain user = (UserDomain) request.getSession().getAttribute("user");
        questionRespDomain.setrUserId(user.getuId());
        questionRespDomain.setrUserName(user.getuName());
        questionRespDomain.setrUserAvatar(user.getuAvatar());
        questionRespDomain.setrTime(new Date());
        questionRespDomain.setrQuestionId(qId);
        questionRespDomain.setrQuestionTitle(qTitle);
        questionRespDomain.setrContent(content);

        String rId = questionRespService.insertQuestionResp(questionRespDomain);
        questionRespDomain.setrId(rId);

        map.put("success", true);
        map.put("questionResp", questionRespDomain);
        out.println(mapper.writeValueAsString(map));
    }
}
