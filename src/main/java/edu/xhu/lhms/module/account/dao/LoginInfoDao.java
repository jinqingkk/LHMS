package edu.xhu.lhms.module.account.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.xhu.lhms.module.account.entity.LoginInfo;
import edu.xhu.lhms.module.account.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LoginInfoDao  extends BaseMapper<LoginInfo> {
    @Select("select * from login_info where user_id = #{id} and create_date = update_date order by id desc limit 1")
    LoginInfo selectByUserId(@Param("id") int id);
}
