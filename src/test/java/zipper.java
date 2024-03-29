import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipInputStream;
public class zipper {
    public  void zipTextFile(String textFilePath, String zipFileName, FX fxInstance) {
        try {
            FileOutputStream fos = new FileOutputStream(zipFileName);
            ZipOutputStream zos = new ZipOutputStream(fos);
            addTextFileToZip(new File(textFilePath), zos);
            zos.close();
            fos.close();
            File file = new File(textFilePath);
            file.delete();
            System.out.println("Архивация завершена.");
            fxInstance.setInfoLabelText("Архивация завершена.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private  void addTextFileToZip(File file, ZipOutputStream zos) throws IOException {
        ZipEntry ze = new ZipEntry(file.getName());
        zos.putNextEntry(ze);
        FileInputStream fis = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fis.read(buffer)) > 0) {
            zos.write(buffer, 0, len);
        }
        zos.closeEntry();
        fis.close();
    }
    public String unzipSingleFile1(String zipFilePath, FX fxInstance) {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry zipEntry = zis.getNextEntry();
            if (zipEntry != null) {
                String fileName = zipEntry.getName();
                String destFilePath = new File(zipFilePath).getParent() + File.separator + fileName;

                try (FileOutputStream fos = new FileOutputStream(destFilePath)) {
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                }

                System.out.println("Разархивация завершена. Файл сохранен как: " + destFilePath);
                fxInstance.setInfoLabelText("Разархивация завершена. Файл сохранен как: " + destFilePath);
                return destFilePath;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public void unzipSingleFile(String zipFilePath, String destFilePath) {
        try {
            FileInputStream fis = new FileInputStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(fis);
            try (FileOutputStream fos = new FileOutputStream(destFilePath)) {
                ZipEntry zipEntry = zis.getNextEntry();
                if (zipEntry != null) {
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                }

                System.out.println("Разархивация завершена.");
            }
            zis.closeEntry();
            zis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
