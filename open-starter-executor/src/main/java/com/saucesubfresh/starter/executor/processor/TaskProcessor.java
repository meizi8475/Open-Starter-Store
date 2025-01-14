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
package com.saucesubfresh.starter.executor.processor;


import com.saucesubfresh.starter.executor.domain.Task;
import com.saucesubfresh.starter.executor.handler.TaskHandler;

import java.util.List;

/**
 * 任务执行向外暴露接口
 *
 * @author lijunping
 */
public interface TaskProcessor<T extends Task> {

  /**
   * 任务执行方法
   *
   * @param tasks 要执行的任务
   * @param handler 任务执行回调函数
   */
  void execute(List<T> tasks, TaskHandler handler);

}
