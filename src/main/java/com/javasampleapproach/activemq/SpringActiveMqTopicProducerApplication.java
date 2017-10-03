package com.javasampleapproach.activemq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.javasampleapproach.activemq.jms.JmsPublisher;
import com.javasampleapproach.activemq.models.Company;
import com.javasampleapproach.activemq.models.Product;

@SpringBootApplication
public class SpringActiveMqTopicProducerApplication implements CommandLineRunner {

	@Autowired
	JmsPublisher publisher;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringActiveMqTopicProducerApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		/*
		 * Apple company & products
		 */
		// initial company and products 
		Product iphone7 = new Product("Iphone 7");
		Product iPadPro = new Product("IPadPro");
		
		List<Product> appleProducts = new ArrayList<Product>(Arrays.asList(iphone7, iPadPro));
		
		Company apple = new Company("Apple", appleProducts);
		
		iphone7.setCompany(apple);
		iPadPro.setCompany(apple);
		
		// send message to ActiveMQ
		publisher.send(apple);
        
        /*
         * Samsung company and products
         */
		Product galaxyS8 = new Product("Galaxy S8");
		Product gearS3 = new Product("Gear S3");
		
		List<Product> samsungProducts = new ArrayList<Product>(Arrays.asList(galaxyS8, gearS3));
		
		Company samsung = new Company("Samsung", samsungProducts);
		
		galaxyS8.setCompany(samsung);
		gearS3.setCompany(samsung);
		
        /*
         * send message to ActiveMQ
         */
		publisher.send(samsung);
	}
}
