package cn.edu.jxnu.happystudying.dao.impl;

import cn.edu.jxnu.happystudying.dao.UserDao;
import cn.edu.jxnu.happystudying.domain.UserDomain;
import cn.edu.jxnu.happystudying.util.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ImplUserDao implements UserDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtils.getDataSource());

    @Override
    public List<UserDomain> queryUser(String uNo, String uPassword) {
        String sql = "SELECT * FROM t_user\n" +
                "WHERE u_no=? AND u_password=?";
        List<UserDomain> user = jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserDomain>(UserDomain.class), uNo, uPassword);
        return user;
    }

    @Override
    public boolean isExistNo(String uNo) {
        String sql = "SELECT * FROM v_student_teacher WHERE s_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<String>(String.class), uNo).size() != 0;
    }

    @Override
    public boolean insertUser(String uNo, String uPassword) throws Exception {
        String sql = "INSERT INTO t_user(u_no,u_password) VALUES(?,?)";
        return jdbcTemplate.update(sql, uNo, uPassword) == 1;
    }

    @Override
    public void updateTeacherByNo(String uNo) {
        String sql = "UPDATE t_user SET is_post=1 WHERE u_no=?";
        jdbcTemplate.update(sql, uNo);
    }

    @Override
    public void updateUserNameByNo(String uNo, String uName) {
        String sql = "UPDATE t_user SET u_name=? WHERE u_no=?";
        jdbcTemplate.update(sql, uName, uNo);
    }
}
