# 開発環境立ち上げ実行
build-run-dev-env:
	cd ./dev_env && \
		docker-compose build --build-arg UID="`id -u`" --build-arg GID="`id -g`" && \
		docker-compose up -d

# 開発環境破棄
clean-dev-env:
	cd ./dev_env && \
		docker-compose stop && docker-compose rm -f

# 最新コードでdev実行
run-dev:
	cd ./dev_env && \
		docker-compose exec web_api_app bash -c "cd app && ./mvnw spring-boot:run"

# 最新コードでpackage build実行
run-prod:
	cd ./dev_env && \
		docker-compose exec web_api_app bash -c "cd app && ./mvnw package && java -jar target/my_contracts_j-0.0.1-SNAPSHOT.jar"

# 最新コードでunittest実行
test:
	cd ./dev_env && \
		docker-compose exec web_api_app bash -c "cd app && ./mvnw test"

# 成果物をclean
clean:
	cd ./dev_env && \
		docker-compose exec web_api_app bash -c "cd app && ./mvnw clean"

ps:
	cd ./dev_env && \
		docker-compose ps -a

# コンテナlog tail
log-tail:
	cd ./dev_env && \
		docker-compose logs -f

login:
	cd ./dev_env && \
		docker-compose exec web_api_app bash

login-root:
	cd ./dev_env && \
		docker-compose exec -u root web_api_app bash

#create-containers:
#	cd ./dev_env && \
#		docker-compose create
#
#start-service:
#	cd ./dev_env && \
#		docker-compose start
#
#up-service:
#	cd ./dev_env && \
#		docker-compose up -d
#
#stop-service:
#	cd ./dev_env && \
#		docker-compose stop
#
#
#ps:
#	cd ./dev_env && \
#		docker-compose ps
#
#log-tail:
#	cd ./dev_env && \
#		docker-compose logs -f
#
#reset-db-password:
#	cd ./dev_env && \
#	docker-compose exec db ./setPassword.sh password
#
#db-login:
#	cd ./dev_env && \
#	docker-compose exec db sqlplus pdbadmin/password@//localhost:1521/my_contracts
