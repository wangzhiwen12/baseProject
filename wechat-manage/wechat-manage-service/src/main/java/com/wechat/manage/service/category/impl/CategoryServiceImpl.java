package com.wechat.manage.service.category.impl;

import com.wechat.manage.exception.BleException;
import com.wechat.manage.mapper.category.TCategoryPropsDictMapper;
import com.wechat.manage.mapper.category.TCategoryValuesDictMapper;
import com.wechat.manage.mapper.category.TProGroupMapper;
import com.wechat.manage.pojo.category.entity.TCategoryPropsDict;
import com.wechat.manage.pojo.category.entity.TCategoryValuesDict;
import com.wechat.manage.pojo.category.entity.TProGroup;
import com.wechat.manage.service.category.intf.ICategoryService;
import com.wechat.manage.utils.ComErrorCodeConstants;
import com.wechat.manage.vo.DataTableResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by kongqf on 2017/3/31.
 */
@Service
public class CategoryServiceImpl implements ICategoryService {
    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private TCategoryPropsDictMapper propsDictMapper;

    @Autowired
    private TCategoryValuesDictMapper valuesMapper;

    @Autowired
    private TProGroupMapper groupMapper;


    @Override
    public boolean saveCategoryPropInfo(List<TCategoryPropsDict> propList, List<TCategoryValuesDict> valueList) {
        boolean flag = true;
        int count = 0;
        if (propList != null && propList.size() > 0) {
            for (int i = 0; i < propList.size(); i++) {
                TCategoryPropsDict propDict = propsDictMapper.selectByPrimaryKey(propList.get(i).getSid());
                if (propDict != null) {
                    propsDictMapper.updateByPrimaryKeySelective(propList.get(i));
                } else {
                    count = propsDictMapper.insertSelective(propList.get(i));
                    if (count != 1) {
                        flag = false;
                        throw new BleException(ComErrorCodeConstants.ErrorCode.CATEGORY_ADD_FAILED_ERROR.getErrorCode(),
                                ComErrorCodeConstants.ErrorCode.CATEGORY_ADD_FAILED_ERROR.getMemo());
                    }
                }
            }
        }

        if (valueList != null && valueList.size() > 0) {
            for (int j = 0; j < valueList.size(); j++) {
                TCategoryValuesDict valueDict = valuesMapper.selectByPrimaryKey(valueList.get(j).getSid());
                if (valueDict != null) {
                    valuesMapper.updateByPrimaryKeySelective(valueList.get(j));
                } else {
                    count = valuesMapper.insertSelective(valueList.get(j));
                    if (count != 1) {
                        throw new BleException(ComErrorCodeConstants.ErrorCode.CATEGORY_ADD_FAILED_ERROR.getErrorCode(),
                                ComErrorCodeConstants.ErrorCode.CATEGORY_ADD_FAILED_ERROR.getMemo());
                    }
                }
            }
        }

        return flag;
    }

    /**
     * 添加商品分组
     *
     * @param dto
     * @return
     */
    @Override
    public boolean saveGroupInfo(TProGroup dto) {
        boolean flag = false;
        int count = 0;
        TProGroup searchGroup = null;
        if (dto.getId() != null) {
            searchGroup = groupMapper.selectByPrimaryKey(dto.getId());
        }
        if (searchGroup != null) {
            count = groupMapper.updateByPrimaryKeySelective(dto);
        } else {
            count = groupMapper.insertSelective(dto);
        }
        if (count == 1) {
            flag = true;
        }
        return flag;
    }

    @Override
    public DataTableResult<TProGroup> findGroupInfoByPage(Map<String, Object> paramMap) {
        DataTableResult<TProGroup> page = new DataTableResult<TProGroup>();
        List<TProGroup> infoList = groupMapper.selectGroupList(paramMap);
        page.setAaData(infoList);
        paramMap.put("start", null);
        paramMap.put("limit", null);
        Integer count = groupMapper.selectGroupCount(paramMap);
        page.setiTotalRecords(count);
        return page;
    }

    /**
     * 删除商品分组
     *
     * @param sid
     * @return
     */
    public boolean deleteProGroupByPrimaryKey(Long sid) {
        boolean flag = false;
        TProGroup proGroup = new TProGroup();
        proGroup.setId(sid);
        TProGroup searchGroup = groupMapper.selectByPrimaryKey(sid);
        if (searchGroup != null) {
            proGroup.setState(0);
            int count = groupMapper.updateByPrimaryKeySelective(proGroup);
            if (count == 1)
                flag = true;
        }
        return flag;
    }
}
