mvn clean
rm -r ~/.m2/repository/mysql/mysql-connector-java
mvn dependency:purge-local-repository
mvn clean install