public class Player {
    private int playerId;
    private String playerName;
    private String playerCountry;

    // Constructor
    public Player(int playerId, String playerName, String playerCountry) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.playerCountry = playerCountry;
    }

    // Getters and setters
    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerCountry() {
        return playerCountry;
    }

    public void setPlayerCountry(String playerCountry) {
        this.playerCountry = playerCountry;
    }
}
