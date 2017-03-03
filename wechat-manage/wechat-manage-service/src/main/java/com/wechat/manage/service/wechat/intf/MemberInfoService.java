package com.wechat.manage.service.wechat.intf;


import com.wechat.manage.pojo.wechat.entity.MemberInfo;
import com.wechat.manage.pojo.wechat.vo.MemberInfoReturnDto;
import com.wechat.manage.vo.DataTableResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by wangxuan on 2016-11-22 0022.
 */
public interface 	MemberInfoService {
	public void memberActivation(String storeCode, String openid);

	public DataTableResult<MemberInfo> getMsgMember(Map<String, Object> paramMap);

	public Map<String, Object> registerMember_2(MemberInfo memberInfo) throws Exception;

	public int memberInfoInit(MemberInfo memberInfo);

	public List<MemberInfo> selectListByParam(MemberInfo record);

	public MemberInfo findMemberInfoByParam(Map<String, Object> paramMap);

	public int updateByPrimaryKeySelective(MemberInfo record);

	String generateMemberCode(Map<String, Object> paramMap);

	@Transactional
	Map<String, Object> registerMember(MemberInfo memberInfo) throws Exception;

	Map<String, Object> getMemberInfo(Map<String, Object> paraMap);

	@Transactional
	Map<String, Object> changePayPassword(Map<String, Object> paraMap) throws Exception;

	MemberInfoReturnDto findMemberAndStoreInfoByPara(Map<String, Object> paraMap);

	@Transactional
	Map<String, Object> modifyMemberInfo(MemberInfo memberInfo) throws Exception;

	public MemberInfoReturnDto queryCurMemberInfo(Map<String, Object> paraMap);
}
