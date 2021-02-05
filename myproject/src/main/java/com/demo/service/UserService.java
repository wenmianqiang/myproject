package com.demo.service;

import com.demo.model.User;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import java.util.List;

public class UserService {

    private User dao = new User().dao();

    //登陆检查
	public boolean loginCheck(String account,String password){
	    String sql = Db.getSql("loginCheck");
        //String sql = "select * from user where (student_account =? or student_id=? or student_tel=? or student_mail=?) and student_pw=?";
        Record result = Db.findFirst(sql, account, account, account, account,password);
        return result != null;
    }


    //删除记录
    public void deleteById(int id) {
        //String sql = Db.getSql("deleteById");
        String sql = "update user set is_delete=1 where id= ? ";
        Db.update(sql, id);
    }

    //查找记录
    public User findById(int id) {
        return dao.findById(id);
    }
    public List<User> findUsers(){
	    String sql = Db.getSql("findUsers");
	    //String sql = "select * from user where is_deleted=0 order by id asc";
        List<User> users = dao.find(sql);
        return users;
    }
    public Page<User> paginate(int pageNumber, int pageSize) {//查询某页的数据
        return dao.paginate(pageNumber, pageSize, "select *", "from user where is_deleted=0 order by id asc");//ASC-升序 DESC-降序
    }

}
