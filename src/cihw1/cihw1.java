package cihw1;

import java.util.Currency;

import cihw1.Canvas;
import cihw1.Sensor;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class cihw1 extends Application {

	static int ratio = 10;
	static int count = 0;
	private Canvas canvasPane;
	private Car car;

	private Label line1Dist = new Label("Line 1 distance");
	private Label line2Dist = new Label("Line 2 distance");
	/**
	 * 
	 */
	private Label line3Dist = new Label("Line 3 distance");
	private Label angleInfo = new Label("");
	private int roadFlag = 0;
	
	public class roadRec extends Rectangle{
		private double width;
		private double height;
		
		public roadRec(double width,double height){
			this.setWidth(width);
			this.setHeight(height);
			this.width = width;
			this.height = height;
		}
	    public Point2D[] getBoundary() {
	        Point2D[] points = new Point2D[2];
	        points[0] = new Point2D(this.getLayoutX(), this.getLayoutY());
	        points[1] = new Point2D(this.getLayoutX() + width, this.getLayoutY() + height);
	        return points;
	    }

	}
	
	private roadRec roadRec1;
	private roadRec roadRec2;
	private roadRec roadRec3;
	private roadRec roadRec4;
	private roadRec roadRec5;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("A battle with Computational Intelligence.");

		BorderPane ciPane = new BorderPane();
		VBox infoBox = new VBox(10);
		Button start = new Button("Start");


		canvasPane = new Canvas();
		car = new Car(this.canvasPane);

		ciPane.setRight(canvasPane);
		ciPane.setLeft(infoBox);

		canvasPane.getChildren().add(car);
		
		
		infoBox.setPadding(new Insets(15, 15, 15, 15));
		infoBox.getChildren().addAll(start, line1Dist, line2Dist, line3Dist,angleInfo);

		Line sensorLine1 = new Line();
		sensorLine1.setStartX(car.getCenterX());
		sensorLine1.setStartY(car.getCenterY());
		sensorLine1.setEndX(car.getCenterX());
		sensorLine1.setEndY(car.getCenterY());
		sensorLine1.startXProperty().bind(car.centerXProperty());
		sensorLine1.startYProperty().bind(car.centerYProperty());
		sensorLine1.setStroke(Color.RED);

		Line sensorLine2 = new Line();
		sensorLine2.setStartX(car.getCenterX());
		sensorLine2.setStartY(car.getCenterY());
		sensorLine2.setEndX(car.getCenterX());
		sensorLine2.setEndY(car.getCenterY());
		sensorLine2.startXProperty().bind(car.centerXProperty());
		sensorLine2.startYProperty().bind(car.centerYProperty());
		sensorLine2.setStroke(Color.BLUE);

		Line sensorLine3 = new Line();
		sensorLine3.setStartX(car.getCenterX());
		sensorLine3.setStartY(car.getCenterY());
		sensorLine3.setEndX(car.getCenterX());
		sensorLine3.setEndY(car.getCenterY());
		sensorLine3.startXProperty().bind(car.centerXProperty());
		sensorLine3.startYProperty().bind(car.centerYProperty());
		sensorLine3.setStroke(Color.GREEN);

		canvasPane.getChildren().addAll(sensorLine1, sensorLine2, sensorLine3);

		System.out.println(Thread.currentThread());

		
		start.setOnMouseClicked(evnet -> {

			System.out.println(car.getCenterX());
			
			new Thread() {
				public void run() {
					while (true) {
						// open thread do something
						
//						System.out.println(Thread.currentThread());

						count++;
						
						try {
//							System.out.println("sleep");
							Thread.sleep(100);
							
							Platform.runLater(new Runnable() {
								//GUI update by javafx thread
								@Override
								public void run() {
									
//									printCurrentThread();
									car.tuneCar(canvasPane);
									car.sensor1.calDistance(canvasPane,roadFlag);
									car.sensor2.calDistance(canvasPane,roadFlag);
									car.sensor3.calDistance(canvasPane,roadFlag);
									
									line1Dist.setText(car.sensor1.getDist());
									line2Dist.setText(car.sensor2.getDist());
									line3Dist.setText(car.sensor3.getDist());
									
									
									angleInfo.setText("angle :"+Math.round(car.angle*1000.0)/1000.0);
									
									
									int line1c = car.sensor1.closestLineId;
									int line2c = car.sensor2.closestLineId;
									int line3c = car.sensor3.closestLineId;

									// tune sensor lines for debug angle
//									sensorLine1.setEndX(car.sensor1.getX());
//									sensorLine1.setEndY(car.sensor1.getY());
//									sensorLine2.setEndX(car.sensor2.getX());
//									sensorLine2.setEndY(car.sensor2.getY());
//									sensorLine3.setEndX(car.sensor3.getX());
//									sensorLine3.setEndY(car.sensor3.getY());

									sensorLine1.setEndX(car.sensor1.lineIntersection[line1c].getX());
									sensorLine1.setEndY(car.sensor1.lineIntersection[line1c].getY());
									sensorLine2.setEndX(car.sensor2.lineIntersection[line2c].getX());
									sensorLine2.setEndY(car.sensor2.lineIntersection[line2c].getY());
									sensorLine3.setEndX(car.sensor3.lineIntersection[line3c].getX());
									sensorLine3.setEndY(car.sensor3.lineIntersection[line3c].getY());

//									sensorLine2.setEndX(car.sensor2.lineIntersection[line2c].getX()-(3*ratio*Math.cos(Math.toRadians(45))));
//									sensorLine2.setEndY(car.sensor2.lineIntersection[line2c].getY()-(3*ratio*Math.cos(Math.toRadians(45))));
//									sensorLine3.setEndX(car.sensor3.lineIntersection[line3c].getX()+(3*ratio*Math.cos(Math.toRadians(45))));
//									sensorLine3.setEndY(car.sensor3.lineIntersection[line3c].getY()-(3*ratio*Math.cos(Math.toRadians(45))));

								}
							});
							
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						if (count > 55) {
							System.out.println("break loop");
							break;
						}
					}
				}
			}.start();
			System.out.println("action done .");
		});
		
		
		Scene primaryScene = new Scene(ciPane);
		primaryStage.setScene(primaryScene);
		primaryStage.setResizable(false);
		primaryStage.show();

	}
	public void printCurrentThread(){
		System.out.println("************************");
		System.out.println(Thread.currentThread());
		System.out.println("************************");

	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
