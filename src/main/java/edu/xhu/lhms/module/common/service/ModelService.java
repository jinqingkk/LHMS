package edu.xhu.lhms.module.common.service;

import com.github.pagehelper.PageInfo;
import edu.xhu.lhms.module.Feedback.entity.Feedback;
import edu.xhu.lhms.module.common.vo.Result;

/**
 * ModelService
 */
public interface ModelService<T,S> {

	Result<T> insertModel(T model);

	Result<T> updateModel(T model);

	Result<Object> deleteModelById(int id);

	Result<T> getModelById(int id);

	Result<PageInfo<Feedback>> getModelsBySearch(S search);
}
