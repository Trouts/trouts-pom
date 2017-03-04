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
	private String value1;
	private String value2;
	private String value3;
	private String value4;
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

	@Column(name = "VALUE_1")
	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}
	@Column(name = "VALUE_2")
	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

	@Column(name = "VALUE_3")
	public String getValue3() {
		return value3;
	}

	public void setValue3(String value3) {
		this.value3 = value3;
	}

	@Column(name = "VALUE_4")
	public String getValue4() {
		return value4;
	}

	public void setValue4(String value4) {
		this.value4 = value4;
	}
}
