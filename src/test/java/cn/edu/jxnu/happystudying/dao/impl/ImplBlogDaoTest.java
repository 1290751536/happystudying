package cn.edu.jxnu.happystudying.dao.impl;

import cn.edu.jxnu.happystudying.dao.BlogDao;
import cn.edu.jxnu.happystudying.domain.BlogDomain;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class ImplBlogDaoTest {
BlogDao blogDao = new ImplBlogDao();
    @Test
    public void insertBlog() {
        BlogDomain blogDomain = new BlogDomain();
        blogDomain.setbUserId("123");
        blogDomain.setbUserName("李四");
        blogDomain.setbActivityId("1000");
        blogDomain.setbPublishTime(new Date());
        blogDomain.setbContent("我也是一篇博客");
        System.out.println(blogDao.insertBlog(blogDomain));
    }
}