/**
 * BenchCode.com Inc.
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.bench.lock;

import com.bench.lock.enums.LockAppNameEnum;
import com.bench.lock.model.LockDataType;

import java.util.concurrent.TimeUnit;


/**
 * 锁组件 <br>
 * 特别要注意，使用默认为redis 因此使用者需要配置redis的相关配置，当执行器执行完毕后 自动解锁
 * 如无法解锁成功 会在 10秒后redis删除该锁
 *
 * 
 * @author cold
 * 
 * @version $Id: LockComponent.java, v 0.1 2011-7-26 下午04:48:19 cold Exp $
 */
public interface LockComponent {


	/**
	 * 根据lockName锁定后（应用远程锁），执行executer，默认是临时锁
	 *
	 * @param lockName
	 * @param executer
	 */
	public void appTryLockForExecute(LockAppNameEnum appName, String lockName, LockExecuter executer);



	/**
	 * 根据lockName锁定后（应用远程锁），执行executer
	 *

	 * @param lockName
	 *
	 * @param executer
	 *
	 * @param executer
	 *            锁执行器
	 * @param waitLockTime
	 *            等待锁时间
	 * @param waitLockTimeUnit
	 */
	public void appTryLockForExecute(LockAppNameEnum appName,String lockName, LockExecuter executer, long waitLockTime, TimeUnit waitLockTimeUnit);

}
