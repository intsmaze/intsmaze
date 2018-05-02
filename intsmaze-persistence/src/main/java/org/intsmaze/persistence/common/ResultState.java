/**
 * @author Allan
 * @belong to  信息科技有限公司
 * @date:2016-5-18 上午8:55:10
 * @version :
 * 
 */

package org.intsmaze.persistence.common;


public class ResultState {
	
	protected String resultCode = HandlerConstants.S_FAIL;
	
	protected String errorCode;
	
	protected String errorMessage;	

	protected OperationInfo opInfo;
	
	public ResultState(){}
	
	public ResultState(String resultCode, String errorCode, String errorMessage)
	{
		this.resultCode = resultCode;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	public OperationInfo getOpInfo() {
		return opInfo;
	}

	public void setOpInfo(OperationInfo opInfo) {
		this.opInfo = opInfo;
	}
	
}
