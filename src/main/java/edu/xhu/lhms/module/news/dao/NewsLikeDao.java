package edu.xhu.lhms.module.news.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.xhu.lhms.module.news.entity.NewsLike;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface NewsLikeDao extends BaseMapper<NewsLike> {
    @Delete("delete from news_like where news_id = #{id} ")
    int deleteByNewsId(int id);
    @Select("select * from news_like where news_id = #{id} and user_id = #{userId} ")
    NewsLike selectByNewIdAndUserId(int id, int userId);

    @Select(" select count(id) from  news_like where news_id = #{id} ")
    int getLikeCount(int id);
}
