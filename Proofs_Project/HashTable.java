import java.util.*;



public class HashTable {
    public int m; // number of slots
    public int n; // number of keys in table
    public ArrayList<LinkedList<Integer>> Table;

    public HashTable(int n){        //takes in number of keys so that can make sure n is always proportional to m
        this.m = 0;
        int temp = 2*n;
		int notPrime=0;
		while(m == 0) {             //find prime size larger than 2*n
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
    // public int m; // number of SLOTS
    // int w;
    // int r;
    // public ArrayList<LinkedList<Integer>>  Table;

    // public HashTable(int w){
    //     this.w = w;
    //     this.r = (int) (w-1)/2 +1;
    //     this.m = power2(r);
    //     this.Table = new ArrayList<LinkedList<Integer>>(m);
    //     for(int i=0; i<m; i++){
    //         Table.add(new LinkedList<>());
    //     }

    //  }
    // /** Calculate 2^w*/
    //  public static int power2(int w) {
    //     return (int) Math.pow(2, w);
    //  }
    

    //Insert key in table
    public void insertKey(Integer key){
       
        int index = Math.abs(key%m);
        Table.get(index).addFirst(key);                 //add key to beginning of linked list
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

        if(Table.get(hash).isEmpty()) return false;     //if list at hash is empty or null, search unsuccessful

        for(Integer k: Table.get(hash)){
            if(k.intValue()==key) return true;      
        }

        return false;                               //if all keys in list examined, search unsuccessful
    }

}
