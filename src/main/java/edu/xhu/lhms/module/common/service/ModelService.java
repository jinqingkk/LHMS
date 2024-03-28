package edu.xhu.lhms.module.common.service;

import com.github.pagehelper.PageInfo;
import edu.xhu.lhms.module.common.vo.Result;
import edu.xhu.lhms.module.common.vo.Search;

/**
 * ModelService
 */
public interface ModelService<T,S> {

	Result<T> insertModel(T model);

	Result<T> updateModel(T model);

	Result<Object> deleteModelById(int id);

	Result<T> getModelById(int id);

	PageInfo<T> getModelsBySearch(S search);
}
