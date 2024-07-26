package org.file.organizer.stategy.move.jar;

import org.file.organizer.stategy.move.MoveFileStrategy;
import org.file.organizer.utils.Directory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class JarFileMoveStrategy implements MoveFileStrategy {

    @Override
    public String moveFile(File file) {
        try {
            BasicFileAttributes fileAttributes = Files.readAttributes(Path.of(Directory.HOME.getDirectory(), file.getName()) , BasicFileAttributes.class);
            FileTime fileCreationTime = fileAttributes.creationTime();
            LocalDateTime creationTime = LocalDateTime.ofInstant(fileCreationTime.toInstant(), ZoneId.systemDefault());
            Path targetPath;
            Path targetFile = Paths.get(Directory.HOME.getDirectory() + "/" + file.getName());
            if (creationTime.getYear() % 2 == 0) {
                targetFile.toFile().renameTo(new File(Directory.DEV.getDirectory() + File.separator + file.getName()));
                return Directory.DEV.getDirectory();
            }else {
                targetFile.toFile().renameTo(new File(Directory.TEST.getDirectory() + File.separator + file.getName()));
                return Directory.TEST.getDirectory();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());

//todo
        }
        return Directory.TEST.getDirectory();
    }
}
