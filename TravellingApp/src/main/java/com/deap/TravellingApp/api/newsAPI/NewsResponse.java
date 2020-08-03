package com.deap.TravellingApp.api.newsAPI;

import java.util.List;

public class NewsResponse {
	
	private String status;
	private String totalResults;
	private List<NewsArticle> articles;

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTotalResults() {
		return totalResults;
	}
	public void setTotalResults(String totalResults) {
		this.totalResults = totalResults;
	}
	public List<NewsArticle> getArticles() {
		return articles;
	}
	public void setArticles(List<NewsArticle> articles) {
		this.articles = articles;
	}

}

