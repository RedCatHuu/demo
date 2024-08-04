package com.example.demo.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserForm {
	
	// nameへのバリデーション設定を追加
	@Size(min = 1, max = 200)
	private String name;
	
	// emailへのバリデーション設定を追加
	@Size(min = 1, max = 200)
	@Email
	private String email;
}
