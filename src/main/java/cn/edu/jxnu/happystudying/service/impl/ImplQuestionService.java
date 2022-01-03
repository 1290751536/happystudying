package cn.edu.jxnu.happystudying.service.impl;

import cn.edu.jxnu.happystudying.dao.QuestionDao;
import cn.edu.jxnu.happystudying.dao.impl.ImplQuestionDao;
import cn.edu.jxnu.happystudying.domain.QuestionDomain;
import cn.edu.jxnu.happystudying.service.QuestionService;

import java.util.List;

public class ImplQuestionService implements QuestionService {
    QuestionDao questionDao = new ImplQuestionDao();

    @Override
    public String insertQuestion(QuestionDomain questionDomain) {
        return questionDao.insertQuestion(questionDomain);
    }

    @Override
    public void updateIconById(String qId, String qIcon) {
        questionDao.updateIconById(qId, qIcon);
    }

    @Override
    public List<QuestionDomain> queryTopQuestion() {
        return questionDao.queryTopQuestion();
    }

    @Override
    public List<QuestionDomain> queryAllQuestionSortByPublishTime() {
        return questionDao.queryAllQuestionSortByPublishTime();
    }

    @Override
    public List<QuestionDomain> queryAllQuestionSortByNumber() {
        return questionDao.queryAllQuestionSortByNumber();
    }

    @Override
    public List<QuestionDomain> queryEssenceQuestionSortByPublishTime() {
        return questionDao.queryEssenceQuestionSortByPublishTime();
    }

    @Override
    public List<QuestionDomain> queryEssenceQuestionSortByNumber() {
        return questionDao.queryEssenceQuestionSortByNumber();
    }

    @Override
    public List<QuestionDomain> queryByKey(String key) {
        return questionDao.queryByKey("%" + key + "%");
    }

    @Override
    public void updateViewNumberById(String qId, String x) {
        questionDao.updateViewNumberById(qId, x);
    }

    @Override
    public void updateResponsesNumberById(String qId, String x) {
        questionDao.updateResponsesNumberById(qId, x);
    }

    @Override
    public List<QuestionDomain> queryById(String qId) {
        return questionDao.queryById(qId);
    }

    @Override
    public void updateQuestionById(QuestionDomain questionDomain) {
        questionDao.updateQuestionById(questionDomain);
    }

    @Override
    public List<QuestionDomain> queryAllQuestionByUserId(String qUserId) {
        return questionDao.queryAllQuestionByUserId(qUserId);
    }
}
