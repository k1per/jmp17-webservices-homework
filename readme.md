Create folder where to upload/download logo.(default C:/images, See spring-boot-rest application.yml) <br />
cd to project directory <br />
Start spring boot by typing in command line -> gradlew :spring-boot-rest:bootRun <br />
Server up and running. Try open browser on url localhost:8080/users/ <br />
Run jersey client example by in typing command line -> gradlew :jersey-client:run -PcommandLineArgs='C:/images' <br />
if you created different logo folder, please specify it in commandLineArgs <br />
