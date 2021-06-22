package com.bench.lock;

/**
 * 锁执行
 * 
 * @author cold
 * 
 * @version $Id: LockExecuter.java, v 0.1 2013-5-24 下午4:40:02 cold Exp $
 */
public interface LockExecuter {

	/**
	 * 锁定后执行
	 * 
	 */
	public void lockedExecute();

	/**
	 * 无法锁定后执行
	 * 
	 */
	public default void canNotLockExecute() {
		// 默认ignore
	}
}
