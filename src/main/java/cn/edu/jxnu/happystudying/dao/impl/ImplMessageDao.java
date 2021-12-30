package cn.edu.jxnu.happystudying.dao.impl;

import cn.edu.jxnu.happystudying.dao.MessageDao;
import cn.edu.jxnu.happystudying.domain.UserMessageDomain;
import cn.edu.jxnu.happystudying.util.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ImplMessageDao implements MessageDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtils.getDataSource());

    @Override
    public List<UserMessageDomain> queryUserMessageById(String uId) {
        String sql = "SELECT * FROM t_user_message WHERE m_user_id=?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserMessageDomain>(UserMessageDomain.class), uId);
    }

    @Override
    public void deleteMessageById(String mId) {
        String sql = "DELETE FROM t_user_message WHERE m_id=?";
        jdbcTemplate.update(sql, mId);
    }

    @Override
    public void deleteMessageByUserId(String uId) {
        String sql = "DELETE FROM t_user_message WHERE m_user_id=?";
        jdbcTemplate.update(sql, uId);
    }
}
