package cn.edu.jxnu.happystudying.service;

import cn.edu.jxnu.happystudying.domain.QuestionDomain;

import java.util.List;

public interface QuestionService {
    public String insertQuestion(QuestionDomain questionDomain);

    public void updateIconById(String qId, String qIcon);

    public List<QuestionDomain> queryTopQuestion();

    public List<QuestionDomain> queryAllQuestionSortByPublishTime();

    public List<QuestionDomain> queryAllQuestionSortByNumber();

    public List<QuestionDomain> queryEssenceQuestionSortByPublishTime();

    public List<QuestionDomain> queryEssenceQuestionSortByNumber();

    public List<QuestionDomain> queryByKey(String key);

    public void updateViewNumberById(String qId,String x);

    public void updateResponsesNumberById(String qId,String x);

    public List<QuestionDomain> queryById(String qId);

    public void updateQuestionById(QuestionDomain questionDomain);

    public List<QuestionDomain> queryAllQuestionByUserId(String qUserId);
}
