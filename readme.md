1. Create folder where to upload/download logo.(default C:/images, See spring-boot-rest application.yml) <br />
2. cd to project directory <br />
3. Start spring boot by typing in command line -> gradlew :spring-boot-rest:bootRun <br />
4. Server up and running. Try open browser on url localhost:8080/users/ <br />
5. Run jersey client example by typing in command line -> gradlew :jersey-client:run -PcommandLineArgs='C:/images' <br />
6. if you created different logo folder, please specify it in commandLineArgs <br />
