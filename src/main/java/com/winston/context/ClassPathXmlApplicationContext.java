package com.winston.context;

import com.winston.beans.BeanDefinition;
import com.winston.beans.factory.AbstractBeanFactory;
import com.winston.beans.factory.AutowireCapableBeanFactory;
import com.winston.beans.io.ResourceLoader;
import com.winston.beans.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * 根据排位置文件的地址获取
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext{
    private String configLocation;

    public ClassPathXmlApplicationContext(String configLocation) throws Exception {
        this(configLocation, new AutowireCapableBeanFactory());
    }

    public ClassPathXmlApplicationContext(String configLocation, AbstractBeanFactory beanFactory) throws Exception {
        super(beanFactory);
        this.configLocation = configLocation;
        refresh();
    }

    @Override
    public void refresh() throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions(configLocation);
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }
    }
}
