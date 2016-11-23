package com.dagan.app.initializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import com.dagan.app.config.MvcConfig;
import com.dagan.app.config.RootConfig;



public class AppInitializer implements WebApplicationInitializer
{

	public void onStartup(ServletContext servletContext) throws ServletException {
		
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(RootConfig.class);
        servletContext.addListener(new ContextLoaderListener(rootContext));
        
        this.addDispatcherServlet(servletContext);
        this.addUtf8CharacterEncodingFilter(servletContext);
		
	}
	
	 private void addDispatcherServlet(ServletContext servletContext)
	    {
	        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
	        applicationContext.getEnvironment().addActiveProfile("production");
	        applicationContext.register(MvcConfig.class);
	        
	        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(applicationContext));
	        dispatcher.setLoadOnStartup(1);
	        dispatcher.addMapping("/");
	        dispatcher.setInitParameter("dispatchOptionsRequest", "true"); // CORS 瑜� �쐞�빐�꽌 option request �룄 諛쏆븘�뱾�씤�떎.
	    }
	    
	   
	    private void addUtf8CharacterEncodingFilter(ServletContext servletContext)
	    {
	        FilterRegistration.Dynamic filter = servletContext.addFilter("CHARACTER_ENCODING_FILTER", CharacterEncodingFilter.class);
	        filter.setInitParameter("encoding", "UTF-8");
	        filter.setInitParameter("forceEncoding", "true");
	        filter.addMappingForUrlPatterns(null, false, "/*");
	    }
    
}
