package edu.xhu.lhms.module.news.controller;

import com.github.pagehelper.PageInfo;
import edu.xhu.lhms.module.common.vo.Result;

import edu.xhu.lhms.module.news.entity.News;
import edu.xhu.lhms.module.news.entity.NewsLike;
import edu.xhu.lhms.module.news.service.NewsService;
import edu.xhu.lhms.module.news.vo.NewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @PostMapping(value = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<News> insertModel(@RequestBody News model) {
        return newsService.insertModel(model);
    }
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<News> updateModel(@RequestBody News model) {
        return newsService.updateModel(model);
    }
    @DeleteMapping(value = "/delete/{id}")
    public Result<Object> deleteModelById(@PathVariable int id) {
        return newsService.deleteModelById(id);
    }

    @GetMapping(value = "/findActByid/{id}")
    public Result<News> getModelById(@PathVariable int id) {
        return newsService.getModelById(id);
    }

    @GetMapping(value = "/getLike")
    public Result<NewsLike> getLike(@RequestParam("id") int id,@RequestParam("userId")int userId) {
        return newsService.getLike(id,userId);
    }
    @GetMapping(value = "/cancelLike")
    public Result<NewsLike> cancelLike(@RequestParam("id") int id,@RequestParam("userId")int userId) {
        return newsService.cancelLike(id,userId);
    }

    @PostMapping(value = "/findNews", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<News> getModelsBySearch(@RequestBody NewsVo search) {
        return newsService.findModelsBySearch(search);
    }
}
