package com.firmname.travel.util;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.google.common.base.CaseFormat;
import com.google.common.base.Strings;

public final class Utils {
	private Utils(){
		throw new Error("I am not supposed to be instantiated!");
	}
	
	public static String columnName2FieldName(String columnName) {
		return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, columnName);
	}

	public static boolean isValidEmail(String email){
		//TODO: to implement
		return true;
	}
	
	public static boolean isValidPhone(String phone){
		//TODO: to implement
		return true;
	}
	
	public static boolean isValidPublicKey(String encryptedModulus, String encryptedPublicExp){
		if(Strings.isNullOrEmpty(encryptedModulus) || Strings.isNullOrEmpty(encryptedPublicExp)){
			return false;
		}
		return encryptedModulus.equals(RsaUtils.getEncryptedRsaModulus()) &&
				encryptedPublicExp.equals(RsaUtils.getEncryptedRsaPublicExp());
	}
	public static String encryptDate(String data){
		return RsaUtils.encryptByPublicKey(data);
	}
	public static String decryptData(String data){
		return RsaUtils.decryptByPrivateKey(data);
	}
	
	public static String getEncryptedRsaPublicExp(){
		return RsaUtils.getEncryptedRsaPublicExp();
	}
	
	public static String getEncryptedRsaModulus(){
		return RsaUtils.getEncryptedRsaModulus();
	}
	
    private static class RsaUtils{
    	private final static String modulusStr = "143379719557920575232325691955136154123550423642310976091288137105049577284658126265643243873100748244779260087528478743869085904287021970815131174451569851159372767390949791024787584152027606835354401759101820821864654606464147754320464822019739352466423868335025406519227600671961036854814104353010313107283";
    	private final static String publicExpStr = "65537";
    	private final static String privateExpStr = "113621705833965623612475926754199859078178605243807628412973578292426412504062129600167567489349334880946806729722012657931259844654874132997907098969166714534994566032941283593324977404690090196467737484250082962667931402017255570065168563287984093142443340716213940466997332788749428194695099095805747546313";
    	private final static String encryptedRsaModulus = MD5Util.MD5(modulusStr); //2633C22FB07572CB42A435351F753B06
    	private final static String encryptedRsaPublicExp = MD5Util.MD5(publicExpStr); //1332BD1DC7CF036CE3E30A2E3BD94CC3
    	private final static RSAPublicKey publicKey;
    	private final static RSAPrivateKey privateKey;

//    	private static KeyPair rsaKeyPair = null;
    	
    	static{
    		RSAPrivateKey tempPrivateKey = null;
    		RSAPublicKey tempPublicKey = null;
    		try{
	    		BigInteger iModulus = new BigInteger(modulusStr);    
				BigInteger iPrivateExp = new BigInteger(privateExpStr);    
				BigInteger iPublicExp = new BigInteger(publicExpStr);    
				KeyFactory keyFactory = KeyFactory.getInstance("RSA");
				RSAPrivateKeySpec privateKeySpec = new RSAPrivateKeySpec(iModulus, iPrivateExp);    
				tempPrivateKey = (RSAPrivateKey) keyFactory.generatePrivate(privateKeySpec);
				
				RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(iModulus, iPublicExp);    
				tempPublicKey = (RSAPublicKey) keyFactory.generatePublic(publicKeySpec);
				
			} catch (NoSuchAlgorithmException e) {
				Logger.fatal("Failed to init RSA Key Factory!", e);
			} catch (InvalidKeySpecException e) {
				Logger.fatal("Failed to generate RSA private key!", e);
			}
			privateKey = tempPrivateKey;
			publicKey = tempPublicKey;
    		
    	}
    	protected static String getEncryptedRsaPublicExp(){
    		return encryptedRsaPublicExp;
    	}
    	
    	protected static String getEncryptedRsaModulus(){
    		return encryptedRsaModulus;
    	}

    	protected static String encryptByPublicKey(String data){
    		try {
    			Cipher cipher = Cipher.getInstance("RSA");
    			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
    			//the max length of data can be encrypted each time <= publicKey module length - 11 
    			int maxLen = (publicKey.getModulus().bitLength() / 8) - 12;
    			StringBuffer encrypted = new StringBuffer();
    			int totalLen = data.getBytes().length;
    			for(int offset = 0; offset < totalLen; offset += maxLen){
    				int len = Math.min(maxLen, totalLen - offset);
    				encrypted.append(bcd2Str(cipher.doFinal(data.getBytes(), offset, len)));
    			}
    			return encrypted.toString();
    		} catch (Exception e) {
    			Logger.fatal("Failed to encrypt data by public key", e);
    			return "";
    		} 
    	}
    	
    	public static String decryptByPrivateKey(String data){
    		try {
    			Cipher cipher = Cipher.getInstance("RSA");
    			cipher.init(Cipher.DECRYPT_MODE, privateKey);
    			int maxLen = privateKey.getModulus().bitLength() / 8;
    			StringBuffer decrypted = new StringBuffer();
    			byte[] bcdBytes = ascii2Bcd(data.getBytes());
    			for(int offset = 0; offset < bcdBytes.length; offset += maxLen){
    				int len = Math.min(maxLen, bcdBytes.length - offset);
    				decrypted.append(new String(cipher.doFinal(bcdBytes, offset, len)));
    			}
    			return decrypted.toString();
    		} catch (InvalidKeyException e) {
    			Logger.fatal("Wrong private RSA key", e);
    		} catch (NoSuchAlgorithmException e) {
    			Logger.fatal("RSA decrypt is not supported!", e);
    		} catch (NoSuchPaddingException e) {
    			Logger.fatal("Failed to init RSA Cipher, no such padding!", e);
    		} catch (IllegalBlockSizeException e) {
    			Logger.error("Illegal block size for Cipher encrypt!", e);;
    		} catch (BadPaddingException e) {
    			Logger.error("Bad padding for cipher encrypt", e);
    		}
    		return "";
    	}
    	
//    	private static KeyPair getRsaKeyPair(){
//    		if(rsaKeyPair == null){
//    			try{
//    				KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
//    				gen.initialize(1024);
//    				rsaKeyPair = gen.generateKeyPair();
//    			} catch (NoSuchAlgorithmException e) {
//    				Logger.fatal("Failed to generate RSA public and private keys", e);
//    			}
//    		}
//    		return rsaKeyPair;
//    	}
    	
        public static byte[] ascii2Bcd(byte[] ascii) {    
            byte[] bcd = new byte[ascii.length / 2];    
            int j = 0;    
            for (int i = 0; i < (ascii.length + 1) / 2; i++) {    
                bcd[i] = _asc2bcd(ascii[j++]);    
                bcd[i] = (byte) (((j >= ascii.length) ? 0x00 : _asc2bcd(ascii[j++])) + (bcd[i] << 4));    
            }    
            return bcd;    
        }    
        public static byte _asc2bcd(byte asc) {    
            byte bcd;    
        
            if ((asc >= '0') && (asc <= '9'))    
                bcd = (byte) (asc - '0');    
            else if ((asc >= 'A') && (asc <= 'F'))    
                bcd = (byte) (asc - 'A' + 10);    
            else if ((asc >= 'a') && (asc <= 'f'))    
                bcd = (byte) (asc - 'a' + 10);    
            else    
                bcd = (byte) (asc - 48);    
            return bcd;    
        }    
        public static String bcd2Str(byte[] bytes) {    
            char temp[] = new char[bytes.length * 2], val;    
        
            for (int i = 0; i < bytes.length; i++) {    
                val = (char) (((bytes[i] & 0xf0) >> 4) & 0x0f);    
                temp[i * 2] = (char) (val > 9 ? val + 'A' - 10 : val + '0');    
        
                val = (char) (bytes[i] & 0x0f);    
                temp[i * 2 + 1] = (char) (val > 9 ? val + 'A' - 10 : val + '0');    
            }    
            return new String(temp);    
        }    
    }
    
    private static class MD5Util {
        protected final static String MD5(String s) {
            char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
            try {
                byte[] btInput = s.getBytes();
                MessageDigest mdInst = MessageDigest.getInstance("MD5");
                mdInst.update(btInput);
                byte[] md = mdInst.digest();
                //to Hex(0x) format
                int j = md.length;
                char str[] = new char[j * 2];
                int k = 0;
                for (int i = 0; i < j; i++) {
                    byte byte0 = md[i];
                    str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                    str[k++] = hexDigits[byte0 & 0xf];
                }
                return new String(str);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}

