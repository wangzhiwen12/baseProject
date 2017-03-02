package com.wechat.manage.pojo.message.resp;

/**
 * 语音消息
 * 
 * @Class Name VoiceMessage
 * @Author kongqf
 * @Create In 2016年9月22日
 */
public class VoiceMessage extends BaseMessage {

	private Voice Voice;

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		Voice = voice;
	}

}
