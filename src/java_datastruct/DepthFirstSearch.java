package java_datastruct;

import java.util.ArrayList;
import java.util.LinkedList;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @description �ڽӾ���ģ����
 * @author beanlam
 * @time 2015.4.17
 */
/*
 * α����
1. ���������ʼ����visited[n] = 0
2. ���ʶ��㣺visited[v] = 1
3. ȡv�ĵ�һ���ڽӵ�w��
4. ѭ���ݹ飺
    while(w����)
        if(wδ�����ʹ�)
            �Ӷ���w�����ݹ�ִ��;
        w = v����һ���ڽӵ�;
 */
public class DepthFirstSearch {
	private int[] node;
	private int[][] edge;

	public DepthFirstSearch(int[] node, int[][] edge) {
		 
		this.node = node;
		this.edge = edge;
	}

	//��ȡindex�ĵ�һ���ڽӽڵ�
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
	//����firt ��ȡ������һ���ֵܽڵ�
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