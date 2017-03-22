package io.communet.demo.utils.test;

import io.communet.demo.utils.OkHttpUtil;
import org.junit.Test;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2017/3/13
 * <p>Version: 1.0
 */
public class OkhttpUtilsTest {

    @Test
    public void testGet(){
        System.out.println(OkHttpUtil.get("http://www.hao123.com"));
    }

    @Test
    public void testPost(){
        String url = "";
    }

}
