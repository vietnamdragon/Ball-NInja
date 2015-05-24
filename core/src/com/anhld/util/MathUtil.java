package com.anhld.util;

public class MathUtil {
	
	public static double getDistance(int x1,int y1,int x2,int y2){
		return Math.sqrt(Math.pow(Math.abs(x2 -x1), 2) + Math.pow(Math.abs(y2 -y1), 2));
	}
	
	public static float convertR(int r){
		return (float)r / 255;
	}
	
	public static float convertG(int g){
		return (float)g / 255;
	}
	
	public static float convertB(int b){
		return (float)b / 255;
	}
}
