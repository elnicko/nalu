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
package com.github.nalukit.nalu.processor.generator;

import com.github.nalukit.nalu.client.internal.ClientLogger;
import com.github.nalukit.nalu.processor.ProcessorUtils;
import com.github.nalukit.nalu.processor.model.MetaModel;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Modifier;

public class FiltersGenerator {
  
  private ProcessingEnvironment processingEnvironment;
  
  private ProcessorUtils processorUtils;
  
  private MetaModel metaModel;
  
  private TypeSpec.Builder typeSpec;
  
  @SuppressWarnings("unused")
  private FiltersGenerator() {
    super();
  }
  
  private FiltersGenerator(Builder builder) {
    this.processingEnvironment = builder.processingEnvironment;
    this.metaModel             = builder.metaModel;
    this.typeSpec              = builder.typeSpec;
    
    setUp();
  }
  
  private void setUp() {
    this.processorUtils = ProcessorUtils.builder()
                                        .processingEnvironment(this.processingEnvironment)
                                        .build();
  }
  
  public static Builder builder() {
    return new Builder();
  }
  
  void generate() {
    // method must always be created!
    MethodSpec.Builder loadFiltersMethod = MethodSpec.methodBuilder("loadFilters")
                                                     .addAnnotation(Override.class)
                                                     .addModifiers(Modifier.PUBLIC);
    
    this.metaModel.getFilters()
                  .forEach(classNameModel -> loadFiltersMethod.addStatement("$T $L = new $T()",
                                                                            ClassName.get(classNameModel.getPackage(),
                                                                                          classNameModel.getSimpleName()),
                                                                            this.processorUtils.createFullClassName(classNameModel.getClassName()),
                                                                            ClassName.get(classNameModel.getPackage(),
                                                                                          classNameModel.getSimpleName()))
                                                              .addStatement("$L.setContext(super.context)",
                                                                            this.processorUtils.createFullClassName(classNameModel.getClassName()))
                                                              .addStatement("$L.setEventBus(super.eventBus)",
                                                                            this.processorUtils.createFullClassName(classNameModel.getClassName()))
                                                              .addStatement("super.routerConfiguration.getFilters().add($L)",
                                                                            this.processorUtils.createFullClassName(classNameModel.getClassName()))
                                                              .addStatement("$T.get().logDetailed(\"AbstractApplication: filter >> $L << created\", 0)",
                                                                            ClassName.get(ClientLogger.class),
                                                                            this.processorUtils.createFullClassName(classNameModel.getClassName())));
    
    typeSpec.addMethod(loadFiltersMethod.build());
  }
  
  public static final class Builder {
    
    ProcessingEnvironment processingEnvironment;
    
    MetaModel metaModel;
    
    TypeSpec.Builder typeSpec;
    
    /**
     * Set the MetaModel of the currently generated eventBus
     *
     * @param metaModel meta data model of the event bus
     * @return the Builder
     */
    public Builder metaModel(MetaModel metaModel) {
      this.metaModel = metaModel;
      return this;
    }
    
    /**
     * Set the typeSpec of the currently generated eventBus
     *
     * @param typeSpec type spec of the current event bus
     * @return the Builder
     */
    Builder typeSpec(TypeSpec.Builder typeSpec) {
      this.typeSpec = typeSpec;
      return this;
    }
    
    public Builder processingEnvironment(ProcessingEnvironment processingEnvironment) {
      this.processingEnvironment = processingEnvironment;
      return this;
    }
    
    public FiltersGenerator build() {
      return new FiltersGenerator(this);
    }
    
  }
  
}
