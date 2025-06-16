package com.example.demo.accessing_data_mysql

import com.example.demo.DemoApplication
import org.springframework.boot.fromApplication
import org.springframework.boot.with

fun main(args: Array<String>) {
	fromApplication<DemoApplication>().with(TestcontainersConfiguration::class).run(*args)
}
