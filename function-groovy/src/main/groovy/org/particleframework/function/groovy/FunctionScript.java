/*
 * Copyright 2017 original authors
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package org.particleframework.function.groovy;

import org.particleframework.context.ApplicationContext;
import org.particleframework.context.env.PropertySource;
import org.particleframework.core.annotation.Internal;
import org.particleframework.function.executor.FunctionInitializer;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Base class for Function scripts
 *
 * @author Graeme Rocher
 * @since 1.0
 */
public abstract class FunctionScript extends FunctionInitializer implements PropertySource {

    public FunctionScript() {
    }

    protected FunctionScript(ApplicationContext applicationContext) {
        super(applicationContext, false);
    }

    private Map<String, Object> props;

    @Override
    @Internal
    public Object get(String key) {
        return resolveProps().get(key);
    }

    @Override
    @Internal
    public Iterator<String> iterator() {
        return resolveProps().keySet().iterator();
    }

    protected void addProperty(String name, Object value) {
        resolveProps().put(name, value);
    }

    private Map<String, Object> resolveProps() {
        if(props == null) {
            props = new LinkedHashMap<>();
        }
        return props;
    }

    @Override
    @Internal
    protected void startThis(ApplicationContext applicationContext) {
        // no-op this, equivalent behaviour will be called from the script constructor
    }

    @Override
    @Internal
    protected void injectThis(ApplicationContext applicationContext) {
        // no-op this, equivalent behaviour will be called from the script constructor
    }
}