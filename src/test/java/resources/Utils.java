package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import org.json.JSONObject;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification req;
	public static String id;
	public RequestSpecification requestSpecification() throws IOException
	{

		if(req==null)
		{
			PrintStream log =new PrintStream(new FileOutputStream("logging.txt"));
			req=new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.addHeader("Cookie","PHPSESSID=6533cdb6c2356af58357f1a56da3c588; active_template::133674=pub_site.1594597622; ezepvv=0; ezovuuidtime_133674=1594597623; ezCMPCCS=true")
					.setContentType(ContentType.JSON).build();
			return req;
		}
		return req;

	}

	
	public static String getGlobalValue(String key) throws IOException
	{
		Properties prop =new Properties();
		FileInputStream fis =new FileInputStream("C:\\Framework\\APIFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);


	}

 
	public String getJsonPath(Response response,String key)
	{
		
			
		String resp=response.asString();
		JsonPath   js = new JsonPath(resp);
		
		return js.get(key).toString();
	}
	
	public String getEmployeId(Response response)
	{
		
		System.out.println(response.asString());
		JSONObject jsonData = new JSONObject(response.asString());
		JSONObject jsonData1 =jsonData.getJSONObject("data");
		String iD=String.valueOf(jsonData1.getInt("id") );
		System.out.println(iD);
		id=iD;
		return iD;
	}



}
