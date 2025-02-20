package friday.gui;
import friday.Friday;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Friday friday;

    private Image jarvisImage = new Image(this.getClass().getResourceAsStream("/images/JARVIS.png"));
    private Image fridayImage = new Image(this.getClass().getResourceAsStream("/images/FRIDAY.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Friday instance */
    public void setFriday(Friday d) {
        friday = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Friday's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        try {
            String input = userInput.getText();
            String response = friday.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, jarvisImage),
                    DialogBox.getFridayDialog(response, fridayImage)
            );
            userInput.clear();
        } catch (Exception e) {
            e.printStackTrace();
            Throwable cause = e.getCause();
            if (cause != null) {
                System.out.println("Root cause: " + cause);
                cause.printStackTrace();
            }
        }
    }
}

