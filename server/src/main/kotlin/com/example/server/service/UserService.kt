package com.example.server.service

import com.example.grpc.proto.Department
import com.example.grpc.proto.User
import com.example.grpc.proto.UserServiceGrpc
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService

/**
 * @Author jw
 * @Date 2023/12/8
 */
@GrpcService
class UserService : UserServiceGrpc.UserServiceImplBase() {
    override fun getUser(request: Department?, responseObserver: StreamObserver<User>?) {
        println(request)
        val user = User.newBuilder()
            .setName("jw")
            .build()
        responseObserver?.onNext(user)
        responseObserver?.onCompleted()
    }
}