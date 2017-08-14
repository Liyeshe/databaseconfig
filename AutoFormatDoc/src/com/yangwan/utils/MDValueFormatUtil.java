package com.yangwan.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.yangwan.bean.AutoFormatBean;

/**
 * @author yangwan
 * �Զ�����MD5ֵ(��Ҫ��������Ϊbwaerpc-i.js�ļ�)
 */
public class MDValueFormatUtil{

	/**
	 * ���ݿ��������
	 */
	private DataBaseUtils dataBaseUtils;
	
	/**
	 * �洢�ӿ���Ϣ���б�
	 */
	private Map<String, AutoFormatBean> autoFormatMap;
	
	/**
	 * �����ļ�·��
	 */
	private String filePath;
	
	/**
	 * �ļ���
	 */
	private String fileName;
	
	public static final int ERROR_LINE = 0;
	
	public static final int FUNCTION_LINE = 1;
	
	public static final int VALUE_LINE = 2;
	
	public MDValueFormatUtil(Map<String, AutoFormatBean> autoFormatMap, String filePath) {
		this.autoFormatMap = autoFormatMap;
		this.filePath = filePath;
	}
	
	public MDValueFormatUtil(Map<String, AutoFormatBean> autoFormatMap) {
		this.autoFormatMap = autoFormatMap;
	}
	
	/**
	 * �����ļ�������
	 * @return
	 * @throws Exception
	 */
	private FileInputStream getFileOperateStream() throws Exception {
		FileInputStream fis = null;
		if(filePath != null){
			File file = new File(filePath);
			fis = new FileInputStream(file);
		}else{
			System.out.println("MDValueFormatUtil�ļ�·��Ϊ��");
		}
		return fis;
	}

	private int mathLine(String lineContent){
		if(lineContent.contains("function(params,resultCallback)")){
			return FUNCTION_LINE;
		}else if(lineContent.contains("new BWAERpc")){
			return VALUE_LINE;
		}else{
			return ERROR_LINE;
		}
	}
	
	/**
	 * �Զ��������� ��ȡMD5 moduleName interfaceName service
	 * @return
	 */
	public void autoAnalysisProcess(){
		if(autoFormatMap == null){
			autoFormatMap = new HashMap<String, AutoFormatBean>();
		}
		FileInputStream fis = null;
		BufferedReader br = null;
		try{
			fis = getFileOperateStream();
			InputStreamReader isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
		}catch(Exception e){  //�ļ��������쳣
			System.out.println("��ȡ�ļ���ȡ��ʧ��,�ļ�·�� :"+this.filePath);
			e.getStackTrace();
		}
		String lineContent = "";
		String pattern = "";
		Pattern r = null;
		Matcher m = null;
		AutoFormatBean tempBean = new AutoFormatBean();
		try{
			while((lineContent = br.readLine()) != null){
				switch(mathLine(lineContent)){
				case FUNCTION_LINE:
					 pattern = "BWAE(\\.)(\\w+)(\\.)(\\w+)(\\.)(\\w+)(\\D+)";
					 r = Pattern.compile(pattern);
					 m = r.matcher(lineContent);
					 if (m.find()) {
						 tempBean.setModuleName(m.group(2)); //����ģ����
						 tempBean.setServiceName(m.group(4));   //���÷�����
						 tempBean.setInterfaceName(m.group(6));  //���ýӿ���
					 }
					break;
				case VALUE_LINE:
					 pattern = "new(.*)(\\+\\'\\/)(\\w+)(\\'.*)";
					 r = Pattern.compile(pattern);
					 m = r.matcher(lineContent);
					 if (m.find()) {
						 tempBean.setMdValue(m.group(3)); //����md5ֵ
						 
						 autoFormatMap.put("", tempBean);
						 tempBean = new AutoFormatBean();
					 }
					 break;
				case ERROR_LINE:
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("�ļ���ȡ���� >>>>>>>>Utils:MDValueFormatUtil");
		}
	}
	
	public void saveToDataBase() throws Exception{
		if(dataBaseUtils != null){
			dataBaseUtils.getConnectionToSQL();
			Connection connection = dataBaseUtils.getConnection();
			
		}else{
			System.out.print(MDValueFormatUtil.class.toString() + "DataBaseUtilsΪ��");
		}
	}
	
	public DataBaseUtils getDataBaseUtils() {
		return dataBaseUtils;
	}

	public void setDataBaseUtils(DataBaseUtils dataBaseUtils) {
		this.dataBaseUtils = dataBaseUtils;
	}
	
	public Map<String, AutoFormatBean> getAutoFormatList() {
		return autoFormatMap;
	}

	public void setAutoFormatList(Map<String, AutoFormatBean> autoFormatMap) {
		this.autoFormatMap = autoFormatMap;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
