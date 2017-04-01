package com.wechat.manage.service.category.intf;

import com.wechat.manage.pojo.category.entity.TCategoryPropsDict;
import com.wechat.manage.pojo.category.entity.TCategoryValuesDict;

import java.util.List;

/**
 * Created by kongqf on 2017/3/31.
 */
public interface ICategoryService {

    public boolean saveCategoryPropInfo(List<TCategoryPropsDict> propList, List<TCategoryValuesDict> valueList);


}
