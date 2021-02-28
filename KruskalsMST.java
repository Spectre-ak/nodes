/*
* Author: Akash Upadhyay
* Email: upadhyayakash2002@gmail.com
*/

//Minimum Spanning Tree Kruskal Algorithm
//Approx Time Complexity: O(nlogn)

//Using HashMap for storing edges weights and PriorityQueue for retrieving edges with minimum weights.
import java.util.*;

public class KruskalsMST {
	// algorithm finds the minimum spanning tree from a connected graph
	// where the mst contains all the vertices from the graph and the overall cost
	// is minimized

	static ArrayList<Node> adjacentList[];

	static HashMap<HashMap<Integer, Integer>, Integer> edgesAndWeightsHashMap = new HashMap<>();
	
	static PriorityQueue<EdgeWithMinimumWeight> pqWithMinimumWeights = new PriorityQueue<EdgeWithMinimumWeight>(new EdgeWithMinimumWeight());

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getNoOfVertices();
		while (true) {
			Scanner scanner = new Scanner(System.in);
			try {
				int src = scanner.nextInt();
				int des = scanner.nextInt();
				int weight = scanner.nextInt();
				if (src == -1)
					break;
				addEdges(src, des, weight);
				pqWithMinimumWeights.add(new EdgeWithMinimumWeight(src,des,weight));
				
			} catch (Exception e) {
				break;
			}

		}
		display();
		
		KruskalMST();

	}

	private static void KruskalMST() {
		//this list will contain the mst edges as strings ex- a-b
		List<String> mstTreeList=new LinkedList<>();
		
		//Hashmap for find sets operation
		HashMap<Integer,Set<Integer>> hashMapForEachVertexSet =new HashMap<>();
		for (int i = 0; i < adjacentList.length; i++) {
			Set<Integer> vertexSet=new HashSet<>();
			vertexSet.add(i+1);
			hashMapForEachVertexSet.put(i+1,vertexSet);
		}
		
		while(!pqWithMinimumWeights.isEmpty()) {
			EdgeWithMinimumWeight edgeWMW=pqWithMinimumWeights.remove();
			int src=edgeWMW.src;
			int des=edgeWMW.des;
			//find_set(src/u) operation -- retrieving connected vertices of this vertex as a set
			Set<Integer> srcSetOfConnectedVertices=hashMapForEachVertexSet.get(src);
			//find_set(des/v) operation
			Set<Integer> desSetOfConnectedVertices=hashMapForEachVertexSet.get(des);

			boolean isSetsDisjointResult=isSetsDisjoint(srcSetOfConnectedVertices,desSetOfConnectedVertices);
			if(isSetsDisjointResult) {
				mstTreeList.add(src+"-"+des);
				srcSetOfConnectedVertices.addAll(desSetOfConnectedVertices);
				
				for(Integer i:desSetOfConnectedVertices) {
					hashMapForEachVertexSet.put(i,srcSetOfConnectedVertices);
				}
			}
		}
		
		System.out.println(mstTreeList);//contains the MST edges
		
	}
	
	static boolean isSetsDisjoint(Set<Integer> srcSet,Set<Integer> desSet) {
		//adding existing elements in the set will return false and when it does
		//the answer is simple the sets are not disjoint
		Set<Integer> newSet=srcSet;
		for(Integer i:desSet) {
			if(newSet.add(i)==false)return false;
		}
		return true;
	}
	
	private static void getNoOfVertices() {
		System.out.println("Enter no of vertices");
		@SuppressWarnings("resource")
		int noOfVertices = new java.util.Scanner(System.in).nextInt();
		adjacentList = new ArrayList[noOfVertices];
		for (int i = 0; i < noOfVertices; i++)
			adjacentList[i] = new ArrayList<Node>();
	}

	private static void addEdges(int src, int des, int weight) {
		// adding edges with possibility of duplicates
		// go for direct adding when user is not going to
		// provide any same edge twice

		if (!adjacentList[src - 1].contains(des - 1)) {
			adjacentList[src - 1].add(new Node(src - 1, des - 1, weight));

			HashMap<Integer, Integer> hm = new HashMap<>();
			hm.put(src - 1, des - 1);
			edgesAndWeightsHashMap.put(hm, weight);

		} else
			return;

		if (!adjacentList[des - 1].contains(src - 1)) {
			adjacentList[des - 1].add(new Node(des - 1, src - 1, weight));

			HashMap<Integer, Integer> hm = new HashMap<>();
			hm.put(des - 1, src - 1);
			edgesAndWeightsHashMap.put(hm, weight);

		} else
			return;

	}

	private static void display() {
		for (int i = 0; i < adjacentList.length; i++) {
			for (int j = 0; j < adjacentList[i].size(); j++) {
				System.out.println("Node " + (i + 1) + " is connected to " + (adjacentList[i].get(j).des + 1)
						+ " with weight " + adjacentList[i].get(j).weight);
			}
		}
	}

	
	static class EdgeWithMinimumWeight implements Comparator<EdgeWithMinimumWeight> {
		int src, des, weight;

		public EdgeWithMinimumWeight() {
		}

		public EdgeWithMinimumWeight(int src, int des, int weight) {
			this.src =src;
			this.des =des;
			this.weight = weight;
		}

		@Override
		public int compare(EdgeWithMinimumWeight n1, EdgeWithMinimumWeight n2) {

			if (n1.weight < n2.weight)
				return -1;
			if (n1.weight > n2.weight)
				return 1;
			return 0;
		}
	}

	static class Node {
		int src, des, weight;

		public Node(int src, int des, int weight) {
			this.src = src;
			this.des = des;
			this.weight = weight;
		}

	}
}
