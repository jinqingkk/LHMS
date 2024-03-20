package edu.xhu.lhms.module.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.xhu.lhms.config.ResourceConfigBean;
import edu.xhu.lhms.module.common.dao.ImageDao;
import edu.xhu.lhms.module.common.entity.Image;
import edu.xhu.lhms.module.common.service.ImageService;
import edu.xhu.lhms.module.common.vo.ImageType;
import edu.xhu.lhms.module.common.vo.Result;
import edu.xhu.lhms.module.common.vo.Search;
import edu.xhu.lhms.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

/**
 * @Description ImageServiceImpl
 * @Author JiangHu
 * @Date 2023/6/12 10:54
 */
@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ResourceConfigBean rcb;
	@Autowired
	private ImageDao imageDao;

	@Override
	public Result<String> uploadImage(String imageTypeName, MultipartFile multipartFile) {
		// 判断 multipartFile 是否为空
		if (multipartFile.isEmpty()) {
			return Result.faild("图片为空，请上传图片。");
		}

		// 判断 multipartFile 是否为图片
		if (!FileUtil.isImage(multipartFile)) {
			return Result.faild("请上传图片文件。");
		}

		// 获取图片类型枚举对象
		ImageType imageType = ImageType.getImageTypeByName(imageTypeName);
		if (imageType ==  null) {
			return Result.faild("该图片类型不支持");
		}

		// TODO 判断 multipartFile 尺寸大小是否在 ImageType 范围内
//		if(multipartFile.getSize()>imageType.size){
//			return  Result.faild("该尺寸大小不匹配");
//		}
		// 获取目标文件夹地址
		String osName = System.getProperty("os.name");
		String destFolder = String.format("%s%s",
				osName.toLowerCase().startsWith("win") ?
						rcb.getResourceLocationWindow() : rcb.getResourceLocationLinux(),
				imageType.name
				);
		File destFolderFile = new File(destFolder);
		if (!destFolderFile.exists()) {
			destFolderFile.mkdirs();
		}

		// 获取目标文件地址，并对 multipartFile 文件重命名
		String fileName = String.format("%s.%s", System.currentTimeMillis(),
				FileUtil.getFileType(multipartFile.getOriginalFilename())
				);
		String destFile = String.format("%s/%s", destFolder, fileName);

		// 获得文件访问路径
		String fileUrl = String.format("%s/%s/%s",
				rcb.getResourcePath(), imageType.name, fileName);

		// 存储图片
		try {
			multipartFile.transferTo(new File(destFile));
			// 返回访问路径
			return Result.ok(fileUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return Result.faild("上传图片失败。");
	}

	@Override
	@Transactional
	public Result<Image> insertModel(Image model) {
		Image temp = imageDao.getImageBySrc(model.getSrc());
		if (temp != null) {
			return Result.faild("该图片已经存在。");
		}

		model.setCreateDate(LocalDateTime.now());
		model.setUpdateDate(LocalDateTime.now());
		imageDao.insert(model);

		return Result.ok(model);
	}

	@Override
	@Transactional
	public Result<Image> updateModel(Image model) {
		Image temp = imageDao.getImageBySrc(model.getSrc());
		if (temp != null && temp.getId() != model.getId()) {
			return Result.faild("该图片已经存在。");
		}

		model.setUpdateDate(LocalDateTime.now());
		imageDao.updateById(model);

		return Result.ok(model);
	}

	@Override
	@Transactional
	public Result<Object> deleteModelById(int id) {
		imageDao.deleteById(id);
		return Result.ok();
	}

	@Override
	public Result<Image> getModelById(int id) {
		return Result.ok(imageDao.selectById(id));
	}

	@Override
	public PageInfo<Image> getModelsBySearch(Search search) {
		search.initSearch();
		PageHelper.startPage(search.getCurrentPage(), search.getPageSize());
		return new PageInfo<Image>(Optional
				.ofNullable(imageDao.getImagesBySearch(search))
				.orElse(Collections.emptyList()));
	}
}
