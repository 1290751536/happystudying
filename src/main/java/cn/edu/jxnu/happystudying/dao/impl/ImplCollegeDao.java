package cn.edu.jxnu.happystudying.dao.impl;

import cn.edu.jxnu.happystudying.dao.CollegeDao;
import cn.edu.jxnu.happystudying.domain.CollegeDomain;
import cn.edu.jxnu.happystudying.util.JdbcUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ImplCollegeDao implements CollegeDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtils.getDataSource());

    @Override
    public List<CollegeDomain> queryAllCollege() {
        String sql = "SELECT * FROM t_college";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<CollegeDomain>(CollegeDomain.class));
    }
}
