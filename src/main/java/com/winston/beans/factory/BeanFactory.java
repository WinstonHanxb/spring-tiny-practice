package com.winston.beans.factory;


/**
 * 工厂接口，定义了获取Bean和注册Bean的两个方法
 */
public interface BeanFactory {
    Object getBean(String name) throws Exception;
}
