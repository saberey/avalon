package test;

import java.util.Calendar;

public class MathTest {

	
	public static void main(String[] args) {
		/*int div = 6;
		int max = 415;
		int divSize = max % div == 0 ? max/div : max/div+1; 
		System.out.println(divSize);
		int tmpStr=0,tmpEnd=0;
		for(int i=0 ;i<divSize;i++){
			tmpStr = i*div;
			tmpEnd = i*div+div-1 > max-1 ? max-1 : i*div+div-1;
			for(int j=tmpStr;j<=tmpEnd;j++){
				System.out.println(j);
			}
		}*/
		//int unit = (int) Math.pow(10, 2);
		//System.out.println(unit);
		//Calendar calendar = Calendar.getInstance();
		//calendar.set(field, value);
		//System.out.println(calendar.getTime());
		//calendar.add(Calendar.DAY_OF_MONTH, -12);
		//System.out.println(calendar.getTime());
		
		for (int i= 1;i<=24;i++){
			if(i>9 ||i<5){
				System.out.println(i);
			}
		}
	}
}
