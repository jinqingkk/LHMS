package edu.xhu.lhms.module.Feedback.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.xhu.lhms.module.Feedback.entity.Feedback;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FeedbackDao extends BaseMapper<Feedback> {
}
