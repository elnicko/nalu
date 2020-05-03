/*
 * Copyright (c) 2018 - 2020 - Frank Hossfeld
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not
 *  use this file except in compliance with the License. You may obtain a copy of
 *  the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *  License for the specific language governing permissions and limitations under
 *  the License.
 */
package com.github.nalukit.nalu.client.application.annotation;

import com.github.nalukit.nalu.client.application.IsLogger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This annotation should be used to activate the logs. It has the following attributes:
 * <ul>
 * <li>logLevel: level of the logs. If the level is set to simple, only the fired events will be
 * displayed in the log, otherwise fired events and handlers of these events will be logged.</li>
 * <li>logger: class of the logger to use.</li>
 * </ul>
 * <br>
 * This annotation should be used only on interfaces that extend <code>IsApplication</code>.
 * <br>
 * The annotation is optional.
 *
 * @author Frank Hossfeld
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Debug {
  
  /**
   * Log Level of the application (during development). There are two
   * levels: <b>SIMPLE</b> and <b>DETAILED</b>.
   * <ul>
   * <li>SIMPLE: basic log information</li>
   * <li>DETAILED: detailed log information</li>
   * </ul>
   *
   * @return log level of the application
   */
  LogLevel logLevel() default LogLevel.SIMPLE;
  
  /**
   * Custom logger definition. Will replace the DefaultLogger.
   *
   * @return the custom logger
   */
  Class<? extends IsLogger> logger();
  
  enum LogLevel {
    SIMPLE,
    DETAILED
  }
  
}
