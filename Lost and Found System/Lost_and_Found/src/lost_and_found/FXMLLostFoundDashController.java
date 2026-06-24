package lost_and_found;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FXMLLostFoundDashController implements Initializable {
    
    private HashMap<String, LostFoundItem> itemMap = new HashMap<>();
    private AVLTree<String, LostFoundItem> itemTree = new AVLTree<>();
    private LostFoundSystem system = new LostFoundSystem();
    private ObservableList<LostFoundItem> observableItemList = FXCollections.observableArrayList();
    private ObservableList<LostFoundItem> filteredItemList = FXCollections.observableArrayList();
    private String selectedImagePath;
    private String defaultImagePath;
    private Image image;
    
    @FXML
    private TextField category;

    @FXML
    private TableColumn<LostFoundItem, String> categoryColumn;

    @FXML
    private TableColumn<LostFoundItem, String> dateColumn;
    
    @FXML
    private TableColumn<LostFoundItem, String> descriptionColumn;
    
    @FXML
    private TableColumn<LostFoundItem, String> locationColumn;
    
    @FXML
    private TableColumn<LostFoundItem, String> statusColumn;
    
    @FXML
    private TableColumn<LostFoundItem, String> typeColumn;
    
    @FXML
    private TableColumn<LostFoundItem, String> uploaderColumn;
    
    @FXML
    private TableView<LostFoundItem> tableView;
    
    @FXML
    private ComboBox<String> status;
    
    @FXML
    private Button clearButton;

    @FXML
    private Button close;

    @FXML
    private DatePicker date;

    @FXML
    private TextField description;

    @FXML
    private ImageView imageView;

    @FXML
    private TextField location;

    @FXML
    private Button minimize;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchField;

    @FXML
    private FontAwesomeIcon searchIcon;

    @FXML
    private Button selectImageButton;   

    @FXML
    private TextField type;   

    @FXML
    private Button uploadButton;

    @FXML
    private TextField uploader;
    
    public void close() {
        System.exit(0);
    }
    
    public void minimize() {
        Stage stage = (Stage) minimize.getScene().getWindow();
        stage.setIconified(true);
    }
    
    public void upload() {
        UploadButtonAction();
    }
    
    public void selectImage() {
        selectImageButtonAction();
    }
    
    public void clearButton() {
        clearUploadForm();
        selectImageButton.getStyleClass().remove("select-btn-selected");
        selectImageButton.getStyleClass().add("select-btn");
    }
    
    public void searchItemFunction() {
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            
            if (newValue == null || newValue.isEmpty()) {
                clearSearchResults();
            } else {
                searchItems();
            }
        });
    }
    
    
    private void searchItems() {
        String searchText = searchField.getText().toLowerCase();
        
        filteredItemList.clear();
      
        LostFoundItem itemByDate = itemMap.get(searchText);
        if (itemByDate != null) {
            
            filteredItemList.add(itemByDate);
        
        } else {
            List<LostFoundItem> searchResults = itemTree.searchInOrder(searchText);
       
            filteredItemList.addAll(searchResults);
        }
        
        tableView.setItems(filteredItemList);
    }
    
    private void clearSearchResults() {
        filteredItemList.clear();
        filteredItemList.addAll(observableItemList);
        tableView.setItems(filteredItemList);
    }
    
    public void tableSelect() {
        LostFoundItem selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
                       
            if (selectedItem.getImage() != null && !selectedItem.getImage().isEmpty()) {
                File imageFile = new File(selectedItem.getImage());
                if (imageFile.exists()) {
                    
                    String uri = imageFile.toURI().toString();
                    
                    image = new Image(uri, 150, 150, false, true);
                    imageView.setImage(image);
                  
                    
                    selectImageButton.getStyleClass().add("select-btn-selected");
            
                } else {              
                    defaultImage();
                }
            } 
        }
    }
    
    private void UploadButtonAction() {
        String statusValue = status.getValue();
        String categoryValue = category.getText();
        String typeValue = type.getText();
        String uploaderValue = uploader.getText();
        String locationValue = location.getText();
        String descriptionValue = description.getText();
        
        LocalDate selectedDate = date.getValue();
        String dateValue = (selectedDate != null) ? selectedDate.toString() : null;
        
        if (statusValue == null ||
            statusValue.isEmpty() ||
            categoryValue.isEmpty() ||
            descriptionValue.isEmpty() ||
            typeValue.isEmpty() ||
            dateValue == null ||
            dateValue.isEmpty()) {
            Alert missingFields = new Alert(Alert.AlertType.ERROR);
            missingFields.setTitle("Error Message");
            missingFields.setHeaderText(null);
            missingFields.setContentText("Fill in all required fields.");
            missingFields.show();
            return;
        }
        
        String imageValue = selectedImagePath != null ? selectedImagePath : " ";
        
        String uniqueID = UUID.randomUUID().toString();
        LostFoundItem newItem = system.addItem(statusValue, dateValue, categoryValue, typeValue, uploaderValue, locationValue, descriptionValue, imageValue);
        
        if (newItem != null) {
            observableItemList.add(newItem);
            itemTree.insert(uniqueID, newItem);
            itemMap.put(dateValue, newItem);
            
            Alert uploadSuccess = new Alert(Alert.AlertType.INFORMATION);
            uploadSuccess.setTitle("Information Message");
            uploadSuccess.setHeaderText(null);
            uploadSuccess.setContentText("Item uploaded successfully.");
            uploadSuccess.show();
            
            saveToFile();
            clearUploadForm();
            imageClear();
            imageButtonClear();
            
        } else {
            Alert uploadFailed = new Alert(Alert.AlertType.ERROR, "Failed to upload item. Please check input fields.");
            uploadFailed.setTitle("Error Message");
            uploadFailed.setHeaderText(null);
            uploadFailed.setContentText("Failed to upload item. Please check all necessary input fields." );
            uploadFailed.show();
        }
        
    }
    
    private void defaultImage() {
        File defaultImageFile = new File(system.getSavedFolderPath() + File.separator + "default_image.jpg");
        String uri = defaultImageFile.toURI().toString();
                    
            image = new Image(uri, 150, 150, false, true);
            imageView.setImage(image);
                    
            selectImageButton.getStyleClass().add("select-btn-selected");
    }
    
    private void imageClear() {
        
        String imageValue;
        
        if (selectedImagePath != null && !selectedImagePath.isEmpty()) {
            imageValue = " ";
            
            selectedImagePath = null;
        } else {
            imageValue = " ";
        }
        
    }
    
    
    private void imageButtonClear() {
        
        imageView.setImage(null);
        selectImageButton.getStyleClass().remove("select-btn-selected");
        selectImageButton.getStyleClass().add("select-btn");
    }
    
    
    private void clearUploadForm() {
        
        status.setValue(null);
        status.setPromptText("Lost or Found");
        status.requestFocus();
        category.clear();
        type.clear();
        uploader.clear();
        location.clear();
        date.setValue(null);
        date.setPromptText("DD/MM/YYYY");
        date.requestFocus();
        description.clear();
        imageButtonClear();
    }
    
    private void configureTableView() {
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        uploaderColumn.setCellValueFactory(new PropertyValueFactory<>("uploader"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
    }
    
    private void selectImageButtonAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        
        Stage stage = (Stage) selectImageButton.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        
        if (selectedFile != null) {
            selectedImagePath = selectedFile.getAbsolutePath();
            
            String uri = selectedFile.toURI().toString();
            
            image = new Image(uri, 153, 153, false, true);
            imageView.setImage(image);
            
            selectImageButton.getStyleClass().add("select-btn-selected");
            
        }
    }
    
    private void saveToFile() {
        String filePath = system.getSavedFilePath();
        if (filePath == null) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

            File file = fileChooser.showSaveDialog(uploadButton.getScene().getWindow());
            if (file != null) {
                filePath = file.getAbsolutePath();
                system.saveFilePath(filePath);
            } else {
                return;
            }
        }
        
        String folderPath = system.getSavedFolderPath();
        if (folderPath == null) {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Select Folder to Save Files");

            File selectedFolder = directoryChooser.showDialog(uploadButton.getScene().getWindow());
            if (selectedFolder != null) {
                folderPath = selectedFolder.getAbsolutePath();
                system.saveFolderPath(folderPath);
            } else {
                return;
            }
        }
        
        if (!observableItemList.isEmpty()) {
            try (FileWriter fileWriter = new FileWriter(filePath, false)) {
                for (LostFoundItem item : observableItemList) {
                    fileWriter.write(item.toText() + System.lineSeparator());
                }
                fileWriter.flush();
            } catch (IOException e) {
                System.err.println("Failed to save items " + e.getMessage());
            }
        } else {
            System.err.println("No items to save.");
        }
        
        for (LostFoundItem item : observableItemList) {
            String imagePath = item.getImage();
            if (imagePath != null && !imagePath.isEmpty()) {
                File sourceImage = new File(imagePath);
                if (sourceImage.exists()) {
                    File destinationImage = new File(folderPath, sourceImage.getName());
                    try {
                        Files.copy(sourceImage.toPath(), destinationImage.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        System.err.println("Failed to save image: " + sourceImage.getName() + " - " + e.getMessage());
                    }
                }
            }
        }
    }
    
    private void loadFile() {
        String filePath = system.getSavedFilePath();
        if (filePath == null || filePath.isEmpty()) {
            System.err.println("No file path found. Cannot load data.");
            return;
        }

        File file = new File(filePath);
        if (!file.exists()) {
            System.err.println("File not found: " + filePath);
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("%");
                if (data.length == 8) { 
                    LostFoundItem item = new LostFoundItem(
                        data[0], // status
                        data[1], // date
                        data[2], // category
                        data[3], // type
                        data[4], // uploader
                        data[5], // location
                        data[6], // description
                        data[7]  // image
                    );
                    
                    String uniqueID = UUID.randomUUID().toString();
                    itemTree.insert(uniqueID, item);
                    itemMap.put(data[1], item);
                    observableItemList.add(item);
                } else {
                    System.out.println("Incorrect number of fields in line: " + line);
                }
            }
            tableView.setItems(observableItemList);
        } catch (IOException e) {
            System.err.println("Error loading file: " + e.getMessage());
        }
    }

    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configureTableView();
        tableView.setItems(observableItemList);
        status.getItems().addAll("Lost", "Found");
        
        loadFile();
        
        searchField.textProperty().addListener((observable, oldValue, newValue) -> searchItems());
    }    
    
}
