package edu.xhu.lhms.module.volunActivity.controller;

import com.github.pagehelper.PageInfo;
import edu.xhu.lhms.module.common.vo.Result;
import edu.xhu.lhms.module.volunActivity.entity.VolunActivity;
import edu.xhu.lhms.module.volunActivity.service.VolunActivityService;
import edu.xhu.lhms.module.volunActivity.vo.VolunActivityVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/volunActivity")
public class VolunActivityController {

    @Autowired
    private VolunActivityService volunActivityService;

    @PostMapping(value = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<VolunActivity> insertModel(@RequestBody VolunActivity model) {
        return volunActivityService.insertModel(model);
    }
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<VolunActivity> updateModel(@RequestBody VolunActivity model) {
        return volunActivityService.updateModel(model);
    }
    @DeleteMapping(value = "/delete/{id}")
    public Result<Object> deleteModelById(@PathVariable int id) {
        return volunActivityService.deleteModelById(id);
    }
    @GetMapping(value = "/findActByid/{id}")
    public Result<VolunActivity> getModelById(@PathVariable int id) {
        return volunActivityService.getModelById(id);
    }
    @GetMapping(value = "/findActByTitle/{title}")
    public Result<VolunActivity> getModelByTitle(@PathVariable String title) {
        return volunActivityService.getModelByTitle(title);
    }
    @PostMapping(value = "/findDonas", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<VolunActivity> getModelsBySearch(@RequestBody VolunActivityVo search) {
        return volunActivityService.findModelsBySearch(search);
    }
}
