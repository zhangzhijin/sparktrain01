package java_datastruct;

import java.util.ArrayList;

public class HuffumanTreeNode {
	public String code;// 节点的哈夫曼编码
	public int codeSize;// 节点哈夫曼编码的长度
	public String data;// 节点的数据
	public int count;// 节点的权值
	public HuffumanTreeNode lChild;
	public HuffumanTreeNode rChild;

	public HuffumanTreeNode() {
	}

	public HuffumanTreeNode(String data, int count) {
		this.data = data;
		this.count = count;
	}

	public HuffumanTreeNode(int count, HuffumanTreeNode lChild, HuffumanTreeNode rChild) {
		this.count = count;
		this.lChild = lChild;
		this.rChild = rChild;
	}

	public HuffumanTreeNode(String data, int count, HuffumanTreeNode lChild, HuffumanTreeNode rChild) {
		this.data = data;
		this.count = count;
		this.lChild = lChild;
		this.rChild = rChild;
	}
	
	public HuffumanTreeNode createHuffmanTree(ArrayList<HuffumanTreeNode> NodeList )
	{
		  NodeList=ascSort(NodeList);
		while(NodeList.size()>1)
		{
			HuffumanTreeNode left =NodeList.remove(0);
			HuffumanTreeNode right =NodeList.remove(0);
			
			int parentWeith=left.count+right.count;
			HuffumanTreeNode parent=new HuffumanTreeNode(parentWeith,left,right);
			NodeList.add(parent);
		}
		HuffumanTreeNode root=NodeList.get(0);
		return root;
		
	}
	
	public ArrayList<HuffumanTreeNode>  ascSort(ArrayList<HuffumanTreeNode> NodeList ){
		 
		
		return NodeList;
		
		
	}
	
}
