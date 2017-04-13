package com.wechat.manage.controller.wechat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.wechat.manage.controller.index.BaseController;
import com.wechat.manage.pojo.product.vo.PcmProSearchDto;
import com.wechat.manage.pojo.product.vo.PcmShoppeProductVo;
import com.wechat.manage.pojo.system.vo.UserBaseInfoDto;
import com.wechat.manage.utils.HttpUtils;
import com.wechat.manage.utils.JsonUtil;
import com.wechat.manage.utils.StringUtils;
import com.wechat.manage.vo.DataTableResult;
import com.wfj.search.utils.zookeeper.discovery.SpringWebMvcServiceProvider;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2017/4/5.
 */
@Controller
@RequestMapping("/shoppePro")
public class ShoppeProController extends BaseController {

    @Autowired
    private SpringWebMvcServiceProvider provider;

    @RequestMapping(value = "/selectShoppeProductPageByParaFromSearch", method = {RequestMethod.GET,RequestMethod.POST}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public DataTableResult<PcmProSearchDto> selectShoppeProductPageByParaFromSearch(PcmShoppeProductVo shoppeProduct){
        Map<String, Object> proMap = new HashMap<String, Object>();
        DataTableResult<PcmProSearchDto> page = new DataTableResult();
        try {

            int currentPage = (shoppeProduct.getiDisplayStart()+shoppeProduct.getiDisplayLength())/shoppeProduct.getiDisplayLength();

            proMap.put("pageSize", shoppeProduct.getiDisplayLength());// 每页显示数量
            proMap.put("currentPage", currentPage);// 当前第几页

            // 商品编码
            String shoppeProSid = shoppeProduct.getShoppeProSid();
            if (StringUtils.isNotEmpty(shoppeProSid)) {
                proMap.put("productCodeStart", shoppeProSid.trim());
            }
            // 专柜商品编码(分段查询的最小值)
            String minShoppeProSid = shoppeProduct.getMinShoppeProSid();
            if (StringUtils.isNotEmpty(minShoppeProSid)) {
                proMap.put("productCodeStart", minShoppeProSid.trim());
            }
            // 专柜商品编码(分段查询的最大值)
            String maxShoppeProSid = shoppeProduct.getMaxShoppeProSid();
            if (StringUtils.isNotEmpty(maxShoppeProSid)) {
                proMap.put("productCodeEnd", maxShoppeProSid.trim());
            }
            // 专柜商品名称
            String shoppeProName = shoppeProduct.getShoppeProName();
            if (StringUtils.isNotEmpty(shoppeProName)) {
                proMap.put("productName", shoppeProName.trim());
            }
            // sku编码
            String productDetailSid = shoppeProduct.getProductDetailSid();
            if (StringUtils.isNotEmpty(productDetailSid)) {
                proMap.put("skuCodeStart", productDetailSid.trim());
            }
            // 商品表SKU 编码(分段查询的最小值)
            String minProductDetailSid = shoppeProduct.getMinProductDetailSid();
            if (StringUtils.isNotEmpty(minProductDetailSid)) {
                proMap.put("skuCodeStart", minProductDetailSid.trim());
            }
            // 商品表SKU 编码(分段查询的最大值)
            String maxProductDetailSid = shoppeProduct.getMaxProductDetailSid();
            if (StringUtils.isNotEmpty(maxProductDetailSid)) {
                proMap.put("skuCodeEnd", maxProductDetailSid.trim());
            }
            // 销售状态
            String saleStatus = shoppeProduct.getSaleStatus();
            if (StringUtils.isNotEmpty(saleStatus)) {
                if ("Y".equals(saleStatus)) {
                    proMap.put("isSale", "0");
                }
                if ("N".equals(saleStatus)) {
                    proMap.put("isSale", "1");
                }
            }
            // 专柜名称
            String shoppeSid = shoppeProduct.getShoppeSid();
            if (StringUtils.isNotEmpty(shoppeSid)) {
                proMap.put("counterCode", shoppeSid);
            }
            // 物料号
            String field4 = shoppeProduct.getField4();
            if (StringUtils.isNotEmpty(field4)) {
                proMap.put("field4", field4);
            }
            // 门店编码
            UserBaseInfoDto curUserInfo = getCurUserInfo();
            String shopNo = curUserInfo.getStoreCode();
            if (StringUtils.isNotEmpty(shopNo)) {
                proMap.put("storeCode", shopNo);
            }
            // 供应商编码
            String supplySid = shoppeProduct.getSupplySid();
            if (StringUtils.isNotEmpty(supplySid)) {
                proMap.put("supplierCode", supplySid);
            }

            String serviceAddress = provider.provideServiceAddress("pcm-item-query").orNull();
            String json = HttpUtils.doPost(serviceAddress, JsonUtil.getJSONString(proMap));

            //PcmProSearchDto
            if (StringUtils.isNotEmpty(json)) {
                JSONObject jsonObject = JSONObject.fromObject(json);
                JSONObject jsonPage = jsonObject.getJSONObject("page");
                Integer total = jsonPage.getInt("total");
                JSONArray jsonList = jsonPage.getJSONArray("list");

                //List<PcmProSearchDto> list = new ArrayList();

                //List<PcmProSearchDto> list = JSONArray.toList(jsonList,new PcmProSearchDto(),new JsonConfig());
                String listStr = jsonList.toString();
                Gson gson = new Gson();
                List<PcmProSearchDto> list = gson.fromJson(listStr, new TypeToken<List<PcmProSearchDto>>(){}.getType());
                System.out.print(list);
                page.setAaData(list);
                total = (total / shoppeProduct.getiDisplayLength()) > 1000 ? 1000 : total;
                page.setiTotalRecords(total);
                page.setsEcho(shoppeProduct.getsEcho());
                page.setiTotalDisplayRecords(page.getiTotalRecords());
            }

        } catch (Exception e) {
            System.out.print(e.fillInStackTrace());
        }
        return page;
    }
    @RequestMapping(value = "/selectStoreSid", method = {RequestMethod.GET,RequestMethod.POST}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String selectStoreSid(){
        String url = "http://10.6.4.22:8042/pcm-inner-sdc/organization/selectListByParam.htm";
        UserBaseInfoDto curUserInfo = getCurUserInfo();
        String shopNo = curUserInfo.getStoreCode();
        Map<String,Object> paramMap = new HashMap();
        paramMap.put("organizationCode",shopNo);
        String json = HttpUtils.doPost(url, JsonUtil.getJSONString(paramMap));
        String sid = "";
        if (StringUtils.isNotEmpty(json)) {
            JSONObject jsonObject = JSONObject.fromObject(json);
            JSONObject data = jsonObject.getJSONObject("data");
            sid = data.getString("sid");
        }
        return sid;
    }

    /**
     * 查询专柜List
     *
     * @param groupSid
     * @param shopSid
     * @param supplySid
     * @param shoppeType
     * @return
     */
    @RequestMapping(value = "/findShoppeList", method = {RequestMethod.GET,RequestMethod.POST}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String findShoppeList(String groupSid, String shopSid, String supplySid, String shoppeType) {

        Map<String, Object> para = new HashMap<String, Object>();
        if (StringUtils.isNotEmpty(groupSid)) {
            para.put("groupSid", Long.parseLong(groupSid));
        }
        if (StringUtils.isNotEmpty(shopSid)) {
            para.put("shopSid", Long.parseLong(shopSid));
        }
        if (StringUtils.isNotEmpty(supplySid)) {
            para.put("supplySid", supplySid);
        }
        if (StringUtils.isNotEmpty(shoppeType)) {
            para.put("shoppeType", shoppeType.trim());
        }

        try {
            String url = "http://10.6.4.22:8043/pcm-admin-sdc/shoppe/findShoppeList.htm";
            String json = HttpUtils.doPost(url, JsonUtil.getJSONString(para));
            para.clear();
            if (StringUtils.isNotEmpty(json)) {
                JSONObject jsonObject = JSONObject.fromObject(json);
                List<?> list = (List<?>) jsonObject.get("data");
                if ((list != null) && (list.size() != 0)) {
                    para.put("list", list);
                    para.put("success", "true");
                } else {
                    para.put("list", null);
                    para.put("success", "true");
                }
            } else {
                para.put("list", null);
                para.put("success", "true");
            }
        } catch (Exception e) {
            para.put("list", null);
            para.put("success", "false");
        }

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.toJson(para);
    }

    /**
     * 查询供应商(与组织机构连表)
     *
     * @param sid
     * @param shopSid
     * @param supplyName
     * @param shopCode
     * @param supplyCode
     * @param supplyType
     * @param status
     * @return String
     * @Methods Name findListSupplier
     * @Create In 2015-12-8 By chengsj
     */
    @RequestMapping(value = "/findListSupplier", method = {RequestMethod.GET,RequestMethod.POST}, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String findListSupplier(String sid, String shopSid, String supplyName, String shopCode,
                                   String supplyCode, String supplyType, String status, String apartOrder) {

        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isNotEmpty(sid)) {
            map.put("sid", sid.trim());
        }
        // 门店sid
        if (StringUtils.isNotEmpty(shopSid)) {
            map.put("shopSid", shopSid.trim());
        }
        if (StringUtils.isNotEmpty(supplyName)) {
            map.put("supplyName", supplyName.trim());
        }
        // 门店编码
        if (StringUtils.isNotEmpty(shopCode)) {
            map.put("shopCode", shopCode.trim());
        }
        if (StringUtils.isNotEmpty(supplyCode)) {
            map.put("supplyCode", supplyCode.trim());
        }
        if (StringUtils.isNotEmpty(supplyType)) {
            map.put("supplyType", supplyType.trim());
        }
        if (StringUtils.isNotEmpty(status)) {
            map.put("status", status.trim());
        }
        if (StringUtils.isNotEmpty(apartOrder)) {
            map.put("apartOrder", apartOrder.trim());
        }

        try {
            String url = "http://10.6.4.22:8043/pcm-admin-sdc/pcmAdminSupplyInfo/findListSupplier.htm";
            String json = HttpUtils.doPost(url, JsonUtil.getJSONString(map));
            JSONObject jsonObject = JSONObject.fromObject(json);
            List<?> list = (List<?>) jsonObject.get("data");
            map.clear();
            if (list != null && list.size() > 0) {
                map.put("list", list);
                map.put("success", "true");
            } else {
                map.put("success", "false");
            }
        } catch (Exception e) {
            map.put("success", "false");
        }

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.toJson(map);
    }
}
