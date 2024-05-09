# a8kjCfg | 
my method to create a config object with spigot api . .// btw it bad method 


# Examples


- In this example am trying to create Test yaml file in certain path 

the final path be like : pluginName/files/arenas/myTest.yml

```java

import org.bukkit.plugin.java.JavaPlugin;

import me.a8kj.configuration.annotations.ConfigurationInfo;
import me.a8kj.configuration.entity.AbstractConfiguration;

@ConfigurationInfo(path = "files/arenas/")
public class TestConfiguration extends AbstractConfiguration {

    public TestConfiguration(String name, JavaPlugin javaPlugin) {
        super(name, javaPlugin);
    }

    public void addString(String path, String value) {
        getFileConfiguration().set(path, value);
        this.save();
    }

}

/////////////////////////////// REGISTER ///////////////////////////////////


public class App extends JavaPlugin {

    private TestConfiguration testConfiguration;

    @Override
    public void onEnable() {
        testConfiguration = new TestConfiguration("myTest", this); 
        testConfiguration.init();
        for (int x = 0; x < 11; x++) {
            testConfiguration.addString("Hello.Hello.Hello-" + x, x + " T " + x);
        }
    }
}


```




