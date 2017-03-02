package cn.trouts.framework.context;

public interface ErrorCode {
	public static final String SYSTEM_ERROR = "S500";
	default String message(){
		return "";
	}

}
