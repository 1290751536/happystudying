package cn.edu.jxnu.happystudying.dao.impl;

import cn.edu.jxnu.happystudying.dao.ActivityDao;
import cn.edu.jxnu.happystudying.domain.ActivityDomain;
import cn.edu.jxnu.happystudying.util.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.List;

public class ImplActivityDao implements ActivityDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtils.getDataSource());

    @Override
    public String insertActivity(final ActivityDomain activityDomain) {
        final String sql = "INSERT INTO t_activities(a_user_id,a_user_name,a_publish_time,a_begin_time,a_end_time,a_title,a_description,a_college,a_grade_level)\n" +
                "VALUES(?,?,?,?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
                                @Override
                                public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                                    PreparedStatement ps = null;
                                    ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                                    ps.setString(1, activityDomain.getaUserId());
                                    ps.setString(2, activityDomain.getaUserName());
                                    ps.setTimestamp(3, new java.sql.Timestamp(activityDomain.getaPublishTime().getTime()));
                                    ps.setTimestamp(4, new java.sql.Timestamp(activityDomain.getaBeginTime().getTime()));
                                    ps.setTimestamp(5, new java.sql.Timestamp(activityDomain.getaEndTime().getTime()));
                                    ps.setString(6, activityDomain.getaTitle());
                                    ps.setString(7, activityDomain.getaDescription());
                                    ps.setString(8, activityDomain.getaCollege());
                                    ps.setString(9, activityDomain.getaGradeLevel());
                                    //...

                                    return ps;
                                }
                            },
                keyHolder);
        return keyHolder.getKey().toString();
    }

    @Override
    public void updateCollegeById(String aId, String aCollege) {
        String sql = "UPDATE t_activities SET a_college=? WHERE a_id=?";
        jdbcTemplate.update(sql, aCollege, aId);
    }

    @Override
    public void updateGradeLevelById(String aId, String aGreadLevel) {
        String sql = "UPDATE t_activities SET a_grade_level=? WHERE a_id=?";
        jdbcTemplate.update(sql, aGreadLevel, aId);
    }

    @Override
    public void updateIconById(String aId, String aIcon) {
        String sql = "UPDATE t_activities SET a_icon=? WHERE a_id=?";
        jdbcTemplate.update(sql, aIcon, aId);
    }

    @Override
    public List<ActivityDomain> queryTopActivity() {
        String sql = "SELECT * FROM t_activities WHERE is_sticky=1 AND is_audit=1\n" +
                "ORDER BY a_publish_time desc";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<ActivityDomain>(ActivityDomain.class));
    }

    @Override
    public List<ActivityDomain> queryAllActivitySortByPublishTime() {
        String sql = "SELECT * FROM t_activities WHERE is_audit=1\n" +
                "ORDER BY a_publish_time desc";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<ActivityDomain>(ActivityDomain.class));
    }

    @Override
    public List<ActivityDomain> queryAllActivitySortByNumber() {
        String sql = "SELECT * FROM t_activities WHERE is_audit=1\n" +
                "ORDER BY a_view_number+a_responses_number desc";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<ActivityDomain>(ActivityDomain.class));
    }

    @Override
    public List<ActivityDomain> queryEssenceActivitySortByPublishTime() {
        String sql = "SELECT * FROM t_activities WHERE is_essence=1 AND  is_audit=1\n" +
                "ORDER BY a_publish_time desc";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<ActivityDomain>(ActivityDomain.class));
    }

    @Override
    public List<ActivityDomain> queryEssenceActivitySortByNumber() {
        String sql = "SELECT * FROM t_activities WHERE is_essence=1 AND  is_audit=1\n" +
                "ORDER BY a_view_number+a_responses_number desc";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<ActivityDomain>(ActivityDomain.class));
    }

    @Override
    public List<ActivityDomain> queryByKey(String key) {
        String sql = "SELECT * FROM t_activities\n" +
                "WHERE a_user_name LIKE ? OR a_title LIKE ? OR a_college LIKE ? OR a_grade_level LIKE ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<ActivityDomain>(ActivityDomain.class), key, key, key, key);
    }

    @Override
    public List<ActivityDomain> queryById(String aId) {
        String sql = "SELECT * FROM t_activities WHERE a_id=?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<ActivityDomain>(ActivityDomain.class), aId);
    }

    @Override
    public void updateViewNumberById(String aId, String x) {
        String sql = "UPDATE t_activities SET a_view_number=a_view_number + ?\n" +
                "WHERE a_id = ?";
        jdbcTemplate.update(sql, x, aId);
    }

    @Override
    public void updateResponsesNumberById(String aId, String x) {
        String sql = "UPDATE t_activities SET a_responses_number=a_responses_number + ?\n" +
                "WHERE a_id = ?";
        jdbcTemplate.update(sql, x, aId);
    }
}
