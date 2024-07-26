package org.file.organizer.utils;

public enum Directory {
    HOME("HOME"),
    TEST("TEST"),
    DEV("DEV");

    private final String directory;

    Directory(String directory) {
        this.directory = directory;
    }

    public String getDirectory() {
        return directory;
    }
}
