package com.sapient.pricetest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.sapient.consumer.DecimalPriceConsumer;
import com.sapient.services.ConsoleOutputService;
import com.sapient.services.DecimalPriceFormatService;
import com.sapient.services.FileInputService;

public class PriceFormatMockitoTest {
	
	
	@Test
	public void test_getPrice_success(){
		
		String filePath="test";
		
		
		FileInputService mockFileInput=Mockito.mock(FileInputService.class);
		DecimalPriceFormatService mockPriceFormat=Mockito.mock(DecimalPriceFormatService.class);
		ConsoleOutputService mockConsoleOutput=Mockito.mock(ConsoleOutputService.class);
		
		DecimalPriceConsumer priceConsumer=new DecimalPriceConsumer(mockFileInput, mockPriceFormat, mockConsoleOutput);
		
		List<String> priceList= new ArrayList<String>();
		List<BigDecimal> formattedPriceList=new ArrayList<BigDecimal>();
			
		Mockito.when(mockFileInput.readInputs(filePath)).thenReturn(priceList);
		Mockito.when(mockPriceFormat.convertPrice(priceList)).thenReturn(formattedPriceList);
		
		List<BigDecimal> finalPriceList= priceConsumer.getFormattedPrice(filePath);
		
		//verification
		Mockito.verify(mockFileInput).readInputs(filePath);
		Mockito.verify(mockPriceFormat).convertPrice(priceList);
				
				
		Assert.assertNotNull(finalPriceList);
		
	}


}
