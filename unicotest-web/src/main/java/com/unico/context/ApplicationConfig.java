package com.unico.context;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 * Web Deployer class
 * @author H.Husain
 */
@javax.ws.rs.ApplicationPath("context")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.unico.rest.PushGCDService.class);
        // resources.add(unico.rest.PushGCDService.class);
    }

}
