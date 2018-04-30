package randomCode.randomProjects.trees;

public class InvertBinaryTree {

	public static void main(String[] args) {
		BinarySearchTree tree = new BinarySearchTree(5);
		
		tree.insert(3);
		tree.insert(9);
		tree.insert(2);
		tree.insert(4);
		tree.insert(7);
		tree.insert(11);
		tree.insert(0);
		tree.insert(8);
		
		tree.printBFS();
		
		tree.invert();
		
		tree.printBFS();
	}

}
