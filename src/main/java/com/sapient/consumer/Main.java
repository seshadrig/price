package com.sapient.consumer;

import java.io.File;
import java.util.List;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.sapient.injector.PriceInjector;


/**
 * This class has the main() method and it calls the method to 
 * read the inputs, calculate the price and return the output 
 * on the console.
 * 
 * @author segopal
 *
 */
public class Main {
	
	

	public static void main(String... args) {
		
		String filePath="src/main/resources/bond_prices.txt";
		File file=new File(filePath);
		if(!file.exists()){
			System.out.println("File does not exist");
			return;
		}
			
		
		Injector inject=Guice.createInjector(new PriceInjector());
		DecimalPriceConsumer priceConsumer=inject.getInstance(DecimalPriceConsumer.class);
		List priceList=priceConsumer.getFormattedPrice(filePath);
		
		if(priceList==null){
			System.out.println("No Input prices found");
		}
			
	}
	
}
