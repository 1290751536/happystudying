package cn.edu.jxnu.happystudying.service.impl;

import cn.edu.jxnu.happystudying.dao.BlogDao;
import cn.edu.jxnu.happystudying.dao.impl.ImplBlogDao;
import cn.edu.jxnu.happystudying.domain.BlogDomain;
import cn.edu.jxnu.happystudying.service.BlogService;

import java.util.List;

public class ImplBlogService implements BlogService {
    BlogDao blogDao = new ImplBlogDao();

    @Override
    public String insertBlog(BlogDomain blogDomain) {
        return blogDao.insertBlog(blogDomain);
    }

    @Override
    public List<BlogDomain> queryBlogByActivityId(String aId) {
        return blogDao.queryBlogByActivityId(aId);
    }
}
