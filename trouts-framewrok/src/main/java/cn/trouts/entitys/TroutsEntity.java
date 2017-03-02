package cn.trouts.entitys;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.hibernate.annotations.GenericGenerator;
import org.slf4j.Logger;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import cn.trouts.framework.context.TroutsCriteria;
import cn.trouts.framework.utils.TroutsLogUtils;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class TroutsEntity extends TroutsCriteria implements Serializable{
	private static final Logger LOGGER = TroutsLogUtils.getLogger(TroutsEntity.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String id;

	
	private String creationUser;

	private ZonedDateTime creationTime;
	

	
	private String lastModificationUser;

	
	private ZonedDateTime lastModificationTime;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	@Column(name = "CREATION_TIME")
	@CreatedDate
	public ZonedDateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(ZonedDateTime creationTime) {
		this.creationTime = creationTime;
	}

	@Column(name = "CREATION_USER")
	@CreatedBy
	public String getCreationUser() {
		return creationUser;
	}

	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}

	@Column(name = "LAST_MODIFICATION_USER")
	@LastModifiedBy
	public String getLastModificationUser() {
		return lastModificationUser;
	}

	public void setLastModificationUser(String lastModificationUser) {
		this.lastModificationUser = lastModificationUser;
	}

	@Column(name = "LAST_MODIFICATION_TIME")
	@LastModifiedDate
	public ZonedDateTime getLastModificationTime() {
		return lastModificationTime;
	}

	public void setLastModificationTime(ZonedDateTime lastModificationTime) {
		this.lastModificationTime = lastModificationTime;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		TroutsEntity other = (TroutsEntity) obj;
		return getId()==other.getId();
	}
	
	
	/**
	 * 加载之后
	 */
	@PostLoad
	public void postLoad() {
	}

	/**
	 * 持久化之前产生该事件
	 */
	@PrePersist
	public void prePersist() {

	}

	/**
	 * 持久化之后产生该事件
	 */
	@PostPersist
	public void postPersist() {
	

		TransactionSynchronizationManager
				.registerSynchronization(new TransactionSynchronizationAdapter() {
					@Override
					public void beforeCommit(boolean readOnly) {

					}

					@Override
					public void afterCompletion(int status) {
						if (status == TransactionSynchronization.STATUS_COMMITTED) {
							
							
							TroutsLogUtils.printLog("{},实体保存成功",this.getClass().getSimpleName());
							
						}
					}
				});

	}


	


	/**
	 * 更新之前产生该事
	 */
	@PreUpdate
	public void preUpdate() {

	}

	/**
	 * 更新之后产生该事
	 */
	@PostUpdate
	public void postUpdate() {

	}

	/**
	 * 删除之前产生该事件
	 */
	@PreRemove
	public void preRemove() {

	}

	/**
	 * 删除之后产生该事件
	 */
	@PostRemove
	public void postRemove() {

	}




}
