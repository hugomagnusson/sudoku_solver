package gui;

import sudoku.Sudoku;

import java.util.function.UnaryOperator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class Controller extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		TextField[][] fields = new TextField[9][9];
		
		
		
		primaryStage.setTitle("Sudoku solver");
		BorderPane bp = new BorderPane();
		HBox hbox = new HBox();
		bp.setBottom(hbox);
		TilePane tiles = new TilePane();
		tiles.setPrefTileHeight(25);
		tiles.setPrefTileWidth(25);
		tiles.setPrefRows(9);
		tiles.setPrefColumns(9);

		StringConverter<Integer> stringConverter = new StringConverter<Integer>() {

	        @Override
	        public String toString(Integer object) {
	            if (object == null || object.intValue() == 0) {
	                return "";
	            }
	            return object.toString() ;
	        }

	        @Override
	        public Integer fromString(String string) {
	            if (string == null || string.isEmpty()) {
	                return 0 ;
	            }
	            return Integer.parseInt(string);
	        }

	    };
		
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				TextField field = new TextField();
				
				if ((i/3 + j/3) % 2 != 0) {
					field.setStyle("-fx-control-inner-background: #00CED1");
				}
				
				UnaryOperator<Change> textFilter = c -> {
					
					if (c.getText().matches("[1-9]")) {
						c.setRange(0, field.getText().length());
						return c;
					}
					if (c.getText().isEmpty()) {
						return c;
					}
					return null;
				};
				TextFormatter<Integer> formatter = new TextFormatter<Integer>(stringConverter, 0, textFilter);
				
				formatter.valueProperty().addListener((obs, oldValue, newValue) -> {
					int old = oldValue.intValue();
					int updated = newValue.intValue();
				});
				
				field.setTextFormatter(formatter);
				fields[i][j] = field;
				//fields[i][j].setPrefSize(30, 30);
				tiles.getChildren().add(fields[i][j]);
			}
		}
		bp.setCenter(tiles);
		Button solve = new Button("Solve");
//		solve.setOnAction(new EventHandler<ActionEvent>() {
//			public void handle(ActionEvent event) {
//				
//			}
//		});
		solve.setOnAction(event -> {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					int entry;
					if (!fields[i][j].getText().equals("")) {
						entry = Integer.parseInt(fields[i][j].getText());
					} else {
						entry = 0;
					}
					Sudoku.setCell(i, j, entry);
				}
			}
			Sudoku.solve();
			Sudoku.printSudoku();
			
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					fields[i][j].setText("" + Sudoku.getCell(i, j));
				}
			}
		});
		
		Button clear = new Button("Clear");
		clear.setOnAction(event -> {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					Sudoku.setCell(i, j, 0);
					fields[i][j].setText("");
				}
			}
		});
		
		hbox.getChildren().addAll(solve, clear);
		
		primaryStage.setScene(new Scene(bp, 215, 250));
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	private boolean valid(String entry) {
		return entry.matches("[0-9]*");
	}

}
