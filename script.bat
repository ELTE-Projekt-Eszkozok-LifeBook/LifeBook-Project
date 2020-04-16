git clone https://github.com/ELTE-Projekt-Eszkozok-LifeBook/LifeBook-Project.git

cd LifeBook-Project/LifeBookProject/LifeBookProject
call mvn clean package
call mvn spring-boot:run

explorer http://localhost:8080/