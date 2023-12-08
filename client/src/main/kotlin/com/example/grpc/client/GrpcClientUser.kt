package com.example.grpc.client

import com.example.grpc.proto.Department
import com.example.grpc.proto.DepartmentServiceGrpc
import com.example.grpc.proto.UserServiceGrpc
import io.grpc.stub.StreamObserver
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

    @GrpcClient("local-grpc-server")
    lateinit var dstb: DepartmentServiceGrpc.DepartmentServiceStub;

    @PostConstruct
    fun getUser() {
        val user = stb.getUser(
            com.example.grpc.proto.Department.newBuilder()
                .setName("jw")
                .build()
        )
        println(user?.name)

        var value = object : StreamObserver<Department> {

            override fun onNext(p0: Department?) {
                println(p0)
            }

            override fun onError(t: Throwable?) {
                println(t)
            }

            override fun onCompleted() {
                println("client onCompleted")
            }
        }

        val department = dstb.getDepartment(value)
        for (index in 1..10) {
            department.onNext(
                com.example.grpc.proto.User.newBuilder()
                    .setName("jw$index")
                    .build()
            )
        }
        department.onCompleted()
    }
}