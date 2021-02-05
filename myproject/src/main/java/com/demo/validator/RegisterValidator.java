package com.demo.validator;

import com.demo.model.User;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class RegisterValidator extends Validator {

    //校验用户名,用户名必须为6-10位字母数字，第一位是字母
    private static final String studentAccountPattern="^[a-zA-Z][\\w]{5,9}$";
    //检验姓名，姓名必须为中文
    private static final String studentNamePattern="^[\\u4e00-\\u9fa5]";
    //检验密码，密码必须为6~10位数字
    private static final String studentPwPattern="^[\\d]{6,10}$";
    //检验邮箱
    private static final String studentMailPattern="^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$";
    //检验手机号
    private static final String studentTelPattern="^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
    //检验身份证号
    private static final String studentIdPattern="(^[1-9]\\\\d{5}(18|19|20)\\\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\\\d{3}[0-9Xx]$)|\"\n"+"+\"(^[1-9]\\\\d{5}\\\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\\\d{3}$)";


    @Override
    protected void validate(Controller c) {

        validateRegex(c.getPara("studentAccount"), studentAccountPattern, "msg", "用户名必须为6-10位字母数字，第一位是字母");
        validateRegex(c.getPara("studentName"), studentNamePattern, "msg", "姓名必须为中文");
        validateRegex(c.getPara("studentPw"), studentPwPattern, "msg", "密码必须为6~10位数字");
        validateRegex(c.getPara("studentMail"), studentMailPattern, "msg", "邮箱格式不正确");
        validateRegex(c.getPara("studentTel"), studentTelPattern, "msg", "手机号码格式不正确");
        validateRegex(c.getPara("studentId"), studentIdPattern, "msg", "身份证号格式不正确");

    }

    @Override
    protected void handleError(Controller c) {
        c.keepModel(User.class, "user");
    }

}
