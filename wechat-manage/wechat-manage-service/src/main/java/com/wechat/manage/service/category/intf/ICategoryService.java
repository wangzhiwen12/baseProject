package com.wechat.manage.service.category.intf;

import com.wechat.manage.pojo.category.entity.TCategoryPropsDict;
import com.wechat.manage.pojo.category.entity.TCategoryValuesDict;
import com.wechat.manage.pojo.category.entity.TProGroup;
import com.wechat.manage.vo.DataTableResult;

import java.util.List;
import java.util.Map;

/**
 * Created by kongqf on 2017/3/31.
 */
public interface ICategoryService {

    public boolean saveCategoryPropInfo(List<TCategoryPropsDict> propList, List<TCategoryValuesDict> valueList);

    boolean saveGroupInfo(TProGroup dto);

    DataTableResult<TProGroup> findGroupInfoByPage(Map<String, Object> paramMap);


}
