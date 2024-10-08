# a8kjCfg | [![](https://jitpack.io/v/a8kj7sea/a8kjCfg.svg)](https://jitpack.io/#a8kj7sea/a8kjCfg)

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
package me.a8kj.test;

import org.bukkit.plugin.java.JavaPlugin;

import lombok.NonNull;
import me.a8kj.configuration.impl.A8kjCfgImpl;
import me.a8kj.configuration.parent.annotations.base.Settings;
import me.a8kj.configuration.parent.annotations.base.Where;

@Settings(
    backupOnSave = true, 
    where = @Where(
        path = "configs/test",
        custom = true,
        backup = @Where.Backup(
            path = "backups",
            custom = true,
            extension = ".bak"
        )
    )
)
public class FullConfig extends A8kjCfgImpl {

    public ConfigTest(@NonNull String fileName, JavaPlugin plugin) {
        super(fileName, plugin);
        this.create();
    }

    public void setString(String text) {
        if (getFileConfiguration() == null) {
            throw new IllegalStateException("FileConfiguration is null!");
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











