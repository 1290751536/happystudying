package cn.edu.jxnu.happystudying.servlet;

import cn.edu.jxnu.happystudying.service.QuestionService;
import cn.edu.jxnu.happystudying.service.impl.ImplQuestionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateQuestionResponsesNumberServlet",value = "/updatequestionresponsenumber.do")
public class UpdateQuestionResponsesNumberServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String aId = request.getParameter("qId");
        String x = request.getParameter("x");
        QuestionService questionService = new ImplQuestionService();
        questionService.updateResponsesNumberById(aId, x);
    }
}
