package edu.xhu.lhms.module.common.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import edu.xhu.lhms.module.common.entity.Image;
import edu.xhu.lhms.module.common.vo.Search;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ImageDao
 */
@Mapper
@Repository
public interface ImageDao extends BaseMapper<Image> {

	@Select("select * from common_image where src = #{src}")
	Image getImageBySrc(String src);

	@Select("<script>"
			+ "select * from common_image "
			+ "<where> "
			+ "<if test='keyword != \"\" and keyword != null'>"
			+ " and (subject like '%${keyword}%' ) "
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
	List<Image> getImagesBySearch(Search search);

	@Delete("delete from common_image where subject = #{subject}")
	void deleteImagesBySubject(String subject);

	@Select("select * from common_image where subject = #{subject}")
	List<Image> getImagesBySubject(String subject);
}
