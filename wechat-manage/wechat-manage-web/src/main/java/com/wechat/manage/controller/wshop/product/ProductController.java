package com.wechat.manage.controller.wshop.product;

import com.wechat.manage.controller.index.BaseController;
import com.wechat.manage.utils.Common;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by kongqf on 2017/3/29.
 */
@Controller
@RequestMapping("pro")
public class ProductController extends BaseController {

 /*   @Autowired
    private ThreadPoolTaskExecutor taskExecutor;*/

    /**
     * 商品tabpage
     *
     * @param pageIndex
     * @return
     */
    @RequestMapping("/list")
    public String getList(String pageIndex) {
        String destUrl = "";
        if (StringUtils.isEmpty(pageIndex)) {
            destUrl = Common.BACKGROUND_PATH + "/wshop/product/list";
            return destUrl;
        }

        switch (pageIndex) {
            case "1":
                destUrl = Common.BACKGROUND_PATH + "/wshop/product/list";
                break;
            case "2":
                destUrl = Common.BACKGROUND_PATH + "/wshop/category/progrouplist";
                break;
            case "3":
                destUrl = Common.BACKGROUND_PATH + "/wshop/category/list";
                break;
            case "4":
                destUrl = Common.BACKGROUND_PATH + "/page/ActivatePhone/PhoneManage";
                break;
            default:
                destUrl = Common.BACKGROUND_PATH + "/wshop/product/list";
                break;
        }
        return destUrl;
    }

 /*   @ResponseBody
    @RequestMapping(value = "/saveProduct", method = RequestMethod.POST)
    public String saveProduct(@RequestBody MqRequestDataListPara<ProductInfoDto> para2) {
        final MqRequestDataListPara<ProductInfoDto> para = para2;

        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                RequestHeader header = para.getHeader();
            }
        });
        return "";
    }*/
}
