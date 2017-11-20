package com.hnpa.wss.servlet;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ServletProcessor1 {

	public void process(Request request, Response response) {
		String uri = request.getUri();
		String serlvetName = uri.substring(uri.lastIndexOf("/") + 1);
		System.out.println("=================serlvetName:"+serlvetName);
		URLClassLoader loader = null;
		try {
			URL[] urls = new URL[1];
			URLStreamHandler streamHandler = null;
			File classPath = new File(Response.WEB_ROOT);
			// getCanonicalPath返回此抽象路径名的规范路径名字符串。
			String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();

			urls[0] = new URL(null, repository, streamHandler);
			System.out.println(urls[0]);
			loader = new URLClassLoader(urls);
		} catch (IOException e) {
			System.out.println(e.toString());
		}

		Class<?> myClass = null;
		try {
			myClass = loader.loadClass(serlvetName);
		} catch (ClassNotFoundException e1) {
			System.out.println(e1.toString());
		}

		try {
			myClass = loader.loadClass(serlvetName);
		} catch (ClassNotFoundException e) {
			System.out.println(e.toString());
		}

		Servlet servlet = null;
		try {
			servlet = (Servlet) myClass.newInstance();
			servlet.service((ServletRequest) request, (ServletResponse) response);
		} catch (Exception e) {
			System.out.println(e.toString());
		} catch (Throwable e) {
			System.out.println(e.toString());
		}
	}
}