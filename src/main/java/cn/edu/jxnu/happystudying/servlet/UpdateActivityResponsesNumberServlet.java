package cn.edu.jxnu.happystudying.servlet;

import cn.edu.jxnu.happystudying.service.ActivityService;
import cn.edu.jxnu.happystudying.service.impl.ImplActivityService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateActivityResponsesNumberServlet", value = "/updateactivityresponsenumber.do")
public class UpdateActivityResponsesNumberServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String aId = request.getParameter("aId");
        String x = request.getParameter("x");
        ActivityService activityService = new ImplActivityService();
        activityService.updateResponsesNumberById(aId, x);
    }
}
