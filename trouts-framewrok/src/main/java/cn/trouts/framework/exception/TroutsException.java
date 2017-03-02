package cn.trouts.framework.exception;

import java.util.Map;

import cn.trouts.framework.context.ErrorCode;

public class TroutsException extends RuntimeException {
	private ExceptionLocation exceptionLocation;

	public TroutsException(ExceptionLocation exceptionLocation) {
		super(exceptionLocation.getMessage());
		this.exceptionLocation = exceptionLocation;
	}

	public TroutsException(ExceptionLocation exceptionLocation, Exception e) {
		super(exceptionLocation.getMessage(),e);	
		this.exceptionLocation = exceptionLocation;
	}


	class ExceptionLocation {
		private String message;
		private ErrorCode errorCode;
		private String className;
		private String methodName;
		private Integer line;
		private Map parameterMap;

		public ExceptionLocation() {

		}

		public ExceptionLocation(String message, ErrorCode errorCode,
				String className, String methodName, Integer line,
				Map parameterMap) {
			super();
			this.message = message;
			this.errorCode = errorCode;
			this.className = className;
			this.methodName = methodName;
			this.line = line;
			this.parameterMap = parameterMap;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public ErrorCode getErrorCode() {
			
			return errorCode;
		}

		public void setErrorCode(ErrorCode errorCode) {
			this.errorCode = errorCode;
		}

		public String getClassName() {
			return className;
		}

		public void setClassName(String className) {
			this.className = className;
		}

		public String getMethodName() {
			return methodName;
		}

		public void setMethodName(String methodName) {
			this.methodName = methodName;
		}

		public Integer getLine() {
			return line;
		}

		public void setLine(Integer line) {
			this.line = line;
		}

		public Map getParameterMap() {
			return parameterMap;
		}

		public void setParameterMap(Map parameterMap) {
			this.parameterMap = parameterMap;
		}
		
		

	}

}
