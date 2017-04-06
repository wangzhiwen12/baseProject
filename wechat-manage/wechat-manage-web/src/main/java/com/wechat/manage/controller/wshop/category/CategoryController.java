package com.wechat.manage.controller.wshop.category;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wechat.manage.annotation.SystemLog;
import com.wechat.manage.controller.index.BaseController;
import com.wechat.manage.pojo.category.entity.TCategoryPropsDict;
import com.wechat.manage.pojo.category.entity.TCategoryValuesDict;
import com.wechat.manage.pojo.category.entity.TProGroup;
import com.wechat.manage.pojo.category.vo.TProGroupPageDto;
import com.wechat.manage.pojo.system.vo.UserBaseInfoDto;
import com.wechat.manage.service.category.impl.CategoryServiceImpl;
import com.wechat.manage.service.exception.BleException;
import com.wechat.manage.utils.Common;
import com.wechat.manage.utils.JsonUtil;
import com.wechat.manage.utils.MqUtil;
import com.wechat.manage.utils.StringUtils;
import com.wechat.manage.vo.DataTableResult;
import com.wechat.manage.vo.MqRequestDataPara;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kongqf on 2017/3/29.
 */
@Controller
@RequestMapping("category")
public class CategoryController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private CategoryServiceImpl categoryService;

    @RequestMapping("/addUI")
    public String addUI() {
        return Common.BACKGROUND_PATH + "/wshop/category/progroupadd";
    }

    @RequestMapping(value = "/importCategoryPropInfo", method = RequestMethod.POST)
    @ResponseBody
    public String importCategoryPropInfo(HttpServletRequest request,
                                         @RequestBody MqRequestDataPara para) {
        final MqRequestDataPara mqpara = para;
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                JSONObject jsonData = JSONObject.parseObject(mqpara.getData());
                JSONArray propArray = jsonData.getJSONArray("propList");
                JSONArray valueArray = jsonData.getJSONArray("valueList");

                List<TCategoryPropsDict> propList = getCategoryPropsList(propArray);
                List<TCategoryValuesDict> valueList = getCategoryPropValueList(valueArray);

                try {
                    categoryService.saveCategoryPropInfo(propList, valueList);
                } catch (BleException ex) {
                    logger.error(ex.getMessage());
                }

            }
        });

        return JsonUtil.getJSONString(MqUtil.GetMqResponseInfo(para.getHeader()));
    }


    /**
     * 属性字典
     *
     * @param jsona
     * @return
     */
    public List<TCategoryPropsDict> getCategoryPropsList(JSONArray jsona) {
        List<TCategoryPropsDict> propsDicts = new ArrayList<>();

        if (jsona != null) {
            TCategoryPropsDict propDto = null;
            for (int i = 0; i < jsona.size(); i++) {
                propDto = new TCategoryPropsDict();
                JSONObject object = jsona.getJSONObject(i);

                propDto.setSid(Common.getJsonValueL(object, "sid"));
                propDto.setPropsSid(Common.getJsonValueL(object, "propsSid"));
                propDto.setPropsName(Common.getJsonValueS(object, "propsName"));
                propDto.setIsKeyProp(Common.getJsonValueI(object, "isKeyProp"));
                propDto.setIsEnumProp(Common.getJsonValueI(object, "isEnumProp"));
                propDto.setPropsDesc(Common.getJsonValueS(object, "propsDesc"));
                propDto.setPropsCode(Common.getJsonValueS(object, "propsCode"));
                propDto.setStatus(Common.getJsonValueL(object, "status"));
                propDto.setChannelSid(Common.getJsonValueL(object, "channelSid"));
                propDto.setIsKeyProp(Common.getJsonValueI(object, "isErpProp"));
                propDto.setErpType(Common.getJsonValueI(object, "erpType"));
                propDto.setErpPropCode(Common.getJsonValueS(object, "erpPropCode"));
                propDto.setCreateTime(Common.getJsonValueD(object, "createTime"));
                propDto.setOptUserSid(Common.getJsonValueL(object, "optUserSid"));

                propsDicts.add(propDto);
            }
        }
        return propsDicts;
    }

    public List<TCategoryValuesDict> getCategoryPropValueList(JSONArray jsona) {
        List<TCategoryValuesDict> propsDicts = new ArrayList<>();

        if (jsona != null)
            if (jsona != null && jsona.size() > 0) {
                List<TCategoryValuesDict> propsVDicts = new ArrayList<>();
                TCategoryValuesDict propsValue = null;
                for (int j = 0; j < jsona.size(); j++) {
                    propsValue = new TCategoryValuesDict();
                    JSONObject valueObject = jsona.getJSONObject(j);

                    propsValue.setSid(Common.getJsonValueL(valueObject, "sid"));
                    propsValue.setSid(Common.getJsonValueL(valueObject, "valuesSid"));
                    propsValue.setValuesName(Common.getJsonValueS(valueObject, "valuesName"));
                    propsValue.setIsKeyValue(Common.getJsonValueL(valueObject, "isKeyValue"));
                    propsValue.setValuesDesc(Common.getJsonValueS(valueObject, "valuesDesc"));
                    propsValue.setValuesCode(Common.getJsonValueS(valueObject, "valuesCode"));
                    propsValue.setStatus(Common.getJsonValueL(valueObject, "status"));
                    propsValue.setPropsSid(Common.getJsonValueL(valueObject, "propsSid"));
                    propsValue.setSortOrder(Common.getJsonValueL(valueObject, "sortOrder"));
                    propsValue.setChannelSid(Common.getJsonValueL(valueObject, "channelSid"));
                    propsValue.setIsErpValue(Common.getJsonValueL(valueObject, "isErpValue"));

                    propsVDicts.add(propsValue);
                }
            }

        return propsDicts;
    }

    @ResponseBody
    @RequestMapping("/addgroup")
    @SystemLog(module = "商品分组", methods = "商品分组-新增")
    public String addCouponInfo(String groupparentId, String groupname, String groupPicUrl,
                                String Type) {
        UserBaseInfoDto curUser = getCurUserInfo();
        TProGroup proGroup = new TProGroup();
        proGroup.setStoreCode(curUser.getStoreCode());
        if (StringUtils.isEmpty(groupparentId)) {
            proGroup.setParentId(0L);
        }
        proGroup.setName(groupname);
        proGroup.setPicUrl(groupPicUrl);
        if (StringUtils.isEmpty(groupparentId)) {
            proGroup.setType(new Integer(Type));
        }
        if (categoryService.saveGroupInfo(proGroup)) {
            return "success";
        } else {
            return "failed";
        }
    }


    @ResponseBody
    @RequestMapping("/findProGroupList")
    @SystemLog(module = "商品分组", methods = "商品分组-分页查询")
    public DataTableResult<TProGroup> findProGroupList(TProGroupPageDto para) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("start", para.getiDisplayStart());
        paramMap.put("limit", para.getiDisplayLength());

        UserBaseInfoDto curUser = getCurUserInfo();
        paramMap.put("storeCode", curUser.getStoreCode());

        DataTableResult<TProGroup> page = categoryService.findGroupInfoByPage(paramMap);
        page.setiTotalDisplayRecords(page.getiTotalRecords());
        page.setsEcho(para.getsEcho());
        return page;
    }
}
