import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
public class fileProccessor {
    private String fileName;
    String destFilePath;
    private boolean encrypted;
    public fileProccessor (String str, boolean bool){
        fileName = str;
        encrypted = bool;
    }
    public void Process(){
        switch(fileName.charAt(fileName.length()-1)){
            case 'p':
                zipper zipUtil = new zipper();
                String dads_path = "C:\\Users\\ro\\IdeaProjects\\start\\";
                destFilePath =  zipUtil.unzipSingleFile1(dads_path + fileName);
            case 't':

        }


        }
}
