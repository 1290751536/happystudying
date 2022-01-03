package cn.edu.jxnu.happystudying.dao.impl;

import cn.edu.jxnu.happystudying.dao.QuestionRespDao;
import cn.edu.jxnu.happystudying.domain.QuestionRespDomain;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ImplQuestionRespDaoTest {
    QuestionRespDao questionRespDao = new ImplQuestionRespDao();

    @Test
    public void queryById() {
        System.out.println(questionRespDao.queryById("123"));
    }

    @Test
    public void insertQuestionResp() {
        QuestionRespDomain questionRespDomain = new QuestionRespDomain();
        questionRespDomain.setrUserId("222");
        questionRespDomain.setrUserName("王五");
        questionRespDomain.setrUserAvatar("map.jpg");
        questionRespDomain.setrQuestionId("333");
        questionRespDomain.setrTime(new Date());
        questionRespDomain.setrContent("我也是一条回复");
        questionRespDao.insertQuestionResp(questionRespDomain);
    }

    @Test
    public void queryAllQuestionRespByUserId() {
        System.out.println(questionRespDao.queryAllQuestionRespByUserId("123"));
    }
}