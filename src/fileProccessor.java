import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

public class fileProccessor {
    private String fileName;
    private PlainText plainText;
    private XmlArithmeticProcessor xmlArithmeticProcessor;
    private String destFilePath;
    private String dads_path = "C:\\Users\\ro\\IdeaProjects\\start\\";
    private boolean encrypted;
    public fileProccessor (String str, boolean bool){
        fileName = str;
        encrypted = bool;
    }

    public void inProcess(){
        if (fileName.charAt(fileName.length()-1) == 'p'){
            zipper zipUtil = new zipper();
            destFilePath =  zipUtil.unzipSingleFile1(dads_path + fileName);
            fileName = extractFileName(destFilePath);
            System.out.println(fileName);
        }
        switch(fileName.charAt(fileName.length()-1)){
            case 't':
                plainText = new PlainText(dads_path + fileName);
                plainText.PlainTextProcessor();
                break;
            case 'l':
                System.out.println(fileName);
                xmlArithmeticProcessor = new XmlArithmeticProcessor();
                xmlArithmeticProcessor.processXmlFile(dads_path + fileName);
                break;

        }


    }
    public void outProcess(String outFileName){
        switch (outFileName.charAt(outFileName.length()-1)){
            case 't':
                plainText.PlainTextOutProcessor(outFileName);
                break;
            case 'l':
                xmlArithmeticProcessor.processOutXML(outFileName);
                break;
            case 'n':

                break;
        }
    }
    private static String extractFileName(String fullPath) {
        File file = new File(fullPath);
        return file.getName();
    }
}
