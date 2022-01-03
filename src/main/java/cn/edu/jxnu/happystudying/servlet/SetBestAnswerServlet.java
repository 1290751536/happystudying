package cn.edu.jxnu.happystudying.servlet;

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
import java.util.Map;

@WebServlet(name = "SetBestAnswerServlet", value = "/setbestans.do")
public class SetBestAnswerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();

        QuestionRespService questionRespService = new ImplQuestionRespService();
        String qId = request.getParameter("qId");
        String rId = request.getParameter("rId");
//        System.out.println(qId);
        if (questionRespService.queryAdoptQuestionResp(qId).size() > 0) {
            map.put("success", false);
            map.put("errMsg", "已经有了一个优质解答了不能再多啦");
        } else {
            questionRespService.updateAdoptById(rId);
            map.put("success",true);
        }
        out.println(mapper.writeValueAsString(map));
    }
}
