package cn.edu.jxnu.happystudying.dao.impl;

import cn.edu.jxnu.happystudying.dao.QuestionDao;
import cn.edu.jxnu.happystudying.domain.QuestionDomain;
import cn.edu.jxnu.happystudying.util.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.List;

public class ImplQuestionDao implements QuestionDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtils.getDataSource());

    @Override
    public String insertQuestion(final QuestionDomain questionDomain) {
        final String sql = "INSERT INTO t_question(q_user_id,q_user_name,q_publish_time,q_title,q_description,q_diamond_number,q_college,q_grade_level)\n" +
                "VALUES(?,?,?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
                                @Override
                                public PreparedStatement createPreparedStatement(Connection conn) throws SQLException, SQLException {
                                    PreparedStatement ps = null;
                                    ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                                    ps.setString(1, questionDomain.getqUserId());
                                    ps.setString(2, questionDomain.getqUserName());
                                    ps.setTimestamp(3, new Timestamp(questionDomain.getqPublishTime().getTime()));
                                    ps.setString(4, questionDomain.getqTitle());
                                    ps.setString(5, questionDomain.getqDescription());
                                    ps.setInt(6, questionDomain.getqDiamondNumber());
                                    ps.setString(7, questionDomain.getqCollege());
                                    ps.setString(8, questionDomain.getqGradeLevel());
                                    //...

                                    return ps;
                                }
                            },
                keyHolder);
        return keyHolder.getKey().toString();
    }

    @Override
    public void updateIconById(String qId, String qIcon) {
        String sql = "UPDATE t_question SET q_icon=? WHERE q_id=?";
        jdbcTemplate.update(sql, qIcon, qId);
    }

    @Override
    public List<QuestionDomain> queryTopQuestion() {
        String sql = "SELECT * FROM t_question WHERE is_sticky=1\n" +
                "ORDER BY q_publish_time desc";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<QuestionDomain>(QuestionDomain.class));
    }

    @Override
    public List<QuestionDomain> queryAllQuestionSortByPublishTime() {
        String sql = "SELECT * FROM t_question\n" +
                "ORDER BY q_publish_time desc";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<QuestionDomain>(QuestionDomain.class));
    }

    @Override
    public List<QuestionDomain> queryAllQuestionSortByNumber() {
        String sql = "SELECT * FROM t_question\n" +
                "ORDER BY q_view_number+q_responses_number desc";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<QuestionDomain>(QuestionDomain.class));
    }

    @Override
    public List<QuestionDomain> queryEssenceQuestionSortByPublishTime() {
        String sql = "SELECT * FROM t_question WHERE is_essence=1\n" +
                "ORDER BY q_publish_time desc";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<QuestionDomain>(QuestionDomain.class));
    }

    @Override
    public List<QuestionDomain> queryEssenceQuestionSortByNumber() {
        String sql = "SELECT * FROM t_question WHERE is_essence=1\n" +
                "ORDER BY q_view_number+q_responses_number desc";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<QuestionDomain>(QuestionDomain.class));
    }

    @Override
    public List<QuestionDomain> queryByKey(String key) {
        String sql = "SELECT * FROM t_question\n" +
                "WHERE q_user_name LIKE ? OR q_title LIKE ? OR q_college LIKE ? OR q_grade_level LIKE ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<QuestionDomain>(QuestionDomain.class), key, key, key, key);
    }

    @Override
    public void updateViewNumberById(String qId, String x) {
        String sql = "UPDATE t_question SET q_view_number=q_view_number + ?\n" +
                "WHERE q_id = ?";
        jdbcTemplate.update(sql, x, qId);
    }

    @Override
    public void updateResponsesNumberById(String qId, String x) {
        String sql = "UPDATE t_question SET q_responses_number=q_responses_number + ?\n" +
                "WHERE q_id = ?";
        jdbcTemplate.update(sql, x, qId);
    }

    @Override
    public List<QuestionDomain> queryById(String qId) {
        String sql = "select * from t_question where q_id=?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<QuestionDomain>(QuestionDomain.class), qId);
    }

    @Override
    public void updateQuestionById(QuestionDomain questionDomain) {
        String sql = "UPDATE t_question SET q_title=?,q_description=?,q_diamond_number=?,q_college=?,q_grade_level=?\n" +
                "WHERE q_id = ?";
        jdbcTemplate.update(sql, questionDomain.getqTitle(), questionDomain.getqDescription(), questionDomain.getqDiamondNumber(), questionDomain.getqCollege(), questionDomain.getqGradeLevel(), questionDomain.getqId());
    }

    @Override
    public List<QuestionDomain> queryAllQuestionByUserId(String qUserId) {
        String sql = "SELECT * FROM t_question WHERE q_user_id=?\n" +
                "ORDER BY q_publish_time DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<QuestionDomain>(QuestionDomain.class), qUserId);
    }
}
