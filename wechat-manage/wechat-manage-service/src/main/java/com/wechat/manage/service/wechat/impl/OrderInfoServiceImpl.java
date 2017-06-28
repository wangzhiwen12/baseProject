package com.wechat.manage.service.wechat.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wechat.manage.pojo.order.Order;
import com.wechat.manage.pojo.order.OrderItem;
import com.wechat.manage.pojo.order.PrSaleProductDto;
import com.wechat.manage.pojo.order.ProOrder;
import com.wechat.manage.service.util.PropertiesUtils;
import com.wechat.manage.service.wechat.intf.OrderInfoService;
import com.wechat.manage.utils.HttpUtil;
import com.wechat.manage.utils.HttpUtils;
import com.wechat.manage.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by XS on 2017/5/4.
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    private static final Logger logger = LoggerFactory.getLogger(OrderInfoServiceImpl.class);



    @Override
    public Map createOrder(ProOrder proOrder) {

        Map map=new HashMap();
        map.put("success","false");
        List<OrderItem> lstOrderItem=new ArrayList<OrderItem>();
        OrderItem orderItem=new OrderItem();
        Order order=new Order();
        order.setAccountBalanceAmount(new BigDecimal(0));//
        order.setAccountNo(proOrder.getAccountNo());
        order.getCalcBillId();
        order.setCashAmount(new BigDecimal(0));//商品金额
        order.setCashIncome(new BigDecimal(0));
        order.setContactNumber("");
        order.setCouponAmount(new BigDecimal(0));
        order.setDeliveryMode("SHIPPING");
        order.setFromSystem("PC");
        order.setIntegral("0.0");
        order.setIsCod(0);
        order.setMemberNo(proOrder.getMemberNo());
        order.setNeedInvoice("0");
        order.setNeedSendCost(new BigDecimal(0.0));
        order.setOrderItemList(getPorduct(proOrder.getSupplyProductNo(),proOrder.getSaleSum()));//

        order.setOrderSource("C1");
        order.setOrderType("PT");
        order.setPaymentAmount(proOrder.getPaymentAmount());
        order.setPaymentClass("ONLINE");
        order.setPaymentItems(null);
        order.setPromotionAmount(new BigDecimal(0));
        order.setReceptAddress(proOrder.getReceptAddress());
        order.setReceptCityName(proOrder.getReceptCityName());
        order.setReceptCityNo(proOrder.getReceptCityNo());
        order.setReceptDistrictName(proOrder.getReceptDistrictName());
        order.setReceptDistrictNo(proOrder.getReceptDistrictNo());
        order.setReceptName(proOrder.getReceptName());
        order.setReceptPhone(proOrder.getReceptPhone());
        order.setReceptProvName(proOrder.getReceptProvName());
        order.setReceptProvNo(proOrder.getReceptProvNo());
        order.setRequiredDeliveryDate(proOrder.getRequiredDeliveryDate());//配送方式
        order.setSalesAmount(proOrder.getSalesAmount());//销售金额
        order.setSendCost(new BigDecimal(0.0));

        String json= JSON.toJSONString(order);
//        String url = "http://10.6.4.23:8087/oms-core-sdc/order/createCartOrder.htm"; //pre
//        String url = "http://10.6.2.46:8087/oms-core-sdc/order/createCartOrder.htm"; //sit
            String url= PropertiesUtils.findPropertiesKey("oms-core-sdc")+"/order/createCartOrder.htm";
        String response = "";
        try {
            response = HttpUtils.doPost(url, json);
        } catch (Exception e) {

                          }
        if(StringUtils.isNotEmpty(response)) {
            JSONObject parseObject = JSON.parseObject(response);
            String success = parseObject.getString("success");
            if (success.equals("true")) {
                String data = parseObject.getString("data");
                JSONObject jsonObject = JSON.parseObject(data);
                String orderno = jsonObject.get("orderNo").toString();

                map.put("success", "true");
                map.put("orderNo", orderno);

            }


        }
        return map;
    }


    private  List<OrderItem> getPorduct(String supplyProductNo,Integer num) {
        List<OrderItem> lstOrderItem = new ArrayList<OrderItem>();
        OrderItem orderItem = new OrderItem();

        JSONObject jsonObj = new JSONObject();
        List<Map> lstmap=new ArrayList<Map>();
        Map map=new HashMap();
        map.put("supplyProductNo",supplyProductNo);
        lstmap.add(map);
        jsonObj.put("productNos", lstmap);
        String jsonStr = jsonObj.toString();
        logger.info("jsonStr:{}", jsonStr);
//        String url = "http://10.6.4.22:8042/pcm-inner-sdc/product/selectSupplyProductList.htm"; //pre
//        String url = "http://10.6.2.48:8042/pcm-inner-sdc/product/selectSupplyProductList.htm"; //sit
        String url=PropertiesUtils.findPropertiesKey("pcm-inner-sdc")+"/product/selectSupplyProductList.htm";
        String response = "";
        try {
            // {"productNos":[{"supplyProductNo":"000000010019827004"}]}
            response = HttpUtils.doPost(url, jsonStr);
        } catch (Exception e) {

        }

        logger.info("response:{}", response);
        if (StringUtils.isNotEmpty(response)) {
            JSONObject parseObject = JSON.parseObject(response);
            String success = parseObject.getString("success");
            if(success.equals("true")){
                String data = parseObject.getString("data");
                JSONObject jsonObject = JSON.parseObject(data);
                String result = jsonObject.getString("products");
                List<PrSaleProductDto> productDtos = (List<PrSaleProductDto>) JSON.parseArray(result, PrSaleProductDto.class);

                for (PrSaleProductDto item:productDtos ) {
                    OrderItem orderItem1 = new OrderItem();
                    orderItem1.setRowNo(1);
                    orderItem1.setSaleSum(new BigDecimal(num));
                    BeanUtils.copyProperties(item, orderItem1);
                    lstOrderItem.add(orderItem1);
                }
            }
        }

        return lstOrderItem;
    }
}
