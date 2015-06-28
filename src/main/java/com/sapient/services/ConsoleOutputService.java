package com.sapient.services;

import java.math.BigDecimal;
import java.util.List;

import com.google.inject.Singleton;

@Singleton
public class ConsoleOutputService implements OutputService {

	@Override
	public void sendOutput(List<BigDecimal> priceList) {
		
		for(BigDecimal price:priceList){
			System.out.println("formatted price is " + price);
		}
		
	}

}
