package com.wechat.manage.pojo.category.vo;

import com.wechat.manage.pojo.category.entity.TCategoryPropsDict;
import com.wechat.manage.pojo.category.entity.TCategoryValuesDict;

import java.util.List;

/**
 * Created by kongqf on 2017/3/31.
 */
public class TCategoryPropsDictDto extends TCategoryPropsDict {

    private List<TCategoryValuesDict> propValueList;

    public List<TCategoryValuesDict> getPropValueList() {
        return propValueList;
    }

    public void setPropValueList(List<TCategoryValuesDict> propValueList) {
        this.propValueList = propValueList;
    }
}
