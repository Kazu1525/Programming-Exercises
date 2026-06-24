package lost_and_found;

public class LostFoundItem {
    private String status;
    private String date;
    private String category;
    private String type;
    private String uploader;
    private String location;
    private String description;
    private String image;

    public LostFoundItem(String status, String date, String category, String type, String uploader, String location, String description, String image) {
        this.status = status;
        this.date = date;
        this.category = category;
        this.type = type;
        this.uploader = uploader;
        this.location = location;
        this.description = description;
        this.image = image;
    }
    
    public String getImage() {
        return image;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public String getUploader() {
        return uploader;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Status: " + status + "\n" +
               "Date: " + date + "\n" +
               "Category: " + category + "\n" +
               "Type: " + type + "\n" +
               "Uploader: " + uploader + "\n" +
               "Location Lost or Found: " + location + "\n" +
               "Description: " + description;
    }
    
    public String toText() {
        return status + "%" +
               date + "%" +
               category + "%" +
               type + "%" +
               uploader + "%" + 
               location + "%" +
               description + "%" +
               image;
    }

    public boolean matches(String query) {
        if (query == null || query.isEmpty()) {
            return false;
        }
        
        String lowerCaseQuery = query.toLowerCase();
        
        return status.toLowerCase().contains(lowerCaseQuery) ||
               category.toLowerCase().contains(lowerCaseQuery) ||
               type.toLowerCase().contains(lowerCaseQuery) ||
               location.toLowerCase().contains(lowerCaseQuery);
    }
}
