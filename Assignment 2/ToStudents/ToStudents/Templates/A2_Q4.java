package Templates;

import java.math.BigInteger;

public class A2_Q4 {
	
	public static String mod_fibonacci(int N, BigInteger K) {

		if(N<=1) return "X";
		if(N==2) return "Y";
		BigInteger[] seq = new BigInteger[N+1];
		
		seq[1] = new BigInteger("1");
		seq[2] = new BigInteger("2");

		for(int i=3; i<N+1; i++){
			seq[i] = seq[i-2].add(seq[i-1]);
		}

		int n = N;
		BigInteger k = K;
		while(n>3){
			if(seq[n-3].compareTo(k)>=0){	//left subtree has length larger or equal to index of letter in sequence
				n -= 2;		//move into left subtree
				continue;
			}else{
				k = k.subtract(seq[n-3]);	//subtract length of left subtree from count
				n -= 1;		//move into right subtree
				continue;
			}
		}
		if(k.intValue()==2){
			return "Y";
		}else{
			return "X";
		}
	}
	

	public static void main(String[] args) {

		System.out.println(mod_fibonacci(19, new BigInteger("6")));
	}

}
