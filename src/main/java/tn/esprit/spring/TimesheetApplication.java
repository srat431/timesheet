package tn.esprit.spring;

import java.util.EnumSet;

import javax.faces.webapp.FacesServlet;
import javax.servlet.DispatcherType;

import org.ocpsoft.rewrite.servlet.RewriteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import tn.esprit.spring.config.LoginFilter;

@SpringBootApplication
@EnableSwagger2
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "tn.esprit.spring")
@EntityScan(basePackages = "tn.esprit.spring.entities")
public class TimesheetApplication {

	public static void main(String[] args) 
	{SpringApplication.run(TimesheetApplication.class, args);}

	
	
	
	
	
	
	
	
	
	
	
	
	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		FacesServlet servlet = new FacesServlet();
		return new ServletRegistrationBean(servlet, "*.jsf"); }

	@Bean
	public FilterRegistrationBean rewriteFilter() {
		FilterRegistrationBean rwFilter = new FilterRegistrationBean(new RewriteFilter());
		rwFilter.setDispatcherTypes(EnumSet.of(DispatcherType.FORWARD, DispatcherType.REQUEST, DispatcherType.ASYNC, DispatcherType.ERROR));
		rwFilter.addUrlPatterns("/*");
		return rwFilter;
	}


	@Bean
	public FilterRegistrationBean loginFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.addUrlPatterns("/pages/*");
		registration.setFilter(new LoginFilter());
		return registration;
	}

	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/**"))
				.apis(RequestHandlerSelectors.basePackage("tn.esprit.spring"))
				.build();
	}
 
}
