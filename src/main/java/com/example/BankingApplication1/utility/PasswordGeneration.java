package com.example.BankingApplication1.utility;

import java.security.SecureRandom;
import java.util.Random;

public class PasswordGeneration {
	private static final Random RANDOM = new SecureRandom();
	private static final String ALPHABETUSERNAME = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	

	public static String generatePassword(int length) {
		StringBuilder returnValue = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		return new String(returnValue);
	}
	
	public static String generateUsername(int length) {
		StringBuilder returnValue = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			returnValue.append(ALPHABETUSERNAME.charAt(RANDOM.nextInt(ALPHABETUSERNAME.length())));
		}
		return new String(returnValue);
	}

	public static void main(String[] args) {
		int passwordLength = 10;
		int usernameLength=5;
		String username = generateUsername(usernameLength);
		String password = generatePassword(passwordLength);

	}

}



