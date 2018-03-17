package com.winston.beans.factory;

import com.winston.beans.BeanDefinition;
import com.winston.beans.PropertyValue;
import com.winston.BeanReference;


import java.lang.reflect.Field;

public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    /**
     * 覆盖方法，通过定义创建一个bean的实例
     */
    @Override
    protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception{
        Object bean = createBeanInstance(beanDefinition);
        /**
         * 将创建的实例引用先保存起来，再对内部的属性进行注入，解决循环加载问题
         */
        beanDefinition.setBean(bean);
        applyPropertyValues(bean, beanDefinition);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
        return beanDefinition.getBeanClass().newInstance();
    }

    protected void applyPropertyValues(Object bean, BeanDefinition mbd) throws Exception {
        for (PropertyValue propertyValue : mbd.getPropertyValues().getPropertySet()) {
            Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
            declaredField.setAccessible(true);
            /**
             * 装配bean的时候，将ref的bean利用beanRef装配进去
             */
            Object value = propertyValue.getValue();
            if (value instanceof BeanReference) {
                BeanReference beanReference = (BeanReference) value;
                value = getBean(beanReference.getName());
            }
            declaredField.set(bean, value);
        }
    }
}
