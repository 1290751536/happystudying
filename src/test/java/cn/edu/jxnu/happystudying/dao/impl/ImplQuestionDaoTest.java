package cn.edu.jxnu.happystudying.dao.impl;

import cn.edu.jxnu.happystudying.dao.QuestionDao;
import cn.edu.jxnu.happystudying.domain.QuestionDomain;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class ImplQuestionDaoTest {
    QuestionDao questionDao = new ImplQuestionDao();

    @Test
    public void insertQuestion() {
        QuestionDomain questionDomain = new QuestionDomain();
        questionDomain.setqUserId("123");
        questionDomain.setqUserName("liusheng");
        questionDomain.setqPublishTime(new Date());
        questionDomain.setqTitle("baiduyixia");
        questionDomain.setqDescription("$$x+y=2$$");
        questionDomain.setqDiamondNumber(123);
        System.out.println(questionDao.insertQuestion(questionDomain));
    }

    @Test
    public void queryAllQuestionSortByPublishTime() {
        System.out.println(questionDao.queryAllQuestionSortByPublishTime());
    }

    @Test
    public void queryAllQuestionSortByNumber() {
        System.out.println(questionDao.queryAllQuestionSortByNumber());
    }

    @Test
    public void queryByKey() {
        System.out.println(questionDao.queryByKey("%生成树%"));
    }

    @Test
    public void updateViewNumberById() {
        questionDao.updateViewNumberById("10000011", "-23");
    }

    @Test
    public void updateResponsesNumberById() {
        questionDao.updateResponsesNumberById("10000011", "-23");
    }

    @Test
    public void queryById() {
        System.out.println(questionDao.queryById("10000011"));
    }
}