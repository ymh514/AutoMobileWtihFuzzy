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
		protected int closestLineId;
		protected double closestLineDist;
		protected Point2D[] lineIntersection = new Point2D[7];
		
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
		public void calDistanceRec1(Canvas canvasPane){
			double x1 = this.carX, y1 = this.carY, x2 = this.x, y2 = this.y; 
			
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
				if(i == 4 || i == 5){
					tempDist[i] = Double.MAX_VALUE;

				}
				else{
					double x3 = tempLine.get(i).getStartX();
					double y3 = tempLine.get(i).getStartY();
					double x4 = tempLine.get(i).getEndX();
					double y4 = tempLine.get(i).getEndY();
					double c = (y3 - y4) / (x3 - x4);
					double d = (x3 * y4 - x4 * y3) / (x3 - x4);

					double intersectionX = ((x1 - x2) * (x3 * y4 - x4 * y3) - (x3 - x4) * (x1 * y2 - x2 * y1))
						/ ((x3 - x4) * (y1 - y2) - (x1 - x2) * (y3 - y4));

					double intersectionY = ((y1 - y2) * (x3 * y4 - x4 * y3) - (x1 * y2 - x2 * y1) * (y3 - y4))
						/ ((y1 - y2) * (x3 - x4) - (x1 - x2) * (y3 - y4));

					double a = this.x - intersectionX;
					double b = this.y - intersectionY;
					
					lineIntersection[i] = new Point2D(intersectionX, intersectionY);
					
					tempDist[i] = Math.sqrt(a * a + b * b);

				}

			}
			double smallestDist = Double.MAX_VALUE;
			int smallestId = 0;
			for(int i=1;i<tempDist.length;i++){
				if(tempDist[i]<smallestDist){
					smallestDist = tempDist[i];
					smallestId = i;
				}
			}

			closestLineId = smallestId;
			closestLineDist = smallestDist;
		}
		
		public void calDistanceRec2(Canvas canvasPane){
			double x1 = this.carX, y1 = this.carY, x2 = this.x, y2 = this.y; 
			
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
				if(i == 4 || i == 5 || i==2){
					tempDist[i] = Double.MAX_VALUE;

				}
				else{
					double x3 = tempLine.get(i).getStartX();
					double y3 = tempLine.get(i).getStartY();
					double x4 = tempLine.get(i).getEndX();
					double y4 = tempLine.get(i).getEndY();
					double c = (y3 - y4) / (x3 - x4);
					double d = (x3 * y4 - x4 * y3) / (x3 - x4);

					double intersectionX = ((x1 - x2) * (x3 * y4 - x4 * y3) - (x3 - x4) * (x1 * y2 - x2 * y1))
						/ ((x3 - x4) * (y1 - y2) - (x1 - x2) * (y3 - y4));

					double intersectionY = ((y1 - y2) * (x3 * y4 - x4 * y3) - (x1 * y2 - x2 * y1) * (y3 - y4))
						/ ((y1 - y2) * (x3 - x4) - (x1 - x2) * (y3 - y4));

					double a = this.x - intersectionX;
					double b = this.y - intersectionY;
					
					lineIntersection[i] = new Point2D(intersectionX, intersectionY);
					
					tempDist[i] = Math.sqrt(a * a + b * b);

				}

			}
			double smallestDist = Double.MAX_VALUE;
			int smallestId = 0;
			for(int i=1;i<tempDist.length;i++){
				if(tempDist[i]<smallestDist){
					smallestDist = tempDist[i];
					smallestId = i;
				}
			}

			closestLineId = smallestId;
			closestLineDist = smallestDist;
		}

		public void calDistance(Canvas canvasPane){
			double x1 = this.carX, y1 = this.carY, x2 = this.x, y2 = this.y; 
			
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

				double intersectionX = ((x1 - x2) * (x3 * y4 - x4 * y3) - (x3 - x4) * (x1 * y2 - x2 * y1))
					/ ((x3 - x4) * (y1 - y2) - (x1 - x2) * (y3 - y4));

				double intersectionY = ((y1 - y2) * (x3 * y4 - x4 * y3) - (x1 * y2 - x2 * y1) * (y3 - y4))
					/ ((y1 - y2) * (x3 - x4) - (x1 - x2) * (y3 - y4));

				double a = this.x - intersectionX;
				double b = this.y - intersectionY;
				
				lineIntersection[i] = new Point2D(intersectionX, intersectionY);
				
				tempDist[i] = Math.sqrt(a * a + b * b);
			}
			double smallestDist = Double.MAX_VALUE;
			int smallestId = 0;
			for(int i=1;i<tempDist.length;i++){
				if(tempDist[i]<smallestDist){
					smallestDist = tempDist[i];
					smallestId = i;
				}
			}

			closestLineId = smallestId;
			closestLineDist = smallestDist;
		}
		
		public String getDist(){
			String distInfo = "Line"+ (closestLineId+1) +" , distance : "+(Math.round(closestLineDist*1000.0)/1000.0);
			return distInfo;
	    }
		
	}

	protected Sensor sensor1;
	protected Sensor sensor2;
	protected Sensor sensor3;
	private double startPointX = 30;
	private double startPointY = 52;
	

	public Car(Canvas canvasPane){
		this.canvasPane = canvasPane;
		// initial
		this.setCenterX(startPointX * ratio);
		this.setCenterY(startPointY * ratio);
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

	public void tuneCar(Canvas canvasPane){

		this.setCenterY(this.getCenterY()-3);
		// moving function has problem
//		this.setCenterX(this.getCenterX()+Math.cos(Math.toRadians(count*10))+Math.sin(Math.toRadians(count*10)));
//		this.setCenterY(this.getCenterY()+Math.sin(Math.toRadians(count*10))-Math.sin(Math.toRadians(count*10)));
		
		// tune sensors coordinate
		setSensorsCarCoordinate(this.getCenterX(), this.getCenterY());
		
		
		this.sensor1.setX(this.getCenterX());
		this.sensor1.setY(this.getCenterY()-3*ratio);
		this.sensor2.setX(this.getCenterX()+(3*ratio*Math.cos(Math.toRadians(45))));
		this.sensor2.setY(this.getCenterY()-(3*ratio*Math.cos(Math.toRadians(45))));
		this.sensor3.setX(this.getCenterX()-(3*ratio*Math.cos(Math.toRadians(45))));
		this.sensor3.setY(this.getCenterY()-(3*ratio*Math.cos(Math.toRadians(45))));
		

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
