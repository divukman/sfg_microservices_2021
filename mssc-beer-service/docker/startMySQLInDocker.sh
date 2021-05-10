#!/bin/sh
# Continue existing container or start a new one...

imageName="dimitar-mysql"

getContainerId () {
	id=`docker ps -a -f name=dimitar-mysql | grep -w "$imageName" | awk '{print $1}'`
}

tailLogs () {
	docker logs -f "$id"
}

getContainerId

echo "$id"
if [ -n "$id" ];
then
	echo "Container already exists with ID: $id"
	echo "Starting container..."
	docker start "$id"
	tailLogs
else
	echo "Starting new container..."
	docker run --name "$imageName" -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -v /home/dimitar/docker_volumes/mysql_springguru:/var/lib/mysql -v /home/dimitar/Desktop/UDEMY/sql_spring_guru/test_db:/testdbb -v /home/dimitar/Desktop/UDEMY/sql_spring_guru/mywind:/mywind -p 3306:3306 -d mysql
#	getContainerId
#	tailLogs
fi
