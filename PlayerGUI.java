import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PlayerGUI {
    private JFrame frame;
    private JTextField playerIdField, playerNameField, playerCountryField;
    private JTextArea displayArea;
    private Database database;

    public PlayerGUI() {
        database = new Database();
        frame = new JFrame("Player Management");
        frame.getContentPane().setBackground(new Color(128, 128, 255));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.getContentPane().setLayout(new FlowLayout());
        
        // Player ID
        frame.getContentPane().add(new JLabel("Player ID"));
        playerIdField = new JTextField(10);
        playerIdField.setBackground(new Color(128, 128, 192));
        frame.getContentPane().add(playerIdField);
        
        // Player Name
        frame.getContentPane().add(new JLabel("Player Name"));
        playerNameField = new JTextField(20);
        playerNameField.setBackground(new Color(255, 255, 128));
        frame.getContentPane().add(playerNameField);
        
        // Player Country
        frame.getContentPane().add(new JLabel("Player Country"));
        playerCountryField = new JTextField(20);
        playerCountryField.setBackground(new Color(255, 128, 64));
        frame.getContentPane().add(playerCountryField);
        
        // Buttons
        JButton addButton = new JButton("Add Player");
        addButton.setBorder(null);
        addButton.setBackground(new Color(192, 192, 192));
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addPlayer();
            }
        });
        frame.getContentPane().add(addButton);
        
        JButton showButton = new JButton("Show All Players");
        showButton.setBorder(null);
        showButton.setBackground(new Color(128, 128, 128));
        showButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showPlayers();
            }
        });
        frame.getContentPane().add(showButton);
        
        JButton updateButton = new JButton("Update Player");
        updateButton.setBackground(new Color(0, 64, 128));
        updateButton.setBorder(null);
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updatePlayer();
            }
        });
        frame.getContentPane().add(updateButton);
        
        JButton deleteButton = new JButton("Delete Player");
        deleteButton.setBorder(null);
        deleteButton.setBackground(new Color(255, 128, 255));
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deletePlayer();
            }
        });
        frame.getContentPane().add(deleteButton);
        frame.getContentPane().add(new JScrollPane());
        
        // Display area
        displayArea = new JTextArea(10, 30);
        frame.getContentPane().add(displayArea);
        displayArea.setEditable(false);
        
        frame.setVisible(true);
    }

    // Add player to the database
    private void addPlayer() {
        int id = Integer.parseInt(playerIdField.getText());
        String name = playerNameField.getText();
        String country = playerCountryField.getText();
        
        Player player = new Player(id, name, country);
        if (database.addPlayer(player)) {
            displayArea.setText("Player added successfully.");
        } else {
            displayArea.setText("Failed to add player.");
        }
    }
    
    // Show all players
    private void showPlayers() {
        List<Player> players = database.getAllPlayers();
        displayArea.setText("");
        for (Player player : players) {
            displayArea.append("ID: " + player.getPlayerId() + " Name: " + player.getPlayerName() + " Country: " + player.getPlayerCountry() + "\n");
        }
    }
    
    // Update player details
    private void updatePlayer() {
        int id = Integer.parseInt(playerIdField.getText());
        String name = playerNameField.getText();
        String country = playerCountryField.getText();
        
        Player player = new Player(id, name, country);
        if (database.updatePlayer(player)) {
            displayArea.setText("Player updated successfully.");
        } else {
            displayArea.setText("Failed to update player.");
        }
    }
    
    // Delete player
    private void deletePlayer() {
        int id = Integer.parseInt(playerIdField.getText());
        if (database.deletePlayer(id)) {
            displayArea.setText("Player deleted successfully.");
        } else {
            displayArea.setText("Failed to delete player.");
        }
    }
    
    public static void main(String[] args) {
        new PlayerGUI();
    }
}
