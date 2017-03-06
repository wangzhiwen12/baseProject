package com.wechat.manage.controller.wechat;

import com.alibaba.fastjson.JSON;
import com.wechat.manage.controller.index.BaseController;
import com.wechat.manage.mapper.system.AppAccountInfoMapper;
import com.wechat.manage.pojo.fans.entity.Fans;
import com.wechat.manage.pojo.fans.entity.FansTag;
import com.wechat.manage.pojo.fans.vo.*;
import com.wechat.manage.pojo.system.entity.AppAccountInfo;
import com.wechat.manage.service.util.WechatUtil;
import com.wechat.manage.utils.*;
import com.wechat.manage.vo.DataTableParams;
import com.wechat.manage.vo.DataTableResult;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/fans/")
public class FansController extends BaseController {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private AppAccountInfoMapper appAccountInfoMapper;
    @Autowired
    private WechatUtil wechatUtil;
    private Logger logger = Logger.getLogger(FansController.class);

    List<FansTagNotExtends> fsgs = new ArrayList<FansTagNotExtends>();
    List<FansTagNotExtends> fsgsno = new ArrayList<FansTagNotExtends>();

    @RequestMapping("list")
    public String listUI(Model model, Integer id) throws Exception {
        model.addAttribute("res", findByRes());
        model.addAttribute("menuId", id);
        return Common.BACKGROUND_PATH + "/wechat/fans/list";
    }

    /**
     * 获取token
     *
     * @return
     */
    public String getAccessToken() {
        Session session = SecurityUtils.getSubject().getSession();
        String storecode = redisUtil.get(Common.USER_STORE_K + session.getAttribute("userSessionId"), "");
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("storecode", storecode);
        List<AppAccountInfo> appAccountInfoList = appAccountInfoMapper.selectListByParam(paraMap);
        String appid = appAccountInfoList.get(0).getAppid();
        String appsecret = appAccountInfoList.get(0).getAppsecret();
        String accessToken = wechatUtil.getAccessToken(appid, appsecret);
        return accessToken;
    }

    /**
     * 查询所有分组
     *
     * @param dataTableParams
     * @return
     */
    @ResponseBody
    @RequestMapping("getAllTags")
    public DataTableResult findTags(DataTableParams dataTableParams) {
        fsgs.clear();
        fsgsno.clear();
        String accessToken = getAccessToken();
        String fansTagUrl = "https://api.weixin.qq.com/cgi-bin/tags";
        Map<String, Object> fansGroupParam = new HashMap<String, Object>();
        fansGroupParam.put("access_token", accessToken);
        String fansTagResult = HttpUtils.HttpGetByUtf(fansTagUrl, "get", fansGroupParam);
        Result result = com.alibaba.fastjson.JSON.parseObject(fansTagResult, Result.class);
        if (result.getErrmsg() != null) {
            return null;
        }
        logger.info("获取到所有粉丝标签:" + fansTagResult);
        FansTagListNotExtends FansTagListNotExtends = com.alibaba.fastjson.JSON.parseObject(fansTagResult,
                FansTagListNotExtends.class);
        for (FansTagNotExtends fsg : FansTagListNotExtends.getTags()) {
            fsgsno.add(fsg);
        }
        for (FansTagNotExtends fsg : FansTagListNotExtends.getTags()) {
            FansTagNotExtends fss = new FansTagNotExtends();
            fss.setCount(fsg.getCount());
            fss.setId(fsg.getId());
            fss.setName(fsg.getName() + "（" + fsg.getCount() + "）");
            fsgs.add(fss);
        }
        FansTagListNotExtends.setTags(fsgs);
        FansTagList fansTagList = com.alibaba.fastjson.JSON.parseObject(JsonUtil.getJSONString(FansTagListNotExtends),
                FansTagList.class);

        DataTableResult<FansTag> dataTableResult = new DataTableResult<FansTag>();
        dataTableResult.setsEcho(dataTableParams.getsEcho());
        dataTableResult.setAaData(fansTagList.getTags());
        dataTableResult.setiTotalDisplayRecords(fansTagList.getTags().size());
        dataTableResult.setiTotalRecords(fansTagList.getTags().size());
        return dataTableResult;
    }

