package com.wechat.manage.pojo.cart;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 加价购数据结构
 * @author yfc
 *
 */
public class Gift implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 0-赠品/1-加价换购
	 */
	private String mode;	
	/**
	 * 赠品规则序号
	 */
	private Long seqno;
	/**
	 * 活动ID（对应pop_details中pop_event_id）
	 */
	@JSONField(name="event_id")
	private Long eventId;
	/**
	 * 策略ID（对应pop_details中pop_policy_id）
	 */
	@JSONField(name="policy_id")
	private Long policyId;
	/**
	 * 赠品分组关系(0-组之间and+组内or/1-组之间or+组内and)
	 */
	private String relate;	
	/**
	 * 赠品分组号
	 */
	private int group;
	/**
	 * 赠品编码
	 */
	private String code;
	/**
	 * 赠品描述
	 */
	private String name;
	/**
	 * 换购商品维度
	 */
	@JSONField(name="codemode")
	private String codeMode;	
	
	//codejson	 换购商品维度编码
	@JSONField(name="codejson")
	private JSON codeJson;
	/**
	 * 参与模式
	 */
	@JSONField(name="joinmode")
	private String joinMode;
	/**
	 * 价格模式
	 */
	@JSONField(name="prcmode")
	private String prcMode;
	/**
	 * 加价金额
	 */
	private Double poplsj;
	/**
	 * 本项赠品的基础数量
	 */
	@JSONField(name="base_qty")
	private int  baseQty;
	/**
	 * 本项赠品的限制数量
	 */
	@JSONField(name="limit_qty")
	private int limitQty;
	/**
	 * OR分组赠品总的限送数量
	 */
	
	@JSONField(name="max_qty")
	private int maxQty;
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public Long getSeqno() {
		return seqno;
	}
	public void setSeqno(Long seqno) {
		this.seqno = seqno;
	}
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public Long getPolicyId() {
		return policyId;
	}
	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}
	public String getRelate() {
		return relate;
	}
	public void setRelate(String relate) {
		this.relate = relate;
	}
	public int getGroup() {
		return group;
	}
	public void setGroup(int group) {
		this.group = group;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCodeMode() {
		return codeMode;
	}
	public void setCodeMode(String codeMode) {
		this.codeMode = codeMode;
	}
	public String getJoinMode() {
		return joinMode;
	}
	public void setJoinMode(String joinMode) {
		this.joinMode = joinMode;
	}
	public String getPrcMode() {
		return prcMode;
	}
	public void setPrcMode(String prcMode) {
		this.prcMode = prcMode;
	}
	public Double getPoplsj() {
		return poplsj;
	}
	public void setPoplsj(Double poplsj) {
		this.poplsj = poplsj;
	}
	public int getBaseQty() {
		return baseQty;
	}
	public void setBaseQty(int baseQty) {
		this.baseQty = baseQty;
	}
	public int getLimitQty() {
		return limitQty;
	}
	public void setLimitQty(int limitQty) {
		this.limitQty = limitQty;
	}
	public int getMaxQty() {
		return maxQty;
	}
	public void setMaxQty(int maxQty) {
		this.maxQty = maxQty;
	}
	public JSON getCodeJson() {
		return codeJson;
	}
	public void setCodeJson(JSON codeJson) {
		this.codeJson = codeJson;
	}
	
}
