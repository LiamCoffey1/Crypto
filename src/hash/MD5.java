package hash;
import java.util.ArrayList;
import java.util.List;

public class MD5 {
	

	private static final int[] SHIFT_AMTS = {
		    7, 12, 17, 22,
		    5,  9, 14, 20,
		    4, 11, 16, 23,
		    6, 10, 15, 21
		  };
	
	public static void main(String args[]) {
		String s = "test";
		System.out.println(s.getBytes());
		List<Integer> bits = new ArrayList<Integer>();
		
		byte[] msg_bytes = s.getBytes();
		for(char c : s.toCharArray()) {
			String binary = Integer.toString((int) c, 2);
			for(char e : binary.toCharArray()) {
				bits.add(Integer.parseInt(new String("" + e)));
			}
		}
		int n = bits.size();
		int numBlocks = ((n + 8) >>> 6) + 1;
	    int totalLen = numBlocks << 6;
		System.out.println(totalLen);
		bits.add(1);
		
		String binary_n = Integer.toBinaryString(n);
		
		while(binary_n.length() < 64) {
			binary_n = "0" + binary_n;
		}
		
		while(bits.size()%512 < 448)
			bits.add(0);
		
		for(char e : binary_n.toCharArray()) {
			bits.add(Integer.parseInt(new String("" + e)));
		}
		
		int i = 0;
		for(Integer b : bits) {
			if(i % 8 == 0)
				System.out.print(" ");
			if(i % 32 == 0)
				System.out.println();
			System.out.print(b);
			i++;
		}
		System.out.println();
		
		String A = "67452301";
		String B = "EFCDAB89";
		String C = "98BADCFE";
		String D = "10325476";
		
		
		System.out.println(bits.size());
	}
}
