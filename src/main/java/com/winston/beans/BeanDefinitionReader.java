package com.winston.beans;

/**
 * 读取Bean的定义文件，读取这个文件通过和BeanFactory实现了职责分离
 */
public interface BeanDefinitionReader {
    void loadBeanDefinitions(String location) throws Exception;
}
