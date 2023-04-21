package misc;

import java.util.ArrayList;
import java.util.LinkedList;

//This is a directed graph

public class Graph_1 {
	
    public static class Node{
    	public int _id;
    	public ArrayList<Node> adj;
    	public int marked = 0;
    	
    	public Node(int id) {
    		_id = id;
    		adj = new ArrayList<>();
    	}
    	
    }
    
    public ArrayList<Node> nodes;
	
    public Graph_1() {
    	nodes = new ArrayList<>();
    }
    
    public void add(int id) {
     //the nodes will not carry any data,but this is easy to implement
    	Node novo = new Node(id); 
    	nodes.add(novo);
    }
    
    public void link(int id1,int id2){
    	if(id1 >= nodes.size() || id1 < 0) {
    		return;
    	}
    	if(id2 >= nodes.size() || id2 < 0) {
    		return;
    	}
    	if(id1 == id2) {
    		//I dont want link a node to itself
    		return;
    	}
    	
    	// id1 --> id2
    	
    	nodes.get(id1).adj.add(nodes.get(id2));
  	
    }
    
    public boolean exist_path(int id1,int id2){
    	//Searching by level
    	
    	LinkedList<Node> q = new LinkedList<>(); 
    	nodes.get(id1).marked = 1;//i visited the starting node  
    	q.addFirst(nodes.get(id1));//enqueue   	
    	
    	while(!q.isEmpty()){
    		Node r = q.removeLast();//dequeue
    		
    		//System.out.println("Node tested:"+r._id);
    		
    		if(r._id == id2) {
    			return true;
    		}
    		
    		for(int i = 0;i < r.adj.size();i++) {
    			if(r.adj.get(i).marked == 0) {
    				q.addFirst(r.adj.get(i));//enqueue
    				r.adj.get(i).marked = 1;
    			}
    		}    		
    	}
    	return false;
   	
    }
    
    public void reset() {
    	
    	for(int i = 0;i < nodes.size();i++){
    		nodes.get(i).marked = 0;
    	}
    	
    }
    
    public boolean exist_path2(int id1,int id2){
    	//In depth search
    	
    	if(id1 == id2) {
    		return true;
    	}
    	
    	
    	boolean exist = false;
    	
    	nodes.get(id1).marked = 1;
    	
    	for(int i = 0;i < nodes.get(id1).adj.size();i++){
    		Node r = nodes.get(id1).adj.get(i);
    		if(r.marked == 0) {
    			exist =  exist_path2(r._id,id2);
    		}
    	}
    	
    	return exist;
    }
    

}
