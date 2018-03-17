package com.winston.beans.io;

import java.net.URL;

/**
 * 用来根据location加载流
 */
public class ResourceLoader {
    public Resource getResource(String location) {
        URL url = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(url);
    }
}
