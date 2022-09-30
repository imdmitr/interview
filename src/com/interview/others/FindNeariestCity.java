package com.interview.others;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

/**
 * https://leetcode.com/discuss/interview-question/808374/
 * 
 * @author imdmi
 *
 */
public class FindNeariestCity {

	public static String[] findNearestCities(int numOfCities, String[] cities, int[] xCoordinates, int[] yCoordinates,
			int numOfQueries, String[] queries) {

		PriorityQueue<Map.Entry<String, Integer>> pq[] = new PriorityQueue[numOfCities];
		HashMap<String, Integer> map1 = new HashMap<>();
		for (int i = 0; i < cities.length; i++) {
			map1.put(cities[i], i);
		}
		for (int i = 0; i < pq.length; i++)
			pq[i] = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue() != 0 ? a.getValue() - b.getValue()
					: a.getKey().compareTo(b.getKey()));
		for (int i = 0; i < xCoordinates.length; i++) {
			for (int j = 0; j < xCoordinates.length; j++) {
				if (i == j)
					continue;
				if (xCoordinates[i] == xCoordinates[j]) {
					int x = Math.abs(xCoordinates[i] - xCoordinates[j]);
					int y = Math.abs(yCoordinates[i] - yCoordinates[j]);
					int d = x + y;
					HashMap<String, Integer> m = new HashMap<>();
					m.put(cities[j], d);
					for (Map.Entry<String, Integer> e : m.entrySet())
						pq[i].offer(e);
				}
			}
		}
		for (int i = 0; i < yCoordinates.length; i++) {
			for (int j = 0; j < yCoordinates.length; j++) {
				if (i == j)
					continue;
				if (yCoordinates[i] == yCoordinates[j]) {
					int x = Math.abs(xCoordinates[i] - xCoordinates[j]);
					int y = Math.abs(yCoordinates[i] - yCoordinates[j]);
					int d = x + y;
					HashMap<String, Integer> m = new HashMap<>();
					m.put(cities[j], d);
					for (Map.Entry<String, Integer> e : m.entrySet())
						pq[i].offer(e);
				}
			}
		}
		String res[] = new String[numOfQueries];
		for (int i = 0; i < queries.length; i++) {
			int idx = map1.get(queries[i]);
			if (!pq[idx].isEmpty()) {
				Map.Entry<String, Integer> e = pq[idx].peek();
				res[i] = e.getKey();
			} else
				res[i] = null;
		}
		return res;
	}

	public static void main(String[] args) {
		int numOfCities = 6;
		String[] cities = { "green", "yellow", "red", "blue", "grey", "pink" };
		int[] xCoordinates = { 10, 20, 15, 30, 10, 15 };
		int[] yCoordinates = { 30, 25, 30, 40, 25, 25 };
		int numOfQueries = 4;
		String[] queries = { "grey", "blue", "red", "pink" };
		String res[] = findNearestCities(numOfCities, cities, xCoordinates, yCoordinates, numOfQueries, queries);
		for (String s : res)
			System.out.print(s + " ");
	}

	//------------------------------------------ second solution
	
	
	private class City {
	    String name;
	    int x;
	    int y;
	    City(String name, int x, int y){
	        this.name = name;
	        this.x = x;
	        this.y = y;
	    }
	}

	private int getDistance(City c1, City c2){
	    return Math.abs(((c2.x-c1.x) * (c2.x-c1.x)) + ((c2.y-c1.y) * (c2.y-c1.y)));
	}

	public List<String> getNearestCity(List<String> cities, List<Integer> xCoordinates, List<Integer> yCoordinates, List<String> queries) {
	    List<String> result = new ArrayList<>();
	    Map<String,City> cityMap = new HashMap<>();
	    Map<Integer,Set<String>> xMap = new HashMap<>();
	    Map<Integer,Set<String>> yMap = new HashMap<>();
	    
	    for(int i = 0; i < cities.size(); i++){
	        cityMap.put(cities.get(i), new City(cities.get(i), xCoordinates.get(i), yCoordinates.get(i)));
	        xMap.computeIfAbsent(xCoordinates.get(i), s -> new TreeSet<>()).add(cities.get(i));
	        yMap.computeIfAbsent(yCoordinates.get(i), s -> new TreeSet<>()).add(cities.get(i));
	    }
	    
	    for(String query : queries){
	        String resCity = "None";
	        int minDistance = Integer.MAX_VALUE;
	        City qCity = cityMap.get(query);
	        
	        for(String city : xMap.get(qCity.x)){
	            if(city == qCity.name) continue;
	            int distance = getDistance(qCity, cityMap.get(city));
	            if(distance < minDistance){
	                minDistance = distance;
	                resCity = city;
	            }
	        }
	        for(String city : yMap.get(qCity.y)){
	            if(city == qCity.name) continue;
	            int distance = getDistance(qCity, cityMap.get(city));
	            if(distance < minDistance){
	                minDistance = distance;
	                resCity = city;
	            }
	        }
	        
	        result.add(resCity);
	    } 
	    
	    return result;
	}
	
}
