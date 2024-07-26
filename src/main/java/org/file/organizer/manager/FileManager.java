package org.file.organizer.manager;

import org.file.organizer.counter.FileCounter;
import org.file.organizer.stategy.move.MoveFileStrategy;
import org.file.organizer.stategy.move.jar.JarFileMoveStrategy;
import org.file.organizer.stategy.move.xml.XmFileMoveStrategy;

import java.io.File;

public class FileManager {
    private final MoveFileStrategy moveFileStrategy;
    private final FileCounter counter = new FileCounter();

    public FileManager(MoveFileStrategy moveFileStrategy) {
        this.moveFileStrategy = moveFileStrategy;
    }

    public void onNewFile(File file) {
        counter.increment(moveFileStrategy.moveFile(file));
    }
}

