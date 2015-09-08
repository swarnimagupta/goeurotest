package com.go.euro.csv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.go.euro.constants.GoEuroConstants;
import com.go.euro.domain.CSVObject;

public class CSVWriter 
{

    /**
     * This method created CSV file from list of CSVObjects
     * @param cSVObjectsList
     */
	public static void createCSV(final List<CSVObject> cSVObjectsList) 
	{
		String outputFile = "goeurotest.csv";
		FileWriter fileWriter = null;
		try 
		{
			fileWriter = new FileWriter(new File(outputFile));
		    //Write the CSV file header
		    fileWriter.append(GoEuroConstants.FILE_HEADER);
		    //Add a new line separator after the header
		    fileWriter.append(GoEuroConstants.NEWLINE);
		    //Write a new student object list to the CSV file
		    for (CSVObject csvObject : cSVObjectsList) 
		    {
		        fileWriter.append(String.valueOf(csvObject.getId()));
		        fileWriter.append(GoEuroConstants.COMMA_DELIMITER);
		        fileWriter.append(csvObject.getName());
		        fileWriter.append(GoEuroConstants.COMMA_DELIMITER);
		        fileWriter.append(csvObject.getType());
		        fileWriter.append(GoEuroConstants.COMMA_DELIMITER);
		        fileWriter.append(String.valueOf(csvObject.getLatitude()));
		        fileWriter.append(GoEuroConstants.COMMA_DELIMITER);
		        fileWriter.append(String.valueOf(csvObject.getLongitude()));
		        fileWriter.append(GoEuroConstants.NEWLINE);
		    }
		    System.out.println("CSV file was created successfully !!!");
		} 
		catch (IOException e) 
		{
		    System.out.println("Error in CsvFileWriter !!!");
		    e.printStackTrace();
		} 
		finally 
		{
		    try 
		    {
		        fileWriter.flush();
		        fileWriter.close();
		    } 
		    catch (IOException e) 
		    {
		        System.out.println("Error while flushing/closing fileWriter !!!");
		        e.printStackTrace();
		    }
		}
	}
}
