package cihw1;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.shape.Line;

public class Sensor {

	protected double carX;
	protected double carY;
	protected double x;
	protected double y;
	protected int closestLineId;
	protected double closestLineDist;
	protected Point2D[] lineIntersection = new Point2D[8];
	protected double lineFunA;
	protected double lineFunB;
	protected double verticalA;
	protected double verticalB;
	protected String name;

	public Sensor(double x, double y, double carX, double carY,String name) {
		this.x = x;
		this.y = y;
		this.carX = carX;
		this.carY = carY;
		this.name = name;
		// TODO Auto-generated constructor stub
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public void findIntersection(ArrayList<Line> tempLine, double[] tempDist, int i) {
		
		double x3 = tempLine.get(i).getStartX();
		double y3 = tempLine.get(i).getStartY();
		double x4 = tempLine.get(i).getEndX();
		double y4 = tempLine.get(i).getEndY();
		double c = (y3 - y4) / (x3 - x4);
		double d = (x3 * y4 - x4 * y3) / (x3 - x4);

		double intersectionX = ((this.carX - this.getX()) * (x3 * y4 - x4 * y3)
				- (x3 - x4) * (this.carX * this.getY() - this.getX() * this.carY))
				/ ((x3 - x4) * (this.carY - this.getY()) - (this.carX - this.getX()) * (y3 - y4));

		double intersectionY = ((this.carY - this.getY()) * (x3 * y4 - x4 * y3)
				- (this.carX * this.getY() - this.getX() * this.carY) * (y3 - y4))
				/ ((this.carY - this.getY()) * (x3 - x4) - (this.carX - this.getX()) * (y3 - y4));
		
		
		
//		lineIntersection[i] = new Point2D(intersectionX, intersectionY);
//		if(checkPositiveSide(intersectionX, intersectionY)>0){
//			double a = this.x - intersectionX;
//			double b = this.y - intersectionY;
//			tempDist[i] = Math.sqrt(a * a + b * b);
//
//		}
//		else{
//			tempDist[i] = Double.MAX_VALUE;
//
//		}
//		if(checkLineRange(x3,x4,y3,y4,intersectionX,intersectionY) > 0){
//			double a = this.x - intersectionX;
//			double b = this.y - intersectionY;
//			tempDist[i] = Math.sqrt(a * a + b * b);
//			lineIntersection[i] = new Point2D(intersectionX, intersectionY);
//
//		}
//		else{
//			tempDist[i] = Double.MAX_VALUE;
//			lineIntersection[i] = new Point2D(Double.NaN,Double.NaN );
//
//		}

		if(checkPositiveSide(intersectionX, intersectionY)>0){
			if(checkLineRange(x3,x4,y3,y4,intersectionX,intersectionY) > 0){
				double a = this.x - intersectionX;
				double b = this.y - intersectionY;
				tempDist[i] = Math.sqrt(a * a + b * b);
				lineIntersection[i] = new Point2D(intersectionX, intersectionY);

			}
			else{
				tempDist[i] = Double.MAX_VALUE;
				lineIntersection[i] = new Point2D(Double.NaN,Double.NaN );

			}
		}
		else{
			tempDist[i] = Double.MAX_VALUE;
			lineIntersection[i] = new Point2D(Double.NaN, Double.NaN);

		}
//		int count = 0;
//		for(int j = 0;j<lineIntersection.length;j++){
//			if(lineIntersection[j].getX() == Double.NaN && lineIntersection[j].getY() == Double.NaN){
//				count++;
//			}
//		}
//		if(count == 7){
//			lineIntersection[i] = new Point2D(intersectionX, intersectionY);
//
//		}
		
	}
	public int checkLineRange(double lineSX,double lineEX ,double lineSY,double lineEY ,double x,double y){
		
		if(lineSX == lineEX){
			if( y<=lineSY && y>=lineEY){
				return 1;
			}
			else{
				return -1;
			}
		}
		else{
			if( x>=lineSX && x<=lineEX){
				return 1;
			}
			else{
				return -1;
			}

		}
	}

	public int checkPositiveSide(double x, double y) {
		if(verticalA >= 0){
			double ans = verticalA * x - y + verticalB;
			if (ans > 0) {
				return 1;
			} else if (ans < 0) {
				return -1;
			} else {
				return 1;
			}

		}
		//weired > < can chage to debug
		else{
			double ans = -verticalA * x + y - verticalB;
			if (ans < 0) {
				return 1;
			} else if (ans > 0) {
				return -1;
			} else {
				return 1;
			}

		}
		
//		double ans = verticalA * x - y + verticalB;
//		if (ans > 0) {
//			return 1;
//		} else if (ans < 0) {
//			return -1;
//		} else {
//			return 1;
//		}

	}

	public void calDistance(Canvas canvasPane, int roadFlag) {

		lineFunA = (this.carY - this.getY()) / (this.carX - this.getX());
		lineFunB = (this.carX * this.getY() - this.getX() * this.carY) / (this.carX - this.getX());
		// System.out.println("line function : y=" + lineFunA + "x + " +
		// lineFunB);
		verticalA = -1 / lineFunA;
		verticalB = this.carY - this.carX * verticalA;
		// System.out.println("vertical line function : y=" + verticalA + "x + "
		// + verticalB);

		ArrayList<Line> tempLine = new ArrayList<Line>();
		tempLine.add(canvasPane.line1);
		tempLine.add(canvasPane.line2);
		tempLine.add(canvasPane.line3);
		tempLine.add(canvasPane.line4);
		tempLine.add(canvasPane.line5);
		tempLine.add(canvasPane.line6);
		tempLine.add(canvasPane.line7);
		tempLine.add(canvasPane.line8);

		double[] tempDist = new double[8];
		// all line


		if (roadFlag == 1) {
			for (int i = 0; i < tempLine.size(); i++) {
				if (i == 4 || i == 5 || i==7) {
					tempDist[i] = Double.MAX_VALUE;
				} else {
					findIntersection(tempLine, tempDist, i);
				}
			}
		} else if (roadFlag == 2) {
			for (int i = 0; i < tempLine.size(); i++) {
				if (i == 5) {
					tempDist[i] = Double.MAX_VALUE;

				} else {
					findIntersection(tempLine, tempDist, i);
				}
			}
		} else if (roadFlag == 3) {
			for (int i = 0; i < tempLine.size(); i++) {
				if (i==0 || i == 2 || i==5 || i==7) {
					tempDist[i] = Double.MAX_VALUE;

				} else {
					findIntersection(tempLine, tempDist, i);
				}
			}

		} else if (roadFlag == 4) {
			for (int i = 0; i < tempLine.size(); i++) {
				if (i == 0 || i == 2) {
					tempDist[i] = Double.MAX_VALUE;

				} else {
					findIntersection(tempLine, tempDist, i);
				}
			}

		} else if (roadFlag == 5) {
			for (int i = 0; i < tempLine.size(); i++) {
				if (i==0 || i == 1 || i == 2 || i==3) {
					tempDist[i] = Double.MAX_VALUE;

				} else {
					findIntersection(tempLine, tempDist, i);
				}
			}

		}

		double smallestDist = Double.MAX_VALUE;
		int smallestId = 0;
		for (int i = 1; i < tempDist.length; i++) {
			if (tempDist[i] < smallestDist) {
				smallestDist = tempDist[i];
				smallestId = i;
			}
		}
//		if(smallestId == 0){
//			System.out.println("find wrong line :"+smallestId );
//			for(int i=1;i<tempDist.length;i++){
//				System.out.println(tempDist[i]);
//			}
//
//		}
		System.out.println(this.name);
		for(int i=0;i< tempDist.length;i++){
			System.out.println(tempDist[i]);
		}
		System.out.println(" ");
		
		closestLineId = smallestId;
		closestLineDist = smallestDist;

	}

	public String getDist() {
		String distInfo = "Line" + (closestLineId + 1) + " : " + (Math.round(closestLineDist * 1000.0) / 1000.0);
		return distInfo;
	}

}
