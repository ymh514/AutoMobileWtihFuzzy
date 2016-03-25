package cihw1;

import java.util.ArrayList;

public class Fuzzy {
	protected Sensor sensor1;
	protected Sensor sensor2;
	protected Sensor sensor3;
	protected double angle;
	protected double turnAngle;
	protected ArrayList <Sensor> sensorList = new ArrayList<Sensor>();
	protected ArrayList <Integer> distanceDescriptors = new ArrayList<Integer>(); //0:far 1:middle 2:near
	
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
		
		for(int i=0; i < sensorList.size(); i++){
			Sensor sensor = sensorList.get(i);
			Integer des = decitionRank(sensor.closestLineDist);
			distanceDescriptors.add(des);
		}
				
		this.turnAngle = calAngle();
		distanceDescriptors.clear();

		
		return this.turnAngle;
	}
	
	protected Integer decitionRank(double distance){
		
		Integer descriptor = -1;
		
		if( distance > 100)
		{
			descriptor = 0;
			return descriptor;
		}
		else if( (distance > 50)&&(distance <= 100) )
		{
			descriptor = 1;
			return descriptor;
		}
		else if(distance <= 50)
		{
			descriptor = 2;
			return descriptor;
		}
		
		return descriptor;
	}
	
	protected double calAngle(){
		double angle = 0;
		int sensorNumber = distanceDescriptors.size();
		int zeroCount = 0;
		
		// For prevent start , all sensor's distance is all zero : 0 0 0
		if(sensorNumber == 0)
			return 0;
		
		for(int i=0; i < sensorNumber; i++){
			if(distanceDescriptors.get(i) == 0){
				zeroCount++;
			}
		}
		
		if(zeroCount == sensorNumber){
			return 0;
		}
		
		// Fuzzy rules
		if(sensorNumber == 3){
			if( (distanceDescriptors.get(0) == 0) && (distanceDescriptors.get(1) == 0) && (distanceDescriptors.get(2) == 2) ){
				angle = 30;
				return angle;
			}
			
			if( (distanceDescriptors.get(0) == 0) && (distanceDescriptors.get(1) == 1) && (distanceDescriptors.get(2) == 2) ){
				angle = -30;
				return angle;
			}
			
			if( (distanceDescriptors.get(0) == 1) && (distanceDescriptors.get(1) == 0) && (distanceDescriptors.get(2) == 1) ){
				angle = 30;
				return angle;
			}
			
			if( (distanceDescriptors.get(0) == 1) && (distanceDescriptors.get(1) == 1) && (distanceDescriptors.get(2) == 0) ){
				angle = -30;
				return angle;
			}
			
			if( (distanceDescriptors.get(0) == 1) && (distanceDescriptors.get(1) == 2) && (distanceDescriptors.get(2) == 0) ){
				angle = -30;
				return angle;
			}
		}

		return angle;
	}
}
