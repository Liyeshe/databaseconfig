package com.yangwan.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;


public class DataBaseUtils {

	/**
	 * ���ݿ��ַ
	 */
	private static final String databasePath = "jdbc:mysql://172.25.103.231:3306/autoformat";  
	
    private static final String driverPath = "com.mysql.jdbc.Driver";  
    
    /**
     * ���ݿ��û���
     */
    private String userName = "root";  
    
    /**
     * ���ݿ�����
     */
    private String password = "beyonddream";  
    
    /**
     * ���ݿ�����
     */
    private Connection connection = null;  
    
    public DataBaseUtils(){  
        try {  
            Class.forName(driverPath);  
        } catch (ClassNotFoundException e) {  
            e.printStackTrace();  
        }  
        System.out.println("�������ݿ������ɹ�");  
    }  
    
    public boolean getConnectionToSQL(){  
        try {  
        	connection = DriverManager.getConnection(databasePath,userName,password);  
             return true;  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return false;  
    }
    
    public Connection getConnection(){
    	return this.connection;
    }
    
    @Test
    public void testDataBase(){
    	DataBaseUtils dataBaseUtils = new DataBaseUtils();
    	if(dataBaseUtils.getConnectionToSQL()){
    		System.out.println("������ݿ�����");
    	}
    }
}
