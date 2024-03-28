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
//    @Select("<script>"
//            + "select * from news "
//            + "<where> "
//            + "<if test='title  != \"\" and title != null'>"
//            + " and (title like concat('%',#{title},'%') ) "
//            + "</if>"
//            + "<if test='userId  != \"\" and userId != null'>"
//            + " and (user_id = #{userId} ) "
//            + "</if>"
//            + "<if test='(startDate  != \"\" and startDate != null) and (endDate  = \"\" or endDate = null)'>"
//            + " and (start_date > #{startDate} ) "
//            + "</if>"
//            + "<if test='(endDate  != \"\" and endDate != null) and (startDate  = \"\" or startDate = null)'>"
//            + " and (`end_date` &lt; #{endDate} ) "
//            + "</if>"
//            + "<if test='(endDate  != \"\" and endDate != null) and (startDate  != \"\" and startDate != null) '>"
//            + " and ( start_date >= #{startDate} and start_date &lt; end_date and end_date &lt;= #{endDate}) "
//            + "</if>"
//            + "<if test='region  != \"\" and region != null'>"
//            + " and (region like concat('%',#{region},'%') ) "
//            + "</if>"
//            + "</where>"
//            + "<choose>"
//            + "<when test='sort != \"\" and sort != null'>"
//            + " order by #{sort} #{direction}"
//            + "</when>"
//            + "<otherwise>"
//            + " order by id desc"
//            + "</otherwise>"
//            + "</choose>"
//            + "</script>")
@Select("<script>"
        + "select * from news "
        + "<where> "
        + "<if test='title  != \"\" and title != null'>"
        + " and (title like concat('%',#{title},'%') ) "
        + "</if>"
        + "<if test='username  != \"\" and username != null and userId != 0'>"
        + " and (user_id = #{userId} ) "
        + "</if>"
        + "<if test='username  != \"\" and username != null and userId  == 0'>"
        + " and (user_id = 0 ) "
        + "</if>"
        + "<if test='startDate != null'>"
        + " and (start_date &gt;= #{startDate} ) "
        + "</if>"
        + "<if test='endDate != null'>"
        + " and (end_date &lt;= #{endDate} ) "
        + "</if>"
        + "<if test='region  != \"\" and region != null'>"
        + " and (region like concat('%',#{region},'%') ) "
        + "</if>"
        + "<if test='state  != \"\" and state != null'>"
        + " and (state = #{state} ) "
        + "</if>"
        + "</where>"
        + "<choose>"
        + "<when test='sort != \"\" and sort != null'>"
        + " order by #{sort} #{direction}"
        + "</when>"
        + "<otherwise>"
        + " order by id desc"
        + "</otherwise>"
        + "</choose>"
        + "</script>")
    List<News> getNewsBySearch(NewsVo search);

    @Select(" select max(id) from news ")
    int getMaxId();

    @Select("select * from news where title = #{title} ")
    News selectByTitle(String title);
}
