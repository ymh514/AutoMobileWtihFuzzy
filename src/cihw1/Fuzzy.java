package cihw1;

import java.util.ArrayList;

public class Fuzzy {
	protected Sensor sensor1;
	protected Sensor sensor2;
	protected Sensor sensor3;
	protected double angle;
	protected double turnAngle;
	protected ArrayList <Sensor> sensorList = new ArrayList<Sensor>();
	public Fuzzy(Sensor sensor1,Sensor sensor2,Sensor sensor3,Double angle){
		this.sensor1 = sensor1;
		this.sensor2 = sensor2;
		this.sensor3 = sensor3;
		sensorList.add(sensor1);
		sensorList.add(sensor2);
		sensorList.add(sensor3);
		
		this.angle = angle;
		
	}
	public double getTurnAngle(){
		double tempDist = Double.MAX_VALUE;
		int smallestId = 0;
		for(int i=0;i<sensorList.size();i++){
			if(sensorList.get(i).closestLineDist<tempDist){
				smallestId = i;
			}
		}
		
		switch (smallestId){
		case 0:
			if(sensorList.get(smallestId).closestLineDist<100){
				turnAngle = -40;
			}
			break;
		case 1:
			if(sensorList.get(smallestId).closestLineDist<40){
				turnAngle = -10;
			}
			break;
		case 2:
			if(sensorList.get(smallestId).closestLineDist<40){
				turnAngle = 10;
			}
			break;
		}		
		return this.turnAngle;
	}
}
