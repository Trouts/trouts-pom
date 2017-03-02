package cn.trouts.framework.enums;

import cn.trouts.framework.context.ErrorCode;

public enum TroutsErrorCode implements ErrorCode {
	/**
	 * 空白參數，提示不能為空參數
	 */
	S001 {
		public String message() {
			return "参数不能为空";
		}
	},
	S002 {
		public String message() {
			return "无效参数";
		}
	},

	S003 {
		public String message() {
			return "格式错误";
		}
	}

	;

}
