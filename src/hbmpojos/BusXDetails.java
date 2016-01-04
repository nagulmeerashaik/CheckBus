package hbmpojos;

import java.sql.Time;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class BusXDetails {
	int ServiceNo = 0;
	String Name = null;
	String type = null;
	String arr_time = null;
	String dep_time = null;
	String duration_hrs = null;
	int fare = 0;
	int avbl_seats = 0;
	public int getServiceNo() {
		return ServiceNo;
	}
	public void setServiceNo(int serviceNo) {
		ServiceNo = serviceNo;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getArr_time() {
		return arr_time;
	}
	public void setArr_time(String arr_time) {
		this.arr_time = arr_time;
	}
	public String getDep_time() {
		return dep_time;
	}
	public void setDep_time(String dep_time) {
		this.dep_time = dep_time;
	}
	public String getDuration_hrs() {
		return duration_hrs;
	}
	public void setDuration_hrs(String duration_hrs) {
		this.duration_hrs = duration_hrs;
	}
	public int getFare() {
		return fare;
	}
	public void setFare(int fare) {
		this.fare = fare;
	}
	public int getAvbl_seats() {
		return avbl_seats;
	}
	public void setAvbl_seats(int avbl_seats) {
		this.avbl_seats = avbl_seats;
	}
	public String toString() {
		String s= this.getServiceNo()+"==>"+this.getName()+":"+this.getFare()+":"+this.getAvbl_seats();
		return s;
		
	}
}
