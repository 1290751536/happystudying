package cn.edu.jxnu.happystudying.dao.impl;

import cn.edu.jxnu.happystudying.dao.TeacherDao;
import cn.edu.jxnu.happystudying.domain.TeacherDomain;
import cn.edu.jxnu.happystudying.util.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ImplTeacherDao implements TeacherDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtils.getDataSource());

    @Override
    public List<TeacherDomain> queryTeacherByNo(String uNo) {
        String sql = "SELECT * FROM t_teacher WHERE t_id=?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<TeacherDomain>(TeacherDomain.class), uNo);
    }
}
