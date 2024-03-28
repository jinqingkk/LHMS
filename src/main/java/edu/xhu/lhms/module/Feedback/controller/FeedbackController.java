package edu.xhu.lhms.module.Feedback.controller;


import com.github.pagehelper.PageInfo;
import edu.xhu.lhms.module.Feedback.entity.Feedback;
import edu.xhu.lhms.module.Feedback.service.FeedbackService;
import edu.xhu.lhms.module.Feedback.vo.FeedbackVo;
import edu.xhu.lhms.module.common.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

//@CrossOrigin //解决跨域问题
@RestController
@RequestMapping("/feedback")
public class FeedbackController {
	@Autowired
	private FeedbackService feedbackService;

	@PostMapping(value = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Result<Feedback> insertModel(@RequestBody Feedback model) {
		return feedbackService.insertModel(model);
	}

	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Result<Feedback> updateModel(@RequestBody Feedback model) {
		return feedbackService.updateModel(model);
	}
	@DeleteMapping(value = "/delete/{id}")
	public Result<Object> deleteModelById(@PathVariable int id) {
		return feedbackService.deleteModelById(id);
	}
	@GetMapping(value = "/getFeedbackById/{id}")
	public Result<Feedback> getModelById(@PathVariable int id) {
		return feedbackService.getModelById(id);
	}

	@PostMapping(value = "/getFeedbacks", consumes = MediaType.APPLICATION_JSON_VALUE)
	public PageInfo<Feedback> getModelsBySearch(@RequestBody FeedbackVo search) {
		return feedbackService.getModelsBySearch(search);
	}

}
