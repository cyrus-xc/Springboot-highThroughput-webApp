# Springboot-highThroughput-webApp
基于Springboot的高并发电商秒杀系统，支持MD5加密，多服务器负载均衡，以及高达百万同时访问。前端实现Session存储，密码验证，恶意访问校验， 访问限流等功能。后端实现RabbitMQ消息队列降低数据库负载，使用Redis数据库降低数据读写延迟。  使用WSL部署基于本地Linux系统的Redis服务器，支持平行拓展、负载均衡。  技术栈： Springboot, Redis, MySQL, MyBatis plus, Ngix, RabbitMQ, 
