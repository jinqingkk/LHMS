package edu.xhu.lhms.module.common.service;

import edu.xhu.lhms.module.common.entity.Image;
import edu.xhu.lhms.module.common.vo.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * ImageService
 */
public interface ImageService extends ModelService<Image> {

	Result<String> uploadImage(String imageTypeName, MultipartFile multipartFile);
}
