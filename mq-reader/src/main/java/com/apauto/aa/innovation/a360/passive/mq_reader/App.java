package com.apauto.aa.innovation.a360.passive.mq_reader;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        new App().run();
    }
    
    private void run()
    {
    	AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml");
    }
}
