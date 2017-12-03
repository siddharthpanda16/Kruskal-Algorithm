import java.io.*;
import java.util.*;

public class Kruskal {

	/**
	 * ReadCsv - read the assn9_data csv and add to priority queue
	 * @return
	 */
	public PriorityQueue<Edge> ReadCsv()
	{
		PriorityQueue<Edge> pq=new PriorityQueue<>();		
		String row="";
		Scanner sr= null;		
		
		try
		{
			sr=new Scanner(getClass().getResourceAsStream("assn9_data.csv")).useDelimiter("\n");
						
			while(sr.hasNext())
			{
				row=sr.nextLine();
				String[] list=row.split(",");
				
				if(list!=null)
				{					
					for(int i=0; i<list.length-2;i=i+2)
					{
						pq.add(new Edge (list[0],list[i+1],Integer.parseInt(list[i+2])));
					}
				}
			}
			
		}
		catch(Exception e) 
		{
			System.out.println("Exception occured while reading the csv file");
		}		
		return pq;
	}
	
	/**
	 * kruskal - Implements the Kruskals algorithm
	 * @return
	 */
	public List<Edge> kruskal()
	{
	    PriorityQueue<Edge> pq = ReadCsv();  
	    Edge e;	   
	    ArrayList<String> citiesList=new ArrayList<>();  // Stores the vertices - cities 
	    
	    for (Edge edge : pq) {
	    	if(!citiesList.contains(edge.cityFrom))
	    			citiesList.add(edge.cityFrom);	    
	    }
	    
	    List<Edge> mst=new ArrayList<>();   // stores the minimum spanning tree...
	    
	    DisjSets ds = new DisjSets(citiesList.size());  // Creates the Disjoint Sets of size of the cities count...
	    
	    while (mst.size()!= citiesList.size()-1 )
	    {
	        e = pq.remove();  
	        if (ds.find(citiesList.indexOf(e.cityFrom))!=ds.find(citiesList.indexOf(e.cityTo)))    // if not same set (not yet connected)
	        {	                       
	             mst.add(e);   // Add edge to the minimum spanning tree...
	             ds.union(ds.find(citiesList.indexOf(e.cityFrom)), ds.find(citiesList.indexOf(e.cityTo))); // connect them
	         }
		}
	    
	    return mst;
	    
	}
	
	public class Edge implements Comparable<Edge>{
		
		String cityFrom;
		String cityTo;
		int distance;
		
		public Edge(String passedCityFrom, String passedCityTo, int passedCitydistance) {
			cityFrom=passedCityFrom;
			cityTo=passedCityTo;
			distance=passedCitydistance;
		}
		
		@Override
		public int compareTo(Edge cityAttributes) {
			if(this.distance< cityAttributes.distance)
				return -1;
			else if(this.distance> cityAttributes.distance)
				return 1;
			else
				return 0;
			
		}
	}
	
	public static void main(String[] args)
	{
		try
		{
			Kruskal krkl=new Kruskal();
			
			List<Edge> mst= krkl.kruskal();
			StringBuilder sb=new StringBuilder();
			int totalDistance=0;
			for(Edge spanning : mst)
			{
				sb.append(spanning.cityFrom + " --> "+ spanning.cityTo + " : " +spanning.distance +"\n");		
				totalDistance+=spanning.distance;
			}
			
			sb.append("\n"+"Total distance for the tree = "+ totalDistance);
			
			System.out.println(sb);
		}
		catch(Exception e)
		{
			System.out.println("Error finding the minimum spanning between cities...");
		}
	}
}
