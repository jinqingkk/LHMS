package edu.xhu.lhms.module.permission.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.xhu.lhms.module.permission.entity.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PermissionDao extends BaseMapper<Permission> {
    int selectByAuthName(String parentName);

    @Delete("delete from permissiom where parent_id = #{id} ")
    int deleteByParentId(int id);

    @Select(" select * from permission where identity = #{identity} and parent_id = 0 ")
    List<Permission> selectByIdentity(int identity);

    @Select(" select * from permission where  parent_id = #{id} ")
    List<Permission> selectByParentId(int id);
}
