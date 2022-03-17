import java.util.*;



public class HashTable {
    public int m; // number of slots
    public int n; // number of keys in table
    public ArrayList<LinkedList<Integer>> Table;

    //create hash table of size at least 2n to preserve load factor under 0.5
    public HashTable(int n){        
        this.m = 0;
        //find prime size larger than 2*n
        int temp = 2*n;
		int notPrime=0;
		while(m == 0) {             
			for(int i=temp-1;i>1; i--) {
				if(temp%i == 0) {
					notPrime++; 
				}
			}
			if(notPrime == 0) {
				m = temp;
			}else {
				temp++;
				notPrime = 0;
			}
		}
        this.Table = new ArrayList<LinkedList<Integer>>(m);
        this.n = 0;
        for(int i=0; i<m; i++){
            Table.add(new LinkedList<>());
        }
    }
    

    //Insert key in table
    public void insertKey(Integer key){
       
        int index = Math.abs(key%m);
        //add key to beginning of linked list
        Table.get(index).addFirst(key);                 
    }

    //Insert array of keys
    public void insertKeyArray (int[] keyArray){
        for (int key: keyArray) {
            insertKey(key);
        }
    }

    

    //Search for key in table
    public boolean search(int key){
        int hash = Math.abs(key%m);                           
        //if list at hash is empty, search unsuccessful
        if(Table.get(hash).isEmpty()) return false;     
        
        //iterate through linked list at h(k) 
        for(Integer k: Table.get(hash)){
            if(k.intValue()==key) return true;      
        }
        //if all values in list at h(k) have been examined, search unsuccessful
        return false;                               
    }

}
