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
import java.util.Collections;
import java.util.List;
import io.cdap.wrangler.api.Arguments;
import io.cdap.wrangler.api.Directive;
import io.cdap.wrangler.api.DirectiveParseException;
import io.cdap.wrangler.api.ExecutorContext;
import io.cdap.wrangler.api.Row;
import io.cdap.wrangler.api.parser.ByteSize;
import io.cdap.wrangler.api.parser.ColumnName;
import io.cdap.wrangler.api.parser.TimeDuration;
import io.cdap.wrangler.api.parser.TokenType;
import io.cdap.wrangler.api.parser.UsageDefinition;

public class AggregateStats implements Directive {
    private String sizeColumn;
    private String timeColumn;
    private String outSizeColumn;
    private String outTimeColumn;
    private long totalBytes = 0;
    private long totalMillis = 0;

    @Override
    public UsageDefinition define() {
        UsageDefinition.Builder builder = UsageDefinition.builder("aggregate-stats");
        builder.define("sizeColumn", TokenType.COLUMN_NAME);
        builder.define("timeColumn", TokenType.COLUMN_NAME);
        builder.define("outSizeColumn", TokenType.COLUMN_NAME);
        builder.define("outTimeColumn", TokenType.COLUMN_NAME);
        return builder.build();
    }

    @Override
    public void initialize(Arguments arguments) throws DirectiveParseException {
        this.sizeColumn = ((ColumnName) arguments.value("sizeColumn")).value();
        this.timeColumn = ((ColumnName) arguments.value("timeColumn")).value();
        this.outSizeColumn = ((ColumnName) arguments.value("outSizeColumn")).value();
        this.outTimeColumn = ((ColumnName) arguments.value("outTimeColumn")).value();
    }

    @Override
    public List<Row> execute(List<Row> rows, ExecutorContext ctx) {
        for (Row row : rows) {
            String sizeStr = row.getValue(sizeColumn).toString();
            String timeStr = row.getValue(timeColumn).toString();
            ByteSize size = new ByteSize(sizeStr);
            totalBytes += ((ByteSize) size).getBytes(); 
            totalMillis += TimeDuration.parse(timeStr).getMillis();
        }

        Row result = new Row();
        result.add(outSizeColumn, totalBytes / (1024.0 * 1024.0)); // MB
        result.add(outTimeColumn, totalMillis / 1000.0); // seconds

        return Collections.singletonList(result);
    }

    @Override
    public void destroy() { }
}
