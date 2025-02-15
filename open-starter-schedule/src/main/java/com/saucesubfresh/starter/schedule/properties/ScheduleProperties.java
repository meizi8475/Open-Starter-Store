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
package com.saucesubfresh.starter.schedule.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lijunping
 */
@Data
@ConfigurationProperties(prefix = "com.saucesubfresh.schedule")
public class ScheduleProperties {

    /**
     * 时间轮槽数
     */
    private long tickDuration = 60;

    /**
     * 任务池名称 （hash）
     */
    private String taskPoolName = "schedule:task:pool";

    /**
     * 分布式锁名称
     */
    private String lockName = "schedule:lock";
}
