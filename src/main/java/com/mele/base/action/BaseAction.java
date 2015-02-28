package com.mele.base.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.mele.common.PageInfo;
import com.mele.common.ReidsConfig;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class BaseAction extends ActionSupport implements Preparable {

	private static final long serialVersionUID = 1L;

	protected Logger log = Logger.getLogger(getClass().getName());

	/**
	 * 当前url
	 */
	public static String CURRENT_URI_NAME = "currentURI";

	private PageInfo pageInfo = new PageInfo();
	protected int pageNo = ReidsConfig.DEFAULT_PAGE_NO;
	protected int pageSize =ReidsConfig.DEFAULT_PAGE_SIZE;
	protected int pageFlag = 0;// 0分页，1不分页。
	private String currentURI;
	private static final String CHARACTER_ENCODING = "UTF-8";

	protected String token = "userToken";

	public String getCookieValue(String name) {
		Cookie[] cookies = getRequest().getCookies();

		if (cookies == null)
			return null;
		for (Cookie ck : cookies) {
			if (StringUtils.equalsIgnoreCase(name, ck.getName()))
				return ck.getValue();
		}
		return null;
	}
	
	/**
	 * 提前执行的方法
	 * 
	 * @throws Exception
	 */
	public void prepare() throws Exception {
		currentURI = ServletActionContext.getRequest().getRequestURI();
	}

	public String getCurrentURI() {
		return currentURI;
	}

	public void setCurrentURI(String currentURI) {
		this.currentURI = currentURI;
	}

	/**
	 * 得到httprequest
	 * 
	 * @return HttpServletRequest
	 */
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 得到httpresponse
	 * 
	 * @return HttpServletResponse
	 */
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * 得到httpSession
	 * 
	 * @return HttpServletResponse
	 */
	public HttpSession getSession(boolean flag) {
		return ServletActionContext.getRequest().getSession(flag);
	}
	protected String getInputStream(HttpServletRequest request) throws IOException{

       //获取HTTP请求的输入流
       //已HTTP请求输入流建立一个BufferedReader对象
       BufferedReader br =  request.getReader();
       String buffer = null;
       StringBuffer buff = new StringBuffer();
       while ((buffer = br.readLine()) != null) {
             buff.append(buffer+"\n");
       }
       br.close();

       log.info("ContentType="+request.getContentType()+", "+request.getServletPath()+":"+buff.toString());
       return buff.toString().trim();
}
	/**
	 * 发送ajax响应给客户端
	 * 
	 * @param str
	 */
	public void sendAjax(String str) {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setCharacterEncoding(CHARACTER_ENCODING);
			response.getWriter().write(str + "");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendXmlAjax(String str) {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setCharacterEncoding(CHARACTER_ENCODING);
			response.getWriter().write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendTextAjax(String str) {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setCharacterEncoding(CHARACTER_ENCODING);
			response.getWriter().write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the pageInfo
	 */
	public PageInfo getPageInfo() {
		return pageInfo;
	}

	/**
	 * @param pageInfo
	 *            the pageInfo to set
	 */
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	/**
	 * @return the page
	 */
	public int getPagepNO() {
		return pageNo;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * @return the rows
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param rows
	 *            the rows to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the pageFlag
	 */
	public int getPageFlag() {
		return pageFlag;
	}

	/**
	 * @param pageFlag
	 *            the pageFlag to set
	 */
	public void setPageFlag(int pageFlag) {
		this.pageFlag = pageFlag;
	}

	public void downLoad(String pkgZipCollectFileName, HttpServletResponse response) throws IOException {

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		OutputStream os = null;
		InputStream is = null;
		try {
			File file = new File(pkgZipCollectFileName);
			if (!file.exists()) {
				System.out.println("文件不存在");
			}

			is = new FileInputStream(file);
			bis = new BufferedInputStream(is);
			os = response.getOutputStream();
			bos = new BufferedOutputStream(os);

			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("application/x-msdownload;charset=utf-8");
			response.setHeader("Content-disposition", "attachment;filename=" + file.getName());
			// + URLEncoder.encode(file.getName(), "GBK"));

			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = bis.read(buffer, 0, 8192)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}

			bos.flush();
			is.close();
			bis.close();
			os.close();
			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
