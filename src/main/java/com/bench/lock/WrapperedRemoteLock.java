/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.bench.lock;

import java.util.concurrent.TimeUnit;

/**
 * 封装后的远程Lock
 * 
 * @author cold
 * 
 * @version $Id: LockWrapper.java, v 0.1 2013-3-5 下午8:16:21 cold Exp $
 */
public class WrapperedRemoteLock implements com.bench.lock.Lock {

	private com.bench.lock.Lock remoteLock;

	private java.util.concurrent.locks.Lock localLock;

	public WrapperedRemoteLock(com.bench.lock.Lock remoteLock, java.util.concurrent.locks.Lock localLock) {
		super();
		this.remoteLock = remoteLock;
		this.localLock = localLock;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bench.platform.lock.Lock#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return remoteLock.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bench.platform.lock.Lock#lock()
	 */
	@Override
	public boolean tryLock() {
		// TODO Auto-generated method stub
		if (localLock.tryLock()) {
			try {
				boolean locked = remoteLock.tryLock();
				if (!locked) {
					unlock();
				}
				return locked;
			} catch (Exception e) {
				unlock();
				return false;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bench.platform.lock.Lock#unlock()
	 */
	@Override
	public void unlock() {
		// TODO Auto-generated method stub
		try {
			remoteLock.unlock();
		} catch (Exception e) {

		}
		if (localLock != null) {
			try {
				localLock.unlock();
			} catch (Exception e) {
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bench.platform.lock.Lock#forceUnlock()
	 */
	@Override
	public void forceUnlock() {
		// TODO Auto-generated method stub
		if (localLock != null) {
			try {
				localLock.unlock();
			} catch (Exception e) {
			}
		}
		try {
			remoteLock.forceUnlock();
		} catch (Exception e) {

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bench.platform.lock.Lock#tryLock(long,
	 * java.util.concurrent.TimeUnit)
	 */
	@Override
	public boolean tryLock(long time, TimeUnit unit) {
		// TODO Auto-generated method stub
		try {
			if (localLock.tryLock(time, unit)) {
				boolean locked = remoteLock.tryLock(time, unit);
				if (!locked) {
					unlock();
				}
				return locked;
			}
		} catch (Exception e) {
			unlock();
			return false;
		}
		return false;
	}
}
