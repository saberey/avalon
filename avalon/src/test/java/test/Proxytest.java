package test;

import java.lang.reflect.Proxy;

import com.avalon.ms.service.Sa;
import com.avalon.ms.service.handler.SaInvocation;
import com.avalon.ms.service.impl.Saa;

public class Proxytest {

	public static void main(String[] args) {
		
		Saa sa = new Saa();
		SaInvocation saInvocation = new SaInvocation(sa);
		Sa sa1 = (Sa) Proxy.newProxyInstance(Proxytest.class.getClassLoader(),new Class[]{Sa.class}, saInvocation);
		sa1.sasa(1);
	}
}
