package org.cryptoex.store;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Base entity class.
 * 
 * @author _admin
 */
@MappedSuperclass
public class AbstractEntity implements EntitySupport {

	/**
	 * Primary key: auto-increment long.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	public long id;

	/**
	 * Created time (milliseconds).
	 */
	@Column(nullable = false, updatable = false)
	public long createdAt;

	/**
	 * Updated time (milliseconds).
	 */
	@Column(nullable = false)
	public long updatedAt;

	/**
	 * Entity version: increment when update.
	 */
	@Column(nullable = false)
	@JsonIgnore
	public long version;

	// hook for pre-insert:
	@PrePersist
	void preInsert() {
		if (this.createdAt == 0) {
			this.createdAt = this.updatedAt = System.currentTimeMillis();
		}
		this.version = 0;
	}

	// hook for pre-update:
	@PreUpdate
	void preUpdate() {
		this.updatedAt = System.currentTimeMillis();
		this.version++;
	}
}
