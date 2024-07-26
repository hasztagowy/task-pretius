package org.file.organizer;

import org.file.organizer.creator.directory.DirectoryCreator;
import org.file.organizer.event.file.created.service.FileCreatedEventService;
import org.file.organizer.manager.FileManagerFacotry;
import org.file.organizer.observer.FileObserver;
import org.file.organizer.utils.Directory;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        new DirectoryCreator().createDirectories(Directory.HOME.getDirectory(), Directory.TEST.getDirectory(), Directory.DEV.getDirectory());
        FileObserver fileObserver = new FileObserver(Path.of(Directory.HOME.getDirectory()));
        FileManagerFacotry fileManagerFacotry = FileManagerFacotry.getINSTANCE();
        new FileCreatedEventService(fileObserver, fileManagerFacotry.createXMLFileManager(), fileManagerFacotry.createJarFileManager()).start();
    }
}