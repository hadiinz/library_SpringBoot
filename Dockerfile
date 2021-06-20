FROM 192.168.7.245:19095/domain991-developuser01:latest

COPY ./target/libraryPro-1.0-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch libraryPro-1.0-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","libraryPro-1.0-SNAPSHOT.jar"]