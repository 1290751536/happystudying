package cn.edu.jxnu.happystudying.dao;

import cn.edu.jxnu.happystudying.domain.BlogDomain;

import java.util.List;

public interface BlogDao {
    // 新建一条博文记录返回主键id
    public String insertBlog(BlogDomain blogDomain);

    // 查询一个活动下的全部博文
    public List<BlogDomain> queryBlogByActivityId(String aId);
}
