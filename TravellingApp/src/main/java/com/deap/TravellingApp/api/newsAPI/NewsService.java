package com.deap.TravellingApp.api.newsAPI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class NewsService {
	
	@Value("${newsAPIKey}")
	String newsAPIKey;
	
	
	/* das in die Controller Klasse hinzufugen wenn wir das brauchen!
	 * @Autowired
	 * private NewsService newsService;
	 * 
	 * NewsResponse news = newsService.getNews("de");
	 * System.out.println(news.getArticles().get(0).getTitle());
	 */
	
	
	//category can be health, ..
	public NewsResponse getNews(String country,String category) {
	    String url = "https://newsapi.org/v2/top-headlines?country="+country+"&category="+category+"&apiKey="+newsAPIKey;
	    RestTemplate restTemplate = new RestTemplate();
	    try {
	        return restTemplate.getForObject(url, NewsResponse.class);
	    }
	    catch (HttpClientErrorException ex) {
	    	NewsResponse response = new NewsResponse();
	        response.setStatus("Error loading/reading JSON Response object!");
	        return response;
	    } 	    
	}
}
