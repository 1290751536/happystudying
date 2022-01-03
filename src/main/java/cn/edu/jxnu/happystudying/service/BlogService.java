package cn.edu.jxnu.happystudying.service;

import cn.edu.jxnu.happystudying.domain.BlogDomain;

import java.util.List;

public interface BlogService {
    public String insertBlog(BlogDomain blogDomain);

    public List<BlogDomain> queryBlogByActivityId(String aId);
}
