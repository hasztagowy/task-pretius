package org.file.organizer.event.file.created.service;

import org.file.organizer.event.Event;
import org.file.organizer.event.EventListener;
import org.file.organizer.event.file.created.FileCreatedEvent;
import org.file.organizer.manager.FileManager;
import org.file.organizer.observer.FileObserver;

import java.io.File;

public class FileCreatedEventService implements EventListener {

    private final FileObserver fileObserver;
    private final FileManager xmlFileManager;
    private final FileManager jarFileManager;

//    public FileCreatedEventService(FileObserver fileObserver) {
//        this.fileObserver = fileObserver;
//    }

    public FileCreatedEventService(FileObserver fileObserver, FileManager xmlFileManager, FileManager jarFileManager) {
        this.fileObserver = fileObserver;
        this.xmlFileManager = xmlFileManager;
        this.jarFileManager = jarFileManager;
    }

    public void start() throws InterruptedException {
        fileObserver.addListener(this);
        fileObserver.startObserving();
    }


    @Override
    public void process(Event event) {
        if (event instanceof FileCreatedEvent fileCreatedEvent) {
            String fileName = fileCreatedEvent.getPath().getFileName().toString();
            String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
            File file = fileCreatedEvent.getPath().toFile();
            switch (extension) {
                case "xml" -> xmlFileManager.onNewFile(((FileCreatedEvent) event).getPath().toFile());
                case "jar" -> jarFileManager.onNewFile(((FileCreatedEvent) event).getPath().toFile());
            }
        }
    }
}
