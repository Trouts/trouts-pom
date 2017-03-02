package cn.trouts.entitys.framework;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.trouts.entitys.TroutsEntity;

@Entity
@Table(name = "TFK_AUDIT")
public class TroutsAudit extends TroutsEntity{
	
	//操作名称，方法名
    private String operName;

    //操作人
    private String operator;

    //操作参数
    private String operParams;

    //操作结果 成功/失败
    private String operResult;

    //结果消息
    private String resultMsg;

    //操作时间
    private Date operTime = new Date();

    @Column
	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	@Column
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(length=2000)
	public String getOperParams() {
		return operParams;
	}

	public void setOperParams(String operParams) {
		this.operParams = operParams;
	}

	@Column(length=2000)
	public String getOperResult() {
		return operResult;
	}

	public void setOperResult(String operResult) {
		this.operResult = operResult;
	}
	@Column(length=2000)
	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	public Date getOperTime() {
		return operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	@Override
	public String toString() {
		return "TroutsAudit [operName=" + operName + ", operator=" + operator
				+ ", operParams=" + operParams + ", operResult=" + operResult
				+ ", resultMsg=" + resultMsg + ", operTime=" + operTime + "]";
	}
    
    
    

}
