package cihw1;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Car extends Circle {

	private int ratio = 10;
	
	public Car(){
		this.setCenterX(30 * ratio);
		this.setCenterY(52 * ratio);
		this.setRadius(3 * ratio);
		this.setStroke(Color.RED);
		this.setFill(Color.TRANSPARENT);
		this.setStrokeWidth(2);
		

	}
	public void tuneCarF(Canvas canvasPane){
		double tempX = this.getCenterX();
		double tempY = this.getCenterY();
	}
	public void tuneCar(Canvas canvasPane){
		// car.setCenterX(car.getCenterX()-count * 1);
		this.setCenterY(this.getCenterY() -  3);
		Circle path = new Circle();
		path.setCenterX(this.getCenterX());
		path.setCenterY(this.getCenterY());
		path.setRadius(3);
		path.setStroke(Color.DARKGRAY);
		path.setFill(Color.DARKGRAY);

		canvasPane.getChildren().add(path);


	}
}
