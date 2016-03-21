package cihw1;

import java.util.Currency;

import cihw1.Canvas;
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
	private Label line3Dist = new Label("Line 3 distance");

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
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("A battle with Computational Intelligence.");

		BorderPane ciPane = new BorderPane();
		VBox infoBox = new VBox(10);
		Button start = new Button("Start");


		canvasPane = new Canvas();
		car = new Car();

		ciPane.setRight(canvasPane);
		ciPane.setLeft(infoBox);

		canvasPane.getChildren().add(car);
		
		roadRec roadRec1 =  new roadRec(12*ratio,22*ratio);
		roadRec1.setStroke(Color.BLACK);
		roadRec1.setFill(Color.GAINSBORO);
		roadRec1.setDisable(true);
		roadRec1.setLayoutX(24*ratio);
		roadRec1.setLayoutY(30*ratio);
		roadRec1.setVisible(false);
		canvasPane.getChildren().add(roadRec1);
		
		roadRec roadRec2 =  new roadRec(12*ratio,12*ratio);
		roadRec2.setStroke(Color.BLACK);
		roadRec2.setFill(Color.GAINSBORO);
		roadRec2.setDisable(true);
		roadRec2.setLayoutX(36*ratio);
		roadRec2.setLayoutY(30*ratio);
		roadRec2.setVisible(false);
		canvasPane.getChildren().add(roadRec2);

		roadRec roadRec3 =  new roadRec(12*ratio,27*ratio);
		roadRec3.setStroke(Color.BLACK);
		roadRec3.setFill(Color.GAINSBORO);
		roadRec3.setDisable(true);
		roadRec3.setLayoutX(48*ratio);
		roadRec3.setLayoutY(15*ratio);
		roadRec3.setVisible(false);
		canvasPane.getChildren().add(roadRec3);

		
		infoBox.setPadding(new Insets(15, 15, 15, 15));
		infoBox.getChildren().addAll(start, line1Dist, line2Dist, line3Dist);

		Line sensor1 = new Line();
		sensor1.setStartX(car.getCenterX());
		sensor1.setStartY(car.getCenterY());
		sensor1.endXProperty().bind(car.centerXProperty());
		sensor1.endYProperty().bind(car.centerYProperty().subtract(3*ratio));
		sensor1.setStroke(Color.RED);

		Line sensor2 = new Line();
		sensor2.startXProperty().bind(car.centerXProperty());
		sensor2.startYProperty().bind(car.centerYProperty());
		sensor2.endXProperty().bind(car.centerXProperty().add((3*ratio)*Math.cos(Math.toRadians(45))));
		sensor2.endYProperty().bind(car.centerYProperty().subtract((3*ratio)*Math.cos(Math.toRadians(45))));
		sensor2.setStroke(Color.BLUE);

		Line sensor3 = new Line();
		sensor3.startXProperty().bind(car.centerXProperty());
		sensor3.startYProperty().bind(car.centerYProperty());
		sensor3.endXProperty().bind(car.centerXProperty().subtract((3*ratio)*Math.cos(Math.toRadians(45))));
		sensor3.endYProperty().bind(car.centerYProperty().subtract((3*ratio)*Math.cos(Math.toRadians(45))));
		sensor3.setStroke(Color.GREEN);

		canvasPane.getChildren().addAll(sensor1, sensor2, sensor3);

		System.out.println(Thread.currentThread());

		
		System.out.println(car.getCenterX());
		canvasPane.setOnMousePressed(e ->{
			double pressX = e.getX();
			double pressY = e.getY();

			Point2D[] rec1Points = roadRec1.getBoundary();
            Point2D[] rec2Points = roadRec2.getBoundary();
            Point2D[] rec3Points = roadRec3.getBoundary();

            
            if(pressX >rec1Points[0].getX() && pressX < rec1Points[1].getX() && pressY > rec1Points[0].getY() && pressY < rec1Points[1].getY()){
            	System.out.println("in Rect 1");
            }
            else if(pressX >rec2Points[0].getX() && pressX < rec2Points[1].getX() && pressY > rec2Points[0].getY() && pressY < rec2Points[1].getY()){
            	System.out.println("in Rect 2");
            }
            else if(pressX >rec3Points[0].getX() && pressX < rec3Points[1].getX() && pressY > rec3Points[0].getY() && pressY < rec3Points[1].getY()){
            	System.out.println("in Rect 3");
            }
            else{
            	System.out.println("*******");
            }
		});
		
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

									line1Dist.setText("dist : "+car.getCenterY());
									line2Dist.setText("dist : "+car.getCenterY());
									line3Dist.setText("dist : "+car.getCenterY());
									System.out.println(car.getRotate());

								}
							});
							
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						if (count > 30) {
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
