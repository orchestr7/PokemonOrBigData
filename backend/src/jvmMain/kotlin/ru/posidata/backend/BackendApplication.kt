package ru.posidata.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["ru.posidata"])
class BackendApplication

fun main(args: Array<String>) {
	runApplication<BackendApplication>(*args)
}
