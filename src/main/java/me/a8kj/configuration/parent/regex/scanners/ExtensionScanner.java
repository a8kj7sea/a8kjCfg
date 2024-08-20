package me.a8kj.configuration.parent.regex.scanners;

import lombok.NonNull;
import me.a8kj.configuration.parent.regex.RegexScanner;

public class ExtensionScanner extends RegexScanner {

    public ExtensionScanner() {
        super("^\\.[a-zA-Z0-9]+$");
    }

    public ExtensionScanner(@NonNull String text) {
        super("^\\.[a-zA-Z0-9]+$", text);
    }

}
