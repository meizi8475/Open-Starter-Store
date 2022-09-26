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
package com.saucesubfresh.starter.crawler.enums;

import com.saucesubfresh.starter.crawler.exception.CrawlerException;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * @author lijunping
 */
public enum ExpressionType {

    XPath, Regex, Css, JsonPath;

    public static ExpressionType of(String expressionType){
        return Arrays.stream(ExpressionType.values())
                .filter(type -> StringUtils.equals(expressionType, type.name()))
                .findFirst().orElseThrow(()->new CrawlerException("不支持该操作：" + expressionType));
    }

}
