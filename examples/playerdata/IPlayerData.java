

import org.bukkit.entity.Player;

public interface IPlayerData {

    public enum PlayerDataType {
        KILLS, DEATHS, STREAKS, POINTS;
    }

    public int getPlayerData(Player player, PlayerDataType playerDataType);

    public void setPlayerData(Player player, PlayerDataType playerDataType, int amountToSet);

    default public void addPlayerData(Player player, PlayerDataType playerDataType, int amountToAdd) {
        setPlayerData(player, playerDataType, getPlayerData(player, playerDataType) + Math.abs(amountToAdd));
    }

    default public void removePlayerData(Player player, PlayerDataType playerDataType, int amountToRemove) {

        int playerData = getPlayerData(player, playerDataType);

        if (amountToRemove > playerData) {
            setPlayerData(player, playerDataType, 0);
        } else {
            setPlayerData(player, playerDataType, getPlayerData(player, playerDataType) - Math.abs(amountToRemove));
        }

    }

    public boolean exist(Player player);

    default public void setupNewPlayer(Player player) {
        if (exist(player))
            return;

        for (PlayerDataType playerDataType : PlayerDataType.values()) {
            setPlayerData(player, playerDataType, 0);
        }
    }

}
