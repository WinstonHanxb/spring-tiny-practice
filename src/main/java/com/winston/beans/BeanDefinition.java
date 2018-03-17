package com.winston.beans;

/**
 * 定义了什么是bean
 * 以及bean里面需要的是什么信息
 */
public class BeanDefinition {
    private Object bean;
    private Class beanClass;
    private PropertyValues propertyValues =new PropertyValues();
    private String className;

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;

    }

    public Class getBeanClass() {
        return beanClass;
    }


    public void setClassName(String className) {
        this.className = className;
        try {
            this.beanClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
