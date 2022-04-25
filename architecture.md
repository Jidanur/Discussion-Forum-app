Architecture
============

The architecture we are using is an MVC (Model, View, Controller) set up. We have our base models with their fields but no logic in the Models folder. The views within a UI folder, and the controller/logic in the controller's folder. 
There are 3 methods of persistence for our app, stub (arraylist), HTTP (via REST API), and local (HSQLDB). The persistence manager is in charge of the order of which ones to use. If the REST API we have hosted is unavailable, it will auto switch to local persistence. The front end will query the associated manager classes for objects to present in either a form, list, or detail view. The associated manager then will query the persistence manager for a persistence class to use, and from there the provided persistence class, either HTTP or SQL, is in charge of querying/inserting to the correct backend source for data. It is then in charge of cleaning that said data and passing it back to the manager in a java model object as per the model definitions. 

![alt text](architecture_pic.jpg)
