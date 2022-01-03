package cn.edu.jxnu.happystudying.servlet;

import cn.edu.jxnu.happystudying.domain.QuestionDomain;
import cn.edu.jxnu.happystudying.domain.UserDomain;
import cn.edu.jxnu.happystudying.domain.UserMessageDomain;
import cn.edu.jxnu.happystudying.service.MessageService;
import cn.edu.jxnu.happystudying.service.QuestionService;
import cn.edu.jxnu.happystudying.service.impl.ImplMessageService;
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

@WebServlet(name = "InsertQuestionMessageServlet",value = "/insertquestionmsg.do")
public class InsertQuestionMessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();

        UserMessageDomain userMessageDomain = new UserMessageDomain();

        UserDomain userDomain = (UserDomain) request.getSession().getAttribute("user");

        userMessageDomain.setmReplyUserId(Integer.valueOf(userDomain.getuId()));
        userMessageDomain.setmReplyUserName(userDomain.getuName());

        String qId = request.getParameter("qId");
        userMessageDomain.setmQuestionId(Integer.valueOf(qId));

        QuestionService questionService = new ImplQuestionService();
        QuestionDomain questionDomain = questionService.queryById(qId).get(0);

        userMessageDomain.setmUserId(Integer.valueOf(questionDomain.getqUserId()));

        userMessageDomain.setmMessageDescription("回答了您的求解");
        userMessageDomain.setmResponseTime(new Date());
        userMessageDomain.setmQuestionTitle(questionDomain.getqTitle());

        MessageService messageService = new ImplMessageService();
        messageService.insertMessage(userMessageDomain);

        map.put("success", true);
        out.println(mapper.writeValueAsString(map));
    }
}
