package org.file.organizer.observer;

import org.file.organizer.event.Event;
import org.file.organizer.event.EventListener;
import org.file.organizer.event.file.created.FileCreatedEvent;

import java.io.IOException;
import java.nio.file.*;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

public class FileObserver {
    private final WatchService watchService;
    private final ExecutorService executorService;
    private final Set<EventListener> listeners = new HashSet<>();

    public FileObserver(Path directory) {
        try {
            this.watchService = FileSystems.getDefault().newWatchService();
            directory.register(watchService, ENTRY_CREATE);
            this.executorService = Executors.newSingleThreadExecutor();
        } catch (IOException e) {
            throw new RuntimeException("Cannot create watch service", e);
        }
    }

    public void startObserving() {
        executorService.submit(() -> {
            while (true) {
                WatchKey key;
                try {
                    key = watchService.take();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }

                for (WatchEvent<?> event : key.pollEvents()) {
                    if (event.kind() == ENTRY_CREATE) {
                        Path path = (Path) event.context();
                        notifyListeners(new FileCreatedEvent(path));
                    }
                }

                boolean valid = key.reset();
                if (!valid) {
                    break;
                }
            }
        });
    }

    public void addListener(EventListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners(Event event) {
        for (EventListener listener : listeners) {
            listener.process(event);
        }
    }
}
