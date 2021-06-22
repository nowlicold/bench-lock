package com.bench.lock;

import java.util.concurrent.TimeUnit;

/**
 * 软件锁<br>
 * 特别要注意，在使用软件锁时，如果业务执行很快，会在等待时间内，锁定成功多次，所以每次锁定成功后，必须要重新判断业务数据是否可执行
 * 
 * @author cold
 * 
 * @version $Id: Lock.java, v 0.1 2011-7-26 下午05:03:53 cold Exp $
 */
public interface Lock {

	/**
	 * 获取锁名称
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * 锁定,如果能锁定，则立即返回，不能锁定，则可能会等待一段时间，如果能锁定，则返回true，否则返回false<Br>
	 * 
	 * @return
	 */
	public boolean tryLock();

	/**
	 * 尝试锁定
	 * 
	 * @param time
	 *            等待时间
	 * @param unit
	 * @return
	 */
	public boolean tryLock(long time, TimeUnit unit);

	/**
	 * 解锁
	 * 
	 */
	public void unlock();

	/**
	 * 强制解锁
	 */
	public void forceUnlock();
}
