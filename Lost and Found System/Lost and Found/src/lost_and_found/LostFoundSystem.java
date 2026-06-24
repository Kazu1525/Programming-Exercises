package lost_and_found;

import java.io.*;
import java.util.*;
import javax.swing.*;

public class LostFoundSystem {
    private AVLTree<String, LostFoundItem> tree;
    private HashMap<String, LostFoundItem> hashMap;
    private String savedFilePath;
    private String savedFolderPath;
 
    public LostFoundSystem() {
        tree = new AVLTree<>();
        hashMap = new HashMap<>(); 
        loadSavedPaths();
    }
    
    public String getSavedFilePath() {
        return savedFilePath;
    }

    public String getSavedFolderPath() {
        return savedFolderPath;
    }

    public void saveFilePath(String filePath) {
        this.savedFilePath = filePath;
        savePathsToFile();
    }

    public void saveFolderPath(String folderPath) {
        this.savedFolderPath = folderPath;
        savePathsToFile();
    }
    
    public LostFoundItem addItem(String status, String date, String category, String type, String uploader, String location, String description, String image) {
        
        String uniqueID = UUID.randomUUID().toString();
        
        LostFoundItem newItem = new LostFoundItem(status, date, category, type, uploader, location, description, image);
        
        hashMap.put(date, newItem);
        tree.insert(uniqueID, newItem);
        
        
        return newItem;
    }
    
    public void searchItems(String query) {
        List<LostFoundItem> results = new ArrayList<>();
        for (LostFoundItem item : hashMap.values()) {
            if (item.matches(query)) {
                results.add(item);
            }
        }

        if (results.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No items match your query.", "Search Results", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder resultString = new StringBuilder("Search Results:\n");
            for (LostFoundItem item : results) {
                resultString.append(item).append("\n\n");
            }
            JOptionPane.showMessageDialog(null, resultString.toString(), "Search Results", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void loadSavedPaths() {
        File configFile = new File("config.txt");

        if (configFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
                savedFilePath = reader.readLine(); // Read the file path
                savedFolderPath = reader.readLine(); // Read the folder path
                System.out.println("Paths loaded successfully.");
            } catch (IOException e) {
                System.err.println("Error loading paths: " + e.getMessage());
            }
        } else {
            System.out.println("Configuration file not found. No paths loaded.");
        }
    }
    
    public void savePathsToFile() {
        if (savedFilePath != null && savedFolderPath != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("config.txt"))) {
                writer.write(savedFilePath); // Save file path
                writer.newLine(); // New line
                writer.write(savedFolderPath); // Save folder path
                System.out.println("Paths saved successfully.");
            } catch (IOException e) {
                System.err.println("Error saving paths: " + e.getMessage());
            }
        } else {
            System.err.println("Paths are not set. Cannot save paths.");
        }
    }
    
}
