# A detailed example of using Devless As your backend in an Android App

### Step One
Sign up with devless and choose the onclick install and go through the process and get your devless backend runnin on heroku or on your local. I did mine on heroku.

### Step Two
Click on the app section in your dashboard and edit the details most importantly copy your token and
save it somewhere we will come back to it

### Step Three
Go to the services tab and create a services tab and create a service. You can call your service anything.


### Step Four
Click on your service to take you to the service details page where you can add tables.
Add a table and give it a name and add the fields of your choice save it and youre done.


### NB Remember the service name, table name and token

#### All the above steps have to be follwed to arrive at the four things we will need for our android app to be able to talk to our devless backend. From this point I will break it in two parts that is adding devless to an already existing android app or creating one from scratch

# Adding Devless to an already existing project/ Creating a new project

### Step One
Download this zip file [here](https://drive.google.com/drive/u/0/folders/0B3FToPzFdRiXZVh2dE52R1VFemc "Title")  which contains the devless package and unzip it in your local

### Step one(skip if project exist already)
create a new project in android studio by following the wizard and choosing your preferences. Onve that is done go to step two.


### Step two
Enter android studio and Follow these steps.
![Alt text](/images/step2.png)

![Alt text](/images/step3.png)


![Alt text](/images/step5.png)

![Alt text](/images/step6.png)

![Alt text](/images/step7.png)

![Alt text](/images/step8.png)

![Alt text](/images/step9.png)

### Since we have both parts covered lets zzom straight into how to make our andorid app talk to our devless backend


### Step Three
open your app build.gradle file and paste this code there under dependencies
```Java
  dependencies {
    ......
    compile 'com.mcxiaoke.volley:library:1.0.+'
    compile project(":devless")
  }
```

### Step Four
Sync the project and boom your done. Lets go straight to the usage


# USAGE
### Create an instance of the Devless class in your activity and initialize it with your details. You need things to create an instance of the devless class.
#### Url of you app
#### Service name
#### Token
```Java
/*
set it up this way
  String appUrl = put your app url here;
  String serviceName = put your service name here;
  String devlessToken = put your token here;

  Devless devless = new Devless(devlessToken, appUrl, serviceName, this )  
*/

  String appUrl = "http://newerapper.herokuapp.com";
  String serviceName = "Grocery";
  String devlessToken = "7740b4b2303e32957a3215c344b8c21c";
  String tableName = "grocery_table";

  Devless devless = new Devless(devlessToken, appUrl, serviceName, this );
```
If this is done you've set develess up.  Its so simple!

## Lets make a query(get some stuff from our database in devless and load it into our app). You can do that with only the table name now lets see how its done.
#####  Requirements(tableName)
#### Do it this way
```Java
  //String tableName =  your table name

  String tableName = "grocery_list"
  devless.queryData(tableName, new Devless.QueryResponse() {
      @Override
      public void onSuccess(String result) {
          //Do whatever you want with the result. It returs a string
          //tv.setText(result);
          //Toast.makeText(this, result, Toast.LENGTH_LONG).show();
          //Do whatever you wanna do with the response here
      }
  });
```

## Let's post data into our database. You will need the tableName and some data to post lest do an example.

##### Requirements(tableName, data). NB data is in a form of Map<String, Object>.
##### Lets say we want to post {"name": "charles"} into our table in the backend assuming the only filed I our table is name. We will do it this way.

```Java
  //Create data according to your fields
  Map<String, Object> data = new HashMap<>();
  data.put("name","charles");
  //We've created the data we want to post cause our schema on the backend looks something like this
  //tableName =  "grocery_list"
  //fields  name: String

  //Lets post our data now
  //call the addData method on the instance you created earlier and pass in the data and table name

  devless.addData(data,tableName);
  //that's all the data will be created and return an http response of ok
```

### NB: Make sure your table name matches exactly with the one on the devless backend if its "Grocery" don't enter "grocery" and vice versa do things exactly how you did on the backend and you wont have any problems. More to come ie delete update etc. Bless you and keep enjoying devless its super fast and convenient.
