package com.sapient.injector;

import com.google.inject.AbstractModule;
import com.sapient.services.ConsoleOutputService;
import com.sapient.services.DecimalPriceFormatService;
import com.sapient.services.FileInputService;
import com.sapient.services.InputService;
import com.sapient.services.OutputService;
import com.sapient.services.PriceFormatService;



public class PriceInjector extends AbstractModule {

	@Override
	protected void configure() {
		bind(InputService.class).to(FileInputService.class);
		bind(PriceFormatService.class).to(DecimalPriceFormatService.class);
		bind(OutputService.class).to(ConsoleOutputService.class);
	}

	
}
