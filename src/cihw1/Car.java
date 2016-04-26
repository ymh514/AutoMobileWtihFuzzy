package cihw1;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Car extends Circle {
	
	private int ratio = 10;
	private Canvas canvasPane;
	protected Sensor sensor1;
	protected Sensor sensor2;
	protected Sensor sensor3;
	protected double startPointX = 0;
	protected double startPointY = 0;
	protected double angle = 90;
	protected double turnAngle = 0;
	protected Fuzzy fuzzy;
	private double x;
	private double y;

	public Car(Canvas canvasPane){
		this.canvasPane = canvasPane;
		
		// initial
		this.setX(startPointX);
		this.setY(startPointY);
		
		this.setCenterX(transToCanvasX(this.getX()));
		this.setCenterY(transToCanvasY(this.getY()));
		this.setRadius(3 * ratio);
		this.setStroke(Color.INDIANRED);
		this.setFill(Color.TRANSPARENT);
		this.setStrokeWidth(2);
				
		// Set sensors
		sensor1 = new Sensor((this.getX())+3*Math.cos(Math.toRadians(angle)), this.getY()+3*Math.sin(Math.toRadians(angle)),this.getX(),this.getY());
		sensor2 = new Sensor(this.getX()+(3*Math.cos(Math.toRadians(angle-45))), this.getY()+(3*Math.sin(Math.toRadians(angle-45))),this.getX(),this.getY());
		// - means right
		sensor3 = new Sensor(this.getX()+(3*Math.cos(Math.toRadians(angle+45))), this.getY()+(3*Math.sin(Math.toRadians(angle+45))),this.getX(),this.getY());
		// + means left
		

//		System.out.println("original x: "+this.getX()+" y:"+this.getY());
		Circle a = new Circle();
		a.setCenterX(transToCanvasX(sensor1.getX()));
		a.setCenterY(transToCanvasY(sensor1.getY()));
		a.setRadius(3);
		a.setStroke(Color.DARKGRAY);
		a.setFill(Color.RED);
		canvasPane.getChildren().add(a);
		
		Circle b = new Circle();
		b.setCenterX(transToCanvasX(sensor2.getX()));
		b.setCenterY(transToCanvasY(sensor2.getY()));
		b.setRadius(3);
		b.setStroke(Color.DARKGRAY);
		b.setFill(Color.BLUE);
		canvasPane.getChildren().add(b);

		Circle c = new Circle();
		c.setCenterX(transToCanvasX(sensor3.getX()));
		c.setCenterY(transToCanvasY(sensor3.getY()));
		c.setRadius(3);
		c.setStroke(Color.DARKGRAY);
		c.setFill(Color.GREEN);
		canvasPane.getChildren().add(c);

		// Set fuzzy 
		fuzzy = new Fuzzy(sensor1, sensor2, sensor3, angle);
		
	}

	public void tuneCar(Canvas canvasPane){

		// Calculate turn angle 
		turnAngle = 10;
//		turnAngle = fuzzy.getTurnAngle();
//		System.out.println("go x: "+this.getX()+" y:"+this.getY());
		System.out.println("trunAngle :"+turnAngle+" angle :"+this.angle);
		this.setX(this.getX()+Math.cos(Math.toRadians(angle + turnAngle)+Math.sin(Math.toRadians(turnAngle)*Math.sin(Math.toRadians(angle)))));
		this.setY(this.getY()+Math.sin(Math.toRadians(angle + turnAngle)-Math.sin(Math.toRadians(turnAngle)*Math.cos(Math.toRadians(angle)))));
//		System.out.println("after x: "+this.getX()+" y:"+this.getY());

		// Tune car's coordinate
		this.setCenterX(transToCanvasX(this.getX()));
		this.setCenterY(transToCanvasY(this.getY()));
//		System.out.println("layout x :"+this.getCenterX()+" y:"+this.getCenterY());
		System.out.println("");
		
		Circle c = new Circle();
		c.setCenterX(this.getCenterX());
		c.setCenterY(this.getCenterY());
		c.setRadius(3);
		c.setStroke(Color.DARKGRAY);
		c.setFill(Color.GREEN);
		canvasPane.getChildren().add(c);

		// Let sensors know car's coordinate
		setSensorsCarCoordinate(this.getX(), this.getY());
		
		// Calculate angle with x-axis
		angle = angle - (180/Math.PI)*Math.asin((2*Math.sin(Math.toRadians(turnAngle))/(6)));
//		if(angle < 0){
//			angle += 360;
//		}
//		if(angle > 360){
//			angle %= 360;
//		}
		
		// Set sensors X and Y
		this.sensor1.setX((this.getX())+3*Math.cos(Math.toRadians(angle)));
		this.sensor1.setY(this.getY()+3*Math.sin(Math.toRadians(angle)));
		double angleForS2 = angle-45;
//		if(angleForS2 >360){
//			angleForS2 %= 360;
//		}
		this.sensor2.setX(this.getX()+(3*Math.cos(Math.toRadians(angleForS2))));
		this.sensor2.setY(this.getY()+(3*Math.sin(Math.toRadians(angleForS2))));
		double angleForS3 = angle+45;
//		if(angleForS3 <0){
//			angleForS3 += 360;
//		}
		this.sensor3.setX(this.getX()+(3*Math.cos(Math.toRadians(angleForS3))));
		this.sensor3.setY(this.getY()+(3*Math.sin(Math.toRadians(angleForS3))));
		
		// Add path on canvas
		addPathOnCanvas(canvasPane);
		
	}
	public void sliderTuneCar(){
		this.sensor1.setX((this.getX())+3*Math.cos(Math.toRadians(angle)));
		this.sensor1.setY(this.getY()+3*Math.sin(Math.toRadians(angle)));
		double angleForS2 = angle-45;
//		if(angleForS2 <0){
//			angleForS2 += 360;
//		}
		this.sensor2.setX(this.getX()+(3*Math.cos(Math.toRadians(angleForS2))));
		this.sensor2.setY(this.getY()+(3*Math.sin(Math.toRadians(angleForS2))));
		double angleForS3 = angle+45;
//		if(angleForS3 > 360){
//			angleForS3 %= 360;
//		}
		this.sensor3.setX(this.getX()+(3*Math.cos(Math.toRadians(angleForS3))));
		this.sensor3.setY(this.getY()+(3*Math.sin(Math.toRadians(angleForS3))));
		
		setSensorsCarCoordinate(this.getX(),this.getY());
	}
	public void initialSetCar(double coordinateX,double coordinateY){
		
		// Tune car's coordinate
		this.setX(transBackX(coordinateX));
		this.setY(transBackY(coordinateY));

		this.setCenterX(transToCanvasX(this.getX()));
		this.setCenterY(transToCanvasY(this.getY()));

		// Let sensors know car's coordinate
		setSensorsCarCoordinate(this.getX(), this.getY());
				
		// Set sensors X and Y
		this.sensor1.setX((this.getX())+3*Math.cos(Math.toRadians(angle)));
		this.sensor1.setY(this.getY()+3*Math.sin(Math.toRadians(angle)));
		double angleForS2 = angle-45;
//		if(angleForS2 <0){
//			angleForS2 += 360;
//		}
		this.sensor2.setX(this.getX()+(3*Math.cos(Math.toRadians(angleForS2))));
		this.sensor2.setY(this.getY()+(3*Math.sin(Math.toRadians(angleForS2))));
		double angleForS3 = angle+45;
//		if(angleForS3 > 360){
//			angleForS3 %= 360;
//		}
		this.sensor3.setX(this.getX()+(3*Math.cos(Math.toRadians(angleForS3))));
		this.sensor3.setY(this.getY()+(3*Math.sin(Math.toRadians(angleForS3))));
		
		setSensorsCarCoordinate(this.getX(),this.getY());
//		System.out.println("carx :"+this.getX()+" y:"+this.getY());
//		System.out.println("s1 x:"+sensor1.getX()+" y:"+sensor1.getY());
//		System.out.println("s2 x:"+sensor2.getX()+" y:"+sensor2.getY());
//		System.out.println("s3 x:"+sensor3.getX()+" y:"+sensor3.getY());
//		
		Circle a = new Circle();
		a.setCenterX(transToCanvasX(sensor1.getX()));
		a.setCenterY(transToCanvasY(sensor1.getY()));
		a.setRadius(3);
		a.setStroke(Color.DARKGRAY);
		a.setFill(Color.RED);
		canvasPane.getChildren().add(a);
		
		Circle b = new Circle();
		b.setCenterX(transToCanvasX(sensor2.getX()));
		b.setCenterY(transToCanvasY(sensor2.getY()));
		b.setRadius(3);
		b.setStroke(Color.DARKGRAY);
		b.setFill(Color.BLUE);
		canvasPane.getChildren().add(b);

		Circle c = new Circle();
		c.setCenterX(transToCanvasX(sensor3.getX()));
		c.setCenterY(transToCanvasY(sensor3.getY()));
		c.setRadius(3);
		c.setStroke(Color.DARKGRAY);
		c.setFill(Color.GREEN);
		canvasPane.getChildren().add(c);

		// Add path on canvas
	}
	public void setSensorsCarCoordinate(double x,double y){
		this.sensor1.setCarX(x);
		this.sensor2.setCarX(x);
		this.sensor3.setCarX(x);
		this.sensor1.setCarY(y);
		this.sensor2.setCarY(y);
		this.sensor3.setCarY(y);
	}
	public void addPathOnCanvas(Canvas canvasPane){
		
		Circle path = new Circle();
		path.setCenterX(transToCanvasX(this.getX()));
		path.setCenterY(transToCanvasY(this.getY()));
		path.setRadius(3);
		path.setStroke(Color.DARKGRAY);
		path.setFill(Color.DARKGRAY);
		canvasPane.getChildren().add(path);

	}
	public void finalTune(){

		// Just move 
		this.setCenterY(this.getCenterX()-ratio*3);
		addPathOnCanvas(canvasPane);
	}
	public double transToCanvasX(double x) {
		double value = (x + 30) * ratio;
		return value;
	}

	public double transToCanvasY(double y) {
		double value = (-y + 52) * ratio;
		return value;
	}
	public double transBackX(double x){
		double value = (x /ratio)-30;
		return value;
	}
	public double transBackY(double y){
		double value = -1*((y /ratio)-52);
		return value;
	}
	public void setX(double x){
		this.x = x;
	}
	public void setY(double y){
		this.y = y;
	}
	public double getX(){
		return this.x;
	}
	public double getY(){
		return this.y;
	}
}
