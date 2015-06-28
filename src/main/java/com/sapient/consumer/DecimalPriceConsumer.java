package com.sapient.consumer;

import java.math.BigDecimal;
import java.util.List;

import com.google.inject.Inject;
import com.sapient.services.InputService;
import com.sapient.services.OutputService;
import com.sapient.services.PriceFormatService;


/**
 * This class is the consumer class that consumes the input, price calc and output service
 * to get the prices in decimal format.
 * @author segopal
 *
 */
public class DecimalPriceConsumer {
	
	private InputService inputService;
	private PriceFormatService priceFormat;
	private OutputService outputService;
	
	/**
	 * Constructor initializes all the services using DI
	 * @param inputService
	 * @param priceFormat
	 * @param outputService
	 */
	
	@Inject
	public DecimalPriceConsumer(InputService inputService,
			PriceFormatService priceFormat,OutputService outputService) {
		this.inputService = inputService;
		this.priceFormat = priceFormat;
		this.outputService=outputService;
	}
	
	/**
	 * 
	 * 
	 * @param filePath
	 * @return list of prices in decimal format sorted in ascending order
	 */
	
	public List getFormattedPrice(String filePath){
		
		List<String> inputList=inputService.readInputs(filePath);
		List<BigDecimal> formattedPriceList=null;
		
		if(inputList!=null){
		
			formattedPriceList=priceFormat.convertPrice(inputList);
			
			if(formattedPriceList!=null){
				outputService.sendOutput(formattedPriceList);	
			}
	}
		
		return formattedPriceList;
}
	

}
