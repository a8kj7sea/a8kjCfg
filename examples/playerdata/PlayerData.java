
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.collect.Sets;

import me.a8kj.configuration.entity.AbstractConfiguration;

public class PlayerData extends AbstractConfiguration implements IPlayerData {

    public PlayerData(String name, JavaPlugin javaPlugin) {
        super(name, javaPlugin);
    }

    private final String dataPath = "players.%name.%data_type";

    @Override
    public int getPlayerData(Player player, PlayerDataType playerDataType) {
        if (!exist(player)) {
            setupNewPlayer(player);
            this.save();
            return 0;
        }

        return this.getFileConfiguration().getInt(new String(dataPath)
                .replace("%name", player.getName())
                .replace("%data_type", playerDataType.name().toLowerCase()));
    }

    @Override
    public void setPlayerData(Player player, PlayerDataType playerDataType, int amountToSet) {

        if (!exist(player)) {
            setupNewPlayer(player);
            setPlayerData(player, playerDataType, Math.abs(amountToSet));
            this.save();
            return;
        }

        this.getFileConfiguration().set(new String(dataPath)
                .replace("%name", player.getName())
                .replace("%data_type", playerDataType.name().toLowerCase()), amountToSet);
        this.save();
    }

    @Override
    public boolean exist(Player player) {
        return this.getFileConfiguration().contains("players." + player.getName());
    }

    public Set<TopPlayer> getTopByPlayerDataType(int limit, PlayerDataType playerDataType) {
        HashMap<String, Integer> dataMap = new HashMap<String, Integer>();
        for (String name : this.getFileConfiguration().getKeys(false)) {
            @SuppressWarnings("deprecation")
            int targetData = getPlayerData(Bukkit.getOfflinePlayer(name).getPlayer(), playerDataType);
            dataMap.put(name, targetData);
        }

        Set<TopPlayer> topPlayers = Sets.newHashSet();

        dataMap.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).limit(limit)
                .forEachOrdered(new Consumer<Map.Entry<String, Integer>>() {
                    AtomicInteger position = new AtomicInteger(1);

                    @Override
                    public void accept(Map.Entry<String, Integer> entry) {
                        topPlayers.add(new TopPlayer(entry.getKey(), entry.getValue(), position.getAndIncrement()));
                    }
                });
        return topPlayers;
    }

}
