package files;


import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UseFiles {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("C:\\Users\\wangsw\\Desktop");
        Files.list(path).forEach(System.out::println);

        try {
            Files.createDirectory(Paths.get("C:\\Users\\wangsw\\Desktop\\tmp"));
        }catch (FileAlreadyExistsException e){
            e.printStackTrace();
        }
    }
}
