package cihw1;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Car extends Circle {

	private int ratio = 10;
	
	public Car(){
		this.setCenterX(20 * ratio);
		this.setCenterY(60 * ratio);
		this.setRadius(3 * ratio);
		this.setStroke(Color.RED);
		this.setFill(Color.TRANSPARENT);

	}
	public Car getCar(){
		return this;
	}
}
