/*
 * Copyright 2025 arslansalim.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.cdap.directives.row;

import java.util.List;

import io.cdap.wrangler.api.Arguments;
import io.cdap.wrangler.api.Row;


class AggregateStats {

    List<Row> execute(List<Row> inputRows, Object object) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    void initialize(Arguments arguments) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
