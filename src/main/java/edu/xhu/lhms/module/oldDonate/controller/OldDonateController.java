package edu.xhu.lhms.module.oldDonate.controller;

import com.github.pagehelper.PageInfo;
import edu.xhu.lhms.module.common.vo.Result;
import edu.xhu.lhms.module.oldDonate.entity.OlddonateInfo;
import edu.xhu.lhms.module.oldDonate.service.OldDonateService;
import edu.xhu.lhms.module.oldDonate.vo.OldDonateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oldDonate")
public class OldDonateController {

    @Autowired
    private OldDonateService oldDonateService;

    @PostMapping(value = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<OlddonateInfo> insertModel(@RequestBody OlddonateInfo model) {
        return oldDonateService.insertModel(model);
    }
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<OlddonateInfo> updateModel(@RequestBody OlddonateInfo model) {
        return oldDonateService.updateModel(model);
    }
    @DeleteMapping(value = "/delete/{id}")
    public Result<Object> deleteModelById(@PathVariable int id) {
        return oldDonateService.deleteModelById(id);
    }
    @GetMapping(value = "/findActByid/{id}")
    public OlddonateInfo getModelById(@PathVariable int id) {
        return oldDonateService.getModelById(id);
    }
    @GetMapping(value = "/findActByTitle/{donaThings}")
    public Result<OlddonateInfo> getModelByDonaThings(@PathVariable String donaThings) {
        return oldDonateService.getModelByDonaThings(donaThings);
    }
    @PostMapping(value = "/findDonas", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<OlddonateInfo> getModelsBySearch(@RequestBody OldDonateVo search) {
        return oldDonateService.findModelsBySearch(search);
    }
}
