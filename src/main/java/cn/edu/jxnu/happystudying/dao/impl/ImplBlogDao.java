package cn.edu.jxnu.happystudying.dao.impl;

import cn.edu.jxnu.happystudying.dao.BlogDao;
import cn.edu.jxnu.happystudying.domain.BlogDomain;
import cn.edu.jxnu.happystudying.util.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.List;

public class ImplBlogDao implements BlogDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtils.getDataSource());

    @Override
    public String insertBlog(final BlogDomain blogDomain) {
        final String sql = "INSERT INTO t_blog(b_user_id,b_user_name,b_activity_id,b_publish_time,b_content)\n" +
                "VALUES(?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
                                @Override
                                public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                                    PreparedStatement ps = null;
                                    ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                                    ps.setString(1, blogDomain.getbUserId());
                                    ps.setString(2, blogDomain.getbUserName());
                                    ps.setString(3, blogDomain.getbActivityId());
                                    ps.setTimestamp(4, new Timestamp(blogDomain.getbPublishTime().getTime()));
                                    ps.setString(5, blogDomain.getbContent());
                                    //...

                                    return ps;
                                }
                            },
                keyHolder);
        return keyHolder.getKey().toString();
    }

    @Override
    public List<BlogDomain> queryBlogByActivityId(String aId) {
        String sql = "SELECT * FROM t_blog WHERE b_activity_id=?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<BlogDomain>(BlogDomain.class), aId);
    }
}
