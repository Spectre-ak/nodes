package first;import java.util.*;
//directed & undirected and weighted graph
//bfs,dfs,dijkatra,bellman-ford,shortest_path_between 2 nodes
//bellman ford(o(ev)) &&& dijkatra(o(vloge))
//kruskal_mst(O(elogv)) and prims_mst(O(elogv))
public class GRAPH{
	public static void main(String[] args) {Scanner sc=new Scanner(System.in);System.out.println("enter the no of vertices");
		GrapH1112 g=new GrapH1112(sc.nextInt());int i=0;
		//String s1[]= {"1 2 10","1 6 15","2 3 15","2 4 12","4 3 1","4 5 2","6 5 10","3 5 5","3 5 5"};
		while(true) {
			Scanner scc=new Scanner(System.in);
		//System.out.println("Enter the nodes to add directed edge b/w them and the weight");
			String s=scc.nextLine();
			//i++;
			if(s.equals("exit"))break;
			else g.addEdgeUndirected(Integer.parseInt(s.split(" ")[0]),Integer.parseInt(s.split(" ")[1]),Integer.parseInt(s.split(" ")[2]));
		}
		g.display();g.display1();System.out.println();
		g.dfs();System.out.println();g.bfs();System.out.println();g.dijkatra();System.out.println();
		g.dijkatra_using_pq();
		g.bellman_ford_shortest_path();
		System.out.println("Enter the nodes to get the shortest path betweeen them");
		while(true) {
			Scanner scc=new Scanner(System.in);
			String s=scc.nextLine();
			if(s.equals("exit"))break;
			g.get_shortest_path_bw_two_nodes(Integer.parseInt(s.split(" ")[0]),Integer.parseInt(s.split(" ")[1]));
		}
		System.out.println("Enter the vertex to get the shortest path to all other from it");
		while(true) {
			Scanner scc=new Scanner(System.in);
			String s=scc.nextLine();
			if(s.equals("exit"))break;
			g.shortest_path_to_any_vertex(Integer.parseInt(s.split(" ")[0]));
			
		}
		g.kruskal_mst();System.out.println();g.prims_mst();
		
	}
}

