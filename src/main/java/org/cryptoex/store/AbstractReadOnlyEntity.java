package org.cryptoex.store;

import javax.persistence.*;

/**
 * Base read-only entity class.
 * 
 * @author _admin
 */
@MappedSuperclass
public class AbstractReadOnlyEntity implements EntitySupport {

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

	// hook for pre-insert:
	@PrePersist
	void preInsert() {
		if (this.createdAt == 0) {
			this.createdAt = System.currentTimeMillis();
		}
	}

	// hook for pre-update:
	@PreUpdate
	void preUpdate() {
		throw new UnsupportedOperationException("Cannot update read-only entity: " + getClass().getSimpleName());
	}
}
