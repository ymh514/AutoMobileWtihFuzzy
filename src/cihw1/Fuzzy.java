package cihw1;

import java.util.ArrayList;

public class Fuzzy {
	protected Sensor sensor1;
	protected Sensor sensor2;
	protected Sensor sensor3;
	protected double angle;
	protected double turnAngle;
	protected ArrayList <Sensor> sensorList = new ArrayList<Sensor>();
	protected ArrayList <Integer> distance_descriptors = new ArrayList<Integer>(); //0:far 1:middle 2:near
	
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
			Integer des = Decision(sensor.closestLineDist);
			distance_descriptors.add(des);
		}
		
//		System.out.print(distance_descriptors.get(0));
//		System.out.print(distance_descriptors.get(1));
//		System.out.println(distance_descriptors.get(2));
//		System.out.println("================");
		
		this.turnAngle = Cal_Angle();
		distance_descriptors.clear();

		
		return this.turnAngle;
	}
	
	protected Integer Decision(double distance){
		
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
	
	protected double Cal_Angle(){
		double angle = 0;
		int sensor_number = distance_descriptors.size();
		int zero_count = 0;
		
		if(sensor_number == 0)
			return 0;
		
		for(int i=0; i < sensor_number; i++)
			if(distance_descriptors.get(i) == 0)
				zero_count++;
		
		if(zero_count == sensor_number)
			return 0;
		
		if(sensor_number == 3){
			if( (distance_descriptors.get(0) == 0) && (distance_descriptors.get(1) == 0) && (distance_descriptors.get(2) == 2) ){
				angle = 30;
				return angle;
			}
			
			if( (distance_descriptors.get(0) == 0) && (distance_descriptors.get(1) == 1) && (distance_descriptors.get(2) == 2) ){
				angle = -30;
				return angle;
			}
			
			if( (distance_descriptors.get(0) == 1) && (distance_descriptors.get(1) == 0) && (distance_descriptors.get(2) == 1) ){
				angle = 30;
				return angle;
			}
			
			if( (distance_descriptors.get(0) == 1) && (distance_descriptors.get(1) == 1) && (distance_descriptors.get(2) == 0) ){
				angle = -30;
				return angle;
			}
			
			if( (distance_descriptors.get(0) == 1) && (distance_descriptors.get(1) == 2) && (distance_descriptors.get(2) == 0) ){
				angle = -30;
				return angle;
			}
		}
		
		return angle;
	}
}
