
/* <i>One of the best data structure</i> */

/*
In Worst case:
	insert O(logN)
	delete O(logN)
	search O(logN)
*/

import java.awt.Color;
import java.util.Scanner;

public class RedBlackTree {

	static class Node {
		Node parent, left, right;
		int data;
		Color color;

		public Node(Node parent, int data, Color color) {
			this.parent = parent;
			this.data = data;
			this.color = color;
		}
	}

	public static void displayINORDER(Node node) {

		if (node == null)
			return;
		displayINORDER(node.left);
		System.out.print(node.data + " ");
		displayINORDER(node.right);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Root node is always black
		int rootNodeVal=50;
		Node root = new Node(null, rootNodeVal, Color.BLACK);

		while (true) {
			System.out.println("enter the node to add in the tree");
			Scanner sc = new Scanner(System.in);
			int val = sc.nextInt();
			if (val == -1)
				break;
			Node nodeToBeEntered = new Node(null, val, Color.RED);
			Node addedNode = addWithReturnNode(root, nodeToBeEntered);
			displayINORDER(root);
			System.out.println();

			// now new red node is added now we have to check if it forms red-red issue
			root = checkForRedRedissue(addedNode);
			displayINORDER(root);
			System.out.println();

		}

	}

	static Node getOtherSibling(Node node) {
		// if any one of the sibling is null then return that one
		if (node.parent.left == null)
			return null;
		if (node.parent.right == null)
			return null;

		// if both the siblings are not null means one of them is red or black
		if (node.parent.left.data == node.data) {
			return node.parent.right;
		} else
			return node.parent.left;
	}

	public static void rightRotate(Node root, boolean changeColor) {
		Node parent = root.parent;

		if (parent.parent != null) {
			root.parent = parent.parent;
			if (parent.parent.right == parent) {
				parent.parent.right = root;
			} else {
				parent.parent.left = root;
			}
		} else
			root.parent = null;

		Node right = root.right;
		root.right = parent;
		parent.parent = root;
		parent.left = right;
		if (right != null) {
			right.parent = parent;
		}
		if (changeColor) {
			root.color = Color.BLACK;
			parent.color = Color.RED;
		}
	}

	public static void leftRotate(Node root, boolean changeColor) {
		Node parent = root.parent;

		if (parent.parent != null) {
			root.parent = parent.parent;
			if (parent.parent.right == parent) {
				parent.parent.right = root;
			} else {
				parent.parent.left = root;
			}
		} else
			root.parent = null;

		Node left = root.left;
		root.left = parent;
		parent.parent = root;
		parent.right = left;
		if (left != null) {
			left.parent = parent;
		}
		if (changeColor) {
			root.color = Color.BLACK;
			parent.color = Color.RED;
		}
	}

	public static Node checkForRedRedissue(Node node) {

		// if the parent is not null and color of node.parent and node is red
		if (node.parent != null && node.parent.color == node.color && node.color == Color.RED) {
			// sibling is referring to node.parent.parent children
			Node otherSibling = getOtherSibling(node.parent);
			if (otherSibling == null || otherSibling.color == Color.BLACK) {
				// 1. check if the other sibling is null or sibling is black
				// if it is then rotation is required one of the 4 types

				// implement the rest of the other sibling
				// Believe in yourself, you can do it.
				Node parent = node.parent;
				Node grandParent = node.parent.parent;

				if (isLeftChild(node)) {
					// added red node is left child of its parent
					if (isLeftChild(node.parent)) {
						// parent of added node is left child of its parent
						// left child(node) and left parent(node.parent)
						// rotate right(with color change)
						node = node.parent;

						rightRotate(node, true);

					} else {
						// parent of added node is right child of its parent
						// left child(node) and right parent(node.parent)
						// rotate right and then left(with color change)
						node = node.parent;

						System.out.println("LR " + node.data);

						rightRotate(node.left, false);
						node = node.parent;
						leftRotate(node, true);
					}
				} else {// 45 330 43 2 54 55 333 32 31 63 3344 21 22 23
						// added red node is right child of its parent
					if (isLeftChild(node.parent)) {
						// parent of added node is left child of its parent
						// right child(node) and left parent(node.parent)
						// rotate left and then right(with color change)
						node = node.parent;

						leftRotate(node.right, false);
						node = node.parent;
						rightRotate(node, true);

					} else {
						// parent of added node is right child of its parent
						// right child(node) and right parent(node.parent)
						// rotate left (with color change)
						node = node.parent;
						leftRotate(node, true);

					}
				}
			} else {
				// if the above condition is false which means the sibling is red
				// therefore color change and again function call is required
				node.parent.parent.left.color = Color.BLACK;
				node.parent.parent.right.color = Color.BLACK;
				// if the node.parent.parent is not the main top root of the tree
				if (node.parent.parent.parent != null) {
					node.parent.parent.color = Color.RED;
					return checkForRedRedissue(node.parent.parent);
				} else {
					return node.parent.parent;
				}

			}
		}

		// now we have to return the root
		while (node.parent != null)
			node = node.parent;

		return node;
	}

	static boolean isLeftChild(Node root) {
		if (root.parent.left != null && root.parent.left.data == root.data)
			return true;
		else
			return false;
	}

	static boolean isLeftSiblingNull(Node root) {
		if (root.parent.left == null)
			return true;
		else
			return false;
	}

	static boolean isRightSiblingNull(Node root) {
		if (root.parent.right == null)
			return true;
		else
			return false;
	}

	public static Node addWithReturnNode(Node root, Node nodeToBeEntered) {

		if (nodeToBeEntered.data > root.data) {
			// go to the right subtree
			if (root.right != null) {
				return addWithReturnNode(root.right, nodeToBeEntered);
			} else {
				nodeToBeEntered.parent = root;
				root.right = nodeToBeEntered;
				return root.right;
			}
		} else {
			// go to the left subtree
			if (root.left != null) {
				return addWithReturnNode(root.left, nodeToBeEntered);
			} else {
				nodeToBeEntered.parent = root;
				root.left = nodeToBeEntered;
				return root.left;
			}
		}
	}

}
