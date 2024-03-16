package edu.xhu.lhms.module.loveDonate.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.xhu.lhms.module.loveDonate.entity.LovedonateInfo;
import edu.xhu.lhms.module.loveDonate.vo.LoveDonateVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LoveDonateDao extends BaseMapper<LovedonateInfo> {
    @Select("<script>"
            + "select * from lovedonate_info "
            + "<where> "
            + "<if test='donaThings != \"\" and donaThings != null'>"
            + " and (dona_things like '%${donaThings}%' ) "
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
    List<LovedonateInfo> getActsBySearch(LoveDonateVo search);
    @Select("select * from lovedonate_info where dona_things = #{donaThings")
    LovedonateInfo selectByDonaThing(String donaThings);
}
