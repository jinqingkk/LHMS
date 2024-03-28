package edu.xhu.lhms.module.common.service;

import edu.xhu.lhms.module.common.entity.Image;
import edu.xhu.lhms.module.common.vo.Result;
import edu.xhu.lhms.module.common.vo.Search;
import org.springframework.web.multipart.MultipartFile;

/**
 * ImageService
 */
public interface ImageService extends ModelService<Image, Search> {

	Result<String> uploadImage(String imageTypeName, MultipartFile multipartFile);
}
