package com.winston.context;

import com.winston.beans.factory.AbstractBeanFactory;


public  abstract class AbstractApplicationContext implements ApplicationContext {
    /***
     * 这里选择抽象类的原因是因为抽象类使用的是模板方法，模板类的子类可以保证合理的预期的行为
     */
    protected AbstractBeanFactory beanFactory;

    public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void refresh() throws Exception{

    }
    @Override
    public Object getBean(String name) throws Exception{
        return beanFactory.getBean(name);
    }
}
