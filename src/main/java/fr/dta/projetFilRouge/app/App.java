package fr.dta.projetFilRouge.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@Configuration
@ComponentScan
public class App {

	public App() {
		System.err.println("Start created");
	}
}