    /**
     * 查询所有粉丝
     *
     * @param tagName
     * @param userName
     * @param dataTableParams
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("getAllFans")
    public DataTableResult findFans(String tagName, String userName, DataTableParams dataTableParams) throws Exception {
        String user = null;
        String tag = null;
        if (tagName != null && !"".equals(tagName)) {
            tag = java.net.URLDecoder.decode(tagName, "UTF-8");
        }
        if (userName != null && !"".equals(userName)) {
            user = java.net.URLDecoder.decode(userName, "UTF-8");
        }
        logger.info("tagName" + user + ",,,," + "userName" + tag);
        String accessToken = getAccessToken();
        String openurl = "https://api.weixin.qq.com/cgi-bin/user";
        String method = "get";
        Map<String, Object> fansOpenidParam = new HashMap<String, Object>();
        fansOpenidParam.put("access_token", accessToken);
        fansOpenidParam.put("next_openid", "");
        String resutl = HttpUtils.HttpGetByUtf(openurl, method, fansOpenidParam);
        Result result = com.alibaba.fastjson.JSON.parseObject(resutl, Result.class);
        if (result.getErrmsg() != null) {
            return null;
        }
        FansOpenidList fansOpenidList = com.alibaba.fastjson.JSON.parseObject(resutl, FansOpenidList.class);
        List<FansPost> user_list = new ArrayList<FansPost>();
        FansListPost fansListPost = new FansListPost();

        for (String openid : fansOpenidList.getData().getOpenid()) {
            FansPost fansPost = new FansPost();
            fansPost.setOpenid(openid);
            fansPost.setLang("zh-CN");
            user_list.add(fansPost);
        }
        fansListPost.setUser_list(user_list);
        String userlist_result = HttpUtils.doPost(
                "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=" + accessToken,
                com.alibaba.fastjson.JSON.toJSONString(fansListPost));
        logger.info("获取到粉丝结果集：" + userlist_result);
        Result r = com.alibaba.fastjson.JSON.parseObject(userlist_result, Result.class);
        if (r.getErrmsg() != null) {
            return null;
        }
        FansResultsNotExtends fansResultsNotExtends = com.alibaba.fastjson.JSON.parseObject(userlist_result,
                FansResultsNotExtends.class);
        for (FansNotExtends fs : fansResultsNotExtends.getUser_info_list()) {
            List<String> tags = fs.getTagid_list();
            for (int i = 0; i < tags.size(); i++) {
                for (FansTagNotExtends FansTagNotExtends : fsgsno) {
                    if (tags.get(i).equals(FansTagNotExtends.getId())) {
                        tags.set(i, FansTagNotExtends.getName());
                    }
                }
            }
            fs.setTagid_list(tags);

            if (fs.getRemark() != null && !"".equals(fs.getRemark())) {
                fs.setNickname(fs.getRemark() + "  (" + fs.getNickname() + ")");
            }
        }
        List<FansNotExtends> newfans = new ArrayList<FansNotExtends>();
        if (tag != null) {
            String[] tagname = tag.split("（");
            for (FansNotExtends fans : fansResultsNotExtends.getUser_info_list()) {
                for (String t : fans.getTagid_list()) {
                    if (t.equals(tagname[0])) {
                        newfans.add(fans);
                    }
                }
            }

            fansResultsNotExtends.setUser_info_list(newfans);
        } else if (user != null) {
            for (FansNotExtends fans : fansResultsNotExtends.getUser_info_list()) {
                if (fans.getNickname().indexOf(user) >= 0) {
                    newfans.add(fans);
                } else if (fans.getRemark().equals(user)) {
                    newfans.add(fans);
                }
            }
            fansResultsNotExtends.setUser_info_list(newfans);
        }

        FansResults fansResults = com.alibaba.fastjson.JSON.parseObject(JSON.toJSONString(fansResultsNotExtends),
                FansResults.class);
        DataTableResult<Fans> dataTableResult = new DataTableResult<Fans>();
        dataTableResult.setsEcho(dataTableParams.getsEcho());
        dataTableResult.setAaData(fansResults.getUser_info_list());
        dataTableResult.setiTotalDisplayRecords(fansResults.getUser_info_list().size());
        dataTableResult.setiTotalRecords(fansResults.getUser_info_list().size());
        return dataTableResult;

    }

    /**
     * 查询黑名单列表
     *
     * @param request
     * @param dataTableParams
     * @return
     */
    @ResponseBody
    @RequestMapping("getBlackList")
    public DataTableResult getBlackList(HttpServletRequest request, DataTableParams dataTableParams) {
        String accessToken = getAccessToken();
        String blackurl = "https://api.weixin.qq.com/cgi-bin/tags/members/getblacklist?access_token=";
        Map<String, Object> fansOpenidParam = new HashMap<String, Object>();
        fansOpenidParam.put("access_token", accessToken);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("begin_openid", "");
        String resutl = HttpUtils.doPost(blackurl + accessToken, JsonUtils.mapToJson(map));
        FansOpenidList fansOpenidList = com.alibaba.fastjson.JSON.parseObject(resutl, FansOpenidList.class);
        List<FansPost> user_list = new ArrayList<FansPost>();
        FansListPost fansListPost = new FansListPost();

        DataTableResult<Fans> dataTableResult = new DataTableResult<Fans>();
        if (fansOpenidList.getCount() > 0) {
            for (String openid : fansOpenidList.getData().getOpenid()) {
                FansPost fansPost = new FansPost();
                fansPost.setOpenid(openid);
                fansPost.setLang("zh-CN");
                user_list.add(fansPost);
            }
            fansListPost.setUser_list(user_list);
            String userlist_result = HttpUtils.doPost(
                    "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=" + accessToken,
                    com.alibaba.fastjson.JSON.toJSONString(fansListPost));
            logger.info("获取到粉丝结果集：" + userlist_result);
            FansResults fansResults = com.alibaba.fastjson.JSON.parseObject(userlist_result, FansResults.class);

            dataTableResult.setsEcho(dataTableParams.getsEcho());
            dataTableResult.setAaData(fansResults.getUser_info_list());
            dataTableResult.setiTotalDisplayRecords(fansResults.getUser_info_list().size());
            dataTableResult.setiTotalRecords(fansResults.getUser_info_list().size());
        }
        return dataTableResult;
    }

