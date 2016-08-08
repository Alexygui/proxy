package com.imooc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.omg.CORBA.portable.InvokeHandler;

import com.imooc.proxy.Car;
import com.imooc.proxy.Moveable;

public class Test {

	/**
	 * 动态代理测试类，里面执行time记录的业务逻辑，外面执行log的业务逻辑
	 */
	public static void main(String[] args) {
		//new一个Movable接口的对象
		Car car = new Car();
		//将Car对象传入TimeHandler的构造器，并在invoke()方法中添加Car类以外的业务逻辑
		InvocationHandler h = new TimeHandler(car);
		//动态代理Car类，执行TimeHandler中的业务逻辑
		Moveable time = (Moveable)Proxy.newProxyInstance(Car.class.getClassLoader(),
												Car.class.getInterfaces(), h);
		//将time对象传入LogHandler的构造器，并在invoke()方法中添加time对象对应的类以外的业务逻辑
		InvocationHandler log = new LogHandler(time);
		//执行LogHandler中的业务逻辑
		Moveable m2 = (Moveable) Proxy.newProxyInstance(Car.class.getClassLoader(), 
												Car.class.getInterfaces(), log);
		m2.move();
	}
}
