package soa.t2d.base;

public class RestfulResponse {
	private int resCode;
	private String resMessage;
	private Object resData;
	
	
	public RestfulResponse() {
		
	}

	public RestfulResponse(int resCode, String resMessage, Object resData) {
		this.resCode = resCode;
		this.resMessage = resMessage;
		this.resData = resData;
	}

	public int getResCode() {
		return resCode;
	}

	public void setResCode(int resCode) {
		this.resCode = resCode;
	}

	public String getResMessage() {
		return resMessage;
	}

	public void setResMessage(String resMessage) {
		this.resMessage = resMessage;
	}

	public Object getResData() {
		return resData;
	}

	public void setResData(Object resData) {
		this.resData = resData;
	}
	
	
}
