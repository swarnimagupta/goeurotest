package com.go.euro.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.go.euro.constants.GoEuroConstants;
import com.go.euro.domain.CSVObject;

public class GoEuroUtil 
{

	/**
	 * This method is used to get HTTP Connection
	 * @param url
	 * @return HttpURLConnection
	 * @throws IOException
	 */
	public static HttpURLConnection getHttpConnection(final URL url) throws IOException
	{
		final URLConnection urlConnection = url.openConnection();
		HttpURLConnection connection = null;
		if (urlConnection instanceof HttpURLConnection)
		{
			connection = (HttpURLConnection) urlConnection;
		}
		else
		{
			// "::::::::::::::::::::::::::::::URL not an http URL::::::::::::::::::::::::::::::::"
		}
		return connection;
	}
	
	/**
	 * This method is used to retrieve the JSON string from the connection response.
	 * @param connection
	 * @return String
	 * @throws IOException
	 */
	public static String getJsonDataString(final HttpURLConnection connection) throws IOException
	{
		final BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String jsonData = GoEuroConstants.EMPTY_STRING;
		String line = GoEuroConstants.EMPTY_STRING;
		while ((line = in.readLine()) != null)
		{
			jsonData += line + GoEuroConstants.NEWLINE;
		}
		in.close();
		return jsonData;
	}
	
	/**
	 * This method is used to create list of csv objects from the json array
	 * @param arr
	 * @return List<CSVObject>
	 */
	public static List<CSVObject> getCSVObjectsList(final JSONArray arr) 
	{
		final List<CSVObject> cSVObjectsList = new ArrayList<CSVObject>();
		System.out.println(arr.size());
		if (null!=arr && arr.size()>0)
		{
			CSVObject csvObject = null;
			for(int i=0;i<arr.size();i++)
			{
				JSONObject object = (JSONObject) arr.get(i);
				csvObject = new CSVObject();
				csvObject.setId((Long) object.get(GoEuroConstants._ID));
				csvObject.setName((String) object.get(GoEuroConstants.NAME));
				csvObject.setType((String) object.get(GoEuroConstants.TYPE));
				JSONObject obj = (JSONObject) object.get(GoEuroConstants.GEO_POSITION);
				csvObject.setLatitude((Double) obj.get(GoEuroConstants.LATITUDE));
				csvObject.setLongitude((Double) obj.get(GoEuroConstants.LONGITUDE));
				cSVObjectsList.add(csvObject);
			}
		}
		return cSVObjectsList;
	}
}
