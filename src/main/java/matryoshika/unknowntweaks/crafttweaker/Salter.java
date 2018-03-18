package matryoshika.unknowntweaks.crafttweaker;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

public class Salter {
	
	public static String getSalted(String password) {
		String encodedPassword = "";
		Base64 base64 = new Base64();
		String saltChars = "The only real laughter comes from despair.";
		byte[] salt = base64.decode(saltChars);
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			digest.update(salt);

			byte[] btPass = digest.digest(password.getBytes("UTF-8"));
			for (int i = 0; i < password.length(); i++) {
				digest.reset();
				btPass = digest.digest(btPass);
			}
			encodedPassword = base64.encodeAsString(btPass);

		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {

		}
		return encodedPassword;
	}
}
