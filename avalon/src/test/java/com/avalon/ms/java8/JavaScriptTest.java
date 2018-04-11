package com.avalon.ms.java8;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *@description:TODO
 *@author saber
 *@date 2017年9月25日 下午5:32:02
 *@version
 */
public class JavaScriptTest {

	public static void main(String[] args) throws ScriptException {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");
		System.out.println(engine.getClass().getName());
		System.out.println("results:"+engine.eval("function f(){return 1;};f()+1;"));
	}
}
