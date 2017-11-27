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
 * 1. ��ʼ�����У�visited[n] = 0 2. ���ʶ��㣺visited[v] = 1 3. ����v������� 4. ѭ����
 * while(�����Ƿ�Ϊ��) v = ����ͷԪ�� w = v�ĵ�һ���ڽӵ� while(w����) if(���wδ����) visited[w] = 1;
 * ����w������� w = ����v����һ���ڽӵ�
 */
public class broadFirstSearch {
	private int[] node;
	private int[][] edge;

	public broadFirstSearch(int[] node, int[][] edge) {

		this.node = node;
		this.edge = edge;
	}

	// ��ȡindex�ĵ�һ���ڽӽڵ�
	public int getFirstNeighbor(int index) {
		for (int i = 0; i < node.length; i++) {
			if (edge[index][i] > 0) {
				return edge[index][i];
			}
			;
		}
		return -1;
	}

	// ����firt ��ȡ������һ���ֵܽڵ�
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