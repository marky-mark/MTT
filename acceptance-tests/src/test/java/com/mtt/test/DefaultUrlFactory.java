package com.mtt.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DefaultUrlFactory implements UrlFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultUrlFactory.class);

    @Value("${mtt.test.web.baseUri:http://localhost:8181/}")
    private String baseUri;

    @Override
    public String createRequestUrl(String relativePath) {
        LOGGER.debug("the base uri " + baseUri);
        return baseUri + relativePath;
    }
}
