package com.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.interceptor.UserInterceptor;
import com.demo.model.Department;
import com.demo.model.User;
import com.demo.service.DepartmentService;
import com.demo.service.UserService;
import com.demo.utils.RespData;
import com.jfinal.aop.Before;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@Before(UserInterceptor.class)
public class UserController extends Controller {

    @Inject
    UserService service;

    @Inject
    DepartmentService departmentService;

    public void index() {
        //render("/register.html");
    }

    //注册 & 添加用户
    //@Before(RegisterValidator.class)
    public void save(@Para("")User user) {
//        System.out.println(getPara("studentId"));
//        System.out.println(user.getStudentAccount());
//        HashMap map = new HashMap();
//        map.put("code", 0);
//        user.save();
//        renderJson(map);
        RespData resp = new RespData();
        resp.setTime(System.currentTimeMillis());
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        resp.setDate(currentTime);
        try {
            //String sql = Db.getSql("findUserByMail");
            String sql = "select * from user where student_mail = ?";
            Record result = Db.findFirst(sql, user.getStudentMail());
            if (result == null){
                user.save();
                //service.findById(user.get("id")).set("created_time", currentTime).update();
                resp.setCode(0);
                resp.setMsg("注册成功！");
                HashMap<String ,Object> map = new HashMap<String, Object>();
                map.put("用户信息", user.keep("student_account","student_name","student_id","student_tel","student_mail","class_name"));
                //1：model.keep(x, y, ...) 只保留指定的属性 2：model.remove(x, y, ...) 只去除指定的属性
                resp.setData(map);
            }
            else{
                resp.setCode(1);
                resp.setMsg("该邮箱已存在，注册失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setCode(1);
            resp.setMsg("注册失败！");
        }
        renderJson(resp);
    }

    //登陆
    //@Before(LoginValidator.class)
    public void login() {
//        String data = getRawData();
//        System.out.println(data);
//        JSONObject jsonObject = JSON.parseObject(data);
//        String account = jsonObject.getString("user");
//        String password = jsonObject.getString("password");
        String account = getPara("user");
        String password = getPara("password");
        System.out.println(account);
        System.out.println(password);
        RespData resp = new RespData();
        resp.setTime(System.currentTimeMillis());
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        resp.setDate(currentTime);
        try {
            if (service.loginCheck(account, password)) {
                resp.setCode(0);
                resp.setMsg("登陆成功！");
                HashMap<String , String> map = new HashMap<String, String>();
                map.put("账号", account);
                map.put("密码", password);
                resp.setData(map);

            } else {
                resp.setCode(1);
                resp.setMsg("该用户不存在，登陆失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setCode(1);
            resp.setMsg("登陆失败！");
        }
        renderJson(resp);

    }

    //添加用户
    public void addStudent(@Para("")User user){
       //见方法public void save(@Para("")User user)
    }

    //修改用户记录
    public void updateStudent(@Para("")User user)
    {
        RespData resp = new RespData();
        resp.setTime(System.currentTimeMillis());
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        resp.setDate(currentTime);
        try {
            user.update();
            //service.findById(user.get("id")).set("modified_time", currentTime).update();
            resp.setCode(0);
            resp.setMsg("修改成功！");
            HashMap<String ,Object> map = new HashMap<String, Object>();
            map.put("用户信息", user.keep("student_account","student_name","student_id","student_tel","student_mail","class_name"));
            resp.setData(map);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setCode(1);
            resp.setMsg("修改失败！");
        }
        renderJson(resp);

    }

    //删除用户记录
    public void deleteStudent(){
        String data = getRawData();
        System.out.println(data);
        JSONObject jsonObject = JSON.parseObject(data);
        int id = jsonObject.getInteger("ID");
        RespData resp = new RespData();
        resp.setTime(System.currentTimeMillis());
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        resp.setDate(currentTime);
        try {
            service.deleteById(id);
            resp.setCode(0);
            resp.setMsg("删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            resp.setCode(1);
            resp.setMsg("删除失败！");
        }
        renderJson(resp);
    }

    //查找用户记录
    public void findStudent() {
        String data = getRawData();
        System.out.println(data);
        JSONObject jsonObject = JSON.parseObject(data);
        int userId = jsonObject.getInteger("userId");
        RespData resp = new RespData();
        resp.setTime(System.currentTimeMillis());
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        resp.setDate(currentTime);
        try{
            User user = service.findById(userId);
            renderJson(user);
            resp.setCode(0);
            resp.setMsg("查找成功！");
        }catch(Exception e) {
            e.printStackTrace();
            resp.setCode(1);
            resp.setMsg("查找失败！");
        }
        renderJson(resp);

    }
    public void findAll(){
        String data = getRawData();
        System.out.println(data);
        JSONObject jsonObject = JSON.parseObject(data);
        int id = jsonObject.getInteger("ID");
        System.out.println(id);

        try{
            if (id == 1 ) {
                List<User> users = service.findUsers();
                JSONArray array = JSONArray.parseArray(JSON.toJSONString(users));
                System.out.println(array);
                renderJson(array);
            }
            else if(id == 2){
                List<Department> departments = departmentService.findDepartments();
                JSONArray array = JSONArray.parseArray(JSON.toJSONString(departments));
                System.out.println(array);
                renderJson(array);
            }
            else if(id == 0){
                List<Department> className = departmentService.findClassName();
                JSONArray array = JSONArray.parseArray(JSON.toJSONString(className));
                System.out.println(array);
                renderJson(array);
            }
            else{
                renderJson("{\"error\":查找出现错误}" );
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    //删除一个部门，同时删除该部门下的所有用户记录
    public  void deleteClass(String className){
        RespData resp = new RespData();
        resp.setTime(System.currentTimeMillis());
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        resp.setDate(currentTime);
        try {
            departmentService.deleteByName(className);
            resp.setCode(0);
            resp.setMsg("删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            resp.setCode(1);
            resp.setMsg("删除失败！");
        }
        renderJson(resp);
    }

    //增加部门
    public void addDepartment(@Para("")Department department) {
        RespData resp = new RespData();
        resp.setTime(System.currentTimeMillis());
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        resp.setDate(currentTime);
        try {
            //String sql = Db.getSql("findDepartmentByName");

            String sql = "select * from department where class_name = ?";
            Record result = Db.findFirst(sql, department.getClassName());
            if (result == null){
                department.save();
                //departmentService.findById(department.get("id")).set("created_time", currentTime).update();
                resp.setCode(0);
                resp.setMsg("添加成功！");
                HashMap<String ,Object> map = new HashMap<String, Object>();
                map.put("部门信息", department.keep("class_name"));
                resp.setData(map);
            }
            else{
                resp.setCode(1);
                resp.setMsg("该部门已存在，添加失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setCode(1);
            resp.setMsg("添加失败！");
        }
        renderJson(resp);
    }

    //修改部门记录
    public void updateDepartment(@Para("")Department department)
    {
        RespData resp = new RespData();
        resp.setTime(System.currentTimeMillis());
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        resp.setDate(currentTime);
        try {
            department.update();
            //departmentService.findById(department.get("id")).set("modified_time", currentTime).update();
            resp.setCode(0);
            resp.setMsg("修改成功！");
            HashMap<String ,Object> map = new HashMap<String, Object>();
            map.put("部门信息", department.keep("class_name"));
            resp.setData(map);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setCode(1);
            resp.setMsg("修改失败！");
        }
        renderJson(resp);
    }

    //查找部门记录
    public void findDepartment(int classId) {
        RespData resp = new RespData();
        resp.setTime(System.currentTimeMillis());
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        resp.setDate(currentTime);
        try{
            Department department = departmentService.findById(classId);
            renderJson(department);
            resp.setCode(0);
            resp.setMsg("查找成功！");
        }catch(Exception e) {
            e.printStackTrace();
            resp.setCode(1);
            resp.setMsg("查找失败！");
        }
        renderJson(resp);

    }

    //分页显示
    public  void findUsers()
    {
        String page = getPara("page");
        String number = getPara("number");
        if(!StrKit.isBlank(page) && !StrKit.isBlank(number)){
            int pageNumber=Integer.parseInt(page);
            int pageSize=Integer.parseInt(number);
            Page<User> users = service.paginate(pageNumber,pageSize);
            JSONArray array = JSONArray.parseArray(JSON.toJSONString(users));
            System.out.println(array);
            renderJson(array);

        }else{
            renderJson("{\"error\":查找出现错误}" );

        }

    }



}
