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
            + "<if test=' createDate != null'>"
            + " and (create_Date &gt;= #{createDate} ) "
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
    @Select("select * from volun_activity where title = #{title} ")
    VolunActivity selectByTitle(String title);
    @Select(" select max(id) from volun_activity ")
    int getMaxId();

    @Select("select id ,title from volun_activity ")
    List<VolunActivity> getTitleList();
}
