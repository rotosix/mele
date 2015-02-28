package com.mele.rmi;

import java.net.MalformedURLException;
import com.caucho.hessian.client.HessianProxyFactory;

public class RMIServiceHelper {

	private static HessianProxyFactory factory =   new HessianProxyFactory();

	@SuppressWarnings("unchecked")
	public static <T> T getRmiService(Class<T> serviceType, String serviceURI) {
		factory.setConnectTimeout(10000);

		try {
			return (T) factory.create(serviceType, serviceURI, serviceType.getClassLoader());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) { 

	}
}
