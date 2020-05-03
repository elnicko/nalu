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

package com.github.nalukit.nalu.simpleapplication02.client.logger;

import com.github.nalukit.nalu.client.application.IsLogger;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DefaultLogger
    implements IsLogger {
  
  private static final String INDENT = "    ";
  
  @Override
  public void log(String message,
                  int depth) {
    if ("on".equals(System.getProperty("superdevmode",
                                       "off"))) {
      System.out.println(createLog(message,
                                   depth));
    }
  }
  
  private String createLog(String message,
                           int depth) {
    if (depth == 0) {
      return "Nalu-Logger -> " + message;
    } else {
      String indent = IntStream.range(0,
                                      depth)
                               .mapToObj(i -> INDENT)
                               .collect(Collectors.joining("",
                                                           "",
                                                           message));
      return "Nalu-Logger -> " + indent;
    }
  }
  
}
