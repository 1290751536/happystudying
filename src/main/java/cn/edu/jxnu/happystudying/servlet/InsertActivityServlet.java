package cn.edu.jxnu.happystudying.servlet;

import cn.edu.jxnu.happystudying.domain.ActivityDomain;
import cn.edu.jxnu.happystudying.domain.UserDomain;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "InsertActivityServlet", value = "/user/insertactivity.do")
public class InsertActivityServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();

        ActivityDomain activityDomain = new ActivityDomain();

        UserDomain user = (UserDomain) request.getSession().getAttribute("user");

        activityDomain.setaUserId(user.getuId());
        activityDomain.setaUserName(user.getuName());
        activityDomain.setaTitle(request.getParameter("title"));
        activityDomain.setaDescription(request.getParameter("content"));
        activityDomain.setaPublishTime(new Date());
        activityDomain.setaCollege(request.getParameter("college"));
        activityDomain.setaGradeLevel(request.getParameter("gradeLevel"));

        System.out.println(activityDomain);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            activityDomain.setaBeginTime(formatter.parse(request.getParameter("beginTime")));
            activityDomain.setaEndTime(formatter.parse(request.getParameter("endTime")));
            activityDomain.setaEndTime(formatter.parse(request.getParameter("endTime")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        System.out.println(activityDomain);

        ActivityService activityService = new ImplActivityService();
        String aId = activityService.insertActivity(activityDomain);
        activityDomain.setaId(aId);
        // 插入完了7个基本功能

        String aCollage = request.getParameter("college");
        if (!isEmpty(aCollage)) {
            activityService.updateCollegeById(aId, aCollage);
        }
        String aGreadLevel = request.getParameter("gradeLevel");
        if (!isEmpty(aGreadLevel)) {
            activityService.updateGradeLevelById(aId, aGreadLevel);
        }

        map.put("success", true);
        map.put("activityDomain", activityDomain);
        map.put("id", aId);
        out.println(mapper.writeValueAsString(map));
    }

    boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }
}
