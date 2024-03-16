package edu.xhu.lhms.module.needThings.controller;

import com.github.pagehelper.PageInfo;
import edu.xhu.lhms.module.common.vo.Result;
import edu.xhu.lhms.module.needThings.entity.NeedThing;
import edu.xhu.lhms.module.needThings.service.NeedThingService;
import edu.xhu.lhms.module.needThings.vo.NeedThingVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/needThing")
public class NeedThingController {

    @Autowired
    private NeedThingService needThingService;

    @PostMapping(value = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<NeedThing> insertModel(@RequestBody NeedThing model) {
        return needThingService.insertModel(model);
    }
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<NeedThing> updateModel(@RequestBody NeedThing model) {
        return needThingService.updateModel(model);
    }
    @DeleteMapping(value = "/delete/{id}")
    public Result<Object> deleteModelById(@PathVariable int id) {
        return needThingService.deleteModelById(id);
    }
    @GetMapping(value = "/findActByid/{id}")
    public NeedThing getModelById(@PathVariable int id) {
        return needThingService.getModelById(id);
    }
    @GetMapping(value = "/findActByItem/{item}")
    public Result<NeedThing> getModelByItem(@PathVariable String item) {
        return needThingService.getModelByItem(item);
    }
    @PostMapping(value = "/findDonas", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<NeedThing> getModelsBySearch(@RequestBody NeedThingVo search) {
        return needThingService.findModelsBySearch(search);
    }
}
