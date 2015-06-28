package com.sapient.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import com.google.inject.Singleton;

@Singleton
public class DecimalPriceFormatService implements PriceFormatService {

	final BigDecimal THIRTYTWO=new BigDecimal("32.0");
	final int DECIMALS=6;
	final BigDecimal SIXTYFOUR=new BigDecimal("64.0");
	
	
	/**
	 * This method converts a list of prices in 1/32 format to a decimal format
	 * and returns a list of prices which are sorted
	 * 
	 *   @param inputList 	list of prices in 1/32 format
	 *   @return List 		list of formatted prices in decimal format
	 */
	 
	@Override
	public List convertPrice(List<String> inputList) {
		
		BigDecimal priceDecimals;
		ArrayList<BigDecimal> priceList=new ArrayList<BigDecimal>();
		
		
		
		for(String price32:inputList){
			
			StringTokenizer st=new StringTokenizer(price32,"-");
			
			//The first part of the string has the whole number
			priceDecimals=new BigDecimal(st.nextToken());
			
			//check if the second part of the string after the - has a + or not
			String secondPart=st.nextToken();
			
			//if it has a + then add the 1/64 value to the decimal price
			if (secondPart.indexOf("+")>0){
				
				String part=secondPart.substring(0,secondPart.indexOf("+"));
				priceDecimals=priceDecimals.add(priceFormat(part));
				priceDecimals=priceDecimals.add(new BigDecimal("1.0").divide(SIXTYFOUR).setScale(6,RoundingMode.HALF_UP));
				
			}
			else{
				
				priceDecimals=priceDecimals.add(priceFormat(secondPart));
				
			}
			
			priceList.add(priceDecimals);	
			
		}
		
		Collections.sort(priceList);
		return priceList;
	}
	
	/**
	 * This method divides the value by 32 and rounds the result to 6 decimal places
	 * 
	 * @param 	secondPart 	contains the value of the String after the '-' in the price
	 * @BigDecimal 			contains the decimal value of price
	 */
	
	public BigDecimal priceFormat(String secondPart){
		
		return new BigDecimal(secondPart).divide(THIRTYTWO).setScale(DECIMALS, RoundingMode.HALF_UP);
		
	}

}
