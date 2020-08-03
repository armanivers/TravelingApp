# TravelingApp
Full stack demo example of a website for booking hotels, activities, adding reviews, logging-in and out and so on.. 

In oder to run it on your own, you need to use the TravelingAppDb.sql file located in resources folder, this is the needed database for the website. Upload it to your own MYSQL server and then set up the needed acces in application.properties (mysql server url, mysql server account name+pass)

==> Run TravelingAppApplication.java in src/main...

Furthermore you need to register at openweather and newsapi so you can input your own api keys in the application.properties in order to be able to use some of the functionalities.

(WARNING! When searching for destinations, your newsapi max search per day limit may exceed because every destination search gathers 3 different news requests and this times the destinations you've searched for equals the daily limit of the free api account)

You can login using the example acccounts of the given database file.

Known bugs:
-> create review doesnt work (index error?), but updating an existing review works fine.
-> password reset link doesn't send an email to the given user's account (this is implemented, but I personally didn't set up a gmail smtp account in order to give eclipse the access to send emails), if you use the password reset function, just check out for the console output link and paste it in your browser.
