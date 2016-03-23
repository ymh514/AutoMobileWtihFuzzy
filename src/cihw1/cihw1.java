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
		
		setRoadRec();

		
		infoBox.setPadding(new Insets(15, 15, 15, 15));
		infoBox.getChildren().addAll(start, line1Dist, line2Dist, line3Dist);

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

		
		
		canvasPane.setOnMousePressed(e ->{
			// boundary setting
			double pressX = e.getX();
			double pressY = e.getY();

			Point2D[] rec1Points = roadRec1.getBoundary();
            Point2D[] rec2Points = roadRec2.getBoundary();
            Point2D[] rec3Points = roadRec3.getBoundary();
            Point2D[] rec4Points = roadRec4.getBoundary();
            Point2D[] rec5Points = roadRec5.getBoundary();
            

            
            if(pressX >rec1Points[0].getX() && pressX < rec1Points[1].getX() && pressY > rec1Points[0].getY() && pressY < rec1Points[1].getY()){
            	System.out.println("in Rect 1");
            }
            else if(pressX >rec2Points[0].getX() && pressX < rec2Points[1].getX() && pressY > rec2Points[0].getY() && pressY < rec2Points[1].getY()){
            	System.out.println("in Rect 2");
            }
            else if(pressX >rec3Points[0].getX() && pressX < rec3Points[1].getX() && pressY > rec3Points[0].getY() && pressY < rec3Points[1].getY()){
            	System.out.println("in Rect 3");
            }
            else if(pressX >rec4Points[0].getX() && pressX < rec4Points[1].getX() && pressY > rec4Points[0].getY() && pressY < rec4Points[1].getY()){
            	System.out.println("in Rect 4");
            }
            else if(pressX >rec5Points[0].getX() && pressX < rec5Points[1].getX() && pressY > rec5Points[0].getY() && pressY < rec5Points[1].getY()){
            	System.out.println("in Rect 5");
            }
            else{
            	System.out.println("Out of road ");
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
									
									// debug sensor position
//									System.out.println("sensor1 x :"+car.sensor1.getCordX() + "sensor1 y : "+car.sensor1.getCordY());
//									System.out.println("sensor2 x :"+car.sensor2.getCordX() + "sensor2 y : "+car.sensor2.getCordY());
//									System.out.println("sensor3 x :"+car.sensor3.getCordX() + "sensor3 y : "+car.sensor3.getCordY());

									// tune sensor lines
									sensorLine1.setEndX(car.sensor1.getX());
									sensorLine1.setEndY(car.sensor1.getY());
									sensorLine2.setEndX(car.sensor2.getX());
									sensorLine2.setEndY(car.sensor2.getY());
									sensorLine3.setEndX(car.sensor3.getX());
									sensorLine3.setEndY(car.sensor3.getY());

									// fake distance showing label
									line1Dist.setText(car.sensor1.getDist(canvasPane));
									line2Dist.setText(car.sensor2.getDist(canvasPane));
									line3Dist.setText(car.sensor3.getDist(canvasPane));

									printCarPosition(car.getCenterX(), car.getCenterY());
								}
							});
							
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						if (count > 60) {
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
	public void setRoadRec(){
		roadRec1 =  new roadRec(12*ratio,10*ratio);
		roadRec1.setStroke(Color.BLACK);
		roadRec1.setFill(Color.GAINSBORO);
		roadRec1.setDisable(true);
		roadRec1.setLayoutX(24*ratio);
		roadRec1.setLayoutY(42*ratio);
		roadRec1.setVisible(false);
		canvasPane.getChildren().add(roadRec1);
		
		roadRec2 =  new roadRec(12*ratio,12*ratio);
		roadRec2.setStroke(Color.BLACK);
		roadRec2.setFill(Color.GAINSBORO);
		roadRec2.setDisable(true);
		roadRec2.setLayoutX(24*ratio);
		roadRec2.setLayoutY(30*ratio);
		roadRec2.setVisible(false);
		canvasPane.getChildren().add(roadRec2);

		roadRec3 =  new roadRec(12*ratio,12*ratio);
		roadRec3.setStroke(Color.BLACK);
		roadRec3.setFill(Color.GAINSBORO);
		roadRec3.setDisable(true);
		roadRec3.setLayoutX(36*ratio);
		roadRec3.setLayoutY(30*ratio);
		roadRec3.setVisible(false);
		canvasPane.getChildren().add(roadRec3);

		roadRec4 =  new roadRec(12*ratio,12*ratio);
		roadRec4.setStroke(Color.BLACK);
		roadRec4.setFill(Color.GAINSBORO);
		roadRec4.setDisable(true);
		roadRec4.setLayoutX(48*ratio);
		roadRec4.setLayoutY(30*ratio);
		roadRec4.setVisible(false);
		canvasPane.getChildren().add(roadRec4);

		roadRec5 =  new roadRec(12*ratio,15*ratio);
		roadRec5.setStroke(Color.BLACK);
		roadRec5.setFill(Color.GAINSBORO);
		roadRec5.setDisable(true);
		roadRec5.setLayoutX(48*ratio);
		roadRec5.setLayoutY(15*ratio);
		roadRec5.setVisible(false);
		canvasPane.getChildren().add(roadRec5);
	}
	
	public void printCarPosition(double x,double y){
		// boundary setting
		double pressX = x;
		double pressY = y;

		Point2D[] rec1Points = roadRec1.getBoundary();
        Point2D[] rec2Points = roadRec2.getBoundary();
        Point2D[] rec3Points = roadRec3.getBoundary();
        Point2D[] rec4Points = roadRec4.getBoundary();
        Point2D[] rec5Points = roadRec5.getBoundary();
        

        
        if(pressX >rec1Points[0].getX() && pressX < rec1Points[1].getX() && pressY > rec1Points[0].getY() && pressY < rec1Points[1].getY()){
        	System.out.println("in Rect 1");
        }
        else if(pressX >rec2Points[0].getX() && pressX < rec2Points[1].getX() && pressY > rec2Points[0].getY() && pressY < rec2Points[1].getY()){
        	System.out.println("in Rect 2");
        }
        else if(pressX >rec3Points[0].getX() && pressX < rec3Points[1].getX() && pressY > rec3Points[0].getY() && pressY < rec3Points[1].getY()){
        	System.out.println("in Rect 3");
        }
        else if(pressX >rec4Points[0].getX() && pressX < rec4Points[1].getX() && pressY > rec4Points[0].getY() && pressY < rec4Points[1].getY()){
        	System.out.println("in Rect 4");
        }
        else if(pressX >rec5Points[0].getX() && pressX < rec5Points[1].getX() && pressY > rec5Points[0].getY() && pressY < rec5Points[1].getY()){
        	System.out.println("in Rect 5");
        }
        else{
        	System.out.println("Out of road ");
        }
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
