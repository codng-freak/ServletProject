package com.project.contextlistener;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.PropertyConfigurator;

/**
 * This class is used to implement a servlet context listener for logger configuration.
 * It implements a ServletContextListener interface and defines the methods. 
 * @author Harsh
 *
 */
@WebListener("application context listener")
public class ContextListener implements ServletContextListener{
	
	/**
	 * Method to initialize the process and notify it. It takes an event as an argument.
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		
		ServletContext context = event.getServletContext();
		String log4jConfiguration = context.getInitParameter("log4j-config-location");
		String fullPath = context.getRealPath("") + File.separator + log4jConfiguration;
		
		PropertyConfigurator.configure(fullPath);
	}

	/**
	 * Method to destroy and notify about ServletContext.
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
