0.Create folder where to upload/download logo.(default C:/images, See spring-boot-rest application.yml)
1. cd to project directory
2. Start spring boot by typing in command line -> gradlew :spring-boot-rest:bootRun
Server up and running. Try open browser on url localhost:8080/users/
3. Run jersey client example by in typing command line -> gradlew :jersey-client:run -PcommandLineArgs='C:/images'
if you created different logo folder, please specify it in commandLineArgs