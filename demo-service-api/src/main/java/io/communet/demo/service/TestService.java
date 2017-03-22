package io.communet.demo.service;

import io.communet.demo.common.vo.Response;

/**
 * <p>function:
 * <p>User: LeeJohn
 * <p>Date: 2017/3/21
 * <p>Version: 1.0
 */
public interface TestService {
    Response<String> test(String params1);
}
