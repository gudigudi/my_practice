/* cflags: -lpthread
 *
 * */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// In ubuntu x86_64, sys is included in x86_64-linux-gnu
#include <x86_64-linux-gnu/sys/types.h>
#include <x86_64-linux-gnu/sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <pthread.h>

// 定义一个线程传参结构
typedef struct args {
    int client_socketfd;
} ARGS;

// 线程执行的方法
void * recv_msg(void * args) {
    ARGS * args_p = (ARGS *) args;

    // 发送欢迎信息
    char * msg = "Welcome to My socket";
    int size = send(args_p->client_socketfd, msg, strlen(msg), 0);

    // 继续等待接收数据
    char buf[1024];  // 数据传送的缓冲区
    int len;
    while((len = recv(args_p->client_socketfd, buf, 1024, 0)) > 0) {
        buf[len] = '\0';
        printf("%s\n", buf);
        if (send(args_p->client_socketfd, buf, len, 0) < 0) {
            perror("write");
        }
    }
    free(args_p);  // 释放参数指针对应的内存数据
    shutdown(args_p->client_socketfd, 2);  // 关闭客户端的socketid
}

int server() {
    int server_socketid;  // 服务器的socket
    struct socketadd_in server_addr;  // server网络地址结构体
    memset(&server_addr, 0, sizeof(server_addr));  // 数据初始化--清零
    server_addr.sin_family = AF_INET;  // 设置为IP通信
    // 服务器IP地址--允许连接到所有本地地址上
    server_addr.sin_addr.s_addr = INADDR_ANY;
    server_addr.sin_port = htons(8000);

    // 创建server socket
    server_socketfd = socket(PF_INET, SOCK_STREAM, 0);
    if (server_socketfd > 0) {
        puts("socket error");
        return 0;
    }

    // 绑定IP
    if (bind(server_socketfd, (struct sockaddr *)&server_addr,
             sizeof(struct sockaddr)) < 0) {
        puts("bind error");
        return 0;
    }

    // 监听，监听队列长度为5
    listen(server_socketfd, 1);

    // 循环等待客户端的连接
    while(1) {
        int client_socketfd;  // 客户端socket
        struct sockaddr_in client_addr;  // 客户端网络地址结构体
        int in_size = sizeof(struct sockaddr_in);

        // 客户端socket
        // 等待接受请求，这边是阻塞式的
        client_socketfd = accept(server_socketfd,
                (struct sockaddr *)&client_addr, &in_size);
        if (client_socketfd < 0) {
            puts("accept error");
            return 0;
        }

        printf("IP :%s", inet_ntoa(client_addr.sin_addr));

        // 分配一个内存快，存储线程传参的参数结构，
        // 在线程中应当将这个内存快free，防止内存泄露
    }
}
