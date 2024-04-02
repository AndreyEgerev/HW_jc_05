package DAO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class WorkingFile {
    private final String FILE_EXTENSION = ".txt";
    private final String FILE_BACKUP_EXTENSION = ".backup";
    private final String SOURCE_PATH = "\\source";
    private final String BACKUP_PATH = "\\backup";
    private final String path;

    public WorkingFile() {
        this.path = System.getProperty("user.dir") + SOURCE_PATH;
    }

    public boolean writeData(String dataObject, String data){
        String pathToWrite = path+"\\"+dataObject+FILE_EXTENSION;
        File objectFile = new File(pathToWrite);
        if (!objectFile.exists()){
            try {
                objectFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (FileWriter fileObjectWrite = new FileWriter(pathToWrite,false)){
            fileObjectWrite.write(data);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> readData(String sourceFileName){
        List<String> data = new ArrayList<String>();
        Path sourcePath = Paths.get(".", SOURCE_PATH, sourceFileName + FILE_EXTENSION);
        try {
            data = Files.readAllLines(sourcePath);
        } catch (IOException e) {
            System.out.println("Ошибка загрузки данных из файла " + sourcePath.getFileName() + " " + e.getMessage());;
        }
        return data;
    }
    public boolean backupData(){
        Path sourcePath = Paths.get(".", SOURCE_PATH);
        Path backupPath = Paths.get(sourcePath.toString(),BACKUP_PATH);
        try {
            if (!Files.exists(backupPath)){
                Files.createDirectory(backupPath);
            }
            List<Path> nameFiles = Files.list(sourcePath).toList();
            for (Path sourceFile:
                 nameFiles) {
                if (Files.isRegularFile(sourceFile)){
                    System.out.println(sourceFile.getFileName()+ " in working");
                    Path backupFile = Paths.get(backupPath.toString(),
                            sourceFile.getFileName().toString() + FILE_BACKUP_EXTENSION);
                    if (Files.exists(backupFile)){
                        Files.delete(backupFile);
                    }
                    Files.copy(sourceFile,backupFile);
                    System.out.println("Backup file " + sourceFile.getFileName() + " has been created");
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка резервного копирования " + e.getMessage());
            return false;
        }
        return true;
    }
}
