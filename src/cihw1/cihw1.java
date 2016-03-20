package cihw1;

import java.util.Currency;

import cihw1.Canvas;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
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
import javafx.stage.Stage;
import javafx.util.Duration;

public class cihw1 extends Application {

	static int ratio = 10;
	static int count = 0;
	private Canvas canvasPane;
	private Car car;
	private String dst1 = "";
	private String dst2 = "";
	private String dst3 = "";

	private Label line1Dist = new Label("Line 1 distance");
	private Label line2Dist = new Label("Line 2 distance");
	private Label line3Dist = new Label("Line 3 distance");

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("A battle with Computational Intelligence.");

		BorderPane ciPane = new BorderPane();
		VBox infoBox = new VBox(10);
		Button start = new Button("Start");
		// Label line1Dist = new Label("Line 1 distance");
		// Label line2Dist = new Label("Line 2 distance");
		// Label line3Dist = new Label("Line 3 distance");

		infoBox.setPadding(new Insets(15, 15, 15, 15));
		infoBox.getChildren().addAll(start, line1Dist, line2Dist, line3Dist);

		canvasPane = new Canvas();
		car = new Car();

		ciPane.setRight(canvasPane);
		ciPane.setLeft(infoBox);

		canvasPane.getChildren().add(car);

		Line line1 = new Line();
		line1.setStartX(0);
		line1.setStartY(0);
		line1.endXProperty().bind(car.centerXProperty().subtract(ratio * 3));
		line1.endYProperty().bind(car.centerYProperty());

		Line line2 = new Line();
		line2.setStartX(0);
		line2.setStartY(0);
		line2.endXProperty().bind(car.centerXProperty());
		line2.endYProperty().bind(car.centerYProperty().subtract(ratio * 3));

		Line line3 = new Line();
		line3.setStartX(0);
		line3.setStartY(0);
		line3.endXProperty().bind(car.centerXProperty().add(ratio * 3));
		line3.endYProperty().bind(car.centerYProperty());

		canvasPane.getChildren().addAll(line1, line2, line3);

		System.out.println(Thread.currentThread());

		start.setOnMouseClicked(evnet -> {

			System.out.println(car.getCenterX());
			// while (count<10) {
			// new Thread() {
			// public void run() {
			// System.out.println(Thread.currentThread());
			// tuneCar(car);
			// try {
			// Thread.sleep(1000);
			// } catch (InterruptedException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			// }
			// }.start();
			// count++;
			// }
			new Thread() {
				public void run() {

					while (true) {
//						new Thread(task){		
//						}.start();

						System.out.println(Thread.currentThread());
						tuneCar(car);
						count++;
						
						try {
							System.out.println("sleep");
							Thread.sleep(250);
							
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									System.out.println("******************************************************");
									System.out.println(Thread.currentThread());
									System.out.println("******************************************************");
									drawPath(canvasPane, car);
									 line1Dist.setText("dist : "+car.getCenterX());
									 line2Dist.setText("dist : "+car.getCenterX());
									 line3Dist.setText("dist : "+car.getCenterX());

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

	public void drawPath(Canvas canvasPane, Circle car) {
		Circle path = new Circle();
		path.setCenterX(car.getCenterX());
		path.setCenterY(car.getCenterY());
		path.setRadius(3);
		path.setStroke(Color.BLACK);
		path.setFill(Color.BLACK);

		canvasPane.getChildren().add(path);
	}

	public void tuneCar(Circle car) {
		// car.setCenterX(car.getCenterX()-count * 1);
		car.setCenterY(car.getCenterY() - count * 1);
		// line1Dist.setText("dist : "+car.getCenterX());
		// line2Dist.setText("dist : "+car.getCenterX());
		// line3Dist.setText("dist : "+car.getCenterX());

	}

	public static void main(String[] args) {
		launch(args);
	}

}
