package com.wechat.manage.controller.wechat;


import com.wechat.manage.pojo.wechat.vo.WeiXinDto;
import com.wechat.manage.service.util.MessageUtil;
import com.wechat.manage.service.wechat.impl.EventDispatcher;
import com.wechat.manage.service.wechat.impl.MsgDispatcher;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/wechat")
public class WechatSecurity {
	private static Logger logger = Logger.getLogger(WechatSecurity.class);

	@Autowired
	private MsgDispatcher msgDispatcher;
	@Autowired
	private EventDispatcher eventDispatcher;

	// http://117.121.99.11/wechat-web/wechat/security.htm
	@ResponseBody
	@RequestMapping(value = "/security", method = { RequestMethod.GET })
	public Long doGet(WeiXinDto dto) {
		logger.info(dto);
		List<String> list = new ArrayList<String>();
		list.add(dto.getTimestamp());
		list.add(dto.getNonce());
		list.add("qwertyuiop123");
		Collections.sort(list);
		logger.info(DigestUtils.shaHex(list.get(0) + list.get(1) + list.get(2))
				.equals(dto.getSignature()));
		logger.info(DigestUtils.shaHex(list.get(0) + list.get(1) + list.get(2)));
		logger.info(dto.getSignature());
		if (DigestUtils.shaHex(list.get(0) + list.get(1) + list.get(2))
				.equals(dto.getSignature())) {
			logger.info(dto.getEchostr());
			return Long.parseLong(dto.getEchostr());
		}
		return null;
	}

	@RequestMapping(value = "security", method = RequestMethod.POST)
	// post 方法用于接收微信服务端消息
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("这是 post 方法！");
		String processMessage = null;
		try {
			logger.info(request);
			Map<String, String> map = MessageUtil.parseXml(request);
			logger.info(map.toString());
			String msgtype = map.get("MsgType");
			logger.info(msgtype);
			if (MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(msgtype)) {
				logger.info("进入事件处理");
				processMessage = eventDispatcher.processEvent(map); // 进入事件处理
			} else {
				logger.info("进入消息处理");
				processMessage = msgDispatcher.processMessage(map); // 进入消息处理
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		logger.info("---------------------processMessage" + processMessage);
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(processMessage);
		out.close();
	}

}
