package com.coforge.vss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SuppressWarnings("deprecation")
@EnableTransactionManagement
@SpringBootApplication

public class VehicleServiceCentreAngularIntergrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleServiceCentreAngularIntergrationApplication.class, args);
	}
//
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurerAdapter() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**");
//			}
//		};
//	
//	}

}
