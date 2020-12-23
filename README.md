# CafeBellus
CafeBellus 프로젝트 

ㅡmaven의 저장소에서 ojdbc6.jar파일 다운받아서 직접설치 
maven 에는 ojdbc6.jar 를 지원하지 않기 때문에 오라클 사이트에서 다운받은 후
maven 의 bin 폴더에있는 실행파일을 이용하여 maven에 ojdbc6.jar를 직접 다운 받아서 사용합니다. 

cmd 창에서 maven 설치경로의 bin으로 이동하셔서 ojdbc6.jar가 설치된 경로를 입력해서 직접 다운 받도록합니다.

1. cmd 실행
2. maven 설치경로의 bin 폴더로 이동
3. 아래 명령어 실행(ojdbc6.jar 의 위치를 꼭 확인해주세요!!)
D:\JSP\apache-maven-3.6.3\bin>mvn install:install-file -Dfile="D:\ojdbc6.jar" -DgroupId=com.oracle.jdbc -DartifactId=ojdbc6 -Dversion=11.1.0.6.0 -Dpackaging=jar

한 후 프로젝트에서 maven update



