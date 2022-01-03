package cn.edu.jxnu.happystudying.dao.impl;

import cn.edu.jxnu.happystudying.dao.QuestionDao;
import cn.edu.jxnu.happystudying.dao.QuestionRespDao;
import cn.edu.jxnu.happystudying.domain.QuestionRespDomain;
import cn.edu.jxnu.happystudying.util.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ImplQuestionRespDao implements QuestionRespDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtils.getDataSource());

    @Override
    public List<QuestionRespDomain> queryById(String rId) {
        String sql = "select * from t_question_response where r_question_id=?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<QuestionRespDomain>(QuestionRespDomain.class), rId);
    }

    @Override
    public String insertQuestionResp(final QuestionRespDomain questionRespDomain) {
        final String sql = "INSERT INTO t_question_response(r_user_id,r_user_name,r_user_avatar,r_question_id,r_question_title,r_time,r_content)\n" +
                "VALUES(?,?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
                                @Override
                                public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                                    PreparedStatement ps = null;
                                    ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                                    ps.setString(1, questionRespDomain.getrUserId());
                                    ps.setString(2, questionRespDomain.getrUserName());
                                    ps.setString(3, questionRespDomain.getrUserAvatar());
                                    ps.setString(4, questionRespDomain.getrQuestionId());
                                    ps.setString(5, questionRespDomain.getrQuestionTitle());
                                    ps.setTimestamp(6, new java.sql.Timestamp(questionRespDomain.getrTime().getTime()));
                                    ps.setString(7, questionRespDomain.getrContent());
                                    //...

                                    return ps;
                                }
                            },
                keyHolder);
        return keyHolder.getKey().toString();
    }

    @Override
    public List<QuestionRespDomain> queryAdoptQuestionResp(String qId) {
        String sql = "SELECT * from t_question_response WHERE is_adopt=1 AND r_question_id=?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<QuestionRespDomain>(QuestionRespDomain.class), qId);
    }

    @Override
    public void updateAdoptById(String rId) {
        String sql = "UPDATE t_question_response SET is_adopt=1\n" +
                "WHERE r_id=?";
        jdbcTemplate.update(sql, rId);
    }

    @Override
    public List<QuestionRespDomain> queryAllQuestionRespByUserId(String rUserId) {
        String sql = "SELECT * FROM t_question_response WHERE r_user_id=? ORDER BY r_time DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<QuestionRespDomain>(QuestionRespDomain.class), rUserId);
    }
}
