package com.yangwan.utils;


public class MyUtils {

	/**
	 * ���ݷ������ͽӿ����γ�Ψһ��uniqueCode
	 * @param serviceName  ������
	 * @param interfaceName  �ӿ���
	 * @return
	 */
	public static Integer generateUniqueCode(String value){
		return value.hashCode();
	}
	
}
