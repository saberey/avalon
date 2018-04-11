package test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class LongTest {

	public static void main(String[] args) {
		
		//long ln = 123_000_111123L;
		//System.out.println(ln);
		Double d1 = 1121282.28;
		DecimalFormat decimalFormat = new DecimalFormat("########.00");
		String tmp = decimalFormat.format(d1);
		Long l2 = Long.parseLong(tmp);
		System.out.println(l2);
		BigDecimal bigDecimal = new BigDecimal(d1);
		System.out.println(bigDecimal.doubleValue());
		Long l1 = d1.longValue();
		//Long.parseLong("121282028E7");
		
		System.out.println(l1);
	    int UNITS = (int) Math.pow(10, 4);
	    System.out.println(UNITS);
	}
}
