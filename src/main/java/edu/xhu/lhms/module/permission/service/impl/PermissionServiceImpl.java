package edu.xhu.lhms.module.permission.service.impl;

import com.github.pagehelper.PageInfo;
import edu.xhu.lhms.module.Feedback.entity.Feedback;
import edu.xhu.lhms.module.common.vo.Result;
import edu.xhu.lhms.module.common.vo.Search;
import edu.xhu.lhms.module.permission.dao.PermissionDao;
import edu.xhu.lhms.module.permission.entity.Permission;
import edu.xhu.lhms.module.permission.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;

    @Override
    public Result<Permission> insertModel(Permission model) {

        if(model.getParentName()!=null&&model.getParentName()!=""){
            model.setParentId(permissionDao.selectByAuthName(model.getParentName()));
        }
        if(permissionDao.insert(model)>0)
            return Result.ok(model);
        return Result.faild();
    }

    @Override
    public Result<Permission> updateModel(Permission model) {
        Permission permission=permissionDao.selectById(model.getId());
        if(model.getParentName()!=null&&model.getParentName()!=""){
            model.setParentId(permissionDao.selectByAuthName(model.getParentName()));
        }
        if(model.getAuthName()!= permission.getAuthName()){
            permission.setAuthName(model.getAuthName());
        }
        if(model.getPath()!=permission.getPath()){
            permission.setPath(model.getPath());
        }
        if(model.getParentId()!= permission.getParentId()){
            permission.setParentId(model.getParentId());
        }
        if(model.getIdentity()!= permission.getIdentity()){
            permission.setIdentity(model.getIdentity());
        }
        if(permissionDao.updateById(permission)>0)
            return Result.ok();
        return Result.faild();
    }

    @Override
    public Result<Object> deleteModelById(int id) {
        Permission permission=permissionDao.selectById(id);
        if(permission.getParentId()==0){
            if(permissionDao.deleteByParentId(id)>0 && permissionDao.deleteById(id)>0)
                return Result.ok();
        }
        if(permissionDao.deleteById(id)>0)
            return Result.ok();
        return Result.faild();
    }

    @Override
    public Result<Permission> getModelById(int id) {
        return  Result.ok(permissionDao.selectById(id));
    }

    @Override
    public Result<PageInfo<Feedback>> getModelsBySearch(Search search) {
        return null;
    }

    @Override
    public Result<List<Permission>> getPermission(int identity) {
        List<Permission> permissions=permissionDao.selectByIdentity(identity);
        if(permissions==null)
            return Result.faild();
        for(Permission item:permissions){
            List<Permission> children=permissionDao.selectByParentId(item.getId());
            item.setChildren(children);
        }
        return Result.ok(permissions);
    }
}
