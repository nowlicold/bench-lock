package com.bench.lock.impl;

import com.bench.lang.base.string.utils.StringUtils;
import com.bench.lock.LockComponent;
import com.bench.lock.LockExecuter;
import com.bench.lock.enums.LockAppNameEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.integration.redis.util.RedisLockRegistry;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @className LockComponentImpl
 * @autor cold
 * @DATE 2021/6/22 23:34
 **/
@Slf4j
public class LockComponentImpl implements LockComponent {
    @Autowired
    private RedisLockRegistry redisLockRegistry;



    @Override
    public void appTryLockForExecute(LockAppNameEnum appName, String lockName, LockExecuter executer) {
        //无等待锁定
        appTryLockForExecute(appName,lockName,executer,0,null);

    }

    @Override
    public void appTryLockForExecute(LockAppNameEnum appName,String lockName, LockExecuter executer, long waitLockTime, TimeUnit waitLockTimeUnit) {
        //appName_lockName
        //默认为毫秒单位
        TimeUnit defaultWaitTimeUnit = TimeUnit.MILLISECONDS;
        //默认0毫秒不等待
        long defaultWaitLockTime = 0;

        if(waitLockTime != 0){
            defaultWaitLockTime = waitLockTime;
        }
        if(waitLockTimeUnit != null){
            defaultWaitTimeUnit = waitLockTimeUnit;
        }
        Lock lock =  redisLockRegistry.obtain(appName.name() + StringUtils.UNDERSCORE_SIGN+lockName);
        //如果锁异常 则获取不到lock ，则认为系统问题，宁可错误
        if(lock == null){
            throw new RuntimeException("lock获取不到异常");
        }
        boolean locked = false;
        try{
            locked = lock.tryLock(defaultWaitLockTime,defaultWaitTimeUnit);
        }catch(CannotAcquireLockException | InterruptedException ex){
            //无法锁定，抛异常无需处理，锁不住无需释放
            executer.canNotLockExecute();
            return;
        }
        if(locked){
            //如果可以锁定，对执行器进行执行，执行器也有可能抛异常 为了节省资源，捕捉后进行锁释放
            try {
                executer.lockedExecute();
            }catch(Exception ex){
                log.warn("铺捉到执行器异常:ex=",ex);

            }finally {
                //不管异常与否都会解锁
                unlock(lock);
            }
        }else{
            //未锁定，则执行未锁定执行器
            executer.canNotLockExecute();

        }
    }

    private void unlock(Lock lock){
        try {
            lock.unlock();
        }catch(Exception ex){
            log.warn("释放锁失败,ex=",ex);
            return ;
        }
    }

    public static void main(String[] args) {
        System.out.println("test");
    }
}
