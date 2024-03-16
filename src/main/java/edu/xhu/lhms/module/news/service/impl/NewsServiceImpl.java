package edu.xhu.lhms.module.news.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.xhu.lhms.module.account.dao.UserDao;
import edu.xhu.lhms.module.common.vo.Result;
import edu.xhu.lhms.module.common.vo.Search;

import edu.xhu.lhms.module.news.dao.NewsDao;
import edu.xhu.lhms.module.news.entity.News;
import edu.xhu.lhms.module.news.entity.NewsLike;
import edu.xhu.lhms.module.news.service.NewsService;
import edu.xhu.lhms.module.news.vo.NewsLikeDao;
import edu.xhu.lhms.module.news.vo.NewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsDao newsDao;
    @Autowired
    private NewsLikeDao newsLikeDao;
    @Autowired
    private UserDao userDao;
    @Override
    public Result<News> insertModel(News model) {
        model.setCreateDate(LocalDateTime.now());
        model.setUpdateDate(LocalDateTime.now());
        model.setState("新");
        model.setLikeNumber(0);
        if(newsDao.insert(model)>0)
            return Result.ok();
        return Result.faild();
    }

    @Override
    public Result<News> updateModel(News model) {
        News news=newsDao.selectById(model.getId());
        if(model.getTitle()!=news.getTitle()){
            news.setTitle(model.getTitle());
        }
        if(model.getContent()!=news.getContent()){
            news.setContent(model.getContent());
        }
        if(model.getImage()!=news.getImage()){
            news.setImage(model.getImage());
        }
        if(model.getState()!=news.getState()){
            news.setState(model.getState());
        }
        news.setUpdateDate(LocalDateTime.now());
        if(newsDao.updateById(news)>0){
            return Result.ok(news);
        }
        return Result.faild();
    }

    @Override
    public Result<Object> deleteModelById(int id) {
        if(newsLikeDao.deleteByNewsId(id)>0){
            if(newsDao.deleteById(id)>0){
                return Result.ok();
            }
        }
        return Result.faild();
    }

    @Override
    public News getModelById(int id) {
        News news=newsDao.selectById(id);
        if (news!=null)
            news.setUsername(userDao.selectById(news.getUserId()).getUserName());
        return news;
    }

    @Override
    public PageInfo<News> getModelsBySearch(Search search) {
        return null;
    }

    @Override
    public Result<NewsLike> getLike(int id, int userId) {
        NewsLike newsLike=newsLikeDao.selectByNewIdAndUserId(id,userId);
        if(newsLike==null){
            newsLike.setNewsId(id);
            newsLike.setUserId(userId);
            if(newsLikeDao.insert(newsLike)>0){
                News news=newsDao.selectById(id);
                news.setLikeNumber(news.getLikeNumber()+1);
                newsDao.updateById(news);
                return Result.ok(newsLike);
            }
        }
        return Result.faild("已点赞");
    }

    @Override
    public Result<NewsLike> cancelLike(int id, int userId) {
        NewsLike newsLike=newsLikeDao.selectByNewIdAndUserId(id,userId);
        if(newsLike!=null){
            if(newsLikeDao.deleteById(newsLike.getId())>0){
                News news=newsDao.selectById(id);
                news.setLikeNumber(news.getLikeNumber()-1);
                newsDao.updateById(news);
                return Result.ok(newsLike);
            }
        }
        return Result.faild();
    }

    @Override
    public PageInfo<News> findModelsBySearch(NewsVo search) {
        if(search.getUsername()!=null||search.getUsername()==""){
            search.setUserId(userDao.getUserByUserName(search.getUsername()).getId());
        }
        search.initSearch();
        PageHelper.startPage(search.getCurrentPage(), search.getPageSize());
        return new PageInfo<>(Optional
                .ofNullable(newsDao.getActsBySearch(search))
                .orElse(Collections.emptyList()));
    }
}
