package cihw1;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class Canvas extends Pane{

	protected int ratio = 10;
	
	public Canvas(){
		
		this.setBackground(new Background(new BackgroundFill(Color.GAINSBORO, null, null)));
		this.setPrefSize(800, 800);

		Line line1 = new Line();
		line1.setStartX(24 * ratio);
		line1.setStartY(52 * ratio);
		line1.setEndX(36 * ratio);
		line1.setEndY(52 * ratio);
		line1.setStroke(Color.BLACK);
		line1.setStrokeWidth(2);
		this.getChildren().add(line1);

		Line line2 = new Line();
		line2.setStartX(24 * ratio);
		line2.setStartY(52 * ratio);
		line2.setEndX(24 * ratio);
		line2.setEndY(30 * ratio);
		line2.setStroke(Color.BLUE);
		line2.setStrokeWidth(2);
		this.getChildren().add(line2);

		Line line3 = new Line();
		line3.setStartX(36 * ratio);
		line3.setStartY(52 * ratio);
		line3.setEndX(36 * ratio);
		line3.setEndY(42 * ratio);
		line3.setStroke(Color.BLUE);
		line3.setStrokeWidth(2);

		this.getChildren().add(line3);

		Line line4 = new Line();
		line4.setStartX(24 * ratio);
		line4.setStartY(30 * ratio);
		line4.setEndX(48 * ratio);
		line4.setEndY(30 * ratio);
		line4.setStroke(Color.BLUE);
		line4.setStrokeWidth(2);

		this.getChildren().add(line4);

		Line line5 = new Line();
		line5.setStartX(36 * ratio);
		line5.setStartY(42 * ratio);
		line5.setEndX(60 * ratio);
		line5.setEndY(42 * ratio);
		line5.setStroke(Color.BLUE);
		line5.setStrokeWidth(2);

		this.getChildren().add(line5);

		Line line6 = new Line();
		line6.setStartX(48 * ratio);
		line6.setStartY(30 * ratio);
		line6.setEndX(48 * ratio);
		line6.setEndY(15 * ratio);
		line6.setStroke(Color.BLUE);
		line6.setStrokeWidth(2);

		this.getChildren().add(line6);
		
		Line line7 = new Line();
		line7.setStartX(60 * ratio);
		line7.setStartY(42 * ratio);
		line7.setEndX(60 * ratio);
		line7.setEndY(15 * ratio);
		line7.setStroke(Color.BLUE);
		line7.setStrokeWidth(2);
		this.getChildren().add(line7);


	}
	public Canvas getCanvasPane(){
		return this;
	}
}
