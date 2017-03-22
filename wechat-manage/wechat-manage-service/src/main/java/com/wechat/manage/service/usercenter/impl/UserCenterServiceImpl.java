package com.wechat.manage.service.usercenter.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wechat.manage.mapper.usercenter.TUserCenterMapper;
import com.wechat.manage.pojo.usercenter.entity.TPage;
import com.wechat.manage.pojo.usercenter.entity.TUserCenter;
import com.wechat.manage.service.exception.BleException;
import com.wechat.manage.service.usercenter.intf.IUserCenterService;
import com.wechat.manage.service.usercenter.intf.TWPageService;
import com.wechat.manage.service.util.ComErrorCodeConstants;
import com.wechat.manage.service.util.FTPUtils;
import com.wechat.manage.service.util.PropertiesUtils;
import com.wechat.manage.service.util.UUIDUtils;
import com.wechat.manage.utils.StringUtils;

/**
 * 保存会员页面
 * 
 * @author ghost
 *
 */
@Service
public class UserCenterServiceImpl implements IUserCenterService {
	private static Logger logger = LoggerFactory.getLogger(UserCenterServiceImpl.class);
	@Value("${ftp.addr}")
	private String ftpAddress;
	@Value("${ftp.port}")
	private String ftpPort;
	@Value("${ftp.username}")
	private String ftpUsername;
	@Value("${ftp.password}")
	private String ftpPassword;
	@Autowired
	private TWPageService pageService;
	@Autowired
	private TUserCenterMapper userCenterMapper;

	@Override
	public boolean saveUserCenterPage(TPage page, String html, String data, String storeCode) {
		boolean flag = false;
		List<String> propertiesKeys = new ArrayList<String>();
		propertiesKeys.add("ftp.addr");
		propertiesKeys.add("ftp.port");
		propertiesKeys.add("ftp.username");
		propertiesKeys.add("ftp.password");
		Map<String, String> valueMap = analyProperties(propertiesKeys);
		// 先判断我的会员页是否存在，如果不存在直接保存，如果存在则更新
		TPage paramPage = new TPage();
		paramPage.setShopId(page.getShopId());
		paramPage.setType("3");
		List<TPage> list = pageService.selectTPage(paramPage);
		if (list.isEmpty()) {
			String uuid = UUIDUtils.generateUUID();
			page.setIsHome("0");
			page.setPageCode("");
			page.setPageLink("http://" + valueMap.get("ftp.addr") + "/usercenter/" + uuid + ".html");
			page.setType("3");
			page.setStatus("1");
			page.setCreateTime(new Timestamp(System.currentTimeMillis()));
			page.setSeqNo(0);
			// 1、保存页面信息到t_shop
			pageService.insertSelective(page);
			// 2、将页面静态话并上传到ftp
			InputStream input = new ByteArrayInputStream(html.getBytes());
			FTPUtils util = FTPUtils.getInstance();
			flag = util.uploadFile(valueMap.get("ftp.addr"), Integer.valueOf(valueMap.get("ftp.port")), valueMap.get("ftp.username"), valueMap.get("ftp.password"), "/wshop/usercenter",
					uuid + ".html", input);
			if (!flag) {
				throw new BleException(ComErrorCodeConstants.ErrorCode.USERCENTER_ADD_FAILED_ERROR.getErrorCode(),
						ComErrorCodeConstants.ErrorCode.USERCENTER_ADD_FAILED_ERROR.getMemo());
			}
		} else {
			TPage oTPage = list.get(0);
			// 更新页面数据
			oTPage.setWpageTitle(page.getWpageTitle());
			pageService.updateWpage(oTPage);
			// 2、将页面静态话并上传到ftp
			InputStream input = new ByteArrayInputStream(html.getBytes());
			FTPUtils util = FTPUtils.getInstance();
			flag = util.uploadFile(valueMap.get("ftp.addr"), Integer.valueOf(valueMap.get("ftp.port")), valueMap.get("ftp.username"), valueMap.get("ftp.password"), "/wshop/usercenter",
					oTPage.getPageLink().substring(oTPage.getPageLink().lastIndexOf("/")+1), input);
			if (!flag) {
				throw new BleException(ComErrorCodeConstants.ErrorCode.USERCENTER_ADD_FAILED_ERROR.getErrorCode(),
						ComErrorCodeConstants.ErrorCode.USERCENTER_ADD_FAILED_ERROR.getMemo());
			}
		}

		if (StringUtils.isNotEmpty(data)) {
			updateUserCenter(data, storeCode);
		}
		
		return flag;
	}

	/**
	 * 获得properties里对应值
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

	/**
	 * 修改会员页面数据内容
	 */
	public void updateUserCenter(String data, String storeCode) {
		TUserCenter userCenter = userCenterMapper.selectUserCenterByStoreCode(storeCode);
		if (userCenter == null) {
			userCenter = new TUserCenter();
			userCenter.setData(data);
			userCenter.setCreateTime(new Timestamp(System.currentTimeMillis()));
			userCenter.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			userCenter.setStoreCode(storeCode);
			userCenterMapper.insertUserCenter(userCenter);
		} else {
			userCenter.setData(data);
			userCenter.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			userCenterMapper.updateUserCenter(userCenter);
		}
	}

	/**
	 * 查询会员主页数据内容
	 */
	public String getTitleData(String storeCode) {
		TUserCenter userCenter = userCenterMapper.selectUserCenterByStoreCode(storeCode);
		if (userCenter != null) {
			return userCenter.getData();
		} 
		return null;
	}
}
