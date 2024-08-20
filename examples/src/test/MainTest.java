package me.a8kj.test;

import org.bukkit.plugin.java.JavaPlugin;

public class MainTest extends JavaPlugin {
    
    private ConfigTest configTest;

    @Override
    public void onEnable() {
        this.configTest = new ConfigTest("helloworld", this);
        this.configTest.setString("HelloWorld");
    }
}
