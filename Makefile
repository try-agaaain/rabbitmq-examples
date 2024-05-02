install:
	sudo apt-get update
	# 安装RabbitMQ
	sudo apt-get install rabbitmq-server
	# 启动RabbitMQ服务
	sudo service rabbitmq-server start

create_user:

	# 要创建的RabbitMQ用户名和密码
	RABBITMQ_USER="guest"
	RABBITMQ_PASSWORD="guest"

	# 创建新用户
	sudo rabbitmqctl add_user $RABBITMQ_USER $RABBITMQ_PASSWORD

	# 为新用户设置权限
	sudo rabbitmqctl set_permissions -p / $RABBITMQ_USER ".*" ".*" ".*"

	# （可选）将新用户设置为管理员
	# rabbitmqctl set_user_tags $RABBITMQ_USER administrator

	echo "RabbitMQ用户创建并配置完成。"

patch:
	git diff > output.patch	# 导出修改