package edu.xhu.lhms.module.needThings.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.xhu.lhms.module.needThings.entity.NeedThing;
import edu.xhu.lhms.module.needThings.vo.NeedThingVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NeedThingDao extends BaseMapper<NeedThing> {
    @Select("<script>"
            + "select * from need_thing "
            + "<where> "
            + "<if test='item != \"\" and item != null'>"
            + " and (item like '%${item}%' ) "
            + "</if>"
            + "<if test='count != \"\" and count != null'>"
            + " and (count like '%${count}%' ) "
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
    List<NeedThing> getActsBySearch(NeedThingVo search);
    @Select("select * from need_thing where item = #{item")
    NeedThing selectByItem(String item);
}
