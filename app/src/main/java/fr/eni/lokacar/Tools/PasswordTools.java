package fr.eni.lokacar.Tools;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class PasswordTools {
	 
	
	/**
	    * Methode pour hasher un mot de passe en clair -> un mot de passe en MD5
	*/
		public static final String hashPass(String pass) throws NoSuchAlgorithmException {
			MessageDigest mdEnc = MessageDigest.getInstance("MD5"); 
			mdEnc.update(pass.getBytes(), 0, pass.length());
			String md5 = new BigInteger(1, mdEnc.digest()).toString(16); // Encrypted 
			return md5;
		}

		/**
		    * Methode pour vérifier qu'un mot de passe en clair est identique a un mot de pass hashé
		*/
		public static final boolean checkLogin(String passwordClair, String passwordHash) throws  NoSuchAlgorithmException {

			String passHashed = hashPass(passwordClair);
			boolean isOK = false;

			if (passHashed.equals(passwordHash)) {
				isOK =true;
			} else {
				System.out.println("Incorrect password - login combination.");
			}
			return isOK;
		}

}
