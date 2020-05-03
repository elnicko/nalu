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

package com.github.nalukit.nalu.client.component;

import com.github.nalukit.nalu.client.internal.annotation.NaluInternalUse;

public interface IsBlockComponentController<V> {
  
  void setComponent(V component);
  
  /**
   * Shows the block
   *
   * <b>DO NOT CALL THIS METHOD! THIS WILL LEAD TO UNEXPECTED BEHAVIOR!</b>
   */
  @NaluInternalUse
  void show();
  
  /**
   * hides the block
   *
   * <b>DO NOT CALL THIS METHOD! THIS WILL LEAD TO UNEXPECTED BEHAVIOR!</b>
   */
  @NaluInternalUse
  void hide();
  
  void onBeforeShow();
  
  void onBeforeHide();
  
  /**
   * Add the element to the root element
   */
  void append();
  
  //  /**
  //   * Method is called during onAttach.
  //   * Nalu uses the method to call the onAttach-method of the component.
  //   *
  //   * <b>DO NOT CALL THIS METHOD! THIS WILL LEAD TO UNEXPECTED BEHAVIOR!</b>
  //   */
  //  @NaluInternalUse
  //  void onAttach();
  //
  //  /**
  //   * Method is called during onDetach.
  //   * Nalu uses the method to call the onDetach-method of the component.
  //   *
  //   * <b>DO NOT CALL THIS METHOD! THIS WILL LEAD TO UNEXPECTED BEHAVIOR!</b>
  //   */
  //  @NaluInternalUse
  //  void onDetach();
  //
  //  /**
  //   * internal framework method! Will be called by the framework after the
  //   * stop-method of the controller is called
  //   *
  //   * <b>DO NOT CALL THIS METHOD! THIS WILL LEAD TO UNEXPECTED BEHAVIOR!</b>
  //   */
  //  @NaluInternalUse
  //  void removeHandlers();
  //
  //  /**
  //   * The activate-method will be called besides the the start-method.
  //   * In opposite to the start-method, it will also be called in case the controller is cached.
  //   * <p>
  //   * If you have to do something in case controller gets active,
  //   * that's the right place.
  //   */
  //  void activate();
  //
  //  /**
  //   * The deactivate-method will be called besides the the stop-method.
  //   * In opposite to the stop-method, it will also be called in case the controller is cached.
  //   * <p>
  //   * If you have to do something in case controller gets deactivated,
  //   * that's the right place.
  //   */
  //  void deactivate();
  //
  //  /**
  //   * The start-method will be called in case a controller gets instantiated.
  //   * the method will not be called in case a controller is cached.
  //   * <p>
  //   * If you have to do something in case controller gets started,
  //   * that's the right place.
  //   */
  //  void start();
  //
  //  /**
  //   * The stop-method will be called in case a controller is stopped.
  //   * the method will not be called in case a controller is cached.
  //   * <p>
  //   * If you have to do something in case controller gets stopped,
  //   * that's the right place.
  //   */
  //  void stop();
  //
  //  /**
  //   * The bind-method will be called before the component of the
  //   * controller is created.
  //   * <p>
  //   * This method runs before the component and composites are
  //   * created. This is f.e.: a got place to do some
  //   * authentication checks.
  //   * <p>
  //   * Keep in mind, that the method is asynchronous. Once you have
  //   * done your work, you have to call <b>loader.continueLoading()</b>.
  //   * Otherwise Nalu will stop working!
  //   * <p>
  //   * Inside the method can the routing process gets interrupted
  //   * by throwing a RoutingInterceptionException.
  //   * <p>
  //   * <b>The method will not be called in case a controller is cached!</b>
  //   * <p>
  //   * <b>Attention:</b>
  //   * Do not call super.bind(loader)! Cause this will tell Nalu to
  //   * continue loading!
  //   *
  //   * @param loader loader to tell Nalu to continue loading the controller
  //   * @throws com.github.nalukit.nalu.client.exception.RoutingInterceptionException in case the create controller
  //   *                                      process should be interrupted
  //   */
  //  void bind(ControllerLoader loader)
  //      throws RoutingInterceptionException;
  //
  //  interface ControllerLoader {
  //
  //    void continueLoading();
  //
  //  }
  
}
