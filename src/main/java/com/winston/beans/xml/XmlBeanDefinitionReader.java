package com.winston.beans.xml;

import com.winston.BeanReference;
import com.winston.beans.AbstractBeanDefinitionReader;
import com.winston.beans.BeanDefinition;
import com.winston.beans.PropertyValue;
import com.winston.beans.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * 读取xml配置文件
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
    public XmlBeanDefinitionReader(ResourceLoader loader) {
        super(loader);
    }

    @Override
    public void loadBeanDefinitions(String location) throws Exception {
        InputStream inputStream = getResourceLoader().getResource(location).getInputStream();
        doLoadBeanDefinitions(inputStream);
    }

    private void doLoadBeanDefinitions(InputStream inputStream) throws Exception {
        //读取xml文件
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(inputStream);
        //解析xml
        registerBeanDefinition(doc);
        inputStream.close();
    }

    private void registerBeanDefinition(Document doc) {
        Element root = doc.getDocumentElement();

        parseBeanDefinition(root);
    }

    private void parseBeanDefinition(Element root) {
        NodeList list = root.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (node instanceof Element) {

                Element ele = (Element) node;
                processBeanDefinition(ele);
            }
        }
    }

    private void processBeanDefinition(Element ele) {
        String name = ele.getAttribute("name");
        String className = ele.getAttribute("class");
        BeanDefinition beanDefinition = new BeanDefinition();
        processProperty(ele, beanDefinition);
        beanDefinition.setClassName(className);
        getRegistry().put(name, beanDefinition);
    }

    private void processProperty(Element ele, BeanDefinition beanDefinition) {
        NodeList propertyNode = ele.getElementsByTagName("property");
        for (int i = 0; i < propertyNode.getLength(); i++) {
            Node node = propertyNode.item(i);
            if (node instanceof Element) {
                Element propertyEle = (Element) node;
                String name = propertyEle.getAttribute("name");
                String value = propertyEle.getAttribute("value");
                if (value != null && value.length() > 0) {
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
                } else {
                    String ref = propertyEle.getAttribute("ref");
                    if (ref == null || ref.length() == 0) {
                        throw new IllegalArgumentException("Configuration problem: <property> element for property '"
                                + name + "' must specify a ref or value");
                    }
                    BeanReference beanReference = new BeanReference(ref);
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, beanReference));
                }
            }
        }
    }
}
