 import java.util.*;
 import java.io.*;
 public class Main
 {
     @SuppressWarnings("unchecked")
     public static void main(String[] args) {
         Scanner sc=new Scanner(System.in);
         int n = sc.nextInt();
         ArrayList<Integer> adjList[] = new ArrayList[n];
         adjList = intializeList(adjList);
         // HashMap (edge, weight) 
         HashMap<HashMap<Integer, Integer>, Integer> hm = new HashMap<>();
         int no_edges = sc.nextInt();
         while(no_edges-- >0){
             int src = sc.nextInt();
             int des = sc.nextInt();
             int wt = sc.nextInt();
             adjList[src].add(des);
             adjList[des].add(src);
             HashMap<Integer, Integer> edge = new HashMap<>();
             edge.put(src, des);
             hm.put(edge, wt);
             edge = new HashMap<>();
             edge.put(des, src);
             hm.put(edge, wt);
         }
         dijkstra(adjList, hm);
     }
     
     static void dijkstra(ArrayList<Integer> adjList[], HashMap<HashMap<Integer, Integer>, Integer> hm){
         int dist[] = new int[adjList.length];
         int parent[] = new int[adjList.length];
         Arrays.fill(dist, Integer.MAX_VALUE);
         Arrays.fill(parent, 0);
         int src = 0;
         dist[src] = 0;
         PriorityQueue<Node> pq = new PriorityQueue<>(new Node());
         pq.add(new Node(src, 0));
         HashMap<Integer, Boolean> processed = new HashMap<>();
         while(processed.size()!=adjList.length){
              int node = pq.remove().node;
              processed.put(node, true);
              for(int i=0; i<adjList[node].size(); i++){
                  int connectedNode = adjList[node].get(i);
                  if(!processed.getOrDefault(connectedNode, false)){
                      HashMap<Integer, Integer> edge = new HashMap<>();
                      edge.put(node, connectedNode);
                      int weight = hm.get(edge);
                      int distanceSoFar = dist[node] + weight;
                      if(distanceSoFar < dist[connectedNode]){
                          dist[connectedNode] = distanceSoFar;
                          parent[connectedNode] = node;
                      }
                      pq.add(new Node(connectedNode,  dist[connectedNode]));
                  }
              }
         }
         System.out.println(Arrays.toString(dist));
         System.out.println(Arrays.toString(parent));
     }
     
     static ArrayList[] intializeList(ArrayList<Integer> adjList[]){
         for(int i=0;i<adjList.length;i++){
             adjList[i]=new ArrayList<>();
         }
         return adjList;
     }
     
     static class Node implements Comparator<Node>{
         int node, weight;
         public Node(){}
         public Node(int node, int weight){
             this.node=node;
             this.weight=weight;
         }
         @Override
         public int compare(Node o1, Node o2){
             if(o1.weight > o2.weight)
                 return 1;
             else if(o1.weight< o2.weight)
                 return -1;
             else 
                 return 0;
         }
     }
 }
