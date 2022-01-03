package cn.edu.jxnu.happystudying.servlet;

import cn.edu.jxnu.happystudying.domain.QuestionDomain;
import cn.edu.jxnu.happystudying.domain.UserDomain;
import cn.edu.jxnu.happystudying.service.QuestionService;
import cn.edu.jxnu.happystudying.service.UserService;
import cn.edu.jxnu.happystudying.service.impl.ImplQuestionService;
import cn.edu.jxnu.happystudying.service.impl.ImplUserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "UpdateQuestionServlet", value = "/user/updatequestion.do")
public class UpdateQuestionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();

        QuestionDomain questionDomain = new QuestionDomain();
        questionDomain.setqId(request.getParameter("qId"));
        questionDomain.setqTitle(request.getParameter("title"));
        questionDomain.setqCollege(request.getParameter("college"));
        questionDomain.setqGradeLevel(request.getParameter("gradeLevel"));
        questionDomain.setqDescription(request.getParameter("content"));
        questionDomain.setqDiamondNumber(Integer.valueOf(request.getParameter("diamondNumber")));

        UserDomain user = (UserDomain) request.getSession().getAttribute("user");
        int sx = Integer.parseInt(user.getuDiamondNumber());
        QuestionService questionService = new ImplQuestionService();
        int beforeNumber = questionService.queryById(questionDomain.getqId()).get(0).getqDiamondNumber();

        if (sx + beforeNumber < questionDomain.getqDiamondNumber()) {
            map.put("success", false);
            map.put("errMsg", "钻石数不足，请重新选择");
            out.println(mapper.writeValueAsString(map));
            return;
        }

        int now = sx + beforeNumber - questionDomain.getqDiamondNumber();
        user.setuDiamondNumber(String.valueOf(now));
        UserService userService = new ImplUserService();
        request.getSession().setAttribute("user", user);
        userService.updateUserDiamondNumber(user.getuNo(), user.getuDiamondNumber());

        questionService.updateQuestionById(questionDomain);

        map.put("success", true);
        out.println(mapper.writeValueAsString(map));

    }
}
