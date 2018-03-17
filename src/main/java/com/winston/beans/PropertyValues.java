package com.winston.beans;




import java.util.HashSet;
import java.util.Set;


/**
 * 包装一个对象所有的PropertyValue
 * 封装的目的是为了方便实现一些操作
 */
public class PropertyValues {
    private final Set<PropertyValue> propertyValueSet = new HashSet<PropertyValue>();

    public PropertyValues() {
    }

    public void addPropertyValue(PropertyValue py) {
        propertyValueSet.add(py);
    }

    public Set<PropertyValue> getPropertySet() {
        return propertyValueSet;
    }
}
