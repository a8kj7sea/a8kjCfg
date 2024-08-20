# a8kjCfg | [![](https://jitpack.io/v/a8kj7sea/a8kjCfg.svg)](https://jitpack.io/#a8kj7sea/a8kjCfg)

Here's an improved version of your message with better grammar and clarity:

---

My method for creating a config object with the Spigot API is **not recommended**—please avoid using it. A wiki will be released after the stable beta version is available. If you encounter any issues, I’d be glad to hear about them.

### Step 1: Add the JitPack Repository to Your Build File

**Gradle, Maven, SBT, Leiningen:**

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

### Step 2: Add the Dependency

```xml
<dependency>
    <groupId>com.github.a8kj7sea</groupId>
    <artifactId>a8kjCfg</artifactId>
    <version>Tag</version>
</dependency>
```

### Usage

The idea is simple: implement the `A8kjCfgImpl` class and use the `Settings` annotation to modify settings and options.

**Example:**

```java
import org.bukkit.plugin.java.JavaPlugin;

import lombok.NonNull;
import me.a8kj.configuration.impl.A8kjCfgImpl;
import me.a8kj.configuration.parent.annotations.base.Settings;

@Settings(backupOnSave = true)
public class ConfigTest extends A8kjCfgImpl {

    public ConfigTest(@NonNull String fileName, JavaPlugin plugin) {
        super(fileName, plugin);
        this.create();
    }


    public void setString(String text) {
        if (getFileConfiguration() == null) {
            throw new IllegalStateException("FileConfiguation null !");
        }

        this.getFileConfiguration().set("Test", text);
        this.save();
    }
    

}
```

### Future Plans:

- A new version with an improved style.
- Add a comprehensive wiki.
- Introduce processors.











