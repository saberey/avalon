package test;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DES3 {

	private static final String Algorithm = "DESede"; // 瀹氫箟鍔犲瘑绠楁硶,鍙敤

	// DES,DESede,Blowfish
	// keybyte涓哄姞瀵嗗瘑閽ワ紝闀垮害涓�24瀛楄妭
	// src涓鸿鍔犲瘑鐨勬暟鎹紦鍐插尯锛堟簮锛�
	public static String encryptMode(byte[] keybyte, byte[] src) {
		try {
			// 鐢熸垚瀵嗛挜
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
			// 鍔犲瘑
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			// 寮�濮嬪姞瀵嗚繍绠�
			byte[] encryptedByteArray = c1.doFinal(src);
			return Base64.getEncoder().encodeToString(encryptedByteArray);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	// keybyte涓哄姞瀵嗗瘑閽ワ紝闀垮害涓�24瀛楄妭
	// src涓哄姞瀵嗗悗鐨勭紦鍐插尯
	public static byte[] decryptMode(byte[] keybyte, String src) {
		try {
			// 鐢熸垚瀵嗛挜
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
			// 瑙ｅ瘑
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			// 瑙ｅ瘑杩愮畻 灏哹ase64鐨凷tring杞寲涓篵yte[]
			return c1.doFinal(Base64.getDecoder().decode(src));

		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}
	// keybyte涓哄姞瀵嗗瘑閽ワ紝闀垮害涓�24瀛楄妭
	// src涓哄姞瀵嗗悗鐨勭紦鍐插尯
	public static byte[] decryptMode(byte[] keybyte, byte[] src) {
		try {
			// 鐢熸垚瀵嗛挜
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
			// 瑙ｅ瘑
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			// 瑙ｅ瘑杩愮畻涔嬪墠
			byte[] encryptedByteArray = src;
			// 瑙ｅ瘑杩愮畻 灏哹ase64鐨凷tring杞寲涓篵yte[]
			return c1.doFinal(encryptedByteArray);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	// 杞崲鎴愬崄鍏繘鍒跺瓧绗︿覆
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			if (n < b.length - 1)
				hs = hs + ":";
		}
		return hs.toUpperCase();
	}

	public static void main(String[] args) {
		// 娣诲姞鏂板畨鍏ㄧ畻娉�,濡傛灉鐢↗CE灏辫鎶婂畠娣诲姞杩涘幓
	//	Security.addProvider(new com.sun.crypto.provider.SunJCE());
		final byte[] keyBytes = { 0x11, 0x22, 0x4F, 0x58, (byte) 0x88, 0x10,
				0x40, 0x38, 0x28, 0x25, 0x79, 0x51, (byte) 0xCB, (byte) 0xDD,
				0x55, 0x66, 0x77, 0x29, 0x74, (byte) 0x98, 0x30, 0x40, 0x36,
				(byte) 0xE2 }; // 24瀛楄妭鐨勫瘑閽�
		
		String cesKey="IWdfYXBh5S1JqJTN0cFdyn2B";


		String szSrc = "Wangjw.che001";
		System.out.println("加密前的字符串:" + szSrc);

		String encoded = encryptMode(cesKey.getBytes(), szSrc.getBytes());
		System.out.println("加密后的字符串:" + encoded);

		byte[] srcBytes = decryptMode(cesKey.getBytes(), encoded);
		System.out.println("解密后的字符串:" + (new String(srcBytes)));
	}
}