class Edge111{
	int src,des,weight;
	public Edge111(int src,int des,int weight) {
		this.src=src;
		this.des=des;
		this.weight=weight;
	}
}
class GrapH1112{
	List<Edge111> li[];int noofvertices;boolean visited[];String ans="";boolean visited1[];boolean visited11[];boolean visited111[];
	public GrapH1112(int noofvertices) {


		this.noofvertices=noofvertices;
		li=new LinkedList[noofvertices];
		for(int i=0;i<li.length;i++)
			li[i]=new LinkedList<>();
		visited=new boolean[noofvertices];
		visited1=new boolean[noofvertices];
		visited11=new boolean[noofvertices];
		visited111=new boolean[noofvertices];
		
	}
	public void getconnectededges(int n) {
		if(li[n-1].size()>0) {
			for(int i=0;i<li[n-1].size();i++) {
				System.out.println("Node "+n+" is connected to "+(li[n-1].get(i).des+1)+" with weight as "+li[n-1].get(i).weight);
			}
			return;
		}
		System.out.println("There is no directed edge form the node "+n);
	}
	public void display_using_edges() {
		//int src=1;
		for(int i=0;i<li.length;i++) {
			for(Edge111 e:li[i]) {
				System.out.println((e.src+1)+" "+(e.des+1)+" "+(e.weight));
			}
		}
	}
	public void addEdge(int src,int des,int weight) {
		Edge111 e=new Edge111(src-1,des-1,weight);
		for(int i=0;i<li[src-1].size();i++) {
			if(li[src-1].get(i).des==des-1) {
				System.out.println("Edge is already present ");
				return;
			}
		}
		li[src-1].add(e);
	}
	public void display() {
		for(int i=0;i<li.length;i++) {
			System.out.print("node "+(i+1)+" is connected to ");
			if(li[i].size()>0) {
				for(int j=0;j<li[i].size();j++) {
					System.out.print((li[i].get(j).des+1)+" with weight "+li[i].get(j).weight+", ");
				}
			}
			System.out.println();
		}
	}
	public void display1() {
		for(int i=0;i<li.length;i++) {
			System.out.print((i+1)+": [");
			if(li[i].size()>0) {
				for(int j=0;j<li[i].size();j++) {
					System.out.print((li[i].get(j).des+1)+" wt: "+li[i].get(j).weight+", ");
				}
			}
			System.out.print("]");
			System.out.println();
		}
	}
	public int getedgeweight(int src,int des) {

		for(int i=0;i<li[src-1].size();i++) {
			if(li[src-1].get(i).des==des-1) return li[src-1].get(i).weight;
		}
		System.out.println("There is no edge ");return 0;
	}
	public boolean isthereedge(int src,int des) {
		for(int i=0;i<li[src-1].size();i++) {
			if(li[src-1].get(i).des==des-1)return true;
		}
		return false;
	}
	public void getpath(int n) {
		for(int i=0;i<li[n-1].size();i++) {
			if(li[li[n-1].get(i).des].size()>0)getpath((li[n-1].get(i).des+1));
			else System.out.print((li[n-1].get(i).des+1)+" ");
		}
		System.out.println();
	}
	public void dfs() {
		int src=1;
		dfs1(src);
	}
	public void dfs1(int v) {
		visited[v-1]=true;
		System.out.print(v+"->");
		for(int i=0;i<li[v-1].size();i++) {
			int newn=li[v-1].get(i).des;
			if(visited[newn]==false) {visited[newn]=true;dfs1(newn+1);}
		}
	}
	public void dfs2(int v,int n1) {
		visited1[v-1]=true;
		//System.out.print(v+"->")
		if(n1==v) {System.out.println(true);return;}
		for(int i=0;i<li[v-1].size();i++) {
			int newn=li[v-1].get(i).des;
			if(visited1[newn]==false) {visited1[newn]=true;dfs2(newn+1,n1);}
		}
	}
	public void getpathpresent(int n,int n1) {
		
		dfs2(n,n1);
		visited1=new boolean[visited1.length];
	}
	public void bfs() {boolean visited11[]=new boolean[li.length];

		int src=1;
		Queue<Integer> q=new LinkedList<Integer>();
		q.add(src);
		while(!q.isEmpty()) {
			int newnode=q.remove();
			for(int i=0;i<li[newnode-1].size();i++) {
				int newnewnode=li[newnode-1].get(i).des;
				if(visited11[newnewnode]==false) {
					visited11[newnewnode]=true;
					q.add(newnewnode+1);
				}
			}
			visited11[newnode-1]=true;
			System.out.print(newnode+"->");
		}
	}
	public void get_path(int src,int des) {
		List<Integer> ll=bfs_get_path(src,des);
		 int wt=0;
		for(int i=0;i<ll.size()-1;i++) {
			wt=wt+getedgeweight(ll.get(i),ll.get(i+1));
		}System.out.println(ll+" "+wt);
		int vb=visited111.length;
		visited111=new boolean[vb];
	}
	public List bfs_get_path(int src,int des) {
		List ans=new LinkedList<>();
		Queue<Integer> q=new LinkedList<Integer>();
		q.add(src);
		while(!q.isEmpty()) {
			int newnode=q.remove();
			visited111[newnode-1]=true;
			//System.out.print(newnode+"->");
			if(newnode==des) {ans.add(newnode);return ans;}
			else ans.add(newnode);
			for(int i=0;i<li[newnode-1].size();i++) {
				int newnewnode=li[newnode-1].get(i).des;
				if(visited111[newnewnode]==false) {
					q.add(newnewnode+1);
				}
			}
		}
		System.out.println("No path	 exists");
		ans.clear();ans.add(0);return ans;
	}
	public void dijkatra() {


		int src=1;
		int dist[]=new int[li.length];
		int parent[]=new int[li.length];
		List<Integer> l=new LinkedList<>();
		for(int i=0;i<li.length;i++) {dist[i]=Integer.MAX_VALUE;parent[i]=0;l.add(i+1);}
		dist[src-1]=0;parent[src-1]=0;
		while(!l.isEmpty()) {
			//get the vertex from l with the smallest distance
			//remove that vertex from l
			List<A> arr=new LinkedList<A>();
			for(int i=0;i<l.size();i++)arr.add(new A(l.get(i),dist[l.get(i)-1]));
			int min_vertex=arr.get(0).vertex;int min_weight=arr.get(0).weight;
			for(int i=0;i<arr.size();i++) {
				if(min_weight>arr.get(i).weight) {
					min_weight=arr.get(i).weight;min_vertex=arr.get(i).vertex;
				}
			}
			for(int o=0;o<l.size();o++) {
				if(l.get(o)==min_vertex) {
					l.remove(o);break;
				}
			}
			for(int i=0;i<li[min_vertex-1].size();i++) {
				int v=li[min_vertex-1].get(i).des+1;
				if(l.contains(v)) {
					int val=dist[min_vertex-1]+getedgeweight(min_vertex,v);
					if(val<dist[v-1]) {
						dist[v-1]=val;parent[v-1]=min_vertex;
					}
				}
			}
		}
		System.out.println("Distance from source node"+Arrays.toString(dist));
		System.out.println("Parent of each node"+Arrays.toString(parent));
		for(int i=0;i<dist.length;i++) {
			System.out.println("1 to "+(i+1)+" with shortest distance of "+dist[i]);
		}
		for(int i=0;i<parent.length;i++)System.out.println((i+1)+" parent is "+parent[i]);
	}
	public void dijkatra_using_pq() {
		int src=1;
		int dist[]=new int[li.length];
		int parent[]=new int[li.length];
		for(int i=0;i<li.length;i++) {
			dist[i]=Integer.MAX_VALUE;parent[i]=0;}
		List<Integer> chk=new LinkedList<Integer>();
		//PriorityQueue<Node2> pq=new PriorityQueue<>(li.length,new Node2());
		PriorityQueue<Node2> pq=new PriorityQueue<>(new Node2());
		pq.add(new Node2(src,0));dist[src-1]=0;
		while(chk.size()!=li.length) {
			int u=pq.remove().node;
			chk.add(u);
			for(int i=0;i<li[u-1].size();i++) {
				int v=li[u-1].get(i).des+1;
				if(!chk.contains(v)) {
					int val=dist[u-1]+getedgeweight(u,v);
					if(val<dist[v-1]) {
						dist[v-1]=val;parent[v-1]=u;
					}
					pq.add(new Node2(v,dist[v-1]));
				}
			}
		}
		System.out.println("Shortest path using pq and compare"+Arrays.toString(dist));
		System.out.println("Parent array "+Arrays.toString(parent));
	}
	public void get_shortest_path_bw_two_nodes(int src,int des){

		int dist[],parent[];dist=new int[li.length];parent=new int[li.length];
		for(int i=0;i<li.length;i++) {
			dist[i]=Integer.MAX_VALUE;parent[i]=0;
		}dist[src-1]=0;
		PriorityQueue<Node2> pq=new PriorityQueue<>(new Node2());
		for(int i=0;i<dist.length;i++) {
			pq.add(new Node2(i+1,dist[i]));
		}
		List<Integer> l=new ArrayList<Integer>();
		while(l.size()!=li.length) {
			int u=pq.remove().node;l.add(u);
			
			for(int i=0;i<li[u-1].size();i++) {
				int v=li[u-1].get(i).des+1;
				if(!l.contains(v)) {
					if(dist[v-1]>dist[u-1]+getedgeweight(u,v)) {
						dist[v-1]=dist[u-1]+getedgeweight(u,v);
						parent[v-1]=u;
					}
					pq.add(new Node2(v,dist[v-1]));
				}
			}
			
		}
		System.out.println("Shortest path between "+src+"&&"+des+"---"+dist[des-1]);
		System.out.println("Parent as "+Arrays.toString(parent));
	}
	public void addEdgeUndirected(int src,int des,int weight) {
Edge111 e=new Edge111(src-1,des-1,weight);
		for(int i=0;i<li[src-1].size();i++) {
			if(li[src-1].get(i).des==des-1) {
				System.out.println("Edge is already present ");
				return;
			}
		}
		li[src-1].add(e);
		Edge111 e1=new Edge111(des-1,src-1,weight);
		for(int i=0;i<li[des-1].size();i++) {
			if(li[des-1].get(i).des==src-1) {
				System.out.println("Edge is already present ");
				return;
			}
		}
		li[des-1].add(e1);
		
	}
	public void shortest_path_to_any_vertex(int src) {
		int dist[]=new int[li.length];
		int parent[]=new int[li.length];
		for(int i=0;i<li.length;i++) {
			dist[i]=Integer.MAX_VALUE;parent[i]=0;}
		List<Integer> chk=new LinkedList<Integer>();
		//PriorityQueue<Node2> pq=new PriorityQueue<>(li.length,new Node2());
		PriorityQueue<Node2> pq=new PriorityQueue<>(new Node2());
		pq.add(new Node2(src,0));dist[src-1]=0;
		while(chk.size()!=li.length) {
			int u=pq.remove().node;
			chk.add(u);
			for(int i=0;i<li[u-1].size();i++) {
				int v=li[u-1].get(i).des+1;
				if(!chk.contains(v)) {
					int val=dist[u-1]+getedgeweight(u,v);
					if(val<dist[v-1]) {
						dist[v-1]=val;parent[v-1]=u;
					}
					pq.add(new Node2(v,dist[v-1]));
				}
			}
		}
		System.out.println("Shortest path using pq and compare"+Arrays.toString(dist));
		System.out.println("Parent array "+Arrays.toString(parent));

	}
	public void bellman_ford_shortest_path() {

		int src=1;int d[],parent[];
		d=new int[li.length];parent=new int[li.length];
		for(int i=0;i<li.length;i++) {
			d[i]=Integer.MAX_VALUE;parent[i]=0;
		}d[src-1]=0;
		for(int i=0;i<li.length;i++) {
			for(int j=0;j<li[i].size();j++) {
				if(d[i]+getedgeweight(i+1,li[i].get(j).des+1)<d[li[i].get(j).des]) {
					d[li[i].get(j).des]=d[i]+getedgeweight(i+1,li[i].get(j).des+1);
					parent[li[i].get(j).des]=i+1;
				}
			}
		}
		for(int i=0;i<li.length;i++) {
			for(int j=0;j<li[i].size();j++) {
				if(d[i]+getedgeweight(i+1,li[i].get(j).des+1)<d[li[i].get(j).des]) {
					System.out.println("Graph contains negative cycle");
				}
			}
		}
		System.out.println("Shortest_path_using bellman ford"+Arrays.toString(d));
		System.out.println("with parents as"+Arrays.toString(parent));
	}
	public void kruskal_mst() {

		int parent[]=new int[li.length];
		List<Edge111> ans[]=new LinkedList[li.length];
		for(int i=0;i<li.length;i++)ans[i]=new LinkedList<>();
		List<Integer> a[]=new ArrayList[li.length];
		for(int i=0;i<a.length;i++) {
		a[i]=new ArrayList<Integer>();a[i].add(i+1);
		}
		boolean chk[]=new boolean[li.length];for(int i=0;i<chk.length;i++)chk[i]=false;
		PriorityQueue<Node3> pq=new PriorityQueue<Node3>(new Node3());
		for(int i=0;i<li.length;i++) {
			for(int j=0;j<li[i].size();j++) {
				pq.add(new Node3(i+1,li[i].get(j).des+1,getedgeweight(i+1,li[i].get(j).des+1)));
			}
		}
		List<String> lp=new ArrayList<String>();int total_weight=0;
		while(!pq.isEmpty()) {
			Node3 n=pq.remove();int u=n.src;int v=n.des;
			String ss=u+"---"+v; String sss=v+"---"+u;lp.add(sss);
			if(!lp.contains(ss)) {
			List<Integer> find_set_u,find_set_v;
			find_set_u=new ArrayList<>();find_set_v=new ArrayList<>();
			for(int i=0;i<a.length;i++) {
				if(chk[i]==false && a[i].contains(u))find_set_u=a[i];
				if(chk[i]==false && a[i].contains(v))find_set_v=a[i];
			}
			if(find_set_u.size()>0 && find_set_v.size()>0 && !find_set_u.equals(find_set_v)) {
				ans[u-1].add(new Edge111(u-1,v-1,getedgeweight(u,v)));total_weight=total_weight+getedgeweight(u,v);
				ans[v-1].add(new Edge111(v-1,u-1,getedgeweight(v,u)));
				parent[v-1]=u;parent[u-1]=v;
				for(int i=0;i<a.length;i++) {
					if(find_set_u==a[i]) {
						for(int j=0;j<find_set_v.size();j++) {
							a[i].add(find_set_v.get(j));
						}
					}
					if(find_set_v==a[i]) {
						chk[i]=true;
					}
				}
			}
		}}
		for(int i=0;i<ans.length;i++) {
			System.out.print((i+1)+" node is connected to ");
			for(int j=0;j<ans[i].size();j++)
				System.out.print((ans[i].get(j).des+1)+" ");
			System.out.println();
		}
		System.out.println("using parent array "+Arrays.toString(parent));
		System.out.print("minimum cost is "+total_weight);
	}
	public void prims_mst() {
		int src=1;
		int key[]=new int[li.length];
		int parent[]=new int[li.length];
		for(int i=0;i<li.length;i++) {
			key[i]=Integer.MAX_VALUE;parent[i]=0;
		}
		key[src-1]=0;
		List<Integer> l=new ArrayList<Integer>();boolean chk[]=new boolean[li.length];
		for(int i=0;i<chk.length;i++)chk[i]=false;
		while(l.size()!=li.length) {
			PriorityQueue<Node2> pq=new PriorityQueue<Node2>(new Node2());
			for(int i=0;i<key.length;i++) {
				if(chk[i]==false)pq.add(new Node2(i+1,key[i]));
			}
			int u=pq.remove().node;l.add(u);chk[u-1]=true;
			for(int j=0;j<li[u-1].size();j++) {
				int v=li[u-1].get(j).des+1;
				if(!l.contains(v) && getedgeweight(u,v)<key[v-1]) {
					key[v-1]=getedgeweight(u,v);
					parent[v-1]=u;
				}
			}
		}int total_cost=0;
		System.out.println("mst using prims -> parents loc->"+Arrays.toString(parent));
		for(int i=0;i<parent.length;i++) {
			int val=parent[i];
			if(val!=0) {
				System.out.println((i+1)+" parent is "+val);
				total_cost=total_cost+getedgeweight(i+1,val);
			}
			if(val==0)System.out.println((i+1)+" is the root");
		}
		System.out.println("with total cost as "+total_cost);
		for(int i=0;i<parent.length;i++) {
			int val=parent[i];
			if(val!=0) {
				System.out.print((i+1)+"---"+val+"---");
				total_cost=total_cost+getedgeweight(i+1,val);
			}
			if(val==0)System.out.print("---"+(i+1)+"---!");
		}
	}
}
class A{
	int vertex;int weight;
	public A(int vertex,int weight) {
		this.vertex=vertex;
		this.weight=weight;
	}
}
class Node2 implements Comparator<Node2>{
int node,weight;
public Node2() {
}
public Node2(int node,int weight) {
	this.node=node;
	this.weight=weight;
}
@Override
public int compare(Node2 n1,Node2 n2) {

	if(n1.weight<n2.weight)return -1;
	if(n1.weight>n2.weight)return 1;
	return 0;
}
}
class Node3 implements Comparator<Node3>{
	int src;int des;int weight;
	public Node3() {}
	public Node3(int src,int des,int weight) {
		this.src=src;this.des=des;this.weight=weight;
	}
	@Override
	public int compare(Node3 n1,Node3 n2) {
		if(n1.weight<n2.weight)return -1;
		if(n1.weight>n2.weight)return 1;
		return 0;
	}
}