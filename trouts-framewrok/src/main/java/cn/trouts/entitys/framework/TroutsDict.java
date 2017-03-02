package cn.trouts.entitys.framework;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import cn.trouts.entitys.TroutsEntity;

@Entity
@Table(name = "TFK_DICT",uniqueConstraints={@UniqueConstraint(columnNames={"type","name"})})
@DynamicUpdate(true)
@DynamicInsert(true)
public class TroutsDict extends TroutsEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3310105465397122732L;
	private String type;
	private String name;
	private String value;
	private Integer seq;
	private String remark;
	
	

	@Column(name = "TYPE")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name = "NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "VALUE")
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Column(name = "SEQ")
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	
	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	

}
