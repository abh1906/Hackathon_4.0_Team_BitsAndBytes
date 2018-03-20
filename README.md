# Hackathon_4.0_Team_BitsAndBytes
**RFID Based Library Book Search and Antitheft Detection using the Internet of Things.**

This project aims at integrating the rfid modules to the well known **open source LMS :koha.**

STEPS for implementation:

Step1: Installing koha.The link for installation is as follows:https://wiki.koha-community.org/wiki/Koha_on_ubuntu_-_packages

Step 2: created  **databasename=koha_mykoha ,
                 password=sL925avEhO6utH1V ,
                 username=koha_mykoha**


Step3: After installing koha copy the files from the main folder and perform the following:
 1.  paste the file named ports.conf at the file at location **/etc/apache2/ports.conf** 
 2. paste the file named mykoha.conf to the file at location **/etc/apache2/sites-enabled/mykoha.conf**
 
Step4:import the sql file koha_mykoha.sql to the project which contains the newly added and modified tables.
 
 
 The apk file is also included in the main repo folder.
 
 The code for hardware is included in the **nodemcu** folder.
 
 Our instance of koha is installed on the digital ocean droplet whose **ip is:http://45.55.34.11:8080** for administration s    staff.For Patrons login **ip is:http://45.55.34.11:98**
 
 
 Thank You..

