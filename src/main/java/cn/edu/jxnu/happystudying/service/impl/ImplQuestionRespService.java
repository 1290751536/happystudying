package cn.edu.jxnu.happystudying.service.impl;

import cn.edu.jxnu.happystudying.dao.QuestionRespDao;
import cn.edu.jxnu.happystudying.dao.impl.ImplQuestionRespDao;
import cn.edu.jxnu.happystudying.domain.QuestionRespDomain;
import cn.edu.jxnu.happystudying.service.QuestionRespService;

import java.util.List;

public class ImplQuestionRespService implements QuestionRespService {
    QuestionRespDao questionRespDao = new ImplQuestionRespDao();

    @Override
    public List<QuestionRespDomain> queryById(String Id) {
        return questionRespDao.queryById(Id);
    }

    @Override
    public String insertQuestionResp(QuestionRespDomain questionRespDomain) {
        return questionRespDao.insertQuestionResp(questionRespDomain);
    }

    @Override
    public List<QuestionRespDomain> queryAdoptQuestionResp(String qId) {
        return questionRespDao.queryAdoptQuestionResp(qId);
    }

    @Override
    public void updateAdoptById(String rId) {
        questionRespDao.updateAdoptById(rId);
    }

    @Override
    public List<QuestionRespDomain> queryAllQuestionRespByUserId(String rUserId) {
        return questionRespDao.queryAllQuestionRespByUserId(rUserId);
    }
}
