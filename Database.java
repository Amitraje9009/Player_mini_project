import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private Connection connection;
    
    public Database() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/playersdb","root","root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Add a new player
    public boolean addPlayer(Player player) {
        String query = "INSERT INTO players (id, name, country) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, player.getPlayerId());
            stmt.setString(2, player.getPlayerName());
            stmt.setString(3, player.getPlayerCountry());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
   
    public List<Player> getAllPlayers() {
        List<Player> players = new ArrayList<>();
        String query = "SELECT * FROM players";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                players.add(new Player(rs.getInt("id"), rs.getString("name"), rs.getString("country")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }
    
    public boolean updatePlayer(Player player) {
        String query = "UPDATE players SET name = ?, country = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, player.getPlayerName());
            stmt.setString(2, player.getPlayerCountry());
            stmt.setInt(3, player.getPlayerId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
 
    public boolean deletePlayer(int playerId) {
        String query = "DELETE FROM players WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, playerId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
