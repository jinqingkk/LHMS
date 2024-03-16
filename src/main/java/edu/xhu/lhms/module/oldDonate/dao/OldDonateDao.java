package edu.xhu.lhms.module.oldDonate.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.xhu.lhms.module.oldDonate.entity.OlddonateInfo;
import edu.xhu.lhms.module.oldDonate.vo.OldDonateVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OldDonateDao extends BaseMapper<OlddonateInfo> {
    @Select("<script>"
            + "select * from olddonate_info "
            + "<where> "
            + "<if test='oldthings != \"\" and oldthings != null'>"
            + " and (oldthings like '%${oldthings}%' ) "
            + "</if>"
            + "<if test='state != \"\" and state != null'>"
            + " and (state like '%${state}%' ) "
            + "</if>"
            + "<if test=' createDate != null'>"
            + " and (create_Date like '%${createDate}%' ) "
            + "</if>"
            + "<if test=' userId != \"\" and userId != null'>"
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
    List<OlddonateInfo> getActsBySearch(OldDonateVo search);
    @Select("select * from olddonate_info where oldthings = #{oldthings")
    OlddonateInfo selectByDonaThing(String oldthings);
}
