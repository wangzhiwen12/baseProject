package com.wechat.manage.controller.wechat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wechat.manage.pojo.product.vo.PcmProSearchDto;
import com.wechat.manage.pojo.product.vo.PcmShoppeProductVo;
import com.wechat.manage.pojo.product.vo.SearchShoppeProductQueryDto;
import com.wechat.manage.pojo.wechat.entity.CouponInfo;
import com.wechat.manage.utils.HttpUtils;
import com.wechat.manage.utils.JsonUtil;
import com.wechat.manage.utils.StringUtils;
import com.wechat.manage.vo.DataTableResult;
import com.wfj.search.utils.zookeeper.discovery.SpringWebMvcServiceProvider;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Created by dell on 2017/4/5.
 */
@Controller
@RequestMapping("shoppePro")
public class ShoppeProController {

    @Autowired
    private SpringWebMvcServiceProvider provider;

    @RequestMapping(value = "/selectShoppeProductPageByParaFromSearch", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
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
            String field5 = shoppeProduct.getField5();
            if (StringUtils.isNotEmpty(field5)) {
                proMap.put("storeCode", field5);
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
                List<PcmProSearchDto> list = JSONArray.toList(jsonList,new PcmProSearchDto(),new JsonConfig());

                //String listStr = jsonList.toString();
                //Gson gson = new Gson();
                //List<PcmProSearchDto> list = gson.fromJson(listStr, new TypeToken<List<PcmProSearchDto>>(){}.getType());
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
}
