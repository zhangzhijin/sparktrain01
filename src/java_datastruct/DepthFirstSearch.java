package java_datastruct;

import java.util.ArrayList;
import java.util.LinkedList;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @description 邻接矩阵模型类
 * @author beanlam
 * @time 2015.4.17
 */
/*
 * 伪代码
1. 访问数组初始化：visited[n] = 0
2. 访问顶点：visited[v] = 1
3. 取v的第一个邻接点w；
4. 循环递归：
    while(w存在)
        if(w未被访问过)
            从顶点w出发递归执行;
        w = v的下一个邻接点;
 */
public class DepthFirstSearch {
	private int[] node;
	private int[][] edge;

	public DepthFirstSearch(int[] node, int[][] edge) {
		 
		this.node = node;
		this.edge = edge;
	}

	//获取index的第一个邻接节点
	public int getFirstNeighbor(int index )
	{
		for(int i=0;i<node.length;i++)
		{
			if(edge[index][i]>0)
				{
				return edge[index][i];
				};
		}
		return -1;
	}
	//根据firt 获取他的下一个兄弟节点
	public int getNextNeighbor(int first,int nodeIndex){
		
		for(int i=first+1;i<node.length;i++){
			if(edge[nodeIndex][i]>0)
			{
				return edge[nodeIndex][i];
			}
		}
		
		return 1;
		
	}
	public void getDepthFirstSearch(int[] visited,int nodeIndex) {
		 
		if(visited[nodeIndex]==1)
			return;
		 
		visited[nodeIndex]=1;
		 
		int first=getFirstNeighbor(nodeIndex);
		while(first>=0)
		{
			if(visited[first]==0)
			{
				visited[first]=1;
				getDepthFirstSearch(visited,first);
			}
			
			first=getNextNeighbor(first,nodeIndex);
		}
		
		
	}
	
	
	
	

}