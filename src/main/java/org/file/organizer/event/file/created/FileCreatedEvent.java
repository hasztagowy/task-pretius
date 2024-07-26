package org.file.organizer.event.file.created;

import org.file.organizer.event.Event;

import java.nio.file.Path;

public class FileCreatedEvent implements Event {
    private final Path path;

    public FileCreatedEvent(Path path) {
        this.path = path;
    }

    public Path getPath() {
        return path;
    }
}
