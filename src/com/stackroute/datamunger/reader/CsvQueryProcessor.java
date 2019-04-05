package com.stackroute.datamunger.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.stackroute.datamunger.query.DataTypeDefinitions;
import com.stackroute.datamunger.query.Header;

public class CsvQueryProcessor extends QueryProcessingEngine {
	private String fileName;

	// Parameterized constructor to initialize filename
	public CsvQueryProcessor(String fileName) throws FileNotFoundException {

		BufferedReader br = new BufferedReader(new FileReader(fileName));
		this.fileName=fileName;

//		BufferedReader object=new BufferedReader(new FileReader());

	}

	/*
	 * Implementation of getHeader() method. We will have to extract the headers
	 * from the first line of the file.
	 * Note: Return type of the method will be Header
	 */
	
	@Override
	public Header getHeader() throws IOException {
		BufferedReader object=new BufferedReader(new FileReader(this.fileName));
		String string="";
		String[] header=null;
		while((string=object.readLine())!=null){
			if(string.startsWith("id")){
				header=string.split(",");
			}
		}
		System.out.println(header[1]);
		Header object1=new Header(header);


		// read the first line

		// populate the header object with the String array containing the header names
		return object1;
	}

	/**
	 * getDataRow() method will be used in the upcoming assignments
	 */
	
	@Override
	public void getDataRow() {

	}

	/*
	 * Implementation of getColumnType() method. To find out the data types, we will
	 * read the first line from the file and extract the field values from it. If a
	 * specific field value can be converted to Integer, the data type of that field
	 * will contain "java.lang.Integer", otherwise if it can be converted to Double,
	 * then the data type of that field will contain "java.lang.Double", otherwise,
	 * the field is to be treated as String. 
	 * Note: Return Type of the method will be DataTypeDefinitions
	 */
	
	@Override
	public DataTypeDefinitions getColumnType() throws IOException {
		BufferedReader  object=new BufferedReader(new FileReader(this.fileName));
		String[] result=null;
		String string;
		while((string=object.readLine())!=null){
			if(string.startsWith("1")){
				result=string.split(",");
				System.out.println(result[1]);
				break;
			}
		}
		String[] datatype=new String[result.length+1];
		int i=0;
		for(String s:result){
			if(isInteger(s)){
				datatype[i]="java.lang.Integer";
			}
			else if(isDouble(s)){
				datatype[i]="java.lang.Double";
			}
			else{
				datatype[i]="java.lang.String";
			}
			i++;
			datatype[result.length]="java.lang.String";
		}

		DataTypeDefinitions object1=new DataTypeDefinitions(datatype);
		return object1;
	}
	public  boolean isInteger(String s){
		try{
			Integer.parseInt(s);
		}
		catch(NumberFormatException e){
			return false;
		}
		catch(NullPointerException e){
			return false;
		}
		return true;
	}
	public boolean isDouble(String s){
		try{
			Double.parseDouble(s);
		}
		catch(NumberFormatException e){
			return false;
		}
		catch(NullPointerException e){
			return false;
		}
		return true;
	}
	}


