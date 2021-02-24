package Graphs;

import java.util.*;

import java.io.*;

public class UndirectedGraphBFS {

	static ArrayList<Integer> adjacentList[];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getNoOfVertices();
		while (true) {
			Scanner scanner = new Scanner(System.in);
			int src = scanner.nextInt();
			int des = scanner.nextInt();
			if (src == -1)
				break;
			addEdges(src, des);
		}
		display();
		while (true) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Veretx for Breadth first search");
			int src = scanner.nextInt();
			if (src == -1)
				break;
			BreathFirstSearch(src);
		}
	}

	private static void getNoOfVertices() {
		System.out.println("Enter no of vertices");
		@SuppressWarnings("resource")
		int noOfVertices = new java.util.Scanner(System.in).nextInt();
		adjacentList = new ArrayList[noOfVertices];
		for (int i = 0; i < noOfVertices; i++)
			adjacentList[i] = new ArrayList<Integer>();
	}

	private static void addEdges(int src, int des) {
		// adding edges with possibility of duplicates 
		// go for direct adding when user is not going to
		// provide any same edge twice 
		//for directed graph just remove the second if-else block
		if (!adjacentList[src - 1].contains(des - 1)) {
			adjacentList[src - 1].add(des - 1);
		} else
			return;
		if (!adjacentList[des - 1].contains(src - 1)) {
			adjacentList[des - 1].add(src - 1);
		} else
			return;
	}

	private static void display() {
		for (int i = 0; i < adjacentList.length; i++) {
			System.out.print("Node " + (i + 1) + " is connected to ");
			for (int j = 0; j < adjacentList[i].size(); j++) {
				System.out.print(adjacentList[i].get(j) + 1 + ", ");
			}
			System.out.println();
		}
	}

	public static void BreathFirstSearch(int srcNode) {
		boolean visited11[] = new boolean[adjacentList.length];
		int src = srcNode;
		// Initializing the queue and adding the source node to it
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(src);
		visited11[src-1]=true;
		while (!q.isEmpty()) {
			// visiting the current node
			int newnode = q.remove();
			// and adding all connected node to this newnode in the queue

			for (int i = 0; i < adjacentList[newnode - 1].size(); i++) {
				int newnewnode = adjacentList[newnode - 1].get(i);
				if (visited11[newnewnode] == false) {
					visited11[newnewnode] = true;
					q.add(newnewnode + 1);
				}
			}
			//visited11[newnode - 1] = true;
			System.out.print(newnode + "->");
		}
		System.out.println();
	}//1->3->6->4->2->5->    1->3->6->4->2->5->

}
