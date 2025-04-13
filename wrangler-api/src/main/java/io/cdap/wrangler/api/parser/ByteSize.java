/*
 * Copyright © 2025 CDAP, Inc.
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
package io.cdap.wrangler.api.parser;

import com.google.gson.JsonElement;

public class ByteSize implements Token {

    private final String raw;
    private final long bytes;

    public ByteSize(String raw) {
        this.raw = raw;
        this.bytes = parse(raw);
    }

    private long parse(String input) {
        input = input.trim().toUpperCase();
        if (input.endsWith("KB")) {
            return Long.parseLong(input.replace("KB", "")) * 1024;
        
        }if (input.endsWith("MB")) {
            return Long.parseLong(input.replace("MB", "")) * 1024 * 1024;
        
        }if (input.endsWith("GB")) {
            return Long.parseLong(input.replace("GB", "")) * 1024 * 1024 * 1024;
        
        }if (input.endsWith("B")) {
            return Long.parseLong(input.replace("B", ""));
        
        }return Long.parseLong(input);
    }
    public JsonElement toJson(){
        return null;
    }
    public TokenType type(){
        return null;
    }

    public long getBytes() {
        return bytes;
    }

    @Override
    public String value() {
        return raw;
    }
}
