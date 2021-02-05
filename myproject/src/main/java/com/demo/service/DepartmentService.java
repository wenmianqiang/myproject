package com.demo.service;

import com.demo.model.Department;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;

import java.util.List;

public class DepartmentService {
    private Department dao = new Department().dao();


    //删除部门
    public void deleteByName(String className) {
        Db.tx(() -> {
            //String sql1 = Db.getSql("deleteByName1");
            //String sql2 = Db.getSql("deleteByName2");

            String sql1 = "update user set is_delete=1 where class_name= ? ";
            Db.update(sql1, className);
            String sql2 = "update department set is_delete=1 where class_name= ? ";
            Db.update(sql2, className);
            return true;
        });

    }
    //查找部门
    public Department findById(int id) {
        return dao.findById(id);
    }

    public List<Department> findDepartments(){
        String sql = Db.getSql("findDepartments");
        //String sql = "select * from department where is_deleted=0 order by id asc";
        List<Department> departments = dao.find(sql);
        return departments;
    }

    public List<Department> findClassName(){
        String sql = Db.getSql("findClassName");
        //String sql = "select class_name from department where is_deleted=0 order by id asc";
        List<Department> className = dao.find(sql);
        return className;
    }
    public Page<Department> paginate(int pageNumber, int pageSize) {//分页查询，
        return dao.paginate(pageNumber, pageSize, "select *", "from department where is_deleted=0 order by id asc");//ASC-升序 DESC-降序
    }

}
