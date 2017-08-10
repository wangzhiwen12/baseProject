package com.wechat.manage.controller.wshop.wpage;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.wechat.manage.controller.index.BaseController;
import com.wechat.manage.pojo.system.entity.UserFormMap;
import com.wechat.manage.pojo.usercenter.entity.TPage;
import com.wechat.manage.pojo.wshopnav.entity.TPageEdit;
import com.wechat.manage.pojo.wshopnav.entity.TPageExtendsForMap;
import com.wechat.manage.service.usercenter.intf.TPageService;
import com.wechat.manage.service.usercenter.intf.TWPageService;
import com.wechat.manage.service.util.FTPUtils;
import com.wechat.manage.service.util.PropertiesUtils;
import com.wechat.manage.service.util.UUIDUtils;
import com.wechat.manage.utils.Common;
import com.wechat.manage.utils.HttpUtil;
import com.wechat.manage.utils.ResultUtil;
import com.wechat.manage.vo.DataTableParams;
import com.wechat.manage.vo.DataTableResult;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @date 2017年1月17日 上午11:46:28
 */
@Controller
@RequestMapping("/wechatShopPage")
public class WPageController extends BaseController {

    private Logger logger = Logger.getLogger(WPageController.class);
    @Autowired
    private TWPageService tWPageService;

    @Autowired
    private TPageService tpPageService;

    @RequestMapping("/list")
    public String list(Model model, Integer id) throws Exception {
        logger.info("访问list.jsp" + id);
        model.addAttribute("res", findByRes());
        return Common.BACKGROUND_PATH + "/wshop/wpage/list";
    }

    @ResponseBody
    @RequestMapping("selectTPage")
    public List<TPage> selectTPage(TPage tPage) {
        return tWPageService.selectTPage(tPage);
    }

