package com.example.grpc.client

import com.example.grpc.proto.UserServiceGrpc
import net.devh.boot.grpc.client.inject.GrpcClient

import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

/**
 * @Author jw
 * @Date 2023/12/1
 */
@Service
class GrpcClientUser {
    @GrpcClient("local-grpc-server")
    lateinit var stb: UserServiceGrpc.UserServiceBlockingStub;

    @PostConstruct
    fun getUser() {
        val user = stb.getUser(
            com.example.grpc.proto.UserBean.User.newBuilder()
                .setName("jw")
                .build()
        )
        println(user?.name)
    }
}