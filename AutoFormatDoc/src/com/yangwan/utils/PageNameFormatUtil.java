package com.yangwan.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import com.yangwan.bean.AutoFormatBean;

/**
 * page ��Ŀ��������
 * @author yangwan
 * �Զ������ӿڶ�Ӧ��hmtl�ļ� js�ļ�
 */
public class PageNameFormatUtil {

	/**
	 * ǰ����Ŀ·������ȷ��**page��
	 * ����C:\Users\yangwan\WebstormProjects\qmc-mgr_cash-page
	 */
	private String pageWorkPath;

	/**
	 * �洢�ӿ���Ϣ���б�
	 */
	private List<AutoFormatBean> autoFormatList;
	
	public PageNameFormatUtil(List<AutoFormatBean> autoFormatList, String pageWorkPath){
		this.autoFormatList = autoFormatList;
		this.pageWorkPath = pageWorkPath;
	}
	
	
}
