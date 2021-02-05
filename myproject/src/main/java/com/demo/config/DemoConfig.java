package com.demo.config;

import com.demo.controller.IndexController;
import com.demo.controller.LoginController;
import com.demo.controller.RegisterController;
import com.demo.controller.UserController;
import com.demo.interceptor.KuayuInterceptor;
import com.demo.model._MappingKit;
import com.jfinal.config.*;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.server.undertow.UndertowServer;
import com.jfinal.template.Engine;

/**
 * 本 demo 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法
 * 详见 JFinal 俱乐部: http://jfinal.com/club
 * 
 * API 引导式配置
 */
public class DemoConfig extends JFinalConfig {
	
	static Prop p;
	
	/**
	 * 启动入口，运行此 main 方法可以启动项目，此 main 方法可以放置在任意的 Class 类定义中，不一定要放于此
	 */
	public static void main(String[] args) {
		UndertowServer.start(DemoConfig.class);
	}
	
	/**
	 * PropKit.useFirstFound(...) 使用参数中从左到右最先被找到的配置文件
	 * 从左到右依次去找配置，找到则立即加载并立即返回，后续配置将被忽略
	 */
	static void loadConfig() {
		if (p == null) {
			p = PropKit.useFirstFound("demo-config-pro.txt", "demo-config-dev.txt");
		}
	}
	
	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		loadConfig();
		
		me.setDevMode(p.getBoolean("devMode", false));
		
		/**
		 * 支持 Controller、Interceptor、Validator 之中使用 @Inject 注入业务层，并且自动实现 AOP
		 * 注入动作支持任意深度并自动处理循环注入
		 */
		me.setInjectDependency(true);
		
		// 配置对超类中的属性进行注入
		me.setInjectSuperClass(true);

		
		me.setBaseDownloadPath("download");
		//me.setBaseDownloadPath("D:/download");
		me.setBaseUploadPath("upload");
		me.setMaxPostSize(10485760); //10*1024*1024=10M
	}
	
	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		
		me.add("/", IndexController.class);	// 第三个参数为该Controller的视图存放路径
		me.add("/user", UserController.class);// 第三个参数省略时默认与第一个参数值相同
		me.add("/login", LoginController.class);
		me.add("/register", RegisterController.class);
	}
	
	public void configEngine(Engine me) {
		//me.addSharedFunction("/common/_layout.html");
		//me.addSharedFunction("/common/_paginate.html");
	}
	
	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		// 配置 druid 数据库连接池插件
		DruidPlugin druidPlugin = new DruidPlugin(p.get("jdbcUrl"), p.get("user"), p.get("password"));
		me.add(druidPlugin);
		
		// 配置ActiveRecord数据库访问插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		arp.addSqlTemplate("all.sql");
		// 在控制台打印所有的数据库操作语句
		arp.setShowSql(true);
		// 所有映射在 MappingKit 中自动化搞定
		_MappingKit.mapping(arp);
		me.add(arp);
	}
	
	public static DruidPlugin createDruidPlugin() {
		loadConfig();
		
		return new DruidPlugin(p.get("jdbcUrl"), p.get("user"), p.get("password"));
	}
	
	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		me.addGlobalActionInterceptor( new KuayuInterceptor());//跨域
		
	}
	
	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
		
	}
}
