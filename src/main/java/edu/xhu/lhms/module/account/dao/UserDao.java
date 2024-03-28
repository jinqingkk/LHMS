package edu.xhu.lhms.module.account.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.xhu.lhms.module.account.entity.User;
import edu.xhu.lhms.module.account.vo.UserVo;
import edu.xhu.lhms.module.common.vo.Search;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BalanceDao
 */
@Mapper
@Repository
public interface UserDao extends BaseMapper<User> {
	@Select("select * from user_info where open_id = #{openId} limit 1")
    User selectByOpenId(@Param("openId") String openId) ;

    @Select("select * from user_info where user_name = #{userName} limit 1")
	User getUserByUserName(@Param("userName") String userName);
	@Select("select * from user_info where user_name = #{userName} and password=#{password} limit 1")
	User getIdByUsernameAndPassword(@Param("userName") String userName,@Param("password") String password);
	@Select("<script>"
			+ "select * from user_info "
			+ "<where> "
			+ "<if test='keyword != \"\" and keyword != null'>"
			+ " and (user_name like '%${keyword}%' ) "
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
	List<User> getUsersBySearch(Search search);

	@Select("select user_name from user_info")
	List<String> getAllUserName();

	@Select("<script>"
			+ "select * from user_info "
			+ "<where> "
			+ "<if test='userId > 0'>"
			+ " and (id = #{userId} ) "
			+ "</if>"
			+ "<if test='userName  != \"\" and userName != null'>"
			+ " and (user_Name = #{userName} ) "
			+ "</if>"
			+ "<if test='gender  != \"\" and gender != null'>"
			+ " and (gender = #{gender} ) "
			+ "</if>"
			+ "<if test='rellname  != \"\" and rellname != null'>"
			+ " and (rellname = #{rellname} ) "
			+ "</if>"
			+ "<if test='phoneNumber  != \"\" and phoneNumber != null'>"
			+ " and (phone_Number = #{phoneNumber} ) "
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
//	List<User> getUsersBySearchAndUserId( Search search,
//									   @Param("userId") int userId,
//									   @Param("userName") String userName,
//									   @Param("gender") String gender,
//									   @Param("rellname") String rellname,
//										 @Param("phoneNumber")String phoneNumber);
	List<User> getUsersBySearchAndUserId(@Param("sort")String sort,@Param("direction") String direction,  @Param("userId")int userId, @Param("userName") String userName, @Param("gender")String gender,@Param("rellname") String rellname, @Param("phoneNumber")String phoneNumber);
	@Select("<script>"
			+ "select * from user_info "
			+ "<where> "
			+ "<if test='userName  != \"\" and userName != null'>"
			+ " and (user_Name = #{userName} ) "
			+ "</if>"
			+ "<if test='gender  != \"\" and gender != null'>"
			+ " and (gender = #{gender} ) "
			+ "</if>"
			+ "<if test='rellname  != \"\" and rellname != null'>"
			+ " and (rellname = #{rellname} ) "
			+ "</if>"
			+ "<if test='phoneNumber  != \"\" and phoneNumber != null'>"
			+ " and (phone_Number = #{phoneNumber} ) "
			+ "</if>"
			+ "<if test='email  != \"\" and email != null'>"
			+ " and (email = #{email} ) "
			+ "</if>"
			+ "<if test='idNumber  != \"\" and idNumber != null'>"
			+ " and (id_number = #{idNumber} ) "
			+ "</if>"
			+ "<if test='identity  != \"\" and identity != null'>"
			+ " and (identity = #{identity} ) "
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
	List<User> findUsersByIdentity(UserVo search);

	@Update(" update user_info set identity = 1 where id = #{id} ")
	boolean adminToCommon(int id);
	@Update(" update user_info set identity = 2 where id = #{id} ")
	boolean commonToAdmin(int id);

	@Select("select id,user_name from user_info ")
    List<User> getUsernameList();
}
