package symmetric;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DES
{
	
	static byte[] gen64(byte[] key56) {
		byte[] key64 = new byte[8];
		// --- create 64 bit key from 56 bit key
		// least significant bit can have any value
		key64[0] = (byte) (key56[0] & 0xFE); // << 0
		key64[1] = (byte) ((key56[0] << 7) | ((key56[1] & 0xFF) >>> 1));
		key64[2] = (byte) ((key56[1] << 6) | ((key56[2] & 0xFF) >>> 2));
		key64[3] = (byte) ((key56[2] << 5) | ((key56[3] & 0xFF) >>> 3));
		key64[4] = (byte) ((key56[3] << 4) | ((key56[4] & 0xFF) >>> 4));
		key64[5] = (byte) ((key56[4] << 3) | ((key56[5] & 0xFF) >>> 5));
		key64[6] = (byte) ((key56[5] << 2) | ((key56[6] & 0xFF) >>> 6));
		key64[7] = (byte) (key56[6] << 1);
	
		// --- set parity in time independent of the values within key64
		for (int i = 0; i < key64.length; i++) {
		    // if even # bits, make uneven, take last bit of count so XOR with 1
		    // for uneven # bits, make even, take last bit of count so XOR with 0  
		    key64[i] ^= Integer.bitCount(key64[i] ^ 1) & 1;
		}
		return key64;
	}
	
	public static void main (String[] args) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Program to encrypt and decrypt Text using the Symmetric DES algorithm \n\n");

		// Get a cipher object
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		
		System.out.println("e (encrypt) | d (decrypt)");
		String option = sc.next();
		
		/**
		 * Des key crack given cypher, plaintext and first 5 bytes
		 * of key with pkcs5padding
		 */
		String keys = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
		if(option.indexOf("c") != -1) {
			String cypherText = "1+NgaMaVmco=";
			String plaibnText = "Jan13";
			String partialKey = "AAAAAAAAAAAAAA";
			//for(int i = 16; i <= 255; i++) {
				//for(int j = 16; j <= 255; j++) {
					for(int k = 16; k <= 255; k++) {
						Cipher cipher2 = Cipher.getInstance("DES/ECB/PKCS5Padding");
						String potentialKey = partialKey + Integer.toHexString(k);// + Integer.toHexString(j) + Integer.toHexString(k);
						byte[] encodedKey = hexStringToByteArray(potentialKey);
				        Key key5 = new SecretKeySpec(encodedKey,0,encodedKey.length, "DES"); 
				        cipher2.init(Cipher.ENCRYPT_MODE, key5);
						String amalgam=plaibnText;
						byte[] stringBytes = amalgam.getBytes("UTF8");
						byte[] raw = cipher2.doFinal(stringBytes);
						byte[] encodedBytes = Base64.getEncoder().encode(raw);
						String b64output =new String(encodedBytes);
					    if(b64output.equals(cypherText)) {
					    	System.out.println("Key is:  " + potentialKey);
					    	System.out.println("Cypher is:  " + b64output);
					    	System.out.println("Plain is:  " + amalgam);
					    	
					    	// verify key works
					    	cipher.init(Cipher.DECRYPT_MODE, key5);
							byte[] raw2 = Base64.getDecoder().decode(b64output);
							byte[] stringBytes2 = cipher.doFinal(raw2);
							String result = new String( stringBytes2, "UTF8");
							
							System.out.println("Decrypted is:  " + result);
					    	return;
					    }
					//}
				//}
			}
			System.out.println("Not Found");
			/*// for all keys
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] raw = Base64.getDecoder().decode(cypherText);
			byte[] stringBytes = cipher.doFinal(raw);*/
			
			//String result = new String( stringBytes, "UTF8");
			return;
		}
		
		System.out.println("Enter text");
		String text = sc.next();
		
		// Get or generate key

		SecretKey key;
		try
		{
			ObjectInputStream in = new ObjectInputStream(
					new FileInputStream("DESSecretKey.ser"));

			key= (SecretKey)in.readObject();

			in.close();
		}
		catch( FileNotFoundException fnfe)
		{

			System.out.println("KEy file not found, rolling my own now \n\n");
			KeyGenerator generator = KeyGenerator.getInstance("DES");
			generator.init(new SecureRandom());
			key=generator.generateKey();
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("DESSecretKey.ser"));
			out.writeObject(key);
			out.close();
		}



		byte[] pretty_key = key.getEncoded();

		String hex_output="";

		String eye;

		for(int i=0;i<pretty_key.length;i++)
		{

			eye= byteToHex(pretty_key[i]);

			hex_output+=eye;
			if(i<pretty_key.length-1)
			{
				hex_output+=":";
			}
		}

		System.out.println("__________________________________________\n");

		System.out.println("key is "+hex_output+"\n\n");



		System.out.println("length of the key is "+pretty_key.length+" bytes \n");

		System.out.println("__________________________________________\n");


		// Encrypt/Decrypt the input string

		if(option.indexOf("e") !=-1)
		{
			cipher.init(Cipher.ENCRYPT_MODE, key);
			String amalgam=text;
			byte[] stringBytes = amalgam.getBytes("UTF8");
			byte[] raw = cipher.doFinal(stringBytes);
			byte[] encodedBytes = Base64.getEncoder().encode(raw);
			String b64output =new String(encodedBytes);
		    System.out.println("ciphertext (in Base64) is  " +b64output);



		}
		
		else if(option.indexOf("d") !=-1)
		{
			String keyString = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
			byte[] encodedKey = hexStringToByteArray(keyString);
	        Key key1 = new SecretKeySpec(encodedKey,0,encodedKey.length, "AES"); 
			cipher.init(Cipher.DECRYPT_MODE, key1);
						byte[] raw = Base64.getDecoder().decode(text);
						byte[] stringBytes = cipher.doFinal(raw);
						String result = new String( stringBytes, "UTF8");
			System.out.println("The recovered Plaintext is "+result);


		}
		System.out.println("__________________________________________\n");
	}
	/**
	* Convenience method to convert a byte to a hex string.
	*
	* @param data the byte to convert
	* @return String the converted byte
	*/
	public static String byteToHex(byte data)
	{
	StringBuffer buf = new StringBuffer();
	buf.append(toHexChar((data>>>4)&0x0F));
	buf.append(toHexChar(data&0x0F));
	return buf.toString();
	}
	
	/* s must be an even-length string. */
	public static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}

	/**
	* Convenience method to convert an int to a hex char.
	*
	* @param i the int to convert
	* @return char the converted char
	*/
	public static char toHexChar(int i)
	{
	if ((0 <= i) && (i <= 9 ))
	return (char)('0' + i);
	else
	return (char)('A' + (i-10));
	}

}