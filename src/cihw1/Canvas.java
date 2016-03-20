package cihw1;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Canvas extends Pane{

	protected int ratio = 10;
	
	public Canvas(){
		
		this.setPrefSize(800, 800);

		Line startLine = new Line();
		startLine.setStartX(0);
		startLine.setStartY(60 * ratio);
		startLine.setEndX(60 * ratio);
		startLine.setEndY(60 * ratio);
		this.getChildren().add(startLine);

		Line line1 = new Line();
		line1.setStartX(14 * ratio);
		line1.setStartY(60 * ratio);
		line1.setEndX(14 * ratio);
		line1.setEndY(38 * ratio);
		line1.setStroke(Color.BLUE);
		this.getChildren().add(line1);

		Line line2 = new Line();
		line2.setStartX(26 * ratio);
		line2.setStartY(60 * ratio);
		line2.setEndX(26 * ratio);
		line2.setEndY(50 * ratio);
		line2.setStroke(Color.BLUE);
		this.getChildren().add(line2);

		Line line3 = new Line();
		line3.setStartX(14 * ratio);
		line3.setStartY(38 * ratio);
		line3.setEndX(38 * ratio);
		line3.setEndY(38 * ratio);
		line3.setStroke(Color.BLUE);
		this.getChildren().add(line3);

		Line line4 = new Line();
		line4.setStartX(26 * ratio);
		line4.setStartY(50 * ratio);
		line4.setEndX(50 * ratio);
		line4.setEndY(50 * ratio);
		line4.setStroke(Color.BLUE);
		this.getChildren().add(line4);

		Line line5 = new Line();
		line5.setStartX(38 * ratio);
		line5.setStartY(38 * ratio);
		line5.setEndX(38 * ratio);
		line5.setEndY(0 * ratio);
		line5.setStroke(Color.BLUE);
		this.getChildren().add(line5);

		Line line6 = new Line();
		line6.setStartX(50 * ratio);
		line6.setStartY(50 * ratio);
		line6.setEndX(50 * ratio);
		line6.setEndY(0 * ratio);
		line6.setStroke(Color.BLUE);
		this.getChildren().add(line6);


	}
	public Canvas getCanvasPane(){
		return this;
	}
}
