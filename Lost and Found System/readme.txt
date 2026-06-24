Lost and Found System
Java with Ant - Java Application

Project Structure

     Project_Name (Lost_and_Found)
         |________ src
                    |____ lost_and_found
                                |____ AVLNode
                                |____ AVLTree
                                |____ DashDesign.css
                                |____ FXMLLostFoundDash.fxml
                                |____ FXMLLostFoundDashController.java
                                |____ LostFoundGUI.java
                                |____ LostFoundItem.java
                                |____ LostFoundSystem.java
                    |____ main.resources.image_source
                                |____ main_back.jpg
                                |____ usep_circle_logo.png
                    |____ Libraries
                                |____ JavaFX23 - javafx-swt.jar
                                |____ JavaFX23 - javafx-base.jar
                                |____ JavaFX23 - javafx-controls.jar
                                |____ JavaFX23 - javafx-fxml.jar
                                |____ JavaFX23 - javafx-graphics.jar
                                |____ JavaFX23 - javafx-media.jar
                                |____ JavaFX23 - javafx-swing.jar
                                |____ JavaFX23 - javafx-web.jar
                                |____ JDK 21 (Default)
                                |____ fontawesomefx-8-2.jar
         |____ database
                    |____ imagedata
                    |____ itemdata.txt


JavaFX Configuration in NetBeans
Since this is a JavaFX application, ensure the following configurations are applied in NetBeans:

     1. Project Properties:
              - Libraries:
                   - Under Compile - Classpath:
                   - Add Library: JavaFX23.
                   - Add JAR/Folder: fontawesomefx-8.2.jar.

              - Build:
                   - Under Compiling:
                        - Uncheck Compile on Save.

              - Run:
                   - Inside VM Options, paste the following line:
                            : --module-path "C:\Program Files\Java\javafx-sdk-23.0.1\lib" --add-modules javafx.base,javafx.controls,javafx.fxml,javafx.graphics --enable-preview

                   - Replace C:\Program Files\Java\javafx-sdk-23.0.1\lib with the actual path to your javafx-sdk-23.0.1\lib.



I'm using Java Application and doing this because in my Netbeans when I try to build using JavaFX FXML Application I get this message:
-jx-copylibs-warning:
-jx-copylibs:
Java 15 has removed Nashorn, you must provide an engine of running JavaScript yourself. GraalVM JavaScript currently is the preffered option.

And this is the only solution I know right now.



SO before we start and run the program make sure to update the file path of the FXML in the LostFoundGUI Class to your actual path of the FXML. After that we can start running the program.


Initial Setup
    1. Run the Program
           - On the first attempt to upload an item, the program will prompt you to select:
           - A .txt file: Select itemdata.txt (this will overwrite the file).
           - A folder: Select the imagedata folder (it contains a default image)

    2. Uploading Items
           - After completing the initial setup, the program will no longer ask you to select the .txt file or folder.
           - You can upload additional items directly through the GUI.
    3. Viewing Items
           - Select an item in the table view to display its associated image in the image container.

Searching Items
    1. Using AVL Tree
           - Search by:
                 - Status
                 - Category
                 - Type
                 - Location
    2. Using HashMap
	   - Search by providing the full date.


Libraries and Dependencies

    JDK Version: Ensure that you are using JDK 23 or higher.
    External Libraries:
	   - fontawesomefx-8-2.jar for UI icons.



Notes:

    The itemdata.txt file stores details of lost and found items.
    The imagedata folder contains all associated images, including a default image.
    Ensure the program has access to these files for proper functionality.

Thank you for using the Lost and Found System!



