package com.anton.dev.servletfilterlistenerone;

import com.anton.dev.servletfilterlistenerone.filter.MyFilter;
import com.anton.dev.servletfilterlistenerone.listener.MyServletContextListener;
import com.anton.dev.servletfilterlistenerone.servlet.MyServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletContextListener;

/**
 * @author acrispin
 * https://www.boraji.com/spring-boot-using-servlet-filter-and-listener-example-1
 */
@SpringBootApplication
public class SpringBootServletFilterListenerOneApplication {

    // Register Servlet
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean bean = new ServletRegistrationBean(
                new MyServlet(), "/myServlet");
        return bean;
    }

    // Register Filter
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean bean = new FilterRegistrationBean(new MyFilter());
        // Mapping filter to a Servlet
        bean.addServletRegistrationBeans(new ServletRegistrationBean[]{
                servletRegistrationBean()
        });
        return bean;
    }

    // Register ServletContextListener
    @Bean
    public ServletListenerRegistrationBean<ServletContextListener> listenerRegistrationBean() {
        ServletListenerRegistrationBean<ServletContextListener> bean =
                new ServletListenerRegistrationBean<>();
        bean.setListener(new MyServletContextListener());
        return bean;

    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootServletFilterListenerOneApplication.class, args);
    }

}
