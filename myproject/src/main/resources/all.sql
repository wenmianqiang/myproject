
#sql("loginCheck")
  select * from user where (student_account =? or student_id=? or student_tel=? or student_mail=?) and student_pw=?
#end

#sql("findUsers")
  select * from user where is_deleted=0 order by id asc
#end

#sql("findDepartments")
  select * from department where is_deleted=0 order by id asc
#end

#sql("findClassName")
  select class_name from department where is_deleted=0 order by id asc
#end


