package com.example.grpc


import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * @Author jw
 * @Date 2023/12/1
 */
@SpringBootApplication
class GrpcMain

fun main(args: Array<String>) {
    runApplication<GrpcMain>(*args)
}