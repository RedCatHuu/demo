package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.data.entity.User;
import com.example.demo.data.repository.UserRepository;
import com.example.demo.form.UserForm;

@Controller
public class UserController {
	// @Autowiredアノテーションを付けると、Spring Bootが自動でインスタンスをInjectします。
	@Autowired
	private UserRepository userRepository;
	
	// @RequestMapping(path = "/user", method = RequestMethod.GET)の省略版。
	// HTTPのメソッドGETのみ受け付けます。
	@GetMapping("/users")
	public String getUsers(Model model) {
		// ユーザーリスト取得処理を追加
		List<User> users = userRepository.findAll();
		
		// 取得したリストをテンプレートに渡す
		// テンプレートは src/main/resources/templates/users.html とします。
		// 第一引数の"users"は、テンプレートでユーザーリストを呼び出すときの名前、第二引数のusersは、ユーザーリストの実体
		model.addAttribute("users", users);
		return "users";
		
	}
	
	@GetMapping("/newuser")
	public String getNewUser(Model model) {
		// Modelに空のUserFormを追加
		UserForm userForm = new UserForm();
		model.addAttribute("userForm", userForm);
		// テンプレートは src/main/resources/templates/newuser.html とします。
		return "newuser";
	}
	
	@PostMapping("/newuser")
	public String registerUser(@Validated UserForm userForm, BindingResult bindingResult) {
		// バリデーションの結果、エラーがあるかどうかチェック
		if (bindingResult.hasErrors()) {
			// エラーがある場合はユーザー登録画面を返す
			return "newuser";
		}
		User user = new User();
		user.setName(userForm.getName());
		user.setEmail(userForm.getEmail());
		
		// データベースに保存
		userRepository.save(user);
		
		// ユーザー一覧画面へリダイレクト
		return "redirect:/users";
	}
	
	@PostMapping("/users/delete/{id}")
	// 処理の中でidを使えるように、引数にidを追加
	public String deleteUser(@PathVariable Long id) {
		// 指定したIDのユーザーを削除
		userRepository.deleteById(id);
		return "redirect:/users";
	}
}
