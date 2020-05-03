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

import com.github.nalukit.nalu.client.internal.HandlerRegistrations;

public abstract class AbstractComponent<C extends IsComponent.Controller, W>
    implements IsComponent<C, W> {
  
  protected HandlerRegistrations handlerRegistrations = new HandlerRegistrations();
  
  private C controller;
  
  private W element;
  
  public AbstractComponent() {
  }
  
  protected void initElement(W element) {
    this.element = element;
  }
  
  @Override
  public W asElement() {
    assert element != null : "no element set!";
    return this.element;
  }
  
  /**
   * <b>Important:<br>
   * Inside the render-method, you have to call the initElement-method!</b>
   */
  @Override
  public abstract void render();
  
  @Override
  public void bind() {
    // if you need to bind some handlers and would like to do this in a separate method
    // just override this method.
  }
  
  @Override
  public C getController() {
    return this.controller;
  }
  
  @Override
  public void setController(C controller) {
    this.controller = controller;
  }
  
  @Override
  public void onAttach() {
    // if you need to do something in case the widget is added to the DOM tree
  }
  
  @Override
  public void onDetach() {
    // if you need to do something in case the widget is removed from the DOM tree
  }
  
  /**
   * internal framework method! Will be called by the framework after the
   * stop-method f the controller is called
   *
   * <b>DO NOT CALL THIS METHOD! THIS WILL LEAD TO UNEXPECTED BEHAVIOR!</b>
   */
  @Override
  public final void removeHandlers() {
    this.handlerRegistrations.removeHandler();
    this.handlerRegistrations = new HandlerRegistrations();
  }
  
}
