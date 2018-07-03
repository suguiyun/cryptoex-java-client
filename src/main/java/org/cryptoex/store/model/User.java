package org.cryptoex.store.model;

import org.cryptoex.enums.UserType;
import org.cryptoex.store.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Store user entity.
 * 
 * @author _admin
 */
@Entity
@Table(name = "users")
public class User extends AbstractEntity {

	@Column(nullable = false, updatable = false, length = VAR_ENUM)
	public UserType type;

	/**
	 * User level: 0, 1, 2... VIP user has higher value.
	 */
	@Column(nullable = false)
	public int level;

	@Column(nullable = false)
	public boolean canSignin;

	@Column(nullable = false)
	public boolean canTrade;

	@Column(nullable = false)
	public boolean canWithdraw;

	@Override
	public String toString() {
		return String.format("{User: id=%d, type=%s, level=%s, permission(signin,trade,withdraw)=%s,%s,%s}", this.id,
				this.type.name(), this.level, this.canSignin, this.canTrade, this.canWithdraw);
	}
}
