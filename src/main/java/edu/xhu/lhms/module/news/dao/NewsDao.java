package edu.xhu.lhms.module.news.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.xhu.lhms.module.news.entity.News;
import edu.xhu.lhms.module.news.vo.NewsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NewsDao extends BaseMapper<News> {
    @Select("<script>"
            + "select * from news "
            + "<where> "
            + "<if test='image != \"\" and image != null'>"
            + " and (image like '%${image}%' ) "
            + "</if>"
            + "<if test='title != \"\" and title != null'>"
            + " and (title like '%${title}%' ) "
            + "</if>"
            + "<if test='content != \"\" and content != null'>"
            + " and (content like '%${content}%' ) "
            + "</if>"
            + "<if test='state != \"\" and state != null'>"
            + " and (state like '%${state}%' ) "
            + "</if>"
            + "<if test=' createDate != null'>"
            + " and (create_Date like '%${createDate}%' ) "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test='sort != \"\" and sort != null'>"
            + " order by ${sort} ${direction}"
            + "</when>"
            + "<otherwise>"
            + " order by id desc"
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<News> getActsBySearch(NewsVo search);
}
