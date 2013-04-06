package uk.ac.cam.cl.dtg.univdate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.googlecode.htmleasy.HtmleasyProviders;

public class App extends Application {

	public Set<Class<?>> getClasses() {
         Set<Class<?>> myServices = new HashSet<Class<?>>();
        		 
         // Add my own JAX-RS annotated classes
         myServices.addAll(Arrays.asList(new Class<?>[] {Test.class, Template.class}));
         
         // Add Htmleasy Providers
         myServices.addAll(HtmleasyProviders.getClasses());
         
         return myServices;
    }

}
