import java.util.Random;

/**
 * @author Xunhua Wang. All rights reserved.
 * @date 02/16/2012; revised on 09/27/2018; further refined on 09/20/2019, 09/29/2020, 10/07/2022, 03/13/2023
 * 
 */

public class CooperJacksonHarmonCameronRSA
{
	public int gcd (int inE, int inZ) {
        inE = Math.abs(inE);
        inZ = Math.abs(inZ);
        
        // Perform Euclid's algorithm iteratively
        while (inZ != 0) {
            int temp = inZ;
            inZ = inE % inZ;
            inE = temp;
        }
        
        // The GCD is stored in 'inE' at this point
        return inE;
	}

	public void testGcd () {
		int result1 = gcd (29, 288);
		int result2 = gcd (30, 288);
		int result3 = gcd (149, 288);
		int result4 = gcd (2, 4);

		System.out.println ("GCD (29, 288) = 0x" + Integer.toString(result1, 16));
		System.out.println ("GCD (30, 288) = 0x" + Integer.toString(result2, 16));
		System.out.println ("GCD (149, 288) = 0x" + Integer.toString(result3, 16));
		System.out.println ("GCD (2, 4) = 0x" + Integer.toString(result4, 16));
	}

	//
	// We assume that inE < inZ
	// This implementation follows our slides
	// Return:
	//	-1: no inverse
	//	inverse of inE mod inZ
	//
	public int xgcd (int inE, int inZ) {
        int x0 = 1;
		int x1 = 0;
		int y0 = 0;
		int y1 = 1;

		int tempZ = inZ;
    
    	while (inZ != 0) {
			int quotient = inE / inZ;
			int remainder = inE % inZ;
			
			int tempX = x0 - quotient * x1;
			int tempY = y0 - quotient * y1;
			
			x0 = x1;
			y0 = y1;
			x1 = tempX;
			y1 = tempY;
			
			inE = inZ;
			inZ = remainder;
		}

		if (inE != 1){
			// no multiplicative inverse exists
			return -1;
		}

		if (x0 < 0) {
			// result is non-negative
			x0 += tempZ;
		}
    
    	return x0;
	}

	public void testXgcd () {
		int result1 = xgcd (29, 288);
		int result2 = xgcd (30, 288);
		int result3 = xgcd (149, 288);
		int result4 = xgcd (2, 4);

		System.out.println ("29^-1 mod 288 = 0x" + Integer.toString(result1, 16));
		System.out.println ("30^-1 mod 288 = 0x" + Integer.toString(result2, 16));
		System.out.println ("149^-1 mod 288 = 0x" + Integer.toString(result3, 16));
		System.out.println ("2^-1 mod 4 = 0x" + Integer.toString(result4, 16));
	}

	public int[] keygen (int inP, int inQ, int inE) {
		// TO BE FINISHED
        return null;
	}

	//
	// The following method will return an integer array, with [e, N, d] in this order
	//
	public void testKeygen () {
		int[] keypair = keygen (17, 19, 29);

		System.out.println ("e = 0x" + Integer.toString(keypair[0], 16));
		System.out.println ("N = 0x" + Integer.toString(keypair[1], 16));
		System.out.println ("d = 0x" + Integer.toString(keypair[2], 16));
	}

	//
	// Calculate c = a^b mod n, with the square-and-multiply algorithm
	//
	// The following method implements the square-and-multiply algorithm, with Java primitive types
	//
	// Note that even with primitive types, a^b may well exceed the range of Java int
	// For example, 5^20 is too big to be held by a Java primitive integer
	//
	public int modExp (int a, int b, int n) {
		// TO BE FINISHED
        return 0;
	}

	public int encrypt (int message, int inE, int inN) {
		// TO BE FINISHED
        return 0;
	}

	public int decrypt (int ciphertext, int inD, int inN) {
		// TO BE FINISHED
        return 0;
	}

	public void testRSA () {
		int[] keypair = keygen (17, 19, 29);

		int m1 = 130;
		int c1 = encrypt (m1, keypair[0], keypair[1]);
		System.out.println ("The encryption of (m1=0x" + Integer.toString(m1, 16) + ") is 0x" + Integer.toString(c1, 16));
		int cleartext1 = decrypt (c1, keypair[2], keypair[1]);
		System.out.println ("The decryption of (c=0x" + Integer.toString(c1, 16) + ") is 0x" + Integer.toString(cleartext1, 16));

		int m2 = 255;
		int c2 = encrypt (m2, keypair[0], keypair[1]);
		System.out.println ("The encryption of (m2=0x" + Integer.toString(m2, 16) + ") is 0x" + Integer.toString(c2, 16));
		int cleartext2 = decrypt (c2, keypair[2], keypair[1]);
		System.out.println ("The decryption of (c2=0x" + Integer.toString(c2, 16) + ") is 0x" + Integer.toString(cleartext2, 16));
	}

	public static void main (String[] args) {
		CooperJacksonHarmonCameronRSA atrsa = new CooperJacksonHarmonCameronRSA ();

		System.out.println ("********** Small RSA Project output begins ********** ");
		atrsa.testGcd ();
		atrsa.testXgcd ();
		atrsa.testKeygen ();
		atrsa.testRSA ();
	}
}