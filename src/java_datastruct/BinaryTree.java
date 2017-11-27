package java_datastruct;

public class BinaryTree {
	/**
	 * @author yaobo 二叉树的先序中序后序排序
	 * 
	 */
	public TreeNode init() {// 注意必须逆序建立，先建立子节点，再逆序往上建立，因为非叶子结点会使用到下面的节点，而初始化是按顺序初始化的，不逆序建立会报错
		TreeNode J = new TreeNode(8, null, null);
		TreeNode H = new TreeNode(4, null, null);
		TreeNode G = new TreeNode(2, null, null);
		TreeNode F = new TreeNode(7, null, J);
		TreeNode E = new TreeNode(5, H, null);
		TreeNode D = new TreeNode(1, null, G);
		TreeNode C = new TreeNode(9, F, null);
		TreeNode B = new TreeNode(3, D, E);
		TreeNode A = new TreeNode(6, B, C);
		return A; // 返回根节点
	}

	public void printNode(TreeNode node) {
		System.out.print(node.getData());
	}

	public void theFirstTraversal(TreeNode root) { // 先序遍历
		printNode(root);
		if (root.getLeftNode() != null) { // 使用递归进行遍历左孩子
			theFirstTraversal(root.getLeftNode());
		}
		if (root.getRightNode() != null) { // 递归遍历右孩子
			theFirstTraversal(root.getRightNode());
		}
	}

	public void theInOrderTraversal(TreeNode root) { // 中序遍历
		if (root.getLeftNode() != null) {
			theInOrderTraversal(root.getLeftNode());
		}
		printNode(root);
		if (root.getRightNode() != null) {
			theInOrderTraversal(root.getRightNode());
		}
	}

	public void thePostOrderTraversal(TreeNode root) { // 后序遍历
		if (root.getLeftNode() != null) {
			thePostOrderTraversal(root.getLeftNode());
		}
		if (root.getRightNode() != null) {
			thePostOrderTraversal(root.getRightNode());
		}
		printNode(root);
	}

	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree();
		TreeNode root = tree.init();
		System.out.println("先序遍历");
		tree.theFirstTraversal(root);
		System.out.println("");
		System.out.println("中序遍历");
		tree.theInOrderTraversal(root);
		System.out.println("");
		System.out.println("后序遍历");
		tree.thePostOrderTraversal(root);
		System.out.println("");
	}
}