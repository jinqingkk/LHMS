package edu.xhu.lhms.module.Feedback.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.xhu.lhms.module.Feedback.entity.Feedback;
import edu.xhu.lhms.module.Feedback.vo.FeedbackVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FeedbackDao extends BaseMapper<Feedback> {
    @Select("<script>"
            + "select * from feedback "
            + "<where> "
            + "<if test='userId != 0' >"
            + " and (user_id = #{userId} ) "
            + "</if>"
            + "<if test=' createDate != null'>"
            + " and (create_Date &gt;= #{createDate} ) "
            + "</if>"
            + "<if test='title  != \"\" and title != null'>"
            + " and (title like concat('%',#{title},'%') ) "
            + "</if>"
            + "<if test='content  != \"\" and content != null'>"
            + " and (content like concat('%',#{content},'%') ) "
            + "</if>"
            + "<if test='state  != \"\" and state != null'>"
            + " and (state = #{state} ) "
            + "</if>"
            + "</where>"
            + "<choose>"
//            + "<when test='sort != \"\" and sort != null'>"
//            + " order by #{sort} #{direction}"
//            + "</when>"
            + "<otherwise>"
            + " order by id desc"
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<Feedback> getFeedbacksBySearch(FeedbackVo search);
}
