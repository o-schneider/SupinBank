Java EE, MiniProjet M1 SUPINBANK
================================


How to run it?
--------------

To run the web app, you have to 

1) Clone the project on your computer (what a crazy thing) <br />
2) Check the persistence.xml and JMS bindings (check the MessageSender class for that) to make them aware of your Glassfish configuration <br/>
3) Make sure you typed in your SMTP informations and credentials in the MailService class in order to get your user's passwords!  <br />
4) You have to execute the two .sql file present on the project root on your DB Schema, if not, there may be some bugs <br/>
5) Deploy on your glassfish thanks to maven!<br/>
6) If you didn't change the default glassfish ports, just go to http://localhost:8080/SupinBank/<br/>
7) Start to enjoy the app by signing in with bank@advisor.com, with the password password. =)<br />


FAQ
---

Q : The mails are not sent!<br/>
A : After redeployments, Glassfish is not sending the mails properly. Reboot the server and try again, it should work.

Q : As I am trying to log in with valid credentials, the application just says me that an error occured... What happens?<br />
A : Make sure your database is up and the bindings are valid. If they are, reboot glassfish and try again.


More
---

There is a JSF version of the application on the JSF-port branch.<br />
JSf version on the cloude here : http://supinbank.oschneider.cloudbees.net/index.xhtml

Released under the General Public License 3. Have a look at the license file.