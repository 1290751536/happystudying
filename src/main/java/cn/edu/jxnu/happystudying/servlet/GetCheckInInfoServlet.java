package cn.edu.jxnu.happystudying.servlet;

import cn.edu.jxnu.happystudying.domain.CheckInDomain;
import cn.edu.jxnu.happystudying.domain.UserDomain;
import cn.edu.jxnu.happystudying.service.CheckInService;
import cn.edu.jxnu.happystudying.service.impl.ImplCheckInService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet(name = "GetCheckInInfoServlet", value = "/user/getcheckininfo.do")
public class GetCheckInInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();

        UserDomain user = (UserDomain) request.getSession().getAttribute("user");
        CheckInService checkInService = new ImplCheckInService();
        List<CheckInDomain> list = checkInService.queryCheckInById(user.getuId());

        if (list.size() == 0) { //说明第一次登录网站
            checkInService.insertUserId(user.getuId());
            map.put("day", 0);
            map.put("todayIsCheckIn", false);
        } else {
            CheckInDomain checkInDomain = list.get(0);

            Format f = new SimpleDateFormat("yyyy-MM-dd");
            Date today = new Date();

            Calendar c = Calendar.getInstance();
            c.setTime(checkInDomain.getcLatestCheckinsTime());
            c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天

            Date tomorrow = c.getTime();

            if (isSameDay(today, tomorrow) || isSameDay(today, checkInDomain.getcLatestCheckinsTime())) {
                map.put("day", checkInDomain.getcContinuousCheckinsNumber());
            } else {
                map.put("day", 0); // 昨天没签到，把连续签到天数清空
                checkInService.changeZero(user.getuId());
            }

            if (isSameDay(today, checkInDomain.getcLatestCheckinsTime())) {
                map.put("todayIsCheckIn", true);
            } else {
                map.put("todayIsCheckIn", false);
            }
        }
        out.println(mapper.writeValueAsString(map));
    }

    public boolean isSameDay(Date dt1, Date dt2) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String strDate1 = format.format(dt1);
        String strDate2 = format.format(dt2);
        return strDate1.equalsIgnoreCase(strDate2);
    }
}
