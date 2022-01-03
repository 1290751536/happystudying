package cn.edu.jxnu.happystudying.servlet;

import cn.edu.jxnu.happystudying.domain.ActivityDomain;
import cn.edu.jxnu.happystudying.service.ActivityService;
import cn.edu.jxnu.happystudying.service.impl.ImplActivityService;
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

@WebServlet(name = "GetActivityServlet", value = "/getactivity.do")
public class GetActivityServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();

        String aId = request.getParameter("aId");
        ActivityService activityService = new ImplActivityService();
        List<ActivityDomain> list = activityService.queryById(aId);

        try {
            ActivityDomain activity = list.get(0);
            map.put("success", true);
            map.put("activity", activity);
        } catch (Exception e) {
            map.put("success", false);
            map.put("errMsg", "活动编号有误请检查");
        }
        out.println(mapper.writeValueAsString(map));
    }
}
