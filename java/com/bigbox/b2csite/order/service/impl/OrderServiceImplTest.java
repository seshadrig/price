package com.bigbox.b2csite.order.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.bigbox.b2csite.order.dao.OrderDao;
import com.bigbox.b2csite.order.model.domain.OrderSummary;
import com.bigbox.b2csite.order.model.entity.OrderEntity;
import com.bigbox.b2csite.order.model.transformer.OrderEntityToOrderSummaryTransformer;

public class OrderServiceImplTest {
	
	private final static long CUSTOMER_ID=1;
	
	@Test
	public void test_getOrderSummary_success() throws Exception{
		
		//setup
		OrderServiceImpl target=new OrderServiceImpl();
		
		OrderDao mockOrderDao=Mockito.mock(OrderDao.class);
		target.setOrderDao(mockOrderDao);
		
		OrderEntityToOrderSummaryTransformer mockTransformer=Mockito.mock(OrderEntityToOrderSummaryTransformer.class);
		target.setTransformer(mockTransformer);
		
		OrderEntity orderEntity=new OrderEntity();
		List<OrderEntity> orderEntityList=new LinkedList<>();
		orderEntityList.add(orderEntity);
		Mockito.when(mockOrderDao.findOrdersByCustomer(CUSTOMER_ID)).thenReturn(orderEntityList);
		
		OrderSummary orderSummary=new OrderSummary();
		Mockito.when(mockTransformer.transform(orderEntity)).thenReturn(orderSummary);
		
		//execution
		List<OrderSummary> orderSummaryList=target.getOrderSummary(CUSTOMER_ID);
		
		
		//verification
		Mockito.verify(mockOrderDao).findOrdersByCustomer(CUSTOMER_ID);
		Mockito.verify(mockTransformer).transform(orderEntity);
		
		
		Assert.assertNotNull(orderSummaryList);
		Assert.assertEquals(1, orderSummaryList.size());
		
	}

	
}
