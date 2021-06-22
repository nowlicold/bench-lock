/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.bench.lock;

import java.util.List;

/**
 * 锁存储
 * 
 * @author cold
 * 
 * @version $Id: LockStore.java, v 0.1 2013-3-5 下午7:46:26 cold Exp $
 */
public interface LockStore {

	/**
	 * 存储一把锁
	 * 
	 * @param lock
	 */
	public void storeLock(com.bench.lock.Lock lock);

	/**
	 * 移除并返回一把锁
	 * 
	 * @param lockName
	 */
	public com.bench.lock.Lock removeLock(String lockName);

	/**
	 * 返回当前所有的锁
	 * 
	 * @return
	 */
	public List<com.bench.lock.Lock> getAllLocks();
}
