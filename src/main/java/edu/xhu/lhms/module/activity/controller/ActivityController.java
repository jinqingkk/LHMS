package edu.xhu.lhms.module.activity.controller;

import com.github.pagehelper.PageInfo;
import edu.xhu.lhms.module.activity.entity.Activity;
import edu.xhu.lhms.module.activity.service.ActivityService;
import edu.xhu.lhms.module.activity.vo.ActivityVo;
import edu.xhu.lhms.module.common.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    @PostMapping(value = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<Activity> insertModel(@RequestBody Activity model) {
        return activityService.insertModel(model);
    }
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<Activity> updateModel(@RequestBody Activity model) {
        return activityService.updateModel(model);
    }
    @GetMapping(value = "/updateState")
    public Result<Activity> updateModelState(@RequestParam("id") int id,@RequestParam("state") String state) {
        return activityService.updateModelState(id,state);
    }
    @DeleteMapping(value = "/delete/{id}")
    public Result<Object> deleteModelById(@PathVariable int id) {
        return activityService.deleteModelById(id);
    }
    @GetMapping(value = "/findActByid/{id}")
    public Result<Activity> getModelById(@PathVariable int id) {
        return activityService.getModelById(id);
    }
    @GetMapping(value = "/findActByTitle/{title}")
    public Result<Activity> getModelByUserName(@PathVariable String title) {
        return activityService.getModelByTitle(title);
    }
    @PostMapping(value = "/findActs", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<Activity> getModelsBySearch(@RequestBody ActivityVo search) {
        return activityService.findModelsBySearch(search);
    }
}
