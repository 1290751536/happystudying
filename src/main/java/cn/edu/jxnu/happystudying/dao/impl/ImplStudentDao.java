package cn.edu.jxnu.happystudying.dao.impl;

import cn.edu.jxnu.happystudying.dao.StudentDao;
import cn.edu.jxnu.happystudying.domain.StudentDomain;
import cn.edu.jxnu.happystudying.util.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ImplStudentDao implements StudentDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtils.getDataSource());

    @Override
    public List<StudentDomain> queryStudentByNo(String uNo) {
        String sql = "SELECT * FROM t_student WHERE s_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<StudentDomain>(StudentDomain.class), uNo);
    }
}
