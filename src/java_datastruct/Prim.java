package java_datastruct;

import java.util.*;  
public class Prim {  
    static int MAX = Integer.MAX_VALUE;  
  
    public static void main(String[] args) {
    	
        int[][] map = new int[][] {  
                { MAX, 2, 14, 1},  
                { 2, MAX, 5, MAX },  
                { 14, 5, MAX, 14},  
                { 1, MAX, 8,MAX }
               
               };  
        prim(map, map.length);  
        
        Prim a=new Prim();
        a.getPrim(map);
    } 
    
    public void getPrim(int[][] graph){
    int n=graph.length;
    System.out.println(n);
    	
    }
     public static void prim(int[][] graph, int n){  
              
            char[] c = new char[]{'A','B','C','D'};          
            int[] lowcost = new int[n];  //���¼��ϵ���СȨ   
            int[] mid= new int[n];//��ȡǰ�����  
            List<Character> list=new ArrayList<Character>();//�����洢�������˳��  
            int i, j, min, minid , sum = 0;  
            //��ʼ����������  
            for(i=1;i<n;i++)  
            {  
                lowcost[i]=graph[0][i];  
                mid[i]=0;  
            }  
            list.add(c[0]);  
            //һ����Ҫ����n-1����  
            for(i=1;i<n;i++)  
            {  
                 min=MAX;  
                 minid=0;  
                 //ÿ���ҵ����뼯������ĵ�  
                 for(j=1;j<n;j++)  
                 {  
                     if(lowcost[j]!=0&&lowcost[j]<min)  
                     {  
                         min=lowcost[j];  
                         minid=j;  
                     }  
                 }  
                   
                 if(minid==0) return;  
                 list.add(c[minid]);  
                 lowcost[minid]=0;  
                 sum+=min;  
                 System.out.println(c[mid[minid]] + "��" + c[minid] + " Ȩֵ��" + min);  
                 //����õ�󣬸��������㵽���ϵľ���  
                 for(j=1;j<n;j++)  
                 {  
                     if(lowcost[j]!=0&&lowcost[j]>graph[minid][j])  
                     {  
                         lowcost[j]=graph[minid][j];  
                         mid[j]=minid;  
                     }  
                 }  
            }  
            System.out.println("sum:" + sum);  
              
        }  
          
}  