    @RequestMapping("addTag")
    public String addTag(Model model, HttpServletRequest request) throws Exception {

        String id = getPara("id");
        int max = 0;
        String tagid = "";
        List<String> tagsList = new ArrayList<String>();
        List<String> tagList = new ArrayList<String>();
        Map<String, String> map = new HashMap<String, String>();
        if (StringUtils.isNotEmpty(id)) {
            String[] s = null;
            if (id.indexOf(",") > 0) {
                s = id.split(",");
            } else {
                s = new String[]{id};
            }
            DataTableParams dataTableParams = new DataTableParams();
            dataTableParams.setsEcho("1");

            String accessToken = getAccessToken();
            for (int i = 0; i < s.length; i++) {
                List<FansPost> user_list = new ArrayList<FansPost>();
                FansListPost fansListPost = new FansListPost();
                FansPost fansPost = new FansPost();
                fansPost.setOpenid(s[i]);
                fansPost.setLang("zh-CN");
                user_list.add(fansPost);
                fansListPost.setUser_list(user_list);
                String userlist_result = HttpUtils.doPost(
                        "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=" + accessToken,
                        com.alibaba.fastjson.JSON.toJSONString(fansListPost));
                FansResultsNotExtends fansResultsNotExtends = com.alibaba.fastjson.JSON.parseObject(userlist_result,
                        FansResultsNotExtends.class);
                for (FansNotExtends fs : fansResultsNotExtends.getUser_info_list()) {
                    tagList = fs.getTagid_list();
                    tagid = tagList.toString();
                    for (int m = 0; m < tagList.size(); m++) {
                        for (FansTagNotExtends FansTagNotExtends : fsgsno) {
                            if (tagList.get(m).equals(FansTagNotExtends.getId())) {
                                map.put(FansTagNotExtends.getName(), FansTagNotExtends.getId());
                            }
                        }
                    }
                    tagsList.add(String.valueOf(tagList.size()));
                }
            }
            if (s.length == 1) {
                model.addAttribute("tags", tagid);
                model.addAttribute("tagMap", map);
                model.addAttribute("tagCount", tagsList.size());
            } else {
                int[] nums = new int[10];
                for (int j = 0; j < tagsList.size(); j++) {
                    String tag = tagsList.get(j);
                    nums[j] = Integer.parseInt(tag);
                }
                max = nums[0];
                for (int k = 0; k < nums.length; k++) {
                    if (nums[k] > max) {
                        // 判断最大值
                        max = nums[k];
                    }

                }
                model.addAttribute("tags", tagid);
                model.addAttribute("tagCount", max);
                model.addAttribute("tagMap", tagsList.toString());
            }
            model.addAttribute("ids", id);
            model.addAttribute("count", s.length);
            model.addAttribute("groups", this.queryTags(dataTableParams).getAaData());
        }
        return Common.BACKGROUND_PATH + "/wechat/fans/add";
    }

