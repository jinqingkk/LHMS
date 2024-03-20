package edu.xhu.lhms.module.permission.controller;

import edu.xhu.lhms.module.common.vo.Result;
import edu.xhu.lhms.module.permission.entity.Permission;
import edu.xhu.lhms.module.permission.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permission")
public class PermissionController{
    @Autowired
    private PermissionService permissionService;

    @PostMapping(value = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<Permission> insertModel(@RequestBody Permission model) {
        return permissionService.insertModel(model);
    }
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<Permission> updateModel(@RequestBody Permission model) {
        return permissionService.updateModel(model);
    }
    @DeleteMapping(value = "/delete/{id}")
    public Result<Object> deleteModelById(@PathVariable int id) {
        return permissionService.deleteModelById(id);
    }
    @GetMapping(value = "/getPermission/{identity}")
    public Result<List<Permission>> getPermission(@PathVariable int identity) {
        return permissionService.getPermission(identity);
    }
}
