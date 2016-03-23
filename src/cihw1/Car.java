package cihw1;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Car extends Circle {

	private int ratio = 10;
	private Canvas canvasPane;
	public class Sensor {
		
		protected double carX;
		protected double carY;
		protected double x;
		protected double y;
		
		public Sensor(double x, double y,double carX,double carY) {
			this.x = x;
			this.y = y;
			this.carX = carX;
			this.carY = carY;
			// TODO Auto-generated constructor stub
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
	    /**
	     * Computes the angle (in degrees) between the vector represented
	     * by this point and the specified vector.
	     * @param x the X magnitude of the other vector
	     * @param y the Y magnitude of the other vector
	     * @return the angle between the two vectors measured in degrees
	     * @since JavaFX 8.0
	     */
	    public double angle(double x, double y) {
	        final double ax = getX();
	        final double ay = getY();

	        final double delta = (ax * x + ay * y) / Math.sqrt(
	                (ax * ax + ay * ay) * (x * x + y * y));

	        if (delta > 1.0) {
	            return 0.0;
	        }
	        if (delta < -1.0) {
	            return 180.0;
	        }

	        return Math.toDegrees(Math.acos(delta));
	    }
	    public String getDist(Canvas canvasPane){
			// line function of itself
			double x1 = this.carX, y1 = this.carY, x2 = this.x, y2 = this.y; 
//			double a = (y1 - y2) / (x1 - x2);
//			double b = (x1 * y2 - x2 * y1) / (x1 - x2);
//			System.out.println("line function : y=" + a + "x + " + b);
			
			ArrayList<Line> tempLine = new ArrayList<Line>();
			tempLine.add(canvasPane.line1);
			tempLine.add(canvasPane.line2);
			tempLine.add(canvasPane.line3);
			tempLine.add(canvasPane.line4);
			tempLine.add(canvasPane.line5);
			tempLine.add(canvasPane.line6);
			tempLine.add(canvasPane.line7);

			double[] tempDist = new double[7];
			// all line

			for(int i=0;i<tempLine.size();i++){
				
				double x3 = tempLine.get(i).getStartX();
				double y3 = tempLine.get(i).getStartY();
				double x4 = tempLine.get(i).getEndX();
				double y4 = tempLine.get(i).getEndY();
				double c = (y3 - y4) / (x3 - x4);
				double d = (x3 * y4 - x4 * y3) / (x3 - x4);
//				System.out.println("Line"+(i+1)+" Function : y=" + c + "x + " + d);

				double intersectionX = ((x1 - x2) * (x3 * y4 - x4 * y3) - (x3 - x4) * (x1 * y2 - x2 * y1))
					/ ((x3 - x4) * (y1 - y2) - (x1 - x2) * (y3 - y4));

				double intersectionY = ((y1 - y2) * (x3 * y4 - x4 * y3) - (x1 * y2 - x2 * y1) * (y3 - y4))
					/ ((y1 - y2) * (x3 - x4) - (x1 - x2) * (y3 - y4));

				double a = this.x - intersectionX;
				double b = this.y - intersectionY;
				tempDist[i] = Math.sqrt(a * a + b * b);
			}
			double smallestDist = Double.MAX_VALUE;
			int smallestId = 0;
			for(int i=1;i<tempDist.length;i++){
				if(tempDist[i]<smallestDist){
					smallestDist = tempDist[i];
					smallestId = i+1;
				}
//				System.out.println("with line"+(i+1)+" distance : "+tempDist[i]);
			}
//			System.out.println("Line"+smallestId+" is the closest and dist is : "+smallestDist);
			
			// consider ratio
//			String distInfo = "Line"+smallestId+" , distance : "+(Math.round(smallestDist/ratio*1000.0)/1000.0);
			String distInfo = "Line"+smallestId+" , distance : "+(Math.round(smallestDist*1000.0)/1000.0);

			return distInfo;
	    }
	}

	protected Sensor sensor1;
	protected Sensor sensor2;
	protected Sensor sensor3;

	public Car(Canvas canvasPane){
		this.canvasPane = canvasPane;
		// initial
		this.setCenterX(30 * ratio);
		this.setCenterY(52 * ratio);
		this.setRadius(3 * ratio);
		this.setStroke(Color.RED);
		this.setFill(Color.TRANSPARENT);
		this.setStrokeWidth(2);
		
		// set sensors
		sensor1 = new Sensor(this.getCenterX(), this.getCenterY()-3*ratio,this.getCenterX(),this.getCenterY());
		sensor2 = new Sensor(this.getCenterX()-(3*ratio*Math.cos(Math.toRadians(45))), this.getCenterY()-(3*ratio*Math.cos(Math.toRadians(45))),this.getCenterX(),this.getCenterY());
		sensor3 = new Sensor(this.getCenterX()+(3*ratio*Math.cos(Math.toRadians(45))), this.getCenterY()-(3*ratio*Math.cos(Math.toRadians(45))),this.getCenterX(),this.getCenterY());
		
//		sensor3.getDist(canvasPane);
		
	}
	public void tuneCarF(Canvas canvasPane){
		double tempX = this.getCenterX();
		double tempY = this.getCenterY();
	}
	
	public void tuneCar(Canvas canvasPane){

		this.setCenterY(this.getCenterY()-3);

		// moving function has problem
		
//		this.setCenterX(this.getCenterX()+Math.cos(Math.toRadians(count*10))+Math.sin(Math.toRadians(count*10)));
//		this.setCenterY(this.getCenterY()+Math.sin(Math.toRadians(count*10))-Math.sin(Math.toRadians(count*10)));
		
		// tune sensors coordinate
		this.sensor1.setX(this.getCenterX());
		this.sensor1.setY(this.getCenterY()-3*ratio);
		this.sensor2.setX(this.getCenterX()+(3*ratio*Math.cos(Math.toRadians(45))));
		this.sensor2.setY(this.getCenterY()-(3*ratio*Math.cos(Math.toRadians(45))));
		this.sensor3.setX(this.getCenterX()-(3*ratio*Math.cos(Math.toRadians(45))));
		this.sensor3.setY(this.getCenterY()-(3*ratio*Math.cos(Math.toRadians(45))));

		// add path
		addPathOnCanvas(canvasPane);
		
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
