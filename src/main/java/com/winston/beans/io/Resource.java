package com.winston.beans.io;

import java.io.IOException;
import java.io.InputStream;


/**
 * 定义一个接口，用于定义SpringIOC获取的所有资源
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
}
