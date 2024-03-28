package edu.xhu.lhms.module.news.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.xhu.lhms.module.account.dao.UserDao;
import edu.xhu.lhms.module.account.entity.User;
import edu.xhu.lhms.module.common.dao.ImageDao;
import edu.xhu.lhms.module.common.entity.Image;
import edu.xhu.lhms.module.common.vo.ImageType;
import edu.xhu.lhms.module.common.vo.Result;
import edu.xhu.lhms.module.common.vo.Search;

import edu.xhu.lhms.module.news.dao.NewsDao;
import edu.xhu.lhms.module.news.entity.News;
import edu.xhu.lhms.module.news.entity.NewsLike;
import edu.xhu.lhms.module.news.service.NewsService;
import edu.xhu.lhms.module.news.dao.NewsLikeDao;
import edu.xhu.lhms.module.news.vo.NewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsDao newsDao;
    @Autowired
    private NewsLikeDao newsLikeDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ImageDao imageDao;
    @Override
    public Result<News> insertModel(News model) {
        model.setCreateDate(LocalDateTime.now());
        model.setUpdateDate(LocalDateTime.now());
//        model.setState("新");
        model.setLikeNumber(0);
        if(newsDao.insert(model)>0) {//需要加个锁
            int newsId=newsDao.getMaxId();
//             处理images
		    model.getImages().stream().forEach(item -> {
                item.setSubject(
                        String.format("%s%s", ImageType.NEWS.name, newsId));
                item.setCreateDate(LocalDateTime.now());
                item.setUpdateDate(LocalDateTime.now());
                imageDao.insert(item);
            });
            return Result.ok();
        }
        return Result.faild();
    }

    @Override
    public Result<News> updateModel(News model) {

        News news=newsDao.selectById(model.getId());
        if(model.getTitle()!=null&&model.getTitle()!=news.getTitle()){
            news.setTitle(model.getTitle());
        }
        if(model.getContent()!=null&&model.getContent()!=news.getContent()){
            news.setContent(model.getContent());
        }
        if(model.getState()!=null&&model.getState()!=news.getState()){
            news.setState(model.getState());
        }
        if(model.getRegion()!=null&&model.getRegion()!=news.getRegion()){
            news.setRegion(model.getRegion());
        }
        if(model.getStartDate()!=null&&model.getStartDate()!=news.getStartDate()){
            news.setStartDate(model.getStartDate());
        }
        if(model.getEndDate()!=null&&model.getEndDate()!=news.getEndDate()){
            news.setEndDate(model.getEndDate());
        }
        news.setUpdateDate(LocalDateTime.now());
        // 处理images
        if(model.getImages()!=null){
            imageDao.deleteImagesBySubject(
                    String.format("%s%s", ImageType.NEWS.name, model.getId()));
            model.getImages().stream().forEach(item -> {
                item.setSubject(
                        String.format("%s%s", ImageType.NEWS.name, model.getId()));
                item.setCreateDate(LocalDateTime.now());
                item.setUpdateDate(LocalDateTime.now());
                imageDao.insert(item);
            });
        }
        if(newsDao.updateById(news)>0){
            return Result.ok(news);
        }
        return Result.faild();
    }

    @Override
    public Result<Object> deleteModelById(int id) {
        newsLikeDao.deleteByNewsId(id);
        imageDao.deleteImagesBySubject(String.format("%s%s", ImageType.NEWS.name, id));
        return Result.ok(newsDao.deleteById(id));
    }

    @Override
    public Result<News> getModelById(int id) {
        News news=newsDao.selectById(id);
        if (news!=null){
            news.setUsername(userDao.selectById(news.getUserId()).getUserName());
            news.setImages(imageDao.getImagesBySubject(ImageType.NEWS.name+news.getId()));
            news.setLikeNumber(newsLikeDao.getLikeCount(news.getId()));
        }

        return Result.ok(news);
    }

    @Override
    public PageInfo<News> getModelsBySearch(NewsVo search) {
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
    public Result<News> getModelByTitle(String title) {
        News news=newsDao.selectByTitle(title);
        if(news==null)
            return Result.ok(null);
        news.setImages(imageDao.getImagesBySubject(ImageType.NEWS.name+news.getId()));
        news.setLikeNumber(newsLikeDao.getLikeCount(news.getId()));
        news.setUsername(userDao.selectById(news.getUserId()).getUserName());
        return Result.ok(news);
    }

    @Override
    public Result<PageInfo<News>> findModelsBySearch(NewsVo search) {
        if(search.getUsername()!=null&&search.getUsername()!=""){
            User user=userDao.getUserByUserName(search.getUsername());
            if(user!=null)
            {
                search.setUserId(user.getId());
            }
        }
//        List<News> newsList=newsDao.getActsBySearch(search);
//        for(News item:newsList){
//
//        }
        search.initSearch();
        PageHelper.startPage(search.getCurrentPage(), search.getPageSize());
        PageInfo<News> pageInfo = new PageInfo<News>(Optional
                .ofNullable(newsDao.getNewsBySearch(search))
                .orElse(Collections.emptyList()));
        pageInfo.getList().stream().forEach(item -> {
            List<Image> images = Optional
                    .ofNullable(imageDao.getImagesBySubject(
                            String.format("%s%s", ImageType.NEWS.name, item.getId())))
                    .orElse(Collections.emptyList());
            item.setImages(images);
            item.setUsername(userDao.selectById(item.getUserId()).getUserName());
            item.setLikeNumber(newsLikeDao.getLikeCount(item.getId()));
        });
        return Result.ok(pageInfo);

    }
}
