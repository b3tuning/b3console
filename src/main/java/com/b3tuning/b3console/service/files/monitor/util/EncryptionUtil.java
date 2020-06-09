/*
 *  Created on:  Jun 09, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  EncryptionUtil is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.service.files.monitor.util;

import lombok.extern.slf4j.XSlf4j;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableEntryException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

@XSlf4j
public class EncryptionUtil {


	protected static final String ALGORITHM_AES = "AES";
	protected static final String ALGORITHM_RSA = "RSA";

	public static class AES {

		public static byte[] encrypt(byte[] message, SecretKey key) {
			return encode(message, key, ALGORITHM_AES, Cipher.ENCRYPT_MODE);
		}

		public static byte[] decrypt(byte[] message, SecretKey key) {
			return encode(message, key, ALGORITHM_AES, Cipher.DECRYPT_MODE);
		}

		/**
		 * Encrypt a message with a base64 encoded AES key.
		 *
		 * @param message
		 * @param key
		 * @return the encrypted message or null
		 * @see EncryptionUtil.AES#decrypt(byte[], String);
		 * @see EncryptionUtil.AES#exportSecretKey(SecretKey)
		 */
		public static byte[] encrypt(byte[] message, String key) {
			SecretKey secretKey = importSecretKey(key);
			return encode(message, secretKey, ALGORITHM_AES, Cipher.ENCRYPT_MODE);
		}

		/**
		 * Decrypt a message with a base54 encoded AES key.
		 *
		 * @param message
		 * @param key
		 * @return the decrypted message or null
		 * @see EncryptionUtil.AES#encrypt(byte[], String);
		 * @see EncryptionUtil.AES#exportSecretKey(SecretKey)
		 */
		public static byte[] decrypt(byte[] message, String key) {
			SecretKey secretKey = importSecretKey(key);
			return encode(message, secretKey, ALGORITHM_AES, Cipher.DECRYPT_MODE);
		}

		/**
		 * Import a base64 encoded AES key.
		 *
		 * @param base64Key
		 * @return a SecretKey instance or null
		 * @see EncryptionUtil.AES#exportSecretKey(SecretKey)
		 */
		public static SecretKey importSecretKey(String base64Key) {
			byte[] keyBytes = Base64.getDecoder().decode(base64Key);

			// note: the AES SecretKeyFactory is not available
			// http://bugs.java.com/bugdatabase/view_bug.do?bug_id=7022467
			//SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM_AES);
			//SecretKey secretKey = keyFactory.generateSecret(keySpec);

			// So instead just verify the key size and return the SecretKeySpec
			if (keyBytes.length == 16 || keyBytes.length == 24 || keyBytes.length == 32) {
				SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ALGORITHM_AES);
				return keySpec;
			}
			return null;
		}

		/**
		 * Export an AES key as a base64 encoded String.
		 *
		 * @param key
		 * @return the encoded key or null
		 * @see EncryptionUtil.AES#importSecretKey(String)
		 */
		public static String exportSecretKey(SecretKey key) {
			byte[] base64 = Base64.getEncoder().encode(key.getEncoded());
			return new String(base64);
		}
	}

	public static class RSA {

		public static byte[] encrypt(byte[] message, PublicKey key) {
			return encode(message, key, ALGORITHM_RSA, Cipher.PUBLIC_KEY);
		}

		public static byte[] decrypt(byte[] message, PrivateKey key) {
			return encode(message, key, ALGORITHM_RSA, Cipher.PRIVATE_KEY);
		}

		/**
		 * Encrypt a message using a base64 X509 encoded RSA public key
		 *
		 * @param message
		 * @param key
		 * @return the encrypted message or null
		 * @see EncryptionUtil.RSA#decrypt(byte[], String)
		 * @see EncryptionUtil.RSA#exportPublicKey(PublicKey)
		 */
		public static byte[] encrypt(byte[] message, String key) {
			PublicKey publicKey = importPublicKey(key);
			return encode(message, publicKey, ALGORITHM_RSA, Cipher.PUBLIC_KEY);
		}

		/**
		 * Decrypt a message using a base64 PKCS8 encoded RSA private key
		 *
		 * @param message
		 * @param key
		 * @return the decrypted message or null
		 * @see EncryptionUtil.RSA#encrypt(byte[], String)
		 * @see EncryptionUtil.RSA#exportPrivateKey(PrivateKey)
		 */
		public static byte[] decrypt(byte[] message, String key) {
			PrivateKey privateKey = importPrivateKey(key);
			return encode(message, privateKey, ALGORITHM_RSA, Cipher.PRIVATE_KEY);
		}

		/**
		 * Import a base64 PKCS8 encoded RSA private key.
		 *
		 * @param base64Key
		 * @return a PrivateKey instance or null
		 * @see EncryptionUtil.RSA#exportPrivateKey(PrivateKey)
		 */
		public static PrivateKey importPrivateKey(String base64Key) {
			try {
				byte[]              keyBytes   = Base64.getDecoder().decode(base64Key);
				PKCS8EncodedKeySpec keySpec    = new PKCS8EncodedKeySpec(keyBytes);
				KeyFactory          keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
				PrivateKey          privateKey = keyFactory.generatePrivate(keySpec);
				return privateKey;
			} catch (NoSuchAlgorithmException e) {
				log.error("ERROR: this shouldn't happen", e);
			} catch (InvalidKeySpecException e) {
				log.error("The provided key is not valid", e);
			}

			return null;
		}

		/**
		 * Export a private key as a base64 PKCS8 encoded String.
		 * <p>
		 * This should be compatible with the following:
		 * keytool -importkeystore -srckeystore keystore.jks -destkeystore keystore.p12 -deststoretype PKCS12
		 * openssl pkcs12 -in keystore.p12  -nodes -nocerts -out key.pem
		 * openssl pkcs8 -topk8 -in key.pem -outform PEM -nocrypt
		 *
		 * @param key
		 * @return the encoded key or null
		 * @see EncryptionUtil.RSA#importPrivateKey(String)
		 */
		public static String exportPrivateKey(PrivateKey key) {
			try {
				KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
				PKCS8EncodedKeySpec keySpec = keyFactory.getKeySpec(key, PKCS8EncodedKeySpec.class);
				byte[] base64 = Base64.getEncoder().encode(keySpec.getEncoded());
				return new String(base64);
			} catch (NoSuchAlgorithmException e) {
				log.error("ERROR: this shouldn't happen", e);
			} catch (InvalidKeySpecException e) {
				log.error("The provided key is not valid", e);
			}

			return null;
		}

		/**
		 * Import a base64 X509 encoded RSA public key.
		 *
		 * @param base64Key
		 * @return a PublicKey instance or null
		 * @see EncryptionUtil.RSA#exportPublicKey(PublicKey)
		 */
		public static PublicKey importPublicKey(String base64Key) {
			try {
				byte[]             keyBytes   = Base64.getDecoder().decode(base64Key);
				X509EncodedKeySpec keySpec    = new X509EncodedKeySpec(keyBytes);
				KeyFactory         keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
				PublicKey          publicKey  = keyFactory.generatePublic(keySpec);
				return publicKey;
			} catch (NoSuchAlgorithmException e) {
				log.error("ERROR: this shouldn't happen", e);
			} catch (InvalidKeySpecException e) {
				log.error("The provided key is not valid", e);
			}

			return null;
		}

		/**
		 * Export a public key as a base64 X509 encoded String.
		 * <p>
		 * The output of this should be compatible with the following:
		 * keytool -importkeystore -srckeystore keystore.jks -destkeystore keystore.p12 -deststoretype PKCS12
		 * openssl pkcs12 -in keystore.p12  -nodes -nocerts -out key.pem
		 * openssl rsa -in key.pem -pubout -outform PEM
		 *
		 * @param key
		 * @return the encoded key or null
		 * @see EncryptionUtil.RSA#importPublicKey(String)
		 */
		public static String exportPublicKey(PublicKey key) {
			try {
				KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
				X509EncodedKeySpec keySpec = keyFactory.getKeySpec(key, X509EncodedKeySpec.class);
				byte[] base64 = Base64.getEncoder().encode(keySpec.getEncoded());
				return new String(base64);
			} catch (NoSuchAlgorithmException e) {
				log.error("ERROR: this shouldn't happen", e);
			} catch (InvalidKeySpecException e) {
				log.error("The provided key is not valid", e);
			}

			return null;
		}
	}

	/**
	 * Load a JKS default java keystore.
	 * <p>
	 * A default keystore can be generated using the keytool:
	 * keytool -genkey -alias brevity -keyalg RSA -keystore keystore.jks -keysize 2048
	 *
	 * @param keyStoreFile
	 * @param keyStorePass
	 * @return a KeyStore instance or null
	 */
	public static Optional<KeyStore> loadDefaultKeyStore(String keyStoreFile, String keyStorePass) {
		log.debug("Loading keyStoreFile: {}", new File(keyStoreFile).getAbsolutePath());
		try (InputStream keyStoreStream = new FileInputStream(keyStoreFile)) {
			KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
			keystore.load(keyStoreStream, keyStorePass.toCharArray());

			return Optional.of(keystore);

		} catch (FileNotFoundException e) {
			log.error("KeyStore {} doesn't exist!", keyStoreFile, e);
		} catch (KeyStoreException e) {
			log.error("Couldn't load keystore JKS...", e);
		} catch (NoSuchAlgorithmException | CertificateException | IOException e) {
			log.error("Error loading key", e);
		}

		return Optional.empty();
	}

	public static KeyStore.PrivateKeyEntry getPrivateKeyEntry(KeyStore keyStore, String keyAlias, String keyPass) {
		try {
			if (keyStore.containsAlias(keyAlias)) {
				try {
					return (KeyStore.PrivateKeyEntry) keyStore.getEntry(keyAlias, new KeyStore.PasswordProtection(keyPass.toCharArray()));
				} catch (UnrecoverableEntryException | NoSuchAlgorithmException e) {
					log.error("Couldn't load secret key entry for alias {}", keyAlias, e);
				}
			}
		} catch (KeyStoreException e) {
			log.error("KeyStore doesn't contain alias {}", keyAlias, e);
		}

		return null;
	}

	public static Optional<KeyPair> getKeyPair(KeyStore keyStore, String keyAlias, String keyPass) {
		return getKey(keyStore, keyAlias, keyPass)
				.filter(key -> key instanceof PrivateKey)
				.map(key -> {
					try {
						Certificate cert      = keyStore.getCertificate(keyAlias);
						PublicKey   publicKey = cert.getPublicKey();
						return new KeyPair(publicKey, (PrivateKey) key);
					} catch (KeyStoreException e) {
						log.error("Couldn't load public key certificate using alias {}", keyAlias, e);
						return null;
					}
				});
	}

	public static Optional<Key> getKey(KeyStore keyStore, String keyAlias, String keyPass) {
		try {
			if (keyStore.containsAlias(keyAlias)) {
				try {
					return Optional.of(keyStore.getKey(keyAlias, keyPass.toCharArray()));
				} catch (UnrecoverableKeyException | NoSuchAlgorithmException e) {
					log.error("Couldn't load key for alias {}", keyAlias, e);
				}
			}
		} catch (KeyStoreException e) {
			log.error("KeyStore doesn't contain alias {}", keyAlias, e);
		}

		return Optional.empty();
	}

	public static byte[] encode(byte[] message, Key key, String algorithm, int mode) {
		byte[] decoded = null;
		try {
			Cipher cipher = Cipher.getInstance(algorithm);
			cipher.init(mode, key);

			decoded = cipher.doFinal(message);

		} catch (NoSuchAlgorithmException | NoSuchPaddingException e1) {
			e1.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}

		return decoded;
	}

	public static boolean testKeyPair(KeyPair keyPair) {
		byte[] original = "data".getBytes();
		byte[] encrypted = RSA.encrypt(original, keyPair.getPublic());
		byte[] decrypted = RSA.decrypt(encrypted, keyPair.getPrivate());
		return Arrays.equals(original, decrypted);
	}
}
