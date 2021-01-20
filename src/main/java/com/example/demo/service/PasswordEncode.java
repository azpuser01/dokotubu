package com.example.demo.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.stereotype.Service;

@Service
public class PasswordEncode {

	/** パスワード生成アルゴリズム */
	private static final String ALGORITHM = "PBKDF2WithHmacSHA256";
	/** ストレッチング回数 */
	private static final int ITERATION_COUNT = 10000;
	/** 生成される鍵の長さ */
	private static final int KEY_LENGTH = 256;

	public String makePasswordEncord(String password, String salt) {

		// パスワードをChar型の配列、SALT値をbyte型のHASH値配列に変換
		char[] passCharAry = password.toCharArray();
		byte[] hashedSalt = getHashedSalt(salt);

		// 暗号化済の場合は再暗号化を行わない
		if (password.length() == 64) {
			return password;
		}

		// HASH値生成用の鍵情報をオブジェクト化
		PBEKeySpec keySpec = new PBEKeySpec(passCharAry, hashedSalt, ITERATION_COUNT, KEY_LENGTH);

		// HASH値生成用アルゴリズムオブジェクトを生成
		SecretKeyFactory skf;
		try {
			skf = SecretKeyFactory.getInstance(ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}

		// byte配列のHASH値を取得
		SecretKey secretKey;
		try {
			secretKey = skf.generateSecret(keySpec);
		} catch (InvalidKeySpecException e) {
			throw new RuntimeException(e);
		}
		byte[] passByteAry = secretKey.getEncoded();

		// byte配列のHASH値を16進数のStringBuilder型に変換
		StringBuilder sb = new StringBuilder(64);
		for (byte b : passByteAry) {
			sb.append(String.format("%02x", b & 0xff));
		}

		// StringBuilder型のHASH値をString型への変換
		String hash = sb.toString();

		return hash;
	}

	private static byte[] getHashedSalt(String salt) {

		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		messageDigest.update(salt.getBytes());

		return messageDigest.digest();
	}

}