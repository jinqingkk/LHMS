package edu.xhu.lhms.module.common.controller;

import com.github.pagehelper.PageInfo;

import edu.xhu.lhms.module.common.entity.Image;
import edu.xhu.lhms.module.common.service.ImageService;
import edu.xhu.lhms.module.common.vo.Result;
import edu.xhu.lhms.module.common.vo.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description ImageController
 * @Author JiangHu
 * @Date 2023/6/12 13:47
 */
@RestController
@RequestMapping("/api/common")
public class ImageController {

	@Autowired
	private ImageService imageService;


	/**
	 * 127.0.0.1/api/common/image/timeLine ---- post
	 */
	@PostMapping(value = "/image/{imageTypeName}")
	public Result<String> uploadImage(
			@PathVariable String imageTypeName,
			@RequestParam("file") MultipartFile file) {
		return imageService.uploadImage(imageTypeName, file);
	}

	/**
	 * 127.0.0.1/api/common/image ---- post
	 */
	@PostMapping(value = "/image", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Result<Image> insertModel(@RequestBody Image model) {
		return imageService.insertModel(model);
	}

	/**
	 * 127.0.0.1/api/common/image ---- put
	 */
	@PutMapping(value = "/image", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Result<Image> updateModel(@RequestBody Image model) {
		return imageService.updateModel(model);
	}

	/**
	 * 127.0.0.1/api/common/image/1 ---- delete
	 */
	@DeleteMapping(value = "/image/{id}")
	public Result<Object> deleteModelById(@PathVariable int id) {
		return imageService.deleteModelById(id);
	}

	/**
	 * 127.0.0.1/api/common/image/1 ---- get
	 */
	@GetMapping(value = "/image/{id}")
	public Result<Image> getModelById(@PathVariable int id) {
		return imageService.getModelById(id);
	}

	/**
	 * 127.0.0.1/api/common/images ---- post
	 */
	@PostMapping(value = "/images", consumes = MediaType.APPLICATION_JSON_VALUE)
	public PageInfo<Image> getModelsBySearch(@RequestBody Search search) {
		return imageService.getModelsBySearch(search);
	}
}
