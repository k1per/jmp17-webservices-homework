1. Create folder where to keep uploaded logos.(default C:/images, See spring-boot-rest application.yml) <br />
2. cd to project directory <br />
3. Start spring boot by typing in command line -> gradlew :spring-boot-rest:bootRun <br />
4. Server up and running. Try open browser on url localhost:8080/users/ <br />
5. Create folder where to download and specify it in commandLineArgs (e.g. -PcommandLineArgs='C:/mycreatedfolder') <br />
6. Run jersey client example by typing in command line and providing argument -> <br />
gradlew :jersey-client:run -PcommandLineArgs='C:/downloads' <br />

