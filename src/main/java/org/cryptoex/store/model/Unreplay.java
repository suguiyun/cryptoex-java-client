package org.cryptoex.store.model;

import org.cryptoex.store.AbstractReadOnlyEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Make api un-replayable by unique constraint.
 * 
 * @author _admin
 */
@Entity
@Table(name = "unreplays", uniqueConstraints = @UniqueConstraint(name = "UNI_UNIQUE_ID", columnNames = "uniqueId"))
public class Unreplay extends AbstractReadOnlyEntity {

	@Column(nullable = false, updatable = false, length = VAR_CHAR_50)
	public String uniqueId;

	@Override
	public String toString() {
		return String.format("{Unreplay: uniqueId=%d}", this.uniqueId);
	}
}
