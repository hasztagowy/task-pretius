package org.file.organizer.creator.directory;

import java.nio.file.Path;

public class DirectoryCreator {

    public void createDirectories(String... directories){
        for(String directory : directories){
            createDirectory(directory);
        }
    }

    private void createDirectory(final String directory) {
        Path path = Path.of(directory);
        path.toFile().mkdir();
    }
}
