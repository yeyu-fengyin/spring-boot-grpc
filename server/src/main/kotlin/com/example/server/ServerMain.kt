package com.example.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * @Author jw
 * @Date 2023/12/8
 */
@SpringBootApplication
class ServerMain

fun main(args: Array<String>) {
    runApplication<ServerMain>(*args)
}
