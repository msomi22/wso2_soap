/**
 * 
 */
package au.edu.marrowa.unilib.service;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import au.edu.marrowa.unilib.bean.Response;

/*
 * 
 */
public class ApacheHttpRestClient {

	public final static void main(String[] args) throws JSONException {

		//String response = "";
		//String isbn = "9780385474542";//Chinua Achebe , Things Fall Apart 
		//String api_key = "AIzaSyBftQydwYJVQWnMErWE7qIJu3avs_oTLWQ";
		//String isbn_url = "https://www.googleapis.com/books/v1/volumes?q=isbn:"+isbn+"&key="+api_key; 

		//response = googleBooksApiCall(response,isbn_url); 

		System.out.println("*********************************************************");
		//processResponse(response, isbn); 
		System.out.println("*********************************************************");

		String javaHome = System.getenv("JAVA_HOME");
		String path = System.getenv("PATH");

		System.out.println("javaHome: " + javaHome);
		System.out.println("path: " + path);




	}

	/**
	 * @param response
	 * @param isbn_url 
	 * @return
	 * @throws IllegalStateException
	 */
	public static String googleBooksApiCall(String response, String isbn_url) throws IllegalStateException {

		HttpClient httpClient = new DefaultHttpClient();
		//String javaHome = System.getenv("JAVA_HOME");
		String trustStore = System.getenv("JAVA_HOME")+"/jre/lib/security/cacerts"; 
		//"/opt/Programs/jdks/jdk1.8.0_131/jre/lib/security/cacerts"

		System.setProperty("javax.net.ssl.trustStore",trustStore); 
		System.setProperty("javax.net.ssl.trustStorePassword", "changeit"); 

		try {

			HttpGet httpGetRequest = new HttpGet(isbn_url);

			// Execute HTTP request
			HttpResponse httpResponse = httpClient.execute(httpGetRequest);

			// Get hold of the response entity
			HttpEntity entity = httpResponse.getEntity();

			// If the response does not enclose an entity, there is no need
			// to bother about connection release
			byte[] buffer = new byte[1024];
			if (entity != null) {
				InputStream inputStream = entity.getContent();
				try {
					int bytesRead = 0;
					BufferedInputStream bis = new BufferedInputStream(inputStream);
					while ((bytesRead = bis.read(buffer)) != -1) {
						String chunk = new String(buffer, 0, bytesRead);
						//System.out.println(chunk);
						response += chunk;
					}
				} catch (IOException ioException) {
					// In case of an IOException the connection will be released
					// back to the connection manager automatically
					ioException.printStackTrace();
				} catch (RuntimeException runtimeException) {
					// In case of an unexpected exception you may want to abort
					// the HTTP request in order to shut down the underlying
					// connection immediately.
					httpGetRequest.abort();
					runtimeException.printStackTrace();
				} finally {
					// Closing the input stream will trigger connection release
					try {
						inputStream.close();
					} catch (Exception ignore) {
					}
				}
			}
		} catch (ClientProtocolException e) {
			// thrown by httpClient.execute(httpGetRequest)
			e.printStackTrace();
		} catch (IOException e) {
			// thrown by entity.getContent();
			e.printStackTrace();
		} finally {
			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			httpClient.getConnectionManager().shutdown();
		}
		return response;
	}

	/**
	 * 
	 * @param response
	 * @param isbn 
	 * @throws JSONException 
	 */
	public static Object processResponse(String response, String isbn) throws JSONException { 

		if(response.length() > 0){

			JSONObject jsnobject = new JSONObject(response);


			if(jsnobject.has("items")) {
				JSONArray items = new JSONArray(jsnobject.get("items").toString());  

				JSONObject item0 = items.getJSONObject(0);

				JSONObject saleInfo = new JSONObject(item0.get("saleInfo").toString());
				String country = saleInfo.get("country").toString();
				String isEbook = saleInfo.get("isEbook").toString();
				String saleability = saleInfo.get("saleability").toString();

				JSONObject volumeInfo = new JSONObject(item0.get("volumeInfo").toString());  

				String averageRating = "0.0";
				String publisher = "";
				String authors = "";
				String publishedDate = "";
				String title = ""; 

				if(volumeInfo.has("averageRating")){
					averageRating = volumeInfo.get("averageRating").toString(); 
				}
				
				if(volumeInfo.has("publisher")){
					publisher = volumeInfo.get("publisher").toString(); 
				}
				
				if(volumeInfo.has("authors")){
					authors = volumeInfo.get("authors").toString(); 
				}
				
				if(volumeInfo.has("publishedDate")){
					publishedDate = volumeInfo.get("publishedDate").toString(); 
				}
				if(volumeInfo.has("title")){ 
					title = volumeInfo.get("title").toString(); 
				}
				
				


				GoogleResponse googleResponse = new GoogleResponse();
				googleResponse.setIsbn(isbn);
				googleResponse.setTitle(title);
				googleResponse.setPublisher(publisher);
				googleResponse.setPublishedDate(publishedDate);
				googleResponse.setAuthors(authors);
				googleResponse.setAverageRating(averageRating);
				googleResponse.setCountry(country);
				googleResponse.setIsEbook(isEbook);
				googleResponse.setSaleability(saleability); 

				return googleResponse;


			}else {
				Response response2 = new Response();
				response2.setDescription("ISBN " + isbn + " not found: "); 
				response2.setMessage("error");
				return response2;
			}



		}else{

			Response response2 = new Response();
			response2.setDescription("Unable to find valid certification path to requested target!"); 
			response2.setMessage("error");
			return response2;
		}



	}
}