    /**
     * 打标签
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "tag", method = RequestMethod.POST)
    public String add_tag(Model model, HttpServletRequest request) {
        String param = null;
        AddTagData requestJson = new AddTagData();
        try {
            param = getData(request);
            requestJson = JSON.parseObject(param, AddTagData.class);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        List<String> openidsList = requestJson.getOpenids();
        List<String> groupidList = requestJson.getGroupids();
        if (openidsList.size() == 1) {
            String oldgroups = requestJson.getOldgroups();
            oldgroups = oldgroups.substring(1, oldgroups.length() - 1);
            oldgroups = oldgroups.replace(" ", "");
            String[] s = null;
            if (oldgroups.indexOf(",") > 0) {
                s = oldgroups.split(",");
            } else {
                s = new String[]{oldgroups};
            }
            for (int j = 0; j < s.length; j++) {
                if (!groupidList.toString().contains(s[j])) {
                    this.remove_tag(openidsList.get(0), s[j]);
                }
            }
        }
        String accessToken = getAccessToken();
        AddTagParam addTagParam = new AddTagParam();
        String addTagUrl = "https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token=";
        List<FansOpenidsParam> openids = addTagParam.getOpenids();
        List<FansGroupidsParam> groups = addTagParam.getGroupids();
        List<String> openidList = requestJson.getOpenids();
        List<String> groupList = requestJson.getGroupids();
        if (openids == null) {
            openids = new ArrayList<FansOpenidsParam>();
            for (String so : openidList) {
                FansOpenidsParam open = new FansOpenidsParam();
                open.setOpenid(so);
                openids.add(open);
            }
        }
        if (groups == null) {
            groups = new ArrayList<FansGroupidsParam>();
            for (String ss : groupList) {
                FansGroupidsParam group = new FansGroupidsParam();
                group.setGroupid(ss);
                groups.add(group);
            }
        }
        addTagParam.setGroupids(groups);
        addTagParam.setOpenids(openids);
        for (FansOpenidsParam fansOpenidsParam : addTagParam.getOpenids()) {
            for (FansGroupidsParam fansGroupidsParam : addTagParam.getGroupids()) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("openid_list", fansOpenidsParam.getOpenid());
                map.put("tagid", fansGroupidsParam.getGroupid());
                String addTagresult = HttpUtils.doPost(addTagUrl + accessToken, JsonUtils.mapToJson(map));
                logger.info("一下用户添加" + addTagParam.getOpenids() + "添加标签：" + "tagId" + addTagresult);
            }
        }

        return "success";
    }

    @RequestMapping("createTag")
    public String createTag(Model model) throws Exception {
        DataTableParams dataTableParams = new DataTableParams();
        dataTableParams.setsEcho("1");
        model.addAttribute("groups", this.queryTags(dataTableParams).getAaData());
        return Common.BACKGROUND_PATH + "/wechat/fans/createTag";
    }

    /**
     * 新建标签
     *
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("create_tag")
    public String create_tag(Model model, String tagName, HttpServletRequest request) throws Exception {
        tagName = java.net.URLDecoder.decode(tagName, "UTF-8");
        String accessToken = getAccessToken();
        String createTagUrl = "https://api.weixin.qq.com/cgi-bin/tags/create?access_token=";
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", tagName);
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("tag", map);
        String create_group = HttpUtils.doPost(createTagUrl + accessToken, JsonUtils.mapToJson(map1));
        logger.info("新建标签：" + create_group);

        return "success";
    }

    /**
     * 删除标签
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteTag")
    public String delete_tag(HttpServletRequest request, String tagName) throws Exception {
        String tag = null;
        String tagid = null;
        if (tagName != null && !"".equals(tagName)) {
            tag = java.net.URLDecoder.decode(tagName, "UTF-8");
            DataTableParams dataTableParams = new DataTableParams();
            dataTableParams.setsEcho("1");
            for (FansTagNotExtends fansTag : fsgsno) {
                if (tag.indexOf(fansTag.getName()) >= 0) {
                    tagid = fansTag.getId();
                }
            }

            String accessToken = getAccessToken();
            String deleteTagUrl = "https://api.weixin.qq.com/cgi-bin/tags/delete?access_token=";
            Map<String, String> map = new HashMap<String, String>();
            map.put("id", tagid);
            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("tag", map);
            String delete_tag = HttpUtils.doPost(deleteTagUrl + accessToken, JsonUtils.mapToJson(map1));
            logger.info("删除标签：" + delete_tag);

            if (delete_tag != null && !"".equals(delete_tag)) {
                Result result = JSON.parseObject(delete_tag, Result.class);
                if (result.getErrmsg().equals("ok")) {
                    return "success";
                } else {
                    return result.getErrmsg();
                }

            } else {
                return "";
            }
        }
        return "";
    }

    /**
     * 重命名标签
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("renameTag")
    public String rename_tag(HttpServletRequest request, String oldTagName, String newTagName) throws Exception {
        String newTagId = null;
        String newName = null;
        String oldName = null;
        Result result = new Result();
        if (newTagName != null && !"".equals(newTagName) && fsgsno.size() > 0) {
            newName = java.net.URLDecoder.decode(newTagName, "UTF-8");
            oldName = java.net.URLDecoder.decode(oldTagName, "UTF-8");
            for (FansTagNotExtends fansTagNotExtends : fsgsno) {
                if (oldName.equals(fansTagNotExtends.getName())) {
                    newTagId = fansTagNotExtends.getId();
                }
            }
            String accessToken = getAccessToken();
            String updateTagnameUrl = "https://api.weixin.qq.com/cgi-bin/tags/update?access_token=";
            Map<String, String> map = new HashMap<String, String>();
            map.put("id", newTagId);
            map.put("name", newName);
            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("tag", map);
            String rename_tag = HttpUtils.doPost(updateTagnameUrl + accessToken, JsonUtils.mapToJson(map1));
            result = com.alibaba.fastjson.JSON.parseObject(rename_tag, Result.class);
            logger.info("重命名分组：" + rename_tag);
            if (result.getErrmsg().equals("ok")) {
                return "success";
            } else {
                return "error";
            }
        } else {
            return result.getErrmsg();
        }
    }

    /**
     * 粉丝去除标签
     *
     * @return
     */
    @RequestMapping("removeTag")
    public void remove_tag(String openid, String groupid) {
        String accessToken = getAccessToken();
        String removeTagUrl = "https://api.weixin.qq.com/cgi-bin/tags/members/batchuntagging?access_token=";
        Map<String, Object> map = new HashMap<String, Object>();
        List<String> list = new ArrayList<String>();
        list.add(openid);
        map.put("openid_list", list);
        map.put("tagid", groupid);
        String remove_tag = HttpUtils.doPost(removeTagUrl + accessToken, JsonUtils.mapToJson(map));
        logger.info("粉丝去除标签：" + remove_tag);
    }

