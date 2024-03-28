package edu.xhu.lhms.module.account.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.xhu.lhms.module.account.entity.LoginInfo;
import edu.xhu.lhms.module.account.entity.User;
import edu.xhu.lhms.module.account.vo.LoginInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LoginInfoDao  extends BaseMapper<LoginInfo> {
    @Select("select * from login_info where user_id = #{id} and create_date = update_date order by id desc limit 1")
    LoginInfo selectByUserId(@Param("id") int id);

    @Select("<script>"
            + "select * from login_info "
            + "<where> "
            + "<if test='userId != 0' >"
            + " and (user_id like #{userId} ) "
            + "</if>"
            + "<if test=' createDate != null'>"
            + " and (create_Date &gt;= #{createDate} ) "
            + "</if>"
            + "<if test='discription  != \"\" and discription != null'>"
            + " and (discription = #{discription} ) "
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
    List<LoginInfo> getLoginInfosBySearch(LoginInfoVo search);
}
