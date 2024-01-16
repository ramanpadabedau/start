import java.io.File;

public class fileProccessor {
    private String fileName;
    private FX fxInstance;

    private JsonArithmeticProcessor jsonArithmeticProcessor;
    private PlainText plainText;
    private XmlArithmeticProcessor xmlArithmeticProcessor;
    private String destFilePath;
    private String dads_path = "C:\\Users\\ro\\IdeaProjects\\start\\";
    public fileProccessor (String str, FX fxInstance){
        this.fxInstance = fxInstance;
        fileName = str;
    }

    public void inProcess(){
        if (fileName.charAt(fileName.length()-1) == 'p'){
            zipper zipUtil = new zipper();
            destFilePath =  zipUtil.unzipSingleFile1(dads_path + fileName, fxInstance);
            fileName = extractFileName(destFilePath);
            System.out.println(fileName);
        }
        switch(fileName.charAt(fileName.length()-1)){
            case 't':
                plainText = new PlainText(dads_path + fileName, fxInstance);
                plainText.PlainTextProcessor();
                break;
            case 'l':
                System.out.println(fileName);
                xmlArithmeticProcessor = new XmlArithmeticProcessor();
                xmlArithmeticProcessor.processXmlFile(dads_path + fileName);
                break;
            case 'n':
                System.out.println(fileName);
                jsonArithmeticProcessor = new JsonArithmeticProcessor();
                jsonArithmeticProcessor.processJsonFile(dads_path + fileName);
                break;
        }


    }
    public void outProcess(String outFileName){
        switch (outFileName.charAt(outFileName.length()-1)){
            case 't':
                plainText.PlainTextOutProcessor2(outFileName);
                break;
            case 'l':
                xmlArithmeticProcessor.processOutXML(outFileName, fxInstance);
                break;
            case 'n':
                jsonArithmeticProcessor.processJsonFileOut(outFileName, fxInstance);
                break;
        }
    }
    private static String extractFileName(String fullPath) {
        File file = new File(fullPath);
        return file.getName();
    }
}
