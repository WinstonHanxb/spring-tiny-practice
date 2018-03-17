package com.winston.beans.factory;

import com.winston.beans.BeanDefinition;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 模板抽象方法，将bean工厂的接口实现，并将创建bean的具体过程交给子类去实现
 */
public abstract class AbstractBeanFactory implements BeanFactory {
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap();
    /**
     * 通过懒加载解决循环依赖问题。
     * 在调用getBean方法时候，如果没有实例，在这个时刻创建实例，如果有（不管内部的属性是否已经初始化完毕），直接返回
     * 循环依赖出现问题，都是因为加载的时候互相依赖，在构造的时候无法完成初始化，懒加载解决了这个问题
     */
    @Override
    public Object getBean(String name)  throws Exception{
        /**
         * 懒加载，在需要bean的时候才真正初始化bean
         */
        BeanDefinition beanDefinition=  beanDefinitionMap.get(name);
        if(null == beanDefinition){
            throw new IllegalArgumentException("No bean named " + name + " is defined");
        }
        Object bean = beanDefinition.getBean();
        if(bean == null){
            bean = doCreateBean(beanDefinition);
        }
        return bean;
    }
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception {
        beanDefinitionMap.put(name, beanDefinition);
    }

    /**
     * 交给子类去实现各种不同定义的bean实现过程
     */
    protected abstract Object doCreateBean(BeanDefinition beanDefinition) throws Exception;
}
