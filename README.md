# springbootwithdocker

Before the run this app, you should download Docker and Docker Compose. If you have both you should follow this commands for run the application.

    $ git clone https://github.com/tugayclml/springbootwithdocker.git

    $ cd springbootwithdocker

This command will create a image with Dockerfile.

    $ docker build -t spring-docker .

This command run the app and database.

    $ docker-compose -f docker-compose.yaml up

And then you can check the app is running.

    $ curl --user admin:admin123 localhost:8080/actuator/health

It will return status is UP. Everything is ok.

I hope this repository help you.