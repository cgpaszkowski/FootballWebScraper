package com.vogella.maven.quickstart;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class App 
{
    public static void main(String[] args )
    {
        System.out.println("Christians Football Schedule Checker");
        
        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("dd MMMM yyyy hh:mm:ss");
        String stringDate = DateFor.format(date);
        System.out.println(stringDate + "\n");
        
        Scanner keyb = new Scanner(System.in);
    	
        System.out.println("For All: Enter 0, \nFor Liverpool: Enter 1, \nFor Toronto FC: Enter 2, \nFor Legia W.: Enter 3, \nFor Halifax W.: Enter 4");
        int option = keyb.nextInt();
        
        switch(option) {
        	case 1:
        		liverpool();
        		break;
        	case 2:
        		toronto();
        		break;
        	case 3:
        		legia();
        		break;
        	case 4:
        		halifax();
        		break;
        	default:
        		System.out.print("All");
        }
        
        System.out.println("Done");
    }
    
    
    public static void liverpool() {
    	try {
        	String website_url = "https://www.liverpoolfc.com/match/2019-20/first-team/fixtures-and-results/?language=Java";
        	
        	Document doc = Jsoup.connect(website_url).get();
        	System.out.printf("Title: %s\n", doc.title());
        	
        	// Get the list of all fixtures
        	Element fixtures_list = doc.getElementsByClass("fixture-list-main").first();
        	Elements listItems = fixtures_list.children();        	
        	
            for (Element el : listItems) {
            	
            	// Extract fixture month and year
                String fixtureMonth = el.getElementsByClass("fixtures-list-title").text();
            	
                // Extract fixture time and date
                String fixtureDate = el.getElementsByClass("date").text();
                
                // Extract fixture opponent
                String teamName = el.getElementsByClass("name").text();
                
                if (el.getElementsByClass("fixtures-list-title").isEmpty()) {
                	System.out.println(fixtureDate + " - " + teamName);
                }
                
                else {
                	System.out.println("\n" + fixtureMonth + "\n");
                }
                
            }
        }
        
        catch(IOException e) {
        	e.printStackTrace();
        }
    }
    
    public static void toronto() {
    	try {
    		String website_url = "https://www.torontofc.ca/schedule?month=all&year=2020&club_options=Filters&op=Update&form_build_id=form-4PDWg6ivzEXb6R0uJ0yeuGEn0vkHZhe5lDbOwT5RTbk&form_id=mp7_schedule_hub_search_filters_form";
    		Document doc = Jsoup.connect(website_url).followRedirects(false).get();
        	System.out.println("Title: Toronto FC Schedule\n");
        	
        	// Get the list of all fixtures
        	Element fixtures_list = doc.getElementsByClass("schedule_list").first();
        	Elements listItems = fixtures_list.children();        	
        	
            for (Element el : listItems) {
            	
            	// Home or Away
            	String location = el.getElementsByClass("match_home_away").text();
            	
            	// Extract fixture opponent
                String teamName = el.getElementsByClass("club_logo").attr("title");
            	
                // Extract fixture time and date
            	String fixtureDate = el.getElementsByClass("match_date").text();
                
            	if (!fixtureDate.isEmpty()) {
            		System.out.println(fixtureDate + " - " + teamName + " (" + location + ")");
            	}
            	
            }
    	}
    	
    	catch(IOException e) {
    		e.printStackTrace();
    	}
    	
    }
    
    public static void legia() {
    	try {
    		String website_url = "https://legia.com/pilka-nozna/terminarz/nadchodzace-mecze";
    		Document doc = Jsoup.connect(website_url).get();
        	System.out.printf("Title: Legia Warszawa Schedule\n");
        	
        	// Get the list of all fixtures
        	Element fixtures_list = doc.getElementsByClass("CDp").first();
        	Elements listItems = fixtures_list.children();        	
        	
            for (Element el : listItems) {
            	
            	// Extract fixture month and year
                String fixtureMonth = el.getElementsByClass("F9av").text();
            	
                // Extract fixture time and date
                String fixtureDate = el.getElementsByClass("BPjp").text();
                
                // Extract fixture opponent
                String teamName = el.getElementsByClass("BPcb BPjt").text();
                
                if (el.getElementsByClass("CDad").isEmpty()) {
                	System.out.println(fixtureDate + " - " + teamName);
                }
                
                else {
                	System.out.println("\n" + fixtureMonth + "\n");
                }
                
            }
    	}
    	catch(IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public static void halifax() {
    	
    	try{
    		String website_url = "https://canpl.ca/schedule/2020/HFX";
    		Document doc = Jsoup.connect(website_url).get();
        	System.out.printf("Title: Halifax Wanderers Schedule");
        	
        	// Get the list of all fixtures
        	Element fixtures_list = doc.getElementsByClass("stats-data-table").first();
        	Elements listItems = fixtures_list.children();
        	
            for (Element el : listItems) {
            	
            	// Home or Away
            	String location = el.getElementsByClass("match_home_away").text();
            	
            	// Extract fixture opponent
                String teamName = el.getElementsByClass("club_logo").attr("title");
            	
                // Extract fixture time and date
            	String fixtureDate = el.getElementsByClass("match_date").text();
                
            	if (!fixtureDate.isEmpty()) {
            		System.out.println(fixtureDate + " - " + teamName + " (" + location + ")");
            	}
            	
            }
        	
    	}
    	catch(IOException e) {
    		e.printStackTrace();
    	}
    }
    
}
