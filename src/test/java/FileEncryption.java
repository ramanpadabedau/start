import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
@RunWith(PowerMockRunner.class)
@PrepareForTest(FileEncryption.class)
public class FileEncryption {
    private static FX fxInstance;
    public FileEncryption(FX fxInstance){
        this.fxInstance = fxInstance;
    }

    public static void Encrypt1(String fileName) {
        try {
            String key = "123321ee123321ee";
            encryptFile1(key, fileName);
            System.out.println("Файл успешно зашифрован и сохранен в файле: " + "encrypted_" + fileName);
            fxInstance.setInfoLabelText("Файл успешно зашифрован и сохранен в файле: " + "encrypted_" + fileName);
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
            fxInstance.setInfoLabelText("Текст успешно расшифрован и сохранен в файле: " + "decrypted_" + encryptedFileName);
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
            fxInstance.setInfoLabelText("Файл успешно расшифрован и сохранен в файле: " + "decrypted_" + encryptedFileName);
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

