package com.example.demo.data.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity // このクラスをEntityとして使うためのアノテーション
@Data // Getter、Setterを省略するためのLombokのアノテーション
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // idがMySQLのauto_incrementの場合、自動生成させるためにアノテーションを付ける 
	// userテーブルのid
	private Long id;
	
	// userテーブルのname
	private String name;
	
	// userテーブルのemail
	private String email;

}