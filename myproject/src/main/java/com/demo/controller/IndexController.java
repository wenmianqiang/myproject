package com.demo.controller;

import com.demo.utils.RespData;
import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * IndexController 指向系统访问首页
 * @author jbolt.cn
 * @email 909854136@qq.com
 * @date 2018年11月4日 下午9:02:52
 */
public class IndexController extends Controller {
	/**
	 * 首页Action
	 */
	public void index(String account,String password) {
		renderText(account+password);
		render("index.html");
	}

	//文件下载
	public  void download(){
		//绝对路径
		//String file = "D:/my-project/share/files/jfinal-all.zip";
		//renderFile(new File(file));
		//相对路径
		//renderFile("file.txt");
		//renderFile("老文件名.txt", "新文件名.txt");

		File file = new File("C:\\Users\\admin\\IdeaProjects\\myproject\\src\\main\\webapp\\download\\file.txt");
		if (file.exists()) { //如果文件存在
			renderFile(file);
		} else {
			renderJson("{\"file\":文件不存在}");
		}

	}


	//文件上传
	public void upload(){
		RespData resp = new RespData();
		resp.setTime(System.currentTimeMillis());
		try {
			UploadFile file = getFile();
			File delFile = new File(file.getUploadPath()+"\\"+file.getFileName());
			resp.setCode(0);
			resp.setMsg("文件上传成功！");
			Map<String ,String> map = new HashMap<String, String>();
			map.put("filePath", delFile.getPath());
			map.put("fileSize", delFile.length()/1024+"");
			resp.setData(map);
		} catch (Exception e) {
			e.printStackTrace();
			resp.setCode(1);
			resp.setMsg("文件上传失败！");
		}
		renderJson(resp);
	}
}