package edu.xhu.lhms.module.activity.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.xhu.lhms.module.activity.entity.Activity;
import edu.xhu.lhms.module.activity.vo.ActivityVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ActivityDao  extends BaseMapper<Activity> {
    @Select("select * from activity where title like '%#{keyword}%'")
    Activity selectByTitle(String title);

    @Select("<script>"
            + "select * from activity "
            + "<where> "
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
            + "<if test='userId != \"\" and userId != null'>"
            + " and (user_id like '%${userId}%' ) "
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
    List<Activity> getActsBySearch(ActivityVo search);
}
