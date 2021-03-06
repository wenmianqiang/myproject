package com.demo.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class LoginValidator extends Validator {

	@Override
	protected void validate(Controller c) {
		validateRequiredString("user", "userMsg", "请输入用户名");
		validateRequiredString("password", "passwordMsg", "请输入密码");

	}

	@Override
	protected void handleError(Controller c) {
		c.keepPara("user");
	}

}
