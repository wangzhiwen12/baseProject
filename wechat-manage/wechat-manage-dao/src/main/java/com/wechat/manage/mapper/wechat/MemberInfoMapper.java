package com.wechat.manage.mapper.wechat;

import com.wechat.manage.pojo.wechat.vo.MemberInfoReturnDto;
import com.wechat.manage.pojo.wechat.entity.MemberInfo;

import java.util.List;
import java.util.Map;

public interface MemberInfoMapper {
	int getCountByParam(Map<String, Object> paramMap);

	int getMsgMemberCount(Map<String, Object> paramMap);

	int deleteByPrimaryKey(Long sid);

	int insert(MemberInfo record);

	int insertSelective(MemberInfo record);

	MemberInfo selectByPrimaryKey(Long sid);

	int updateByPrimaryKeySelective(MemberInfo record);

	int updateByPrimaryKey(MemberInfo record);

	List<MemberInfo> selectListByParam(Map<String, Object> paramMap);

	List<MemberInfo> selectListByParam(MemberInfo record);

	List<MemberInfo> getMemberListBy(Map<String, Object> paramMap);

	List<MemberInfo> getMsgMember(Map<String, Object> paramMap);

	Map<String, Object> selectMaxMemberCodeByParam(Map<String, Object> paramMap);

	/**
	 * 查询会员及会员卡信息
	 *
	 * @param paramMap
	 * @return
	 */
	List<MemberInfoReturnDto> selectMemberAndCardInfoListByParam(Map<String, Object> paramMap);

	/**
	 * 查询个人资料(会员信息以及门店信息)
	 *
	 * @param paramMap
	 * @return
	 */
	List<MemberInfoReturnDto> findMemberAndStoreInfoByPara(Map<String, Object> paramMap);

	/**
	 * 获取当前会员信息
	 *
	 * @param paramMap
	 * @return
	 */
	List<MemberInfoReturnDto> selectCurMemberInfo(Map<String, Object> paramMap);

	/**
	 * 根据门店编码和openid修改会员信息
	 *
	 * @param memberInfo
	 * @return
	 */
	int updateByParaSelective(MemberInfo memberInfo);
}