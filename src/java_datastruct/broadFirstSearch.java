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
 * 1. 初始化队列：visited[n] = 0 2. 访问顶点：visited[v] = 1 3. 顶点v加入队列 4. 循环：
 * while(队列是否为空) v = 队列头元素 w = v的第一个邻接点 while(w存在) if(如果w未访问) visited[w] = 1;
 * 顶点w加入队列 w = 顶点v的下一个邻接点
 */
public class broadFirstSearch {
	private int[] node;
	private int[][] edge;

	public broadFirstSearch(int[] node, int[][] edge) {

		this.node = node;
		this.edge = edge;
	}

	// 获取index的第一个邻接节点
	public int getFirstNeighbor(int index) {
		for (int i = 0; i < node.length; i++) {
			if (edge[index][i] > 0) {
				return edge[index][i];
			}
			;
		}
		return -1;
	}

	// 根据firt 获取他的下一个兄弟节点
	public int getNextNeighbor(int first, int nodeIndex) {

		for (int i = first + 1; i < node.length; i++) {
			if (edge[nodeIndex][i] > 0) {
				return edge[nodeIndex][i];
			}
		}

		return 1;

	}

	public void getBroadFirstSearch(int[] visited, int nodeIndex) {

		LinkedList queue = new LinkedList();
		visited[nodeIndex] = 1;
		queue.addLast(nodeIndex);
		while (!queue.isEmpty()) {
			int v = (Integer) queue.pollFirst();
			int w = getFirstNeighbor(v);
			while (w >= 0) {
				if (visited[w] == 0) {
					visited[w] = 1;
					queue.addLast(w);
				}
				w = getNextNeighbor(w, v);
			}

		}

	}

}