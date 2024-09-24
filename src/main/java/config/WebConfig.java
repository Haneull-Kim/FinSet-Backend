package config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import security.config.SecurityConfig;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {


    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {RootConfig.class, SecurityConfig.class};
    }


    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {ServletConfig.class};
    }


    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }



}
