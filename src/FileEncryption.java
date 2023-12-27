import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
public class FileEncryption {
    public static void main(String[] args) {

    }

    public static void Encrypt(String fileName) {
        try {
            String key = "123321ee123321ee";
            encryptFile(key, fileName);
            System.out.println("Текст успешно зашифрован и сохранен в файле: " + "encrypted_" + fileName);
            File file = new File(fileName);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void encryptFile(String key, String inputFile)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException,
            IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        String outputFileName = "encrypted_" + inputFile;
        try (FileInputStream inputStream = new FileInputStream(inputFile);
             CipherOutputStream cipherOutputStream = new CipherOutputStream(new FileOutputStream(outputFileName), cipher)) {
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) >= 0) {
                cipherOutputStream.write(buffer, 0, bytesRead);
            }
        }
    }
    public static void Encrypt1(String fileName) {
        try {
            String key = "123321ee123321ee";
            encryptFile1(key, fileName);
            System.out.println("Файл успешно зашифрован и сохранен в файле: " + "encrypted_" + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        File originalFile = new File(fileName);
        System.out.println(fileName);
        originalFile.delete();
    }
    private static void encryptFile1(String key, String inputFile)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        String outputFileName = "encrypted_" + inputFile;
        try (FileInputStream inputStream = new FileInputStream(inputFile);
             CipherOutputStream cipherOutputStream = new CipherOutputStream(new FileOutputStream(outputFileName), cipher)) {
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) >= 0) {
                cipherOutputStream.write(buffer, 0, bytesRead);
            }
        }
    }
    public static String Decrypt(String encryptedFileName) {
        try {
            decryptFile("123321ee123321ee", encryptedFileName);
            System.out.println("Текст успешно расшифрован и сохранен в файле: " + "decrypted_" + encryptedFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("decrypted_" + encryptedFileName);
        return "decrypted_" + encryptedFileName;
    }
    private static void decryptFile(String key, String inputFile)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException,
            IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        String outputFileName = "decrypted_" + inputFile;
        try (CipherInputStream cipherInputStream = new CipherInputStream(new FileInputStream(inputFile), cipher);
             FileOutputStream outputStream = new FileOutputStream(outputFileName)) {
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = cipherInputStream.read(buffer)) >= 0) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }
    public static void Decrypt1(String encryptedFileName) {
        try {
            decryptFile1("123321ee123321ee", encryptedFileName);
            System.out.println("Файл успешно расшифрован и сохранен в файле: " + "decrypted_" + encryptedFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void decryptFile1(String key, String inputFile)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException,
            IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        String outputFileName = "decrypted_" + inputFile.substring(11);
        try (CipherInputStream cipherInputStream = new CipherInputStream(new FileInputStream(inputFile), cipher);
             FileOutputStream outputStream = new FileOutputStream(outputFileName)) {
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = cipherInputStream.read(buffer)) >= 0) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }
}

