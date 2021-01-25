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
        		//legia();
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
    	final String website_url_lfc = "https://www.liverpoolfc.com/match/2020-21/first-team/fixtures-and-results/?language=Java";
    	
    	try {
        	Document doc = Jsoup.connect(website_url_lfc).get();
        	System.out.printf("Title: Liverpool FC Schedule");
        	
        	// Get the list of all fixtures
        	for (Element row : doc.select("ul.fixture-list-main li")) {
            	
        		if(!row.select("h2").text().equals("")) {
        			System.out.println("\n" + row.select("h2").text());
        			continue;
        		}
        		
        		if(row.select("div.date").text().equals("")) {
        			System.out.println("Empty Row");
        			continue;
        		}
        		
            	// Extract fixture month and year
                String fixtureDate = row.select("div.date").text();
                
                // Extract fixture opponent
                String teamName = row.select("div.name").text();
                
                // Home or Away
                String location = row.select("div.name span").text();

                System.out.println(fixtureDate + "(GMT) - " + teamName);
                
            }
        }
        
        catch(IOException e) {
        	e.printStackTrace();
        }
    }
    
    public static void toronto() {
    	final String website_url_tfc = "https://www.torontofc.ca/schedule?month=all&year=2020&club_options=Filters&op=Update&form_build_id=form-4PDWg6ivzEXb6R0uJ0yeuGEn0vkHZhe5lDbOwT5RTbk&form_id=mp7_schedule_hub_search_filters_form";
    	
    	try {
    		Document doc = Jsoup.connect(website_url_tfc).followRedirects(false).get();
        	System.out.println("Title: Toronto FC Schedule\n");
        	
        	// Get the list of all fixtures        	
        	
            for (Element row : doc.select("ul.schedule_list.list-reset li")) {
            	
            	// Home or Away
            	String location = row.select("span.match_home_away").text();
            	
            	// Extract fixture opponent
                String teamName = row.select("div.match_matchup").text();
            	
                // Extract fixture time and date
            	String fixtureDate = row.select("div.match_date").text();
                
            	if (!fixtureDate.isEmpty()) {
            		System.out.println(fixtureDate + " - \n\t" + teamName + " (" + location + ")");
            	}
            	
            }
    	}
    	
    	catch(IOException e) {
    		e.printStackTrace();
    	}
    	
    }
    
/*    
    public static void legia() {
    	try {
    		String website_url = "https://legionisci.com/terminarz_legii";
    		Document doc = Jsoup.connect(website_url).get();
        	System.out.printf("Title: Legia Warszawa Schedule\n");
        	
        	// Get the list of all fixtures
        	Element fixtures_list = doc.getElementsByClass("terminarz").first();
        	Elements listItems = fixtures_list.children();        	
        	
            for (Element el : listItems) {
            	
            	if(el.get) {
            		
            	}
            	
            	else {
            		
            	}
            	// Extract fixture opponent
            	String teamName = el.select("a").first().text();
            	
            	// Extract fixture time and date
                String fixtureDate = el.getElementsByTag("time").text();
                
                
                System.out.println("Test 2");
               	System.out.println(fixtureDate + " - " + teamName);
                
                
            }
    	}
    	catch(IOException e) {
    		e.printStackTrace();
    	}
    }
  */  
    public static void halifax() {
    	
    	final String website_url_hfx = "https://canpl.ca/schedule/2019ALL/HFX";
    	
    	try{
    		Document doc = Jsoup.connect(website_url_hfx).get();
        	System.out.println("Title: Halifax Wanderers Schedule");

        	//won't work because data is loaded dynamically ....
        	System.out.println("Doesn't work because the data loads dynamically");
        	
        	// Get the list of all fixtures
            for (Element row : doc.select("table.stats-data-table.table tr")) {
            	
            	if(row.select("td.table__td.table__td--schedule-date > div.schedule-date_top > span").text().equals("")) {
            		continue;
            	}
            	
            	String fixtureDate = row.select("td.table__td.table__td--schedule-date > div.schedule-date_top > span").text();
            	String teamName;
            	String teamNameH = row.select("td.table__td.table__td--schedule-full-home").text();
            	String location;
            	
            	if(teamNameH.equals("HFX Wanderers")) {
            		//get away team
            		teamName = row.select("td.table__td.table__td--schedule-full-away").text();
            		//set location home
            		location = "Home";
            	}
            	else {
            		//get home team
            		teamName = teamNameH;
            		//set location away
            		location = "Away";
            	}
            	
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
