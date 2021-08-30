package javdir;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class DijkstraUndirected {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter no of vertices");
		int n=scanner.nextInt();
		@SuppressWarnings("unchecked")
		
		ArrayList<Integer> arrayList[]=new ArrayList[n];
		for(int i=0;i<n;i++)arrayList[i]=new ArrayList<>();
		
		System.out.println("enter no of edges");int ed=scanner.nextInt();
		
		HashMap<HashMap<Integer,Integer>,Integer> edgesAndWeights=new HashMap<>();
		
		System.out.println("enter edges and weights");
		
		while(ed-->0) {
			int src=scanner.nextInt();
			int des=scanner.nextInt();
			int weight=scanner.nextInt();
			HashMap<Integer,Integer> edgeHashMap=new HashMap<>();
			edgeHashMap.put(src,des);
			edgesAndWeights.put(edgeHashMap,weight);
			
			HashMap<Integer,Integer> edgeHashMap2=new HashMap<>();
			edgeHashMap2.put(des,src);
			edgesAndWeights.put(edgeHashMap2,weight);
			
			
			arrayList[src].add(des);
			arrayList[des].add(src);
		}
		System.out.println(Arrays.toString(arrayList));
		
		System.out.println(edgesAndWeights);
		dj(arrayList,edgesAndWeights);
		
		
	}

	static void dj(ArrayList<Integer> []ar,HashMap<HashMap<Integer,Integer>,Integer> edgesAndWeights) {
		int src=0;
		int distance[]=new int[ar.length];
		int parent[]=new int[ar.length];
		for (int i = 0; i < parent.length; i++) {
			distance[i]=Integer.MAX_VALUE;
			parent[i]=0;
		}
		
		ArrayList<Integer> chkArrayList=new ArrayList<>();
		
		PriorityQueue<Node> pQueue=new PriorityQueue<>(new Node());
		pQueue.add(new Node(src,0));
		distance[src]=0;
		while(chkArrayList.size()!=ar.length) {
			int shortestWeightPath=pQueue.remove().node;
			chkArrayList.add(shortestWeightPath);
			System.out.println(shortestWeightPath);
			
			for(int i=0;i<ar[shortestWeightPath].size();i++) {
				//Neighboring  neighbouring
				int neighbouringNode=ar[shortestWeightPath].get(i);
				
				if(!chkArrayList.contains(neighbouringNode)) {
					//this is done to get the edge weight b.w the currentNode(shrotestWeightPath) and the neghbourNode
					
					HashMap<Integer,Integer> hm=new HashMap<>();
					hm.put(shortestWeightPath,neighbouringNode);
					
					//this is to calculate the total disatance so far
					int distanceSoFar=distance[shortestWeightPath]+edgesAndWeights.get(hm);
					
					if(distanceSoFar<distance[neighbouringNode]) {
						//Shortest path for this node
						distance[neighbouringNode]=distanceSoFar;
						parent[neighbouringNode]=shortestWeightPath;
						
					}
					pQueue.add(new Node(neighbouringNode,distance[neighbouringNode]));
				}
			}
		}
		
		System.out.println("Distance paths "+Arrays.toString(distance));
		System.out.println("Parent "+Arrays.toString(parent));
	}
	
	static class Node implements Comparator<Node>{

		int node,weight;
		
		public Node(int node,int weight) {
			this.node=node;
			this.weight=weight;
		}
		
		public Node() {
			// TODO Auto-generated constructor stub
		}
		@Override
		public int compare(Node o1, Node o2) {
			if(o1.weight>o2.weight)return 1;
			else if(o1.weight<o2.weight)return -1;
			else return 0;
		}
		
	}
}
