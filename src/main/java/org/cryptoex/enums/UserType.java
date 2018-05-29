package org.cryptoex.enums;

/**
 * User type enumeration.
 * 
 * @author _admin
 */
public enum UserType {
	/**
	 * System manager who make management from api /mapi/*.
	 */
	MANAGER,

	/**
	 * System UI who make request from api /ui/*.
	 */
	UI,

	/**
	 * System wallet who make deposit or withdraw request from api /wallet/*.
	 */
	WALLET,

	/**
	 * Capital pool who keeps assets.
	 */
	ASSET,

	/**
	 * Capital pool who keeps debts.
	 */
	DEBT,

	/**
	 * System user who receive fee from exchange.
	 */
	TRADE_FEE,

	/**
	 * System user who receive fee from withdraw.
	 */
	WITHDRAW_FEE,

	/**
	 * Agent user.
	 */
	BROKER,

	/**
	 * Normal users that do trades.
	 */
	TRADER;
}
