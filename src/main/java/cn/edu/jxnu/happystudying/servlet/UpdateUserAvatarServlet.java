package cn.edu.jxnu.happystudying.servlet;

import cn.edu.jxnu.happystudying.dao.UserDao;
import cn.edu.jxnu.happystudying.domain.UserDomain;
import cn.edu.jxnu.happystudying.service.UserService;
import cn.edu.jxnu.happystudying.service.impl.ImplUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet(name = "UpdateUserAvatarServlet", value = "/user/updateavatar.do")
public class UpdateUserAvatarServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();


        String uploadPath = getServletContext().getRealPath("/front-desk-management/user/avatar");
        String tmpPath = getServletContext().getRealPath("/front-desk-management/user/tmp");
        File tmpFile = new File(tmpPath);

        DiskFileItemFactory factory = getDiskFileItemFactory(tmpFile);
        ServletFileUpload servletFileUpload = getServletFileUpload(factory);
        try {
            Map<String, String> ret = uploadRequest(request, servletFileUpload, uploadPath);
//            System.out.println(mp);
            UserService userService = new ImplUserService();
            UserDomain userDomain = (UserDomain) request.getSession().getAttribute("user");
            String avatarPath = ret.get("avatar");

            userService.updateUserAvatar(userDomain.getuNo(), avatarPath.substring(avatarPath.lastIndexOf('/') + 1));
            userDomain.setuAvatar(avatarPath.substring(avatarPath.lastIndexOf('/') + 1));
            request.getSession().setAttribute("user", userDomain);

            map.put("success", true);
            out.println(mapper.writeValueAsString(map));
        } catch (FileUploadException e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("errMsg", "???????????????????????????????????????????????????");
            out.println(mapper.writeValueAsString(map));
        }
    }

    private Map<String, String> uploadRequest(HttpServletRequest request, ServletFileUpload servletFileUpload, String uploadPath) throws FileUploadException, IOException {
        List<FileItem> fileItems = servletFileUpload.parseRequest(request);
        Map<String, String> mp = new HashMap<>();
        for (FileItem fileItem : fileItems) {
            if (fileItem.isFormField()) {
                String filedName = fileItem.getFieldName(); // ???????????????????????????name????????????
                String value = fileItem.getString("UTF-8"); // FileItem???????????????????????????????????????????????????????????????????????????
                System.out.println(filedName + ":" + value);
                mp.put(filedName, value);
            } else {
                String fileName = fileItem.getName();
                if (fileItem == null || fileName.isEmpty()) {
                    continue;
                }
                String fileExtName = fileName.substring(fileName.lastIndexOf("."));
                String avatar = UUID.randomUUID().toString() + fileExtName;
                mp.put("avatar", uploadPath + "/" + avatar);
                InputStream in = fileItem.getInputStream();
                FileOutputStream fos = new FileOutputStream(uploadPath + "/" + avatar);
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = in.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fileItem.delete(); // ??????????????????????????????
                fos.close();
                in.close();
            }
        }
        return mp;
    }

    private DiskFileItemFactory getDiskFileItemFactory(File tmpFile) {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024 * 1024); // ???????????????1M
        factory.setRepository(tmpFile); // ???????????????
        return factory;
    }

    private ServletFileUpload getServletFileUpload(DiskFileItemFactory factory) {
        ServletFileUpload servletFileUpload = new ServletFileUpload();
        servletFileUpload.setFileItemFactory(factory);
        servletFileUpload.setHeaderEncoding("UTF-8");
        servletFileUpload.setFileSizeMax(1024 * 1024 * 5); // 5M
        return servletFileUpload;
    }

}
