/*
 * Copyright © 2016 Cask Data, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.example;

import io.cdap.cdap.api.annotation.Description;
import io.cdap.cdap.api.annotation.Name;
import io.cdap.cdap.api.annotation.Plugin;
import io.cdap.cdap.api.data.format.StructuredRecord;
import io.cdap.cdap.api.data.schema.Schema;
import io.cdap.cdap.etl.api.Emitter;
import io.cdap.cdap.etl.api.PipelineConfigurer;
import io.cdap.cdap.etl.api.StageConfigurer;
import io.cdap.cdap.etl.api.Transform;
import io.cdap.cdap.etl.api.TransformContext;

/**
 * Transform that can transforms specific fields to lowercase or uppercase.
 */
@Plugin(type = Transform.PLUGIN_TYPE)
@Name(NoOpTransform.NAME)
@Description("Allows values to pass through the transform with no changes.")
public class NoOpTransform extends Transform<StructuredRecord, StructuredRecord> {

  public static final String NAME = "NoOp";

  // configurePipeline is called only once, when the pipeline is deployed. Static validation should be done here.
  @Override
  public void configurePipeline(PipelineConfigurer pipelineConfigurer) {
    StageConfigurer stageConfigurer = pipelineConfigurer.getStageConfigurer();
    // the output schema is always the same as the input schema
    Schema inputSchema = stageConfigurer.getInputSchema();
    stageConfigurer.setOutputSchema(inputSchema);
  }

  // initialize is called once at the start of each pipeline run
  @Override
  public void initialize(TransformContext context) throws Exception {
  }

  // transform is called once for each record that goes into this stage
  @Override
  public void transform(StructuredRecord record, Emitter<StructuredRecord> emitter)
      throws Exception {
    emitter.emit(record);
  }
}
