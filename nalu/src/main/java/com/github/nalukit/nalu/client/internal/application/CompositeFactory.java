/*
 * Copyright (c) 2018 - 2019 - Frank Hossfeld
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

package com.github.nalukit.nalu.client.internal.application;

import com.github.nalukit.nalu.client.component.AbstractComponentController;
import com.github.nalukit.nalu.client.component.AbstractCompositeController;
import com.github.nalukit.nalu.client.exception.RoutingInterceptionException;
import com.github.nalukit.nalu.client.internal.annotation.NaluInternalUse;

import java.util.HashMap;
import java.util.Map;

@NaluInternalUse
public class CompositeFactory {

  /* instance of the controller factory */
  private static CompositeFactory instance;

  /* map of components (key: name of class, Value: ControllerCreator */
  private Map<String, IsCompositeCreator> compositeCreatorFactory;

  /* map of stored components (key: name of class, Value: instance of controller */
  private Map<String, AbstractCompositeController<?, ?, ?>> compositeControllerStore;

  private CompositeFactory() {
    this.compositeCreatorFactory = new HashMap<>();
    this.compositeControllerStore = new HashMap<>();
  }

  public static CompositeFactory get() {
    if (instance == null) {
      instance = new CompositeFactory();
    }
    return instance;
  }

  public void registerComposite(String controller,
                                 IsCompositeCreator creator) {
    this.compositeCreatorFactory.put(controller,
                                     creator);
  }

  public CompositeInstance getComposite(String controller,
                           String... parms)
      throws RoutingInterceptionException {
    if (this.compositeCreatorFactory.containsKey(controller)) {
      IsCompositeCreator compositeCreator = this.compositeCreatorFactory.get(controller);
      return compositeCreator.create(parms);
    }
    return null;
  }

  public AbstractCompositeController<?, ?, ?> getCompositeFormStore(String controllerClassName) {
    return this.compositeControllerStore.get(this.classFormatter(controllerClassName));
  }

  public <C extends AbstractCompositeController<?, ?, ?>> void storeInCache(C controller) {
    String key = this.classFormatter(controller.getClass()
                                               .getCanonicalName());
    this.compositeControllerStore.put(key,
                                      controller);
  }

  public <C extends AbstractCompositeController<?, ?, ?>> void removeFromCache(C controller) {
    String key = this.classFormatter(controller.getClass()
                                               .getCanonicalName());
    this.compositeControllerStore.remove(key);
  }

  public void clearControllerCache() {
    this.compositeControllerStore.clear();
  }

  private String classFormatter(String className) {
    return className.replace(".",
                             "_");
  }

}
