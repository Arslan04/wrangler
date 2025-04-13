/*
 * Copyright © 2017-2019 Cask Data, Inc.
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


package io.cdap.directives.row;

import io.cdap.wrangler.api.Row;
// Removed the import as the package io.cdap.wrangler.api.executor does not exist
import io.cdap.wrangler.api.parser.ColumnName;
import io.cdap.wrangler.api.parser.TokenType;
import io.cdap.wrangler.api.Arguments;
import io.cdap.wrangler.api.parser.UsageDefinition;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import com.google.gson.JsonElement;

public class AggregateStatsTest {

    @Test
    public void testAggregateStatsDirective() throws Exception {
        AggregateStats directive = new AggregateStats();

        Arguments arguments = new Arguments() {
            private final Map<String, Object> args = Map.of(
                "sizeColumn", new ColumnName("size"),
                "timeColumn", new ColumnName("duration"),
                "outSizeColumn", new ColumnName("total_size"),
                "outTimeColumn", new ColumnName("total_time")
            );

         
            @SuppressWarnings("unchecked")
           
            @Override
            public boolean has(String name) {
                return args.containsKey(name);
            }

            @Override
            public int size() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public boolean contains(String name) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public TokenType type(String name) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public int line() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public int column() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public String source() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public JsonElement toJson() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };

        directive.initialize(arguments);

        List<Row> inputRows = new ArrayList<>();
        inputRows.add(new Row("size", "10MB").add("duration", "1h"));
        inputRows.add(new Row("size", "512KB").add("duration", "30m"));

        List<Row> output = directive.execute(inputRows, null); // Pass null if ExecutorContext is not required

        Row result = output.get(0);
        double totalSize = (double) result.getValue("total_size");  // MB
        double totalTime = (double) result.getValue("total_time");  // seconds

        Assert.assertEquals(10.5, totalSize, 0.01);  // 10MB + 0.5MB
        Assert.assertEquals(5400.0, totalTime, 0.01);  // 1h + 30m = 3600 + 1800
    }
}
