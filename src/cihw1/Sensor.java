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

	public Sensor(double x, double y, double carX, double carY) {
		this.x = x;
		this.y = y;
		this.carX = carX;
		this.carY = carY;
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

	public void setCarX(double carX) {
		this.carX = carX;
	}

	public void setCarY(double carY) {
		this.carY = carY;
	}

	public double getCarX() {
		return this.carX;
	}

	public double getCarY() {
		return this.carY;
	}

	public void findIntersection(ArrayList<Line> tempLine, double[] tempDist, int i) {

		double startX = tempLine.get(i).getStartX();
		double startY = tempLine.get(i).getStartY();
		double endX = tempLine.get(i).getEndX();
		double endY = tempLine.get(i).getEndY();

		double intersectionX = ((this.carX - this.getX()) * (startX * endY - endX * startY)
				- (startX - endX) * (this.carX * this.getY() - this.getX() * this.carY))
				/ ((startX - endX) * (this.carY - this.getY()) - (this.carX - this.getX()) * (startY - endY));

		double intersectionY = ((this.carY - this.getY()) * (startX * endY - endX * startY)
				- (this.carX * this.getY() - this.getX() * this.carY) * (startY - endY))
				/ ((this.carY - this.getY()) * (startX - endX) - (this.carX - this.getX()) * (startY - endY));

		lineIntersection[i] = new Point2D(intersectionX, intersectionY);

		// Check intersection point in the line range or not
		if (checkLineRange(startX, endX, startY, endY, intersectionX, intersectionY) > 0) {
			double itsVectorX = this.getX() - this.carX;
			double itsVectorY = this.getY() - this.carY;
			double vectorX = intersectionX - this.getX();
			double vectorY = intersectionY - this.getY();

			// 計算向量內積，如為正，則代表此交點為sensor面對方向
			double ans = itsVectorX * vectorX + itsVectorY * vectorY;
			double a = this.x - intersectionX;
			double b = this.y - intersectionY;

			if (ans > 0) {
				tempDist[i] = Math.sqrt(a * a + b * b);
			} else {
				tempDist[i] = Double.MAX_VALUE;
			}

		} else {
			tempDist[i] = Double.MAX_VALUE;
		}

	}

	public int checkLineRange(double lineSX, double lineEX, double lineSY, double lineEY, double x, double y) {

		if (lineSX == lineEX) {
			if (y <= lineSY && y >= lineEY) {
				return 1;
			} else {
				return -1;
			}
		} else {
			if (x >= lineSX && x <= lineEX) {
				return 1;
			} else {
				return -1;
			}

		}
	}

	public void calDistance(Canvas canvasPane) {
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

		for (int i = 0; i < tempLine.size(); i++) {
			findIntersection(tempLine, tempDist, i);
		}

		double smallestDist = Double.MAX_VALUE;
		int smallestId = 0;
		for (int i = 1; i < tempDist.length; i++) {
			if (tempDist[i] < smallestDist) {
				smallestDist = tempDist[i];
				smallestId = i;
			}
		}

		closestLineId = smallestId;
		closestLineDist = smallestDist;

	}

	public String getDist() {
		String distInfo = "Line" + (closestLineId + 1) + " : " + (Math.round(closestLineDist * 1000.0) / 1000.0);
		return distInfo;
	}

}
