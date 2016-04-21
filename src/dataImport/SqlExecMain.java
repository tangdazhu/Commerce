package dataImport;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.CommodistTemplate;

public class SqlExecMain {
	
	public static void main(String[] args){	
		
		ApplicationContext context = 
	             new ClassPathXmlApplicationContext("./test/Beans.xml");

		CommodistTemplate template = 
	      (CommodistTemplate)context.getBean("CommodistTemplate");
		
		int[] number=template.batchInsert(new Integer[100]);
		
		System.out.println("insert Commodist test data of "+ number.length);
		
		
	}
}
