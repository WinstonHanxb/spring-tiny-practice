package com.winston.beans;

import com.winston.beans.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {
    /**存放Bean定义的map*/
    private Map<String, BeanDefinition> registry;
    private ResourceLoader resourceLoader;

    /**
     * 定义了DefinitionReader通过loader来实现加载
     * @param loader
     */
    public AbstractBeanDefinitionReader(ResourceLoader loader) {
        this.registry = new HashMap<String, BeanDefinition>();
        this.resourceLoader = loader;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

    public Map<String, BeanDefinition> getRegistry() {
        return registry;
    }
}
