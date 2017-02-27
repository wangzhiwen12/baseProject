package com.wechat.manage.pojo.system.entity;

/**
 * Created by XS on 2016/12/29.
 */

import com.wechat.manage.annotation.TableSeg;
import com.wechat.manage.utils.FormMap;

/**
 * 实体表
 */
@TableSeg(tableName = "organization_info", id="sid")
public class GroupFormMap extends FormMap<String,Object> {
    private static final long serialVersionUID = 1L;

}
