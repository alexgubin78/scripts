/**
 * Created by oracle on 12/8/16.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataFixScripts {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter folder name: ");
        String folderName = br.readLine();
        System.out.print(folderName);

        Path path = Paths.get(folderName);
        //if directory exists?
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                //fail to create directory
                e.printStackTrace();
            }
        }
    }
}