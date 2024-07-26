package org.file.organizer.stategy.move.xml;

import org.file.organizer.stategy.move.MoveFileStrategy;
import org.file.organizer.utils.Directory;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class XmFileMoveStrategy implements MoveFileStrategy {

    @Override
    public String moveFile(File file) {
        Path targetFile = Paths.get(Directory.HOME.getDirectory() + "/" + file.getName());
        targetFile.toFile().renameTo(new File(Directory.DEV.getDirectory() + File.separator + file.getName()));
//            Files.move(file.toPath(), targetPath);

//            return Directory.DEV.getDirectory();
        return Directory.DEV.getDirectory();
    }
}
