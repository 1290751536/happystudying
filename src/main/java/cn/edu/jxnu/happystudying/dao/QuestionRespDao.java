package cn.edu.jxnu.happystudying.dao;

import cn.edu.jxnu.happystudying.domain.QuestionRespDomain;

import java.util.List;

public interface QuestionRespDao {
    // 根据问答编号返回全部的回复信息
    public List<QuestionRespDomain> queryById(String rId);

    // 插入一条回复，返回主键信息
    public String insertQuestionResp(QuestionRespDomain questionRespDomain);

    // 查询优质解答
    public List<QuestionRespDomain> queryAdoptQuestionResp(String qId);

    // 根据回复号设置一个为优质解答
    public void updateAdoptById(String rId);

    // 根据用户id查找到全部的回复信息
    public List<QuestionRespDomain> queryAllQuestionRespByUserId(String rUserId);
}
