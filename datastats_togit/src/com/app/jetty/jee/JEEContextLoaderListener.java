package com.app.jetty.jee;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;

/**
 * JEEContextLoaderListener
 * 
 * This creates the JEEContextLoader to hook the pitchfork ebj3
 * environment into webapps.
 *
 */
public class JEEContextLoaderListener extends ContextLoaderListener
{
    protected ContextLoader createContextLoader()
    {
        return new JEEContextLoader();
    }
}
