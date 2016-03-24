package cihw1;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Car extends Circle {
	
	private int ratio = 10;
	private Canvas canvasPane;
	protected double angle = 90;
	protected double turnAngle = 40;
	protected Sensor sensor1;
	protected Sensor sensor2;
	protected Sensor sensor3;
	protected double startPointX = 30;
	protected double startPointY = 52;
	protected Fuzzy fuzzy;
	

	public Car(Canvas canvasPane){
		this.canvasPane = canvasPane;
		// initial
		this.setCenterX(startPointX * ratio);
		this.setCenterY(startPointY * ratio);
		this.setRadius(3 * ratio);
		this.setStroke(Color.RED);
		this.setFill(Color.TRANSPARENT);
		this.setStrokeWidth(2);
		
		String s1 = "sensor1";
		String s2 = "sensor2";
		String s3 = "sensor3";
		
		// set sensors
		sensor1 = new Sensor((this.getCenterX())+3*ratio*Math.cos(Math.toRadians(angle)), this.getCenterY()-3*ratio*Math.sin(Math.toRadians(angle)),this.getCenterX(),this.getCenterY(),s1);
		sensor2 = new Sensor(this.getCenterX()+(3*ratio*Math.cos(Math.toRadians(angle+45))), this.getCenterY()-(3*ratio*Math.sin(Math.toRadians(angle+45))),this.getCenterX(),this.getCenterY(),s2);
		sensor3 = new Sensor(this.getCenterX()+(3*ratio*Math.cos(Math.toRadians(angle-45))), this.getCenterY()-(3*ratio*Math.sin(Math.toRadians(angle-45))),this.getCenterX(),this.getCenterY(),s3);
		
		fuzzy = new Fuzzy(sensor1, sensor2, sensor3, angle);
//		sensor3.getDist(canvasPane);
		
	}

	public void tuneCar(Canvas canvasPane){

		// fuzzy first
		turnAngle = fuzzy.getTurnAngle();
		
//		this.setCenterY(this.getCenterY()-3);
		// moving function has problem
		this.setCenterX(ratio*(this.getCenterX()/ratio+Math.cos(Math.toRadians(angle + turnAngle))+Math.sin(Math.toRadians(turnAngle))*Math.sin(Math.toRadians(angle))));
		this.setCenterY(ratio*(this.getCenterY()/ratio-Math.sin(Math.toRadians(angle + turnAngle))+Math.sin(Math.toRadians(turnAngle))*Math.cos(Math.toRadians(angle))));

		// tune sensors coordinate
		setSensorsCarCoordinate(this.getCenterX(), this.getCenterY());
		// maybe need mulitple ratio                                                    here
		angle = angle - (180/Math.PI)*Math.asin((2*Math.sin(Math.toRadians(turnAngle))/(6)));
		if(angle < 0){
			angle += 360;
		}
		if(angle > 360){
			angle %= 360;
		}
//		angle %= 360;
		
		this.sensor1.setX((this.getCenterX())+3*ratio*Math.cos(Math.toRadians(angle)));
		this.sensor1.setY(this.getCenterY()-3*ratio*Math.sin(Math.toRadians(angle)));
		double angleForS2 = angle-45;
		if(angleForS2 <0){
			angleForS2 += 360;
		}
		this.sensor2.setX(this.getCenterX()+(3*ratio*Math.cos(Math.toRadians(angleForS2))));
		this.sensor2.setY(this.getCenterY()-(3*ratio*Math.sin(Math.toRadians(angleForS2))));
		double angleForS3 = angle+45;
		if(angleForS3 > 360){
			angleForS3 %= 360;
		}
		this.sensor3.setX(this.getCenterX()+(3*ratio*Math.cos(Math.toRadians(angleForS3))));
		this.sensor3.setY(this.getCenterY()-(3*ratio*Math.sin(Math.toRadians(angleForS3))));
		

		// add path
		addPathOnCanvas(canvasPane);

		
	}
	public void setSensorsCarCoordinate(double x,double y){
		this.sensor1.carX = x;
		this.sensor1.carY = y;
		this.sensor2.carX = x;
		this.sensor2.carY = y;
		this.sensor3.carX = x;
		this.sensor3.carY = y;
	}
	public void addPathOnCanvas(Canvas canvasPane){
		
		Circle path = new Circle();
		path.setCenterX(this.getCenterX());
		path.setCenterY(this.getCenterY());
		path.setRadius(3);
		path.setStroke(Color.DARKGRAY);
		path.setFill(Color.DARKGRAY);
		canvasPane.getChildren().add(path);

	}
}
