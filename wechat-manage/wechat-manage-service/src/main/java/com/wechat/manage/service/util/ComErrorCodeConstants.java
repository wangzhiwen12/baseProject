package com.wechat.manage.service.util;

/**
 * @author kongqf
 * @create 2016-12-30
 */
public class ComErrorCodeConstants {
    public enum ErrorCode {

        /* 页面管理 */
        PAGE_ADD_FAILED_ERROR("001001001", "页面保存失败！"),PAGE_DATA_ISNULL("001001002","数据不存在请联系管理员!"),
        USERCENTER_ADD_FAILED_ERROR("001001003", "会员页面保存失败！");
        private String errorCode;
        private String memo;

        private ErrorCode() {
        };

        private ErrorCode(String errorCode, String memo) {
            this.errorCode = errorCode;
            this.memo = memo;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }
    }
}
