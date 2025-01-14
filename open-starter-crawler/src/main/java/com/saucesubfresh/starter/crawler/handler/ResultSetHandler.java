/*
 * Copyright © 2022 organization SauceSubFresh
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.saucesubfresh.starter.crawler.handler;

import com.saucesubfresh.starter.crawler.domain.SpiderRequest;

import java.util.List;
import java.util.Map;

/**
 * 结果处理
 *
 * @author lijunping
 */
public interface ResultSetHandler {

    /**
     * 结果解析
     * @param request
     * @param content
     * @return
     */
    List<Map<String, Object>> handler(SpiderRequest request, String content);
}
