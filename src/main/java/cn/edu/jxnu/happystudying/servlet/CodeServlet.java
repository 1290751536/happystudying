package cn.edu.jxnu.happystudying.servlet;

import cn.edu.jxnu.happystudying.util.CodeUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

@WebServlet(value = "/code-servlet.do")
public class CodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map map = CodeUtils.generateCodeAndPic();
        String code = map.get("code").toString();
        BufferedImage img = (BufferedImage) map.get("codePic");
        HttpSession session = request.getSession();
        session.setAttribute("code", code);
        response.setContentType("image/jpeg");
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIO.write(img, "jpeg", out);
    }
}