    /**
     * 修改备注
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("modifyRemark")
    public String modifyRemarks(String newRemarks, String openid) throws Exception {
        String remarks = null;

        if (newRemarks != null && !"".equals(newRemarks) && openid != null && !"".equals(openid)) {
            remarks = java.net.URLDecoder.decode(newRemarks, "UTF-8");
            String accessToken = getAccessToken();
            String modifyRemarksUrl = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=";
            Map<String, String> map = new HashMap<String, String>();
            map.put("openid", openid);
            map.put("remark", remarks);
            String modify_remarks = HttpUtils.doPost(modifyRemarksUrl + accessToken, JsonUtils.mapToJson(map));
            Result result = com.alibaba.fastjson.JSON.parseObject(modify_remarks, Result.class);
            logger.info("修改备注名：" + modify_remarks);
            if (result.getErrmsg().equals("ok")) {
                return "success";
            } else {
                return result.getErrcode();
            }

        } else {
            return "";
        }

    }

    /**
     * 拉入黑名单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "shielding", method = RequestMethod.POST)
    public String shielding(Model model, HttpServletRequest request) {
        String param = null;
        AddTagData requestJson = null;
        try {
            param = getData(request);
            requestJson = JSON.parseObject(param, AddTagData.class);
        } catch (Exception e) {
            // TODO: handle exception
        }
        if (requestJson != null && !"".equals(requestJson)) {
            String accessToken = getAccessToken();
            List<String> openids = requestJson.getOpenids();

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("opened_list", openids);
            map.put("to_groupid", "1");
            String shieldingUrl = "https://api.weixin.qq.com/cgi-bin/groups/members/batchupdate?access_token=";
            String rename_tag = HttpUtils.doPost(shieldingUrl + accessToken, JsonUtils.mapToJson(map));
            logger.info("拉黑用户：" + openids + "结果：" + rename_tag);
        }
        return "success";
    }

    /**
     * 移出黑名单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "removeBlack", method = RequestMethod.POST)
    public String removeBlack(Model model, HttpServletRequest request) {
        String param = null;
        AddTagData requestJson = null;
        try {
            param = getData(request);
            requestJson = JSON.parseObject(param, AddTagData.class);
        } catch (Exception e) {
            // TODO: handle exception
        }
        if (requestJson != null && !"".equals(requestJson)) {
            String accessToken = getAccessToken();
            List<String> openids = requestJson.getOpenids();

            Map<String, List<String>> map = new HashMap<String, List<String>>();
            map.put("opened_list", openids);
            String shieldingUrl = "https://api.weixin.qq.com/cgi-bin/tags/members/batchunblacklist?access_token=";
            String rename_tag = HttpUtils.doPost(shieldingUrl + accessToken, JsonUtils.mapToJson(map));
            logger.info("取消拉黑用户：" + openids + "结果：" + rename_tag);
        }
        return "success";
    }

    /**
     * 查询所有标签
     *
     * @param dataTableParams
     * @return
     */
    public DataTableResult queryTags(DataTableParams dataTableParams) {
        fsgs.clear();
        fsgsno.clear();
        String accessToken = getAccessToken();
        String fansTagUrl = "https://api.weixin.qq.com/cgi-bin/tags";
        Map<String, Object> fansGroupParam = new HashMap<String, Object>();
        fansGroupParam.put("access_token", accessToken);
        String fansTagResult = HttpUtils.HttpGetByUtf(fansTagUrl, "get", fansGroupParam);
        logger.info("获取到所有粉丝标签:" + fansTagResult);
        FansTagListNotExtends FansTagListNotExtends = com.alibaba.fastjson.JSON.parseObject(fansTagResult,
                FansTagListNotExtends.class);
        for (FansTagNotExtends fsg : FansTagListNotExtends.getTags()) {
            fsgsno.add(fsg);
        }
        for (FansTagNotExtends fsg : FansTagListNotExtends.getTags()) {
            FansTagNotExtends fss = new FansTagNotExtends();
            fss.setCount(fsg.getCount());
            fss.setId(fsg.getId());
            fss.setName(fsg.getName());
            fsgs.add(fss);
        }
        FansTagListNotExtends.setTags(fsgs);
        FansTagList fansTagList = com.alibaba.fastjson.JSON.parseObject(JsonUtil.getJSONString(FansTagListNotExtends),
                FansTagList.class);

        DataTableResult<FansTag> dataTableResult = new DataTableResult<FansTag>();
        dataTableResult.setsEcho(dataTableParams.getsEcho());
        dataTableResult.setAaData(fansTagList.getTags());
        dataTableResult.setiTotalDisplayRecords(fansTagList.getTags().size());
        dataTableResult.setiTotalRecords(fansTagList.getTags().size());
        return dataTableResult;
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
}
