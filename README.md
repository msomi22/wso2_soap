# WSO2 SOAP Servive using wso2as-5.3.0 Application Server 

* This is a simple university library service that enables students to:
* borrow books
* order books via Google book API
* query history : borrow, order, return
* query student profile
* view list of available books and their status 

## How to setup the server ##
* place your jasrs here ... repository/components/lib
* the jars are as shown:
* apache-logging-log4j
* commons-dbutils-1.7
* commons-logging-1.1.3
* httpclient-4.2.3
* httpcore-4.2.3
* java-json
* postgresql-42.2.2

## Configure the server ##
* locate --- repository/conf/datasources
* modify the master-datasources.xml
* 

## add this 
## <datasource>
              <name>UNILIB_DB</name>
              <description>The datasource used for unilib manager</description>
              <jndiConfig>
                    <name>jdbc/WSO2CarbonUnilibDB</name> 
              </jndiConfig>
              <definition type="RDBMS">
                    <configuration>
                        <url>jdbc:postgresql://localhost:5432/gregdb</url>
                        <username>unilib</username>
                        <password>unilib@2018</password>
                        <driverClassName>org.postgresql.Driver</driverClassName>
                        <maxActive>80</maxActive>
                        <maxWait>60000</maxWait>
                        <minIdle>5</minIdle>
                        <testOnBorrow>true</testOnBorrow>
                        <defaultAutoCommit>false</defaultAutoCommit>
                        <validationInterval>30000</validationInterval>
                   </configuration>
               </definition> 
        </datasource> 
##  end 

* export your aar file here
* repository/deployment/server/axis2services

## How to run the server 
* navigate --- wso2as-5.3.0/bin
* run wso2server.sh 
* see the url to the admin console on the terminal output 
* cheers 
