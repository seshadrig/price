package com.sapient.pricetest;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.sapient.consumer.DecimalPriceConsumer;
import com.sapient.injector.PriceInjector;

public class PriceFormatTest {
	
	private DecimalPriceConsumer target=null;
	String filePathTest="src/test/resources/test.txt";
	String filePath="src/main/resources/bond_prices.txt";
	
	@Before
	public void setup(){
		
		File file=new File(filePath);
		Injector inject=Guice.createInjector(new PriceInjector());
		target=inject.getInstance(DecimalPriceConsumer.class);
	}
	
	/*This test is for verifying the price conversion*/
	@Test
	public void test_formatPrice_success(){
	
		List result=target.getFormattedPrice(filePathTest);
		
		Assert.assertNotNull(result);
		Assert.assertEquals(99.937500, ((BigDecimal)result.get(0)).doubleValue(),0.000001);
		Assert.assertEquals(100.25, ((BigDecimal)result.get(1)).doubleValue(),0.000001);
		Assert.assertEquals(100.265625, ((BigDecimal)result.get(2)).doubleValue(),0.000001);
		Assert.assertEquals(100.5, ((BigDecimal)result.get(3)).doubleValue(),0.000001);
		Assert.assertEquals(101.296875, ((BigDecimal)result.get(4)).doubleValue(),0.000001);
	}
	
	/*This test is for verifying the total value and count*/
	@Test
	public void test_formatPriceTotal_success(){
	
		List<BigDecimal> result=target.getFormattedPrice(filePath);
		
		Assert.assertNotNull(result);
		Assert.assertEquals(50,result.size());
		
		double totalPrice=0;
		
		for(BigDecimal price:result){
			totalPrice += price.doubleValue();
		}
		
		Assert.assertEquals(5000, totalPrice,0);
	}

}
