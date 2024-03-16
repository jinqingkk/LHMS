package edu.xhu.lhms.module.volunActivity.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.xhu.lhms.module.volunActivity.entity.VolunActivity;
import edu.xhu.lhms.module.volunActivity.vo.VolunActivityVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface VolunActivityDao extends BaseMapper<VolunActivity> {
    @Select("<script>"
            + "select * from volun_activity "
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
    List<VolunActivity> getActsBySearch(VolunActivityVo search);
    @Select("select * from volun_activity where title = #{title")
    VolunActivity selectByTitle(String title);
}
