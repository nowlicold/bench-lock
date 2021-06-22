package com.bench.lock;


import com.yuan.common.enums.lock.LockTypeEnum;

/**
 * 创建Lock
 * 
 * @author cold
 * 
 * @version $Id: LockCreater.java, v 0.1 2011-8-2 下午04:41:24 cold Exp $
 */
public interface LockCreater {

	/**
	 * 初始化
	 */
	public void init() throws Exception;

	/**
	 * 创建应用Lock，和应用集群相关
	 * 
	 * @param lockName
	 * @return
	 */
	public Lock createAppLock(String lockName);

	/**
	 * 创建应用Lock，和应用集群相关
	 * 
	 * @param lockName
	 * @param tempLock
	 *            是否临时锁，如果是临时锁，则使用后，将被删除
	 * @return
	 */
	public Lock createAppLock(String lockName, boolean tempLock);

	/**
	 * 创建全局Lock，和应用无关
	 * 
	 * @param lockName
	 * @return
	 */
	public Lock createGlobalLock(String lockName);

	/**
	 * 创建全局Lock，和应用无关
	 * 
	 * @param lockName
	 * @param tempLock
	 *            是否临时锁，如果是临时锁，则使用后，将被删除
	 * @return
	 */
	public Lock createGlobalLock(String lockName, boolean tempLock);

	/**
	 * 得到锁类型
	 * 
	 * @return
	 */
	public LockTypeEnum getLockType();

}
