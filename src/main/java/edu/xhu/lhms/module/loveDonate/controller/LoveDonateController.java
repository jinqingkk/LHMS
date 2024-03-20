package edu.xhu.lhms.module.loveDonate.controller;

import com.github.pagehelper.PageInfo;
import edu.xhu.lhms.module.common.vo.Result;
import edu.xhu.lhms.module.loveDonate.entity.LovedonateInfo;
import edu.xhu.lhms.module.loveDonate.service.LoveDonateService;
import edu.xhu.lhms.module.loveDonate.vo.LoveDonateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loveDonate")
public class LoveDonateController {

    @Autowired
    private LoveDonateService loveDonateService;

    @PostMapping(value = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<LovedonateInfo> insertModel(@RequestBody LovedonateInfo model) {
        return loveDonateService.insertModel(model);
    }
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<LovedonateInfo> updateModel(@RequestBody LovedonateInfo model) {
        return loveDonateService.updateModel(model);
    }
    @DeleteMapping(value = "/delete/{id}")
    public Result<Object> deleteModelById(@PathVariable int id) {
        return loveDonateService.deleteModelById(id);
    }
    @GetMapping(value = "/findActByid/{id}")
    public Result<LovedonateInfo> getModelById(@PathVariable int id) {
        return loveDonateService.getModelById(id);
    }
    @GetMapping(value = "/findActByTitle/{donaThings}")
    public Result<LovedonateInfo> getModelByDonaThings(@PathVariable String donaThings) {
        return loveDonateService.getModelByDonaThings(donaThings);
    }
    @PostMapping(value = "/findDonas", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<LovedonateInfo> getModelsBySearch(@RequestBody LoveDonateVo search) {
        return loveDonateService.findModelsBySearch(search);
    }
}
