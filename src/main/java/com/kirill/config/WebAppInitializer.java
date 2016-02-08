package com.kirill.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by Kirill on 10.01.2016.
 */

/*
public class WebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext container) throws ServletException {
        // Create the 'root' Spring application context
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(AppConfig.class);

        // Manage the lifecycle of the root application context
        container.addListener(new ContextLoaderListener(rootContext));

        // Create the dispatcher servlet's Spring application context
        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(SpringMVCConfig.class);

        // Register and map the dispatcher servlet
        ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
}
*/

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        WebApplicationContext context = getContext();
//        servletContext.addListener(new ContextLoaderListener(context));
//        ServletRegistration.Dynamic dispatcher =
//                servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
//        dispatcher.setLoadOnStartup(1);
//        dispatcher.addMapping("/");
//    }
//
//    private AnnotationConfigWebApplicationContext getContext() {
//        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//        context.register(AppConfig.class);
//        return context;
//    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {SpringConfig.class}; // We don`t need any special servlet config yet.WebAppInitializer.class
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {SpringMVCConfig.class}; // null
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}