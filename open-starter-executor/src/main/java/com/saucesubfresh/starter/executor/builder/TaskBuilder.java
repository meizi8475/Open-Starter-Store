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
package com.saucesubfresh.starter.executor.builder;

import com.saucesubfresh.starter.executor.domain.Task;
import com.saucesubfresh.starter.executor.handler.TaskHandler;

/**
 * 1. 把执行的任务转换为 Runnable
 *
 * 2. 对 Runnable 进行代理并返回 Runnable 的代理
 *
 * @author lijunping
 */
public interface TaskBuilder {

  /**
   * 构建任务方法
   *
   * @param task
   * @param handler
   * @return
   */
  Runnable build(Task task, TaskHandler handler);

}
