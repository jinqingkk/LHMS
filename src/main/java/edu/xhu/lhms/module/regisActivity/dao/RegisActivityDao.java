package edu.xhu.lhms.module.regisActivity.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.xhu.lhms.module.regisActivity.entity.RegisActivity;
import edu.xhu.lhms.module.regisActivity.vo.RegisActivityVo;
import edu.xhu.lhms.module.volunActivity.entity.VolunActivity;
import edu.xhu.lhms.module.volunActivity.vo.VolunActivityVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RegisActivityDao extends BaseMapper<RegisActivity> {
    @Select("<script>"
            + "select * from regis_activity "
            + "<where> "
            + "<if test='actId != 0 and actId != null'>"
            + " and (act_id = #{actId} ) "
            + "</if>"
            + "<if test='userId != 0 and userId != null'>"
            + " and (user_id = #{userId} ) "
            + "</if>"
            + "<if test='state != \"\" and state != null'>"
            + " and (state = #{state} ) "
            + "</if>"
            + "<if test=' createDate != null'>"
            + " and (create_Date &gt;=  #{createDate} ) "
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
    List<RegisActivity> getRegisActsBySearch(RegisActivityVo search);

    @Select("select count(id) from regis_activity where  act_id = #{actId} ")//user_id = #{userId} and
    int getRegisCount(int actId);//int userId,

    @Delete("delete from regis_activity where act_id = #{id}")
    void deleteByVolunActId(int id);

    @Select("select * from regis_activity where act_id = #{actId} ")
    RegisActivity selectByActId(int actId);
    @Select("select * from regis_activity where user_id = #{userId} ")
    RegisActivity selectByUserId(int userId);

    @Select("select * from regis_activity where act_id = #{actId} and user_id = #{userId} and (state != '未审核' or state != '已审核通过') ")
    RegisActivity selectByActIdAndUserId(Integer actId, Integer userId);
}
