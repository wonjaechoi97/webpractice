package main;

import chap07.ExeCalculator;
import chap07.ImpeCalculator;
import chap07.RecCalculator;

public class MainProxy {
	public static void main(String[] args) {
		ExeCalculator ttCal1 = new ExeCalculator(new ImpeCalculator());
		System.out.println(ttCal1.factorial(20));
		
		ExeCalculator ttCal2 = new ExeCalculator(new RecCalculator());
		System.out.println(ttCal2.factorial(20));
	}
}
