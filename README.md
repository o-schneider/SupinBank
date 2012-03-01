Java EE, MiniProjet M1 SUPINBANK
================================


How to run it?
--------------

To run the web app, you have to 

1) Clone the project on your computer (what a crazy thing) <br />
2) Check the persistence.xml and JMS bindings (check the MessageSender class for that) to make them aware of your Glassfish configuration
3) You have to execute the two .sql file present on the project root on your DB Schema, if not, there may be some bugs
4) Deploy on your glassfish thanks to maven!
5) If you didn't change the default glassfish ports, just go to http://localhost:8080/SupinBank/
6) Start to enjoy the app by signing in with bank@advisor.com, with the password password. =)


FAQ
---

Q : The mails are not sent!
A : After redeployments, Glassfish is not sending the mails properly. Reboot the server and try again, it should work.

Q : As I am trying to log in with valid credentials, the application just says me that an error occured... What happens?
A : Make sure your database is up and the bindings are valid. If they are, reboot glassfish and try again.
