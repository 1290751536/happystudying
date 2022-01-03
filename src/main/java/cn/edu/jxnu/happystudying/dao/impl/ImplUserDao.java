package cn.edu.jxnu.happystudying.dao.impl;

import cn.edu.jxnu.happystudying.dao.UserDao;
import cn.edu.jxnu.happystudying.domain.UserDomain;
import cn.edu.jxnu.happystudying.domain.UserMessageDomain;
import cn.edu.jxnu.happystudying.util.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
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

    @Override
    public void updateUserSexByNo(String uNo, String uSex) {
        String sql = "UPDATE t_user SET u_sex=? WHERE u_no=?";
        jdbcTemplate.update(sql, uSex, uNo);
    }

    @Override
    public void updateUserRegisterTime(String uNo, Date uRegisterTime) {
        String sql = "UPDATE t_user SET u_register_time=? WHERE u_no=?";
        jdbcTemplate.update(sql, uRegisterTime, uNo);
    }

    @Override
    public void updateUserEmailByNo(String uNo, String uEmail) {
        String sql = "UPDATE t_user SET u_email=? WHERE u_no=?";
        jdbcTemplate.update(sql, uEmail, uNo);
    }

    @Override
    public void updateUserAddressByNo(String uNo, String uAddress) {
        String sql = "UPDATE t_user SET u_address=? WHERE u_no=?";
        jdbcTemplate.update(sql, uAddress, uNo);
    }

    @Override
    public void updateUserSignatureByNo(String uNo, String uSignature) {
        String sql = "UPDATE t_user SET u_signature=? WHERE u_no=?";
        jdbcTemplate.update(sql, uSignature, uNo);
    }

    @Override
    public void updateUserAvatar(String uNo, String uAvatar) {
        String sql = "UPDATE t_user SET u_avatar=? WHERE u_no=?";
        jdbcTemplate.update(sql, uAvatar, uNo);
    }

    @Override
    public void updateUserPassword(String uNo, String uPassword) {
        String sql = "UPDATE t_user SET u_password=? WHERE u_no=?";
        jdbcTemplate.update(sql, uPassword, uNo);
    }

    @Override
    public void updateUserDiamondNumber(String uNo, String uDiamondNumber) {
        String sql = "UPDATE t_user SET u_diamond_number=? WHERE u_no=?";
        jdbcTemplate.update(sql, uDiamondNumber, uNo);
    }

    @Override
    public List<UserDomain> queryUserById(String uId) {
        String sql = "SELECT * FROM t_user WHERE u_id=?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserDomain>(UserDomain.class), uId);
    }
}
