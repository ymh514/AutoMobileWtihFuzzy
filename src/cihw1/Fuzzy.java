package cihw1;

import java.util.ArrayList;

public class Fuzzy {
	private double angle;
	private double turnAngle;
	private ArrayList <Sensor> sensorList = new ArrayList<Sensor>();
	private ArrayList <Integer> distanceDescriptors = new ArrayList<Integer>(); //0:far 1:middle 2:near
	
	public Fuzzy(Sensor sensor1,Sensor sensor2,Sensor sensor3,Double angle){
		sensorList.add(sensor1);
		sensorList.add(sensor2);
		sensorList.add(sensor3);
		
		this.angle = angle;
		
	}
	public double getTurnAngle(){
		
		for(int i=0; i < sensorList.size(); i++){
			Sensor sensor = sensorList.get(i);
			Integer des = decitionRank(sensor.getClosestLineDistance());
			distanceDescriptors.add(des);
		}
				
		this.turnAngle = calAngle();
		distanceDescriptors.clear();

		
		return this.turnAngle;
	}
	
	public Integer decitionRank(double distance){
		
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
	
	public double calAngle(){
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
		
//		int s1 = distanceDescriptors.get(0);
//		int s2 = distanceDescriptors.get(1);
//		int s3 = distanceDescriptors.get(2);
//		// Fuzzy rules
//		
//		if(sensorNumber == 3){
//			if(s1 == 0 && s2 == 0 && s3 == 0){
//				
//			}else if(s1 == 0 && s2 == 0 && s3 == 0){
//				return angle;
//
//				
//			}else if(s1 == 0 && s2 == 0 && s3 == 1){
//				return angle;
//
//				
//			}else if(s1 == 0 && s2 == 0 && s3 == 2){
//				return angle;
//
//				
//			}else if(s1 == 0 && s2 == 1 && s3 == 0){
//				return angle;
//
//				
//			}else if(s1 == 0 && s2 == 1 && s3 == 1){
//				return angle;
//
//				
//			}else if(s1 == 0 && s2 == 1 && s3 == 2){
//				return angle;
//
//			}else if(s1 == 0 && s2 == 2 && s3 == 0){
//				return angle;
//
//			}else if(s1 == 0 && s2 == 2 && s3 == 1){
//				return angle;
//
//			}else if(s1 == 0 && s2 == 2 && s3 == 2){
//				return angle;
//
//			}else if(s1 == 1 && s2 == 0 && s3 == 0){
//				return angle;
//
//			}else if(s1 == 1 && s2 == 0 && s3 == 1){
//				return angle;
//
//			}else if(s1 == 1 && s2 == 1 && s3 == 0){
//				return angle;
//
//			}else if(s1 == 1 && s2 == 1 && s3 == 1){
//				return angle;
//
//			}else if(s1 == 1 && s2 == 1 && s3 == 2){
//				return angle;
//
//			}else if(s1 == 1 && s2 == 2 && s3 == 0){
//				return angle;
//
//			}else if(s1 == 1 && s2 == 2 && s3 == 1){
//				return angle;
//
//			}else if(s1 == 1 && s2 == 2 && s3 == 2){
//				return angle;
//
//			}else if(s1 == 2 && s2 == 0 && s3 == 0){
//				return angle;
//
//			}else if(s1 == 2 && s2 == 0 && s3 == 1){
//				return angle;
//
//			}else if(s1 == 2 && s2 == 0 && s3 == 2){
//				return angle;
//
//			}else if(s1 == 2 && s2 == 1 && s3 == 0){
//				return angle;
//
//			}else if(s1 == 2 && s2 == 1 && s3 == 1){
//				return angle;
//
//			}else if(s1 == 2 && s2 == 1 && s3 == 2){
//				return angle;
//
//			}else if(s1 == 2 && s2 == 2 && s3 == 0){
//				return angle;
//
//			}else if(s1 == 2 && s2 == 2 && s3 == 1){
//				return angle;
//
//			}else if(s1 == 2 && s2 == 2 && s3 == 2){
//				return angle;
//
//			}
//		}
		
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
