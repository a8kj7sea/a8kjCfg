
public class TopPlayer {

    private String name;
    private int playerDataAmount;
    private int position;

    public TopPlayer(String name, int playerDataAmount, int position) {
        this.name = name;
        this.playerDataAmount = playerDataAmount;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getPlayerDataAmount() {
        return playerDataAmount;
    }

    public int getPosition() {
        return position;
    }
}
