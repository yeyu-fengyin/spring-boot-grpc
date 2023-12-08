package com.example.server.service

import com.example.grpc.proto.Department
import com.example.grpc.proto.DepartmentServiceGrpc
import com.example.grpc.proto.User
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService

/**
 * @Author jw
 * @Date 2023/12/8
 */
@GrpcService
class DepartmentService : DepartmentServiceGrpc.DepartmentServiceImplBase() {
    override fun getDepartment(responseObserver: StreamObserver<Department>?): StreamObserver<User> {
        val value = object : StreamObserver<User> {
            override fun onNext(value: User?) {
                println(value)
                if (value != null) {
                    responseObserver?.onNext(
                        Department.newBuilder()
                            .setName("${value.name} return")
                            .build()
                    )
                }
            }

            override fun onError(t: Throwable?) {
                println(t)
            }

            override fun onCompleted() {
                println("server onCompleted")
                responseObserver?.onCompleted()
            }
        }

        return value
    }
}