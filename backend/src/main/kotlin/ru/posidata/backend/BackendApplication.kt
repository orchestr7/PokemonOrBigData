package ru.posidata.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@ComponentScan(basePackages = ["ru.posidata"])
class BackendApplication


fun main(args: Array<String>) {
	runApplication<BackendApplication>(*args)
}

