FROM openjdk:17
LABEL authors="liamcahill"
COPY build/classes/java/main/ /tmp
WORKDIR /tmp
CMD java org.example.Main


#TODO: Note, if I expect to build a java webapp, I'll need to EXPOSE a port here