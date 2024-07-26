package org.file.organizer.manager;

import org.file.organizer.stategy.move.jar.JarFileMoveStrategy;
import org.file.organizer.stategy.move.xml.XmFileMoveStrategy;

public class FileManagerFacotry {
    private final static FileManagerFacotry INSTANCE = new FileManagerFacotry();

    public static FileManagerFacotry getINSTANCE() {
        return INSTANCE;
    }

    public FileManager createXMLFileManager() {
        return new FileManager(new XmFileMoveStrategy());
    }

    public FileManager createJarFileManager() {
        return new FileManager(new JarFileMoveStrategy());
    }
}
