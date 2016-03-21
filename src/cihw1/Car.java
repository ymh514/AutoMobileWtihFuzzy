package cihw1;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Car extends Circle {

	private int ratio = 10;
	
	public class sensor extends Point2D{
		
		protected double cordX;
		protected double cordY;
		
		public sensor(double x, double y) {
			super(x, y);
			this.cordX = x;
			this.cordY = y;
			// TODO Auto-generated constructor stub
		}
		public void setX(double x){
			this.cordX = x;
		}
		public void setY(double y){
			this.cordY = y;
		}
		public double getCordX(){
			return this.cordX;
		}
		public double getCordY(){
			return this.cordY;
		}
	}
	
	protected sensor sensor1;
	protected sensor sensor2;
	protected sensor sensor3;

	public Car(){
		this.setCenterX(30 * ratio);
		this.setCenterY(52 * ratio);
		this.setRadius(3 * ratio);
		this.setStroke(Color.RED);
		this.setFill(Color.TRANSPARENT);
		this.setStrokeWidth(2);
		
		sensor1 = new sensor(this.getCenterX(), this.getCenterY()-3*ratio);
		sensor2 = new sensor(this.getCenterX()+(3*ratio*Math.cos(Math.toRadians(45))), this.getCenterY()-(3*ratio*Math.cos(Math.toRadians(45))));
		sensor3 = new sensor(this.getCenterX()-(3*ratio*Math.cos(Math.toRadians(45))), this.getCenterY()-(3*ratio*Math.cos(Math.toRadians(45))));
		
		System.out.println("Initial x :"+sensor1.getCordX() + "Initial y : "+sensor1.getCordY());
		
	}
	public void tuneCarF(Canvas canvasPane){
		double tempX = this.getCenterX();
		double tempY = this.getCenterY();
	}
	
	public void tuneCar(Canvas canvasPane,int count){
		// car.setCenterX(car.getCenterX()-count * 1);
		this.setCenterY(this.getCenterY()-3);

		//moving function has problem
//		this.setCenterX(this.getCenterX()+Math.cos(Math.toRadians(count*10))+Math.sin(Math.toRadians(count*10)));
//		this.setCenterY(this.getCenterY()+Math.sin(Math.toRadians(count*10))-Math.sin(Math.toRadians(count*10)));
		
		this.sensor1.setX(this.getCenterX());
		this.sensor1.setY(this.getCenterY()-3*ratio);
		this.sensor2.setX(this.getCenterX()+(3*ratio*Math.cos(Math.toRadians(45))));
		this.sensor2.setY(this.getCenterY()-(3*ratio*Math.cos(Math.toRadians(45))));
		this.sensor3.setX(this.getCenterX()-(3*ratio*Math.cos(Math.toRadians(45))));
		this.sensor3.setY(this.getCenterY()-(3*ratio*Math.cos(Math.toRadians(45))));

		Circle path = new Circle();
		path.setCenterX(this.getCenterX());
		path.setCenterY(this.getCenterY());
		path.setRadius(3);
		path.setStroke(Color.DARKGRAY);
		path.setFill(Color.DARKGRAY);

		canvasPane.getChildren().add(path);


	}
}