    /**
     * 分页查询
     *
     * @param dataTableParams
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("findByPage")
    public DataTableResult findByPage(DataTableParams dataTableParams) throws Exception {
        // 获取当前登录对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        UserFormMap userFormMap = (UserFormMap) Common.findUserSession(request);
        String userId = String.valueOf(userFormMap.get("id"));
        Long longs = Long.valueOf(dataTableParams.getsEcho());
        String pageSize = String.valueOf(dataTableParams.getiDisplayLength());
        List<TPage> list = tWPageService.selectWPageInfo(userId, String.valueOf(dataTableParams.getiDisplayStart()),
                pageSize);
        Long count = tWPageService.selectWPageInfoCount(userId);

        List<TPageExtendsForMap> listMap = new ArrayList<TPageExtendsForMap>();

        if (list.size() > 0) {
            for (TPage tPage : list) {
                listMap.add(com.alibaba.fastjson.JSON.parseObject(net.sf.json.JSONObject.fromObject(tPage).toString(),
                        TPageExtendsForMap.class));
            }
        }
        DataTableResult<TPageExtendsForMap> dataTableResult = new DataTableResult<TPageExtendsForMap>();
        dataTableResult.setAaData(listMap);
        dataTableResult.setiTotalDisplayRecords(count);
        dataTableResult.setiTotalRecords(count);
        dataTableResult.setsEcho(longs.toString());
        return dataTableResult;
    }

    /**
     * 分页查询(草稿)
     *
     * @param dataTableParams
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("findByPage2")
    public DataTableResult findByPage2(DataTableParams dataTableParams) throws Exception {
        // 获取当前登录对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        UserFormMap userFormMap = (UserFormMap) Common.findUserSession(request);
        String userId = String.valueOf(userFormMap.get("id"));
        Long longs = Long.valueOf(dataTableParams.getsEcho());
        String pageSize = String.valueOf(dataTableParams.getiDisplayLength());
        List<TPage> list = tWPageService.selectWPageInfo2(userId, String.valueOf(dataTableParams.getiDisplayStart()),
                pageSize);
        Long count = tWPageService.selectWPageInfoCount2(userId);

        List<TPageExtendsForMap> listMap = new ArrayList<TPageExtendsForMap>();

        if (list.size() > 0) {
            for (TPage tPage : list) {
                listMap.add(com.alibaba.fastjson.JSON.parseObject(net.sf.json.JSONObject.fromObject(tPage).toString(),
                        TPageExtendsForMap.class));
            }
        }
        DataTableResult<TPageExtendsForMap> dataTableResult = new DataTableResult<TPageExtendsForMap>();
        dataTableResult.setAaData(listMap);
        dataTableResult.setiTotalDisplayRecords(count);
        dataTableResult.setiTotalRecords(count);
        dataTableResult.setsEcho(longs.toString());
        return dataTableResult;
    }

    /**
     * 删除微页面
     *
     * @param request
     */
    @ResponseBody
    @RequestMapping("delPage")
    public void delWPage(HttpServletRequest request) {
        try {
            String sid = request.getParameter("sid");
            String createUser = request.getParameter("createUser");
            String pageid = request.getParameter("pageLink");
            if (StringUtils.isNotEmpty(pageid)) {
                tpPageService.deleteWPage(pageid);
            }
            String pageLink = pageid + ".html";
            logger.info("请求参数:sid:" + sid + ",用户id:" + createUser);
            tWPageService.deleteWPage(sid, createUser);// 删除数据库
            // String[] split = pageLink.split("/");
            // String fileName = split[split.length-1];

            boolean deleteFile = deleteFile(pageLink);
            logger.info("删除成功" + deleteFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改序号
     *
     * @param request
     */
    @ResponseBody
    @RequestMapping("updatePageSeqNo")
    public void updatePageSeqNo(HttpServletRequest request) {
        try {
            String sid = request.getParameter("sid");
            String createUser = request.getParameter("createUser");
            String seqNo = request.getParameter("seqNo");
            TPage tpage = new TPage();
            tpage.setSid(sid);
            tpage.setCreateUser(createUser);
            tpage.setSeqNo(Integer.valueOf(seqNo));
            tWPageService.updateWpage(tpage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 复制微页面
     *
     * @param request
     */
    @ResponseBody
    @RequestMapping("copyFile")
    public void copyFile(HttpServletRequest request) {
        String oldName = "";
        String newName = "";
        try {
            String sid = request.getParameter("sid");
            String createUser = request.getParameter("createUser");
            oldName = request.getParameter("pageLink") + ".html";
            newName = UUIDUtils.generateUUID() + ".html";
            logger.info("复制微页面:复制页面地址:" + oldName + ",新页面地址:" + newName);
            TPage tPage = new TPage();
            tPage.setSid(sid);
            tPage.setCreateUser(createUser);
            tWPageService.inestCopyFile(tPage, newName);// 数据库复制
            logger.info("数据库复制页面成功！");
            boolean file = copyFile(oldName, newName);// FDT服务器复制
            logger.info("FTP复制页面成功:" + file);
        } catch (Exception e) {
            logger.error("复制页面失败:复制页面地址:" + oldName + ",新页面地址:" + newName);
            e.printStackTrace();
        }
    }

    @RequestMapping("/add_wpageUI")
    public String addWpage(Model model, Integer id) throws Exception {
        return Common.BACKGROUND_PATH + "/wshop/wpage/wpageAdd";
    }

    @RequestMapping("/edit_wpageUI")
    public String editWpage(Model model, String id, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        model.addAttribute("wPageId", id);
        return Common.BACKGROUND_PATH + "/wshop/wpage/wpageAdd";
    }

    @RequestMapping(value = {"/previewTPage"}, method = {RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> saveUserCenterPage(HttpServletRequest request, HttpServletResponse response) {
        String html = "";
        String id = null;
        boolean flag = false;
        List<String> propertiesKeys = new ArrayList<String>();
        propertiesKeys.add("ftp.addr");
        propertiesKeys.add("ftp.port");
        propertiesKeys.add("ftp.username");
        propertiesKeys.add("ftp.password");
        Map<String, String> valueMap = analyProperties(propertiesKeys);
        try {
            JSONObject jsonData = HttpUtil.HttpGetRequest(request);
            html = jsonData.getString("html");
            id = jsonData.getString("id");
        } catch (IOException ex) {
            logger.error(ex.toString(), ex);
        }

        InputStream input = new ByteArrayInputStream(html.getBytes());
        FTPUtils util = FTPUtils.getInstance();
        flag = util.uploadFile(valueMap.get("ftp.addr"), Integer.valueOf(valueMap.get("ftp.port")),
                valueMap.get("ftp.username"), valueMap.get("ftp.password"), "/wshop/page", id + ".html", input);
        return ResultUtil.creComSucResult("");
    }

    /**
     * 获得properties里对应值
     *
     * @param list
     * @return
     */
    public Map<String, String> analyProperties(List<String> list) {
        Map<String, String> map = new HashMap<String, String>();
        for (String key : list) {
            String value = PropertiesUtils.findPropertiesKey(key);
            map.put(key, value);
        }
        return map;
    }

    @RequestMapping("/preview")
    public String toPreview(Model model, String id,String openId,String storeCode,String appId) {
        String html = "";
        String link = null;
        if (id != null && !"".equals(id)) {
            link = "http://10.6.100.100/page/" + id + ".html";
        }
        //link = "http://10.6.100.100/page/2d4bc56710b8456d873a8afc40b0ad79.html";
        try {
            html = HttpUtil.sendGet(link, null);
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        model.addAttribute("html", html);
        model.addAttribute("openId", openId);
        model.addAttribute("storeCode", storeCode);
        model.addAttribute("appId", appId);
        return Common.BACKGROUND_PATH + "/wshop/wpage/wpagePreviewNew";
    }

    @RequestMapping("/wpageInfo")
    public String wpageInfo(Model model, String id) {
        String html = "";
        String link = null;
        if (id != null && !"".equals(id)) {
            link = "http://10.6.100.100/page/" + id + ".html";
        }
        try {
            html = HttpUtil.sendGet(link, null);
            html = html.replace("handle", "");
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        model.addAttribute("html", html);
        return Common.BACKGROUND_PATH + "/wshop/wpage/wpagePreviewNew";
    }

    /**
     * 上传文件
     *
     * @param fileContent
     * @param fileName
     * @return
     */
    public boolean upLoadFile(String fileContent, String fileName) {
        InputStream input = new ByteArrayInputStream(fileContent.getBytes());
        FTPUtils util = FTPUtils.getInstance();
        boolean result = util.uploadFile("10.6.100.100", 21, "images", "123456", "/wshop/page", fileName, input);
        logger.info("上传文件：" + fileName + "。。。结果：" + result);
        return result;
    }

    /**
     * 删除文件
     *
     * @param fileName
     * @return
     */
    public boolean deleteFile(String fileName) {
        FTPUtils util = FTPUtils.getInstance();
        boolean result = util.delete("/wshop/page/" + fileName, "10.6.100.100", 21, "images", "123456");
        logger.info("删除文件：" + fileName + "。。。结果：" + result);
        return result;
    }

    /**
     * 查询文件
     *
     * @param fileName
     * @return
     */
    public String findFile(String fileName) {
        FTPUtils util = FTPUtils.getInstance();
        String result = null;
        try {
            result = util.readFile(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 远程复制文件
     *
     * @param oldName
     * @param newName
     * @return
     */
    public boolean copyFile(String oldName, String newName) {
        FTPUtils util = FTPUtils.getInstance();
        boolean result = false;

        try {
            result = util.copyFile(oldName, newName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 设置主页
     *
     * @param request
     */
    @ResponseBody
    @RequestMapping("updateHomePage")
    public void updateHomePage(HttpServletRequest request) {
        try {
            String sid = request.getParameter("sid");
            String createUser = request.getParameter("createUser");
            TPage tpage = new TPage();
            tpage.setSid(sid);
            tpage.setCreateUser(createUser);
            tWPageService.updateHomePage(tpage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/updateDraft", method = RequestMethod.POST)
    public String updateDraft(Model model, HttpServletRequest request) throws Exception {
        String param = null;
        String uuid = UUIDUtils.generateUUID();
        boolean flag = false, flagS = false;
        String userId = null;
        String html, htmlS = "";
        String url = request.getRequestURI();
        String rootPath = url.substring(0, url.indexOf("wechatShopPage/"));
        param = getData(request);
        JSONObject json = JSONObject.parseObject(param);
        html = (String) json.get("html");
        htmlS = (String) json.get("htmlS");
        InputStream input = new ByteArrayInputStream(html.getBytes());
        InputStream inputS = new ByteArrayInputStream(htmlS.getBytes());

        List<String> propertiesKeys = new ArrayList<String>();
        propertiesKeys.add("ftp.addr");
        propertiesKeys.add("ftp.port");
        propertiesKeys.add("ftp.username");
        propertiesKeys.add("ftp.password");
        Map<String, String> valueMap = analyProperties(propertiesKeys);
        json.remove("html");
        String data = json.getString("param").toString().replace("\\", "");
        TPage tPage = com.alibaba.fastjson.JSON.parseObject(data, TPage.class);

        UserFormMap userFormMap = (UserFormMap) Common.findUserSession(request);
        userId = String.valueOf(userFormMap.get("id"));
        tPage.setCreateUser(userId);
        tPage.setSid(json.getString("id"));
        FTPUtils util = FTPUtils.getInstance();
        if (tWPageService.selectTPage(tPage).size() == 0) {

            tPage.setPageLink(rootPath + "/wechatShopPage/wpageInfo.shtml?id=" + uuid);
            flag = util.uploadFile(valueMap.get("ftp.addr"), Integer.valueOf(valueMap.get("ftp.port")),
                    valueMap.get("ftp.username"), valueMap.get("ftp.password"), "/wshop/page", uuid + ".html", input);
            flagS = util.uploadFile(valueMap.get("ftp.addr"), Integer.valueOf(valueMap.get("ftp.port")),
                    valueMap.get("ftp.username"), valueMap.get("ftp.password"), "/wshop/page", uuid + "S" + ".html", inputS);

            if (flag == true && flagS == true) {
                logger.info("保存文件：" + uuid + ".html  ===到远程服务器成功");
            }
            if (StringUtils.isNotEmpty(tPage.getWpageTitle())) {
            } else {
                tPage.setWpageTitle("xx");
            }
            tPage.setPageCode("xx");
            tPage.setType("1");
            tPage.setSid(uuid);
            tPage.setIsHome("0");
            tPage.setStatus("0");// 解析传过来的json的status
            tPage.setShopId("1");// 根据用户id找到微店id
            tPage.setCreateTime(new Timestamp(System.currentTimeMillis()));
            tPage.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            tPage.setSeqNo(1);
            tWPageService.insertSelective(tPage);
        } else {
            tPage.setPageLink(rootPath + "/wechatShopPage/preview.shtml?id=" + tPage.getSid());
            flag = util.uploadFile(valueMap.get("ftp.addr"), Integer.valueOf(valueMap.get("ftp.port")),
                    valueMap.get("ftp.username"), valueMap.get("ftp.password"), "/wshop/page",
                    tPage.getSid() + ".html", input);
            flagS = util.uploadFile(valueMap.get("ftp.addr"), Integer.valueOf(valueMap.get("ftp.port")),
                    valueMap.get("ftp.username"), valueMap.get("ftp.password"), "/wshop/page",
                    tPage.getSid() + "S" + ".html", inputS);

            tPage.setStatus("0");
            tWPageService.updateWpage(tPage);
        }
        if (flag == true && flagS == true) {
            return "success";
        } else {
            return "false";
        }
    }
    
    
    @RequestMapping("proList") // 显示图片素材列表
    public String imageList(Model model) throws Exception {
        return Common.BACKGROUND_PATH + "/wshop/wpage/prolist";
    }


    /**
     * 获取流数据
     *
     * @param request
     * @return
     */
    protected String getData(HttpServletRequest request) {
        InputStream stream = null;
        try {
            stream = request.getInputStream();
            Reader reader = new InputStreamReader(request.getInputStream(), "UTF-8");
            StringBuilder response = new StringBuilder();
            final char[] buff = new char[1024];
            int read = 0;
            while ((read = reader.read(buff)) > 0) {
                response.append(buff, 0, read);
            }

            return response.toString();
        } catch (Exception e) {
            e.getMessage();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    @ResponseBody
    @RequestMapping(value = "/saveWPage", method = RequestMethod.POST)
    public String saveWPage(Model model, HttpServletRequest request) throws Exception {
        String param = null;
        String uuid = UUIDUtils.generateUUID();
        boolean flag = false, flagS = false;
        String userId = null;
        String html, htmlS = "";
        String url = request.getRequestURL().toString();
        String rootPath = url.substring(0, url.indexOf("wechatShopPage/"));
        param = getData(request);
        JSONObject json = JSONObject.parseObject(param);
        html = (String) json.get("html");
        htmlS = (String) json.get("htmlS");
        InputStream input = new ByteArrayInputStream(html.getBytes());
        InputStream inputS = new ByteArrayInputStream(htmlS.getBytes());

        List<String> propertiesKeys = new ArrayList<String>();
        propertiesKeys.add("ftp.addr");
        propertiesKeys.add("ftp.port");
        propertiesKeys.add("ftp.username");
        propertiesKeys.add("ftp.password");
        Map<String, String> valueMap = analyProperties(propertiesKeys);
        json.remove("html");
        String data = json.getString("param").toString().replace("\\", "");
        TPage tPage = com.alibaba.fastjson.JSON.parseObject(data, TPage.class);

        UserFormMap userFormMap = (UserFormMap) Common.findUserSession(request);
        userId = String.valueOf(userFormMap.get("id"));
        tPage.setCreateUser(userId);
        tPage.setSid(json.getString("id"));
        FTPUtils util = FTPUtils.getInstance();
        if (tWPageService.selectTPage(tPage).size() == 0) {

            tPage.setPageLink(rootPath + "wechatShopPage/preview.shtml?id=" + uuid);
            flag = util.uploadFile(valueMap.get("ftp.addr"), Integer.valueOf(valueMap.get("ftp.port")),
                    valueMap.get("ftp.username"), valueMap.get("ftp.password"), "/wshop/page", uuid + ".html", input);
            flagS = util.uploadFile(valueMap.get("ftp.addr"), Integer.valueOf(valueMap.get("ftp.port")),
                    valueMap.get("ftp.username"), valueMap.get("ftp.password"), "/wshop/page", uuid + "S" + ".html", inputS);

            if (flag == true && flagS == true) {
                logger.info("保存文件：" + uuid + ".html  ===到远程服务器成功");
            }
            if (StringUtils.isNotEmpty(tPage.getWpageTitle())) {
            } else {
                tPage.setWpageTitle("xx");
            }
            tPage.setPageCode("xx");
            tPage.setType("1");
            tPage.setSid(uuid);
            tPage.setIsHome("0");
            tPage.setStatus("1");// 解析传过来的json的status
            tPage.setShopId("1");// 根据用户id找到微店id
            tPage.setCreateTime(new Timestamp(System.currentTimeMillis()));
            tPage.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            tPage.setSeqNo(1);
            tWPageService.insertSelective(tPage);
        } else {
            tPage.setPageLink(rootPath + "wechatShopPage/preview.shtml?id=" + tPage.getSid());
            flag = util.uploadFile(valueMap.get("ftp.addr"), Integer.valueOf(valueMap.get("ftp.port")),
                    valueMap.get("ftp.username"), valueMap.get("ftp.password"), "/wshop/page",
                    tPage.getSid() + ".html", input);
            flagS = util.uploadFile(valueMap.get("ftp.addr"), Integer.valueOf(valueMap.get("ftp.port")),
                    valueMap.get("ftp.username"), valueMap.get("ftp.password"), "/wshop/page",
                    tPage.getSid() + "S" + ".html", inputS);

            tPage.setStatus("1");
            tWPageService.updateWpage(tPage);
        }
        if (flag == true && flagS == true) {
            return "success";
        } else {
            return "false";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getWPageInfo", method = RequestMethod.POST)
    public String saveWgetWPageInfoPage(Model model, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String pageId = getData(request);
        String html = "";
        String link = null;
        if (pageId != null && !"".equals(pageId)) {
            link = "http://10.6.100.100/page/" + pageId + "S.html";
        }
        try {
            html = HttpUtil.sendGet(link, null);
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }

        return html;
    }

    @ResponseBody
    @RequestMapping(value = "/queryHomePage", method = RequestMethod.GET)
    public String queryHomePage() {
        return tWPageService.queryHomePage();
    }

}
