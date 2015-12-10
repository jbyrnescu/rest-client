package org.jsoftware.rest.plugins;

import org.apache.http.client.methods.HttpRequestBase;
import org.jsoftware.rest.RestClientPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 */
public class RequestTimePlugin implements RestClientPlugin {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void plugin(PluginContext context, PluginChain chain) throws Exception {
        long start = System.currentTimeMillis();
        try {
            chain.continueChain();
        } finally {
            long time = System.currentTimeMillis() - start;
            HttpRequestBase request = context.getRequest();
            logger.info("Request time for {} {} was {}ms.", request.getMethod(), request.getURI(), time);
        }
    }
}
