package edu.xhu.lhms.module.permission.service;

import edu.xhu.lhms.module.common.service.ModelService;
import edu.xhu.lhms.module.common.vo.Result;
import edu.xhu.lhms.module.permission.entity.Permission;

import java.util.List;

public interface PermissionService  extends ModelService<Permission> {
    Result<List<Permission>> getPermission(int identity);
}
