package edu.xhu.lhms.module.news.service;

import com.github.pagehelper.PageInfo;
import edu.xhu.lhms.module.common.service.ModelService;
import edu.xhu.lhms.module.common.vo.Result;
import edu.xhu.lhms.module.news.entity.News;
import edu.xhu.lhms.module.news.entity.NewsLike;
import edu.xhu.lhms.module.news.vo.NewsVo;

public interface NewsService extends ModelService<News> {

    PageInfo<News> findModelsBySearch(NewsVo search);

    Result<NewsLike> getLike(int id, int userId);

    Result<NewsLike> cancelLike(int id, int userId);
}
