package me.a8kj.configuration.parent.regex.scanners;

import lombok.NonNull;
import me.a8kj.configuration.parent.regex.RegexScanner;

public class FolderPathScanner extends RegexScanner {

    public FolderPathScanner() {
        super("^[^/\\\\:*?\"<>|]+(?:/[^/\\\\:*?\"<>|]+)*$");
    }

    public FolderPathScanner(@NonNull String text) {
        super("^[^/\\\\:*?\"<>|]+(?:/[^/\\\\:*?\"<>|]+)*$", text);
    }
}
