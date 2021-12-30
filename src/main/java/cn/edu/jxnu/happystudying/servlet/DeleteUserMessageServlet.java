package cn.edu.jxnu.happystudying.servlet;

import cn.edu.jxnu.happystudying.service.MessageService;
import cn.edu.jxnu.happystudying.service.impl.ImplMessageService;
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

@WebServlet(name = "DeleteUserMessageServlet", value = "/user/deletemsg.do")
public class DeleteUserMessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();

        String mId = request.getParameter("mId");
        MessageService messageService = new ImplMessageService();
        messageService.deleteMessageById(mId);
        map.put("success", true);
        out.println(mapper.writeValueAsString(map));
    }
}
