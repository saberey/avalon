package test;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;

public class SystemTime {

	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		/*SystemTime sTime = new SystemTime();
		SystemTime sTime2 = new SystemTime();
		System.out.println("c"+System.currentTimeMillis());
		System.out.println("n"+System.nanoTime());
		System.out.println(sTime.hashCode());
		System.out.println(sTime2.hashCode());*/
		
		SystemTime st = new SystemTime();
		//st.checkAnnotation(SystemTime.class.getMethod("test", String.class));
		
		PrtTImpl prt = new PrtTImpl();
		InvocationHandler   invocationHandler = new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				// TODO Auto-generated method stub
				System.out.println(method.getName());
				//System.out.println(method.geta(PrintS.class));
				//System.out.println(method.getAnnotation(PrintS.class));
				List<Annotation> mAList = Arrays.asList(method.getAnnotations());
				System.out.println(mAList.size());
				Arrays.asList(method.getAnnotations())
				.forEach(annotation -> 
				{if(annotation.annotationType().equals(PrintS.class))System.out.println(method+" annotation prints");}
				//{System.out.println(annotation.toString());}
				);
       				st.checkAnnotation(method);
				return method.invoke(prt, args);
			}
		};
		PrtT prtProxy = (PrtT) Proxy.newProxyInstance(SystemTime.class.getClassLoader(),new Class[]{PrtT.class}, invocationHandler);
		prtProxy.print("hello world!");
		
		
		new Thread(() -> System.out.println("hello lambda")).start();;
	}
	
	@PrintS
	public void test(String arg){
		System.out.println("test:"+arg);
	}
	
	
	public void checkAnnotation(Method m){
		
		if(m.isAnnotationPresent(PrintS.class)){
			//PrintS ps = m.getAnnotation(PrintS.class);
			Class<?>[] classes = m.getParameterTypes();
			if(classes.length>0){
				for(Class<?> curCls : classes){
					System.out.println("parameterType:"+curCls);
				}
			}
			Parameter[] paras = m.getParameters();
			if(paras.length>0){
				for (Parameter parameter : paras) {
					System.out.println("parameter:"+parameter.getType());
				}
			}
		}
	}
}

