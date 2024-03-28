package edu.xhu.lhms.module.regisActivity.controller;
import com.github.pagehelper.PageInfo;
import edu.xhu.lhms.module.common.vo.Result;
import edu.xhu.lhms.module.regisActivity.entity.RegisActivity;
import edu.xhu.lhms.module.regisActivity.service.RegisActivityService;
import edu.xhu.lhms.module.regisActivity.vo.RegisActivityVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/regisActivity")
public class RegisActivityController {
    @Autowired
    private RegisActivityService regisActivityService;
    @PostMapping(value = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<RegisActivity> insertModel(@RequestBody RegisActivity model) {return regisActivityService.insertModel(model);
    }
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<RegisActivity> updateModel(@RequestBody RegisActivity model) {return regisActivityService.updateModel(model);
    }
    @DeleteMapping(value = "/delete/{id}")
    public Result<Object> deleteModelById(@PathVariable int id) {return regisActivityService.deleteModelById(id);
    }
    @GetMapping(value = "/findRegisActByid/{id}")
    public Result<RegisActivity> getModelById(@PathVariable int id) {return regisActivityService.getModelById(id);
    }
    @GetMapping(value = "/findRegisActByUserId/{userId}")
    public Result<RegisActivity> getModelByUserId(@PathVariable int userId) {return regisActivityService.getModelByUserId(userId);
    }
    @GetMapping(value = "/findRegisActByActId/{actId}")
    public Result<List<RegisActivity>> getModelByActId(@PathVariable int actId) {return regisActivityService.getModelByActId(actId);
    }
    @PostMapping(value = "/findRegisActs", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<PageInfo<RegisActivity>> getModelsBySearch(@RequestBody RegisActivityVo search) {return regisActivityService.findModelsBySearch(search);
    }
}