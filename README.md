# bluefairy
A simple docker web client.

## Usage
1. Installation of Docker. And enable DockerRemoteAPI.
2. Installation of MongoDB. And configure to allow remote access.
3. Set the DockerAPI endpoint and MongoDB connection info in the `application.properties`
4. `mvn spring-boot:run`

## License
This software is licensed under the [MIT License](http://opensource.org/licenses/MIT).

## Examples
If building all services to the local host, IP address replace to "127.0.0.1" from "0.0.0.0".

Installation of Docker.
```bash
$ ifconfig | grep eth1 -A 1
eth1      Link encap:Ethernet  HWaddr 00:1c:42:1a:ce:47
          inet addr:10.37.129.10  Bcast:10.37.129.255  Mask:255.255.255.0

$ uname -a
Linux ubuntu 3.13.0-24-generic #41~14.04.1-Ubuntu SMP Wed Feb 11 19:30:13 UTC 2015 x86_64 x86_64 x86_64 GNU/Linux

$ sudo apt-get install linux-image-3.16.0-31-generic
$ sudo reboot
```
```bash
$ uname -a
Linux ubuntu 3.16.0-31-generic #41~14.04.1-Ubuntu SMP Wed Feb 11 19:30:13 UTC 2015 x86_64 x86_64 x86_64 GNU/Linux

$ sudo apt-get install apt-transport-https
$ sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys 36A1D7869245C8950F966E92D8576A8BA88D21E9
$ sudo sh -c "echo deb https://get.docker.com/ubuntu docker main > /etc/apt/sources.list.d/docker.list"
$ sudo apt-get update

$sudo apt-get install lxc-docker
$ docker --version
Docker version 1.5.0, build a8a31ef
```

Enable DockerRemoteAPI.
```bash
$ sudo vi /etc/init/docker.conf

#        DOCKER_OPTS=
        DOCKER_OPTS=' -H tcp://0.0.0.0:2376 -H unix:///var/run/docker.sock'
```
```bash
$ sudo service docker restart
docker stop/waiting
docker start/running, process 15741
```

Installation of MongoDB
```bash
$ ifconfig | grep eth1 -A 1
eth1      Link encap:Ethernet  HWaddr 00:1c:42:1a:ce:48
          inet addr:10.37.129.11  Bcast:10.37.129.255  Mask:255.255.255.0

$ sudo apt-get install mongodb
```

Configure to allow remote access.
```bash
$ sudo vi /etc/mongodb.conf

#bind_ip = 127.0.0.1
bind_ip = 0.0.0.0
```
```bash
$ echo 'db.addUser("admin", "password12345678");' | mongo localhost/bluefairy
```

Set the DockerAPI endpoint and MongoDB connection info in the `application.properties`
```bash
$ vi src/main/resource/application.properties

server.port=41000
server.contextPath=/bluefairy
#logging.file=logs/bluefairy.log
logging.level.org.springframework.web=INFO
security.basic.enabled=false
bluefairy.docker.remoteApi=http://10.37.129.10:2376/
bluefairy.mongo.uri=mongodb://10.37.129.11:27017/
bluefairy.mongo.database=bluefairy
bluefairy.mongo.username=admin
bluefairy.mongo.password=password12345678
```

Run spring-boot
```bash
mvn spring-boot:run
```
