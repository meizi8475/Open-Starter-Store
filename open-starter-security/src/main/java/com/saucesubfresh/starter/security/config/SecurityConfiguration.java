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
package com.saucesubfresh.starter.security.config;

import com.saucesubfresh.starter.security.interceptor.SecurityInterceptor;
import com.saucesubfresh.starter.security.properties.SecurityProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lijunping
 */
@ConditionalOnBean(SecurityInterceptor.class)
public class SecurityConfiguration implements WebMvcConfigurer {

    private final SecurityProperties securityProperties;
    private final SecurityInterceptor securityInterceptor;

    public SecurityConfiguration(SecurityProperties securityProperties, SecurityInterceptor securityInterceptor) {
        this.securityProperties = securityProperties;
        this.securityInterceptor = securityInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(securityInterceptor)
                .excludePathPatterns(securityProperties.getIgnorePaths())
                .excludePathPatterns(securityProperties.getDefaultIgnorePaths());
    }
}
