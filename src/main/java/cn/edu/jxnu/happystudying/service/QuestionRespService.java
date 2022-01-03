package cn.edu.jxnu.happystudying.service;

import cn.edu.jxnu.happystudying.domain.QuestionRespDomain;

import java.util.List;

public interface QuestionRespService {
    public List<QuestionRespDomain> queryById(String Id);

    public String insertQuestionResp(QuestionRespDomain questionRespDomain);

    public List<QuestionRespDomain> queryAdoptQuestionResp(String qId);

    public void updateAdoptById(String rId);

    public List<QuestionRespDomain> queryAllQuestionRespByUserId(String rUserId);
}
