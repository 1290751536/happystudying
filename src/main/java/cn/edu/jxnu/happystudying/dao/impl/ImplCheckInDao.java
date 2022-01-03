package cn.edu.jxnu.happystudying.dao.impl;

import cn.edu.jxnu.happystudying.dao.CheckInDao;
import cn.edu.jxnu.happystudying.domain.CheckInDomain;
import cn.edu.jxnu.happystudying.util.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

public class ImplCheckInDao implements CheckInDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtils.getDataSource());

    @Override
    public List<CheckInDomain> queryCheckInById(String cId) {
        String sql = "SELECT * FROM t_checkins_user\n" +
                "WHERE c_id=?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<CheckInDomain>(CheckInDomain.class), cId);
    }

    @Override
    public void insertUserId(String cId) {
        String sql = "INSERT INTO t_checkins_user(c_id)\n" +
                "VALUES(?)";
        jdbcTemplate.update(sql, cId);
    }

    @Override
    public void changeZero(String cId) {
        String sql = "UPDATE t_checkins_user SET c_continuous_checkins_number=0\n" +
                "WHERE c_id=?";
        jdbcTemplate.update(sql, cId);
    }

    @Override
    public void updateTodayData(String cId, Date today) {
        String sql = "UPDATE t_checkins_user SET c_continuous_checkins_number=c_continuous_checkins_number+1,c_latest_checkins_time=?\n" +
                "WHERE c_id=?";
        jdbcTemplate.update(sql, today, cId);
    }
}
