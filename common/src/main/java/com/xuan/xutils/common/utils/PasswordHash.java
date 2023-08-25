package com.xuan.xutils.common.utils;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * 密码加盐加密算法
 * 参看:http://crackstation.net/hashing-security.htm
 *
 * @author xuan
 * @date 17/8/2.
 */
public class PasswordHash {
    private static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

    /**
     * The following constants may be changed without breaking existing hashes
     */
    private static final int SALT_BYTE_SIZE = 24;
    private static final int HASH_BYTE_SIZE = 24;
    private static final int PBKDF2_ITERATIONS = 1000;

    private static final int ITERATION_INDEX = 0;
    private static final int SALT_INDEX = 1;
    private static final int PBKDF2_INDEX = 2;

    private static final String SEPERATOR = ":";
    private static final String ENCODE = "UTF-8";

    /**
     * Get salt from hash
     *
     * @param hash hash
     * @return salt
     */
    public static String getSaltByHash(String hash) {
        String[] params = hash.split(SEPERATOR);
        return params[SALT_INDEX];
    }

    /**
     * Get password from hash
     *
     * @param hash hash
     * @return password
     */
    public static String getPasswordByHash(String hash) {
        String[] params = hash.split(SEPERATOR);
        return params[PBKDF2_INDEX];
    }

    /**
     * Create password hash
     *
     * @param password password
     * @param salt     salt
     * @return hash
     */
    public static String createHash(String password, String salt) {
        String p = "";
        try {
            p = createHash(password.toCharArray(), salt.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    /**
     * Create password hash
     *
     * @param password password
     * @return hash
     */
    public static String createHash(String password) {
        return createHash(password, PasswordHash.createSalt());
    }

    /**
     * Validate password
     *
     * @param password password
     * @param hash     hash
     * @return true/false
     */
    public static boolean validatePassword(String password, String hash) {
        boolean r = false;
        try {
            r = validatePassword(password.toCharArray(), hash);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    /**
     * Create Salt
     *
     * @return salt
     */
    public static String createSalt() {
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);
        return toHex(salt);
    }

    /**
     * Returns a salted PBKDF2 hash of the password.
     *
     * @param password the password to hash
     * @return a salted PBKDF2 hash of the password
     */
    private static String createHash(char[] password, byte[] salt)
        throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Hash the password
        byte[] hash = pbkdf2(password, salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
        // format iterations:salt:hash
        return PBKDF2_ITERATIONS + SEPERATOR + new String(salt, Charset.forName(ENCODE)) + SEPERATOR + toHex(hash);
    }

    /**
     * Validates a password using a hash.
     *
     * @param password    the password to check
     * @param correctHash the hash of the valid password
     * @return true if the password is correct, false if not
     */
    private static boolean validatePassword(char[] password, String correctHash)
        throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Decode the hash into its parameters
        String[] params = correctHash.split(SEPERATOR);
        int iterations = Integer.parseInt(params[ITERATION_INDEX]);
        byte[] salt = params[SALT_INDEX].getBytes();
        byte[] hash = fromHex(params[PBKDF2_INDEX]);
        // Compute the hash of the provided password, using the same salt, 
        // iteration count, and hash length
        byte[] testHash = pbkdf2(password, salt, iterations, hash.length);
        // Compare the hashes in constant time. The password is correct if
        // both hashes match.
        return slowEquals(hash, testHash);
    }

    /**
     * Compares two byte arrays in length-constant time. This comparison method
     * is used so that password hashes cannot be extracted from an on-line
     * system using a timing attack and then attacked off-line.
     *
     * @param a the first byte array
     * @param b the second byte array
     * @return true if both byte arrays are the same, false if not
     */
    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++) {
            diff |= a[i] ^ b[i];
        }
        return diff == 0;
    }

    /**
     * Computes the PBKDF2 hash of a password.
     *
     * @param password   the password to hash.
     * @param salt       the salt
     * @param iterations the iteration count (slowness factor)
     * @param bytes      the length of the hash to compute in bytes
     * @return the PBDKF2 hash of the password
     */
    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes)
        throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
        return skf.generateSecret(spec).getEncoded();
    }

    /**
     * Converts a string of hexadecimal characters into a byte array.
     *
     * @param hex the hex string
     * @return the hex string decoded into a byte array
     */
    private static byte[] fromHex(String hex) {
        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;
    }

    /**
     * Converts a byte array into a hexadecimal string.
     *
     * @param array the byte array to convert
     * @return a length*2 character string encoding the byte array
     */
    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }

    public static void main(String[] args) {
        String password = "12345";
        String salt = PasswordHash.createSalt();
        String hash = PasswordHash.createHash("12345", salt);
        System.out.println("password:" + password);
        System.out.println("hash:" + hash);
        System.out.println("salt:" + salt);

        System.out.println("--------------------");

        System.out.println("加密后的密码：" + PasswordHash.getPasswordByHash(hash));
        System.out.println("salt：" + PasswordHash.getSaltByHash(hash));
        System.out.println(PasswordHash.validatePassword("12345",
            "1000:6f9c1af7f36ce2b16705963c6b0024b2433c5e4d29591d34:64812be088c0a28668a27a4c4c8be921db0b0a5a59f79a6f"));
    }

}
