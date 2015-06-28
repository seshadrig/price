package com.sapient.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.inject.Singleton;

/**
 * This service will read the inputs from a file and store it in
 * an arraylist
 * @author segopal
 *
 */

@Singleton
public class FileInputService implements InputService {
	
	public List readInputs(String filePath){
		
		ArrayList<String> priceList=null;
		
		try{
		
			System.out.println("Reading inputs");
			Scanner scan=new Scanner(new File(filePath));
			priceList=new ArrayList<String>();
			while(scan.hasNext()){
				priceList.add(scan.next());
			}
			scan.close();

		}
		catch(FileNotFoundException fne){
			fne.printStackTrace();
			return null;
		}
		
		return priceList;		
	}

}
