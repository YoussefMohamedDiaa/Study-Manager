package application;
	
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;


public class Main extends Application {
	static Button startButton = new Button("Start");
	@Override
	public void start(Stage primaryStage) {
		try {
			
			BorderPane root = new BorderPane(queueSystem());
			Scene scene = new Scene(root,600,123);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Study Manager");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static StackPane createTaskNode(String taskText) {
	    
		StackPane stack = new StackPane();
		stack.setMaxSize(120.0f, 120.0f);
	    stack.setBackground(new Background(new BackgroundFill(Color.web("#b0d4c5"), CornerRadii.EMPTY, Insets.EMPTY)));
		
	    Text text = new Text (taskText);
	    text.setStyle("-fx-font-weight: bold");

		Circle node = new Circle();
		node.setCenterX(100.0f);
		node.setCenterY(100.0f);
		node.setRadius(50.0f);
		node.setFill(javafx.scene.paint.Color.BURLYWOOD);
		stack.getChildren().addAll(node, text);
		
		return stack;
	}
	
     public static HBox createTaskQueue() {
	    HBox queue = new HBox();
	    queue.getStyleClass().add("hbox");
	    queue.setBackground(new Background(new BackgroundFill(Color.web("#b0d4c5"), CornerRadii.EMPTY, Insets.EMPTY)));
	    queue.setMaxHeight(122f);
	    queue.getChildren().addAll();
	    
	    return queue;
	}
    
     public static HBox smallModle(){
    	 HBox ch = new HBox();
    	 HBox queue = createTaskQueue();
    	 ch.getChildren().addAll(queue,startButton);
    	 startButton.setOnMouseClicked(new EventHandler() {
 			@Override
 			public void handle(Event e) {
 				 push((pop(queue)),queue);
 			}
 		});
    	 return ch;
     }
     
     public static VBox leftControls(HBox queue) {
    	 VBox controlsList = new VBox();
    	 TextArea input = new TextArea();
    	 input.setMaxHeight(90f);
    	 input.setMinWidth(70f);
    	 input.setMaxWidth(70f);
    	 Button insert = new Button("insert");
    	 insert.setOnMouseClicked(new EventHandler() {
   			@Override
   			public void handle(Event e) {
   				push(createTaskNode(input.getText()),queue);
   				input.clear();
   			}
   		});
    	 insert.setMaxHeight(22f);
    	 insert.setMinWidth(70f);
    	 controlsList.getChildren().addAll(input, insert);
    	 return controlsList;
     }
     
     public static VBox rightControls(HBox queue) {
    	 VBox controlsList = new VBox();
    	
    	 Button done = new Button("Done");
    	 done.setOnMouseClicked(new EventHandler() {
  			@Override
  			public void handle(Event e) {
  				push((pop(queue)),queue);
  			}
  		});
    	 done.setMinHeight(122f);
    	 done.setMinWidth(70f);
    	 controlsList.getChildren().addAll( done);
    	 return controlsList;
     }
     
     public static HBox queueSystem() {
    	 HBox systemHbox = new HBox();
    	 HBox queue = createTaskQueue();
         systemHbox.getChildren().addAll(leftControls(queue),queue,rightControls(queue));
    	 return systemHbox;
     }
     
	 public static StackPane pop(HBox queue) {
		 return (StackPane)queue.getChildren().remove(queue.getChildren().size()-1);
	 }
	 
	 public static void push(StackPane node, HBox queue) {
		 queue.getChildren().add(0, node);
	 }
	 
}
