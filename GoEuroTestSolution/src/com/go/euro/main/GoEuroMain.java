package com.go.euro.main;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.go.euro.constants.GoEuroConstants;
import com.go.euro.csv.CSVWriter;
import com.go.euro.domain.CSVObject;
import com.go.euro.util.GoEuroUtil;

/**
 * @author swarnima.gupta
 */
public class GoEuroMain 
{

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
        String cityName = sc.nextLine();
        try
        {
        	final URL url = new URL("http://api.goeuro.com/api/v2/position/suggest/en/"+cityName);
    		final HttpURLConnection connection = GoEuroUtil.getHttpConnection(url);
    		final String jsonData = GoEuroUtil.getJsonDataString(connection);
    		if (null!=jsonData && !GoEuroConstants.EMPTY_STRING.equals(jsonData))
    		{
    			//parse the response json
    			final JSONParser parser = new JSONParser();
    			final JSONArray arr = (JSONArray) parser.parse(jsonData);
    			final List<CSVObject> cSVObjectsList = GoEuroUtil.getCSVObjectsList(arr);
    			//create CSV
    			CSVWriter.createCSV(cSVObjectsList);
    		}
        }
        catch(MalformedURLException e)
        {
        	e.printStackTrace();
        }
        catch(IOException e)
        {
        	e.printStackTrace();
        } catch (ParseException e) 
        {
			e.printStackTrace();
		}
		sc.close();
	}
}
