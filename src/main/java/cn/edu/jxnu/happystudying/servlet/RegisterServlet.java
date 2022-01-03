package cn.edu.jxnu.happystudying.servlet;

import cn.edu.jxnu.happystudying.domain.StudentDomain;
import cn.edu.jxnu.happystudying.domain.TeacherDomain;
import cn.edu.jxnu.happystudying.service.StudentService;
import cn.edu.jxnu.happystudying.service.TeacherService;
import cn.edu.jxnu.happystudying.service.UserService;
import cn.edu.jxnu.happystudying.service.impl.ImplStudentService;
import cn.edu.jxnu.happystudying.service.impl.ImplTeacherService;
import cn.edu.jxnu.happystudying.service.impl.ImplUserService;
import cn.edu.jxnu.happystudying.util.Md5Utils;
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
import java.util.List;
import java.util.Map;

@WebServlet(name = "RegisterServlet", value = "/user/register.do")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();

        String uNo = request.getParameter("uNo");
        String uPassword = request.getParameter("uPassword");

        UserService userService = new ImplUserService();
        if (!userService.isExistNo(uNo)) {
            map.put("success", false);
            map.put("errMsg", "该学号或者教工号不存在，请联系管理员添加进库");
            out.println(mapper.writeValueAsString(map));
            return;
        }

        try {
            // 注册成功
            if (userService.register(uNo, Md5Utils.digest(uPassword))) {
                // 判断是否为老师
                TeacherService teacherService = new ImplTeacherService();
                List<TeacherDomain> teachers = teacherService.queryTeacherByNo(uNo);
                if (teachers.size() > 0) { // 更新用户名，默认为系统中存储的姓名
                    userService.updateUserNameByNo(uNo, teachers.get(0).gettName());
                    userService.setSex(uNo, teachers.get(0).gettSex());
                    userService.updateTeacherByNo(uNo);
                } else {
                    StudentService studentService = new ImplStudentService();
                    List<StudentDomain> students = studentService.queryStudentByNo(uNo);
                    userService.updateUserNameByNo(uNo, students.get(0).getsName());
                    userService.setSex(uNo, students.get(0).getsSex());
                }

                userService.setRegisterTime(uNo, new Date());

                map.put("success", true);
                out.println(mapper.writeValueAsString(map));
                return;
            } else {
                map.put("success", false);
                map.put("errMsg", "服务器繁忙请稍后再试");
                out.println(mapper.writeValueAsString(map));
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("errMsg", "该用户已经注册，请直接登录");
            out.println(mapper.writeValueAsString(map));
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
