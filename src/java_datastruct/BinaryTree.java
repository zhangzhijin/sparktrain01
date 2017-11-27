package java_datastruct;

public class BinaryTree {
	/**
	 * @author yaobo �����������������������
	 * 
	 */
	public TreeNode init() {// ע��������������Ƚ����ӽڵ㣬���������Ͻ�������Ϊ��Ҷ�ӽ���ʹ�õ�����Ľڵ㣬����ʼ���ǰ�˳���ʼ���ģ����������ᱨ��
		TreeNode J = new TreeNode(8, null, null);
		TreeNode H = new TreeNode(4, null, null);
		TreeNode G = new TreeNode(2, null, null);
		TreeNode F = new TreeNode(7, null, J);
		TreeNode E = new TreeNode(5, H, null);
		TreeNode D = new TreeNode(1, null, G);
		TreeNode C = new TreeNode(9, F, null);
		TreeNode B = new TreeNode(3, D, E);
		TreeNode A = new TreeNode(6, B, C);
		return A; // ���ظ��ڵ�
	}

	public void printNode(TreeNode node) {
		System.out.print(node.getData());
	}

	public void theFirstTraversal(TreeNode root) { // �������
		printNode(root);
		if (root.getLeftNode() != null) { // ʹ�õݹ���б�������
			theFirstTraversal(root.getLeftNode());
		}
		if (root.getRightNode() != null) { // �ݹ�����Һ���
			theFirstTraversal(root.getRightNode());
		}
	}

	public void theInOrderTraversal(TreeNode root) { // �������
		if (root.getLeftNode() != null) {
			theInOrderTraversal(root.getLeftNode());
		}
		printNode(root);
		if (root.getRightNode() != null) {
			theInOrderTraversal(root.getRightNode());
		}
	}

	public void thePostOrderTraversal(TreeNode root) { // �������
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
		System.out.println("�������");
		tree.theFirstTraversal(root);
		System.out.println("");
		System.out.println("�������");
		tree.theInOrderTraversal(root);
		System.out.println("");
		System.out.println("�������");
		tree.thePostOrderTraversal(root);
		System.out.println("");
	}
}