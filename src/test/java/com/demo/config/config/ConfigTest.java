package com.demo.config.config;

import com.demo.config.www.service.cache.config.impl.ConfigCacheServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Wang Junbo
 * @Description
 * @create 2020-09-01 15:19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/config/applicationContext.xml"})
public class ConfigTest {

    @Autowired
    private ConfigCacheServiceImpl configCacheService;

    @Test
    public void testRefreshGet() {
        System.out.println(configCacheService.get("home.module.core.web.url", true));
        configCacheService.refresh();
        System.out.println(configCacheService.get("home.module.core.web.url", true));
    }

    /**
     * 测试刷新过程中获取配置
     *
     * 刷新方法加上线程睡眠 模拟耗时的刷新操作，模拟刷新后的值变化
     *         try {
     *             Thread.sleep(4000L);
     *             this.configCache.put("home.module.core.web.url", "core");
     *         } catch (InterruptedException e) {
     *             e.printStackTrace();
     *         }
     * 运行结果：
     * main get http://192.168.107.106:8080/scooper-core-rest-web/#/main/addressManage
     * Thread-3 refresh
     * Thread-13 get core
     * Thread-11 get core
     * Thread-12 get core
     * Thread-7 get core
     * Thread-8 get core
     * Thread-9 get core
     * Thread-10 get core
     * Thread-4 get core
     * Thread-6 get core
     * Thread-5 get core
     *
     */
    @Test
    public void testRefreshGet1() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " get " + configCacheService.get("home.module.core.web.url", true));

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " refresh");
                configCacheService.refresh();
            }).start();
        }

        Thread.sleep(1000L);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " get " + configCacheService.get("home.module.core.web.url", true));
            }).start();
        }
        // 必须让主线程等待，否则会直接跑完  控制台看不到其它线程打印的结果
        Thread.sleep(10000L);
    }
}
