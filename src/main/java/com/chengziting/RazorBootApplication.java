package com.chengziting;

import com.chengziting.razor.core.SpringContextUtil;
import com.chengziting.razor.core.interceptor.AuthorizationInterceptor;
import com.chengziting.razor.core.interceptor.PermissionInterceptor;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication
@EntityScan(basePackages = {"com.chengziting.razor.model"})
@ComponentScan(basePackages = {"com.chengziting.razor"})
public class RazorBootApplication  implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(RazorBootApplication.class, args);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		AuthorizationInterceptor authorizationInterceptor = SpringContextUtil.getApplicationContext().getBean(AuthorizationInterceptor.class);
		PermissionInterceptor permissionInterceptor = SpringContextUtil.getApplicationContext().getBean(PermissionInterceptor.class);
		registry.addInterceptor(authorizationInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/**/*.html","/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg", "/**/fonts/*");
		registry.addInterceptor(permissionInterceptor);
	}
}
