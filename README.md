### This package lets you run a [devless](www.devless.io) backend with your android app

Read more about setting up devless [here](docs/devless-signup.md) 

# USAGE

### Create an instance of the Devless class in your activity and initialize it with your details. You need things to create an instance of the devless class.
*Url of you app*
*Service name*
*Token*

```Java
/*
set it up this way
  String appUrl = app url here;
  String serviceName = service name here;
  String devlessToken = token here;

  Devless devless = new Devless(devlessToken, appUrl, serviceName, this )  
*/

  String appUrl = "http://newerapper.herokuapp.com";
  String serviceName = "Grocery";
  String devlessToken = "7740b4b2303e32957a3215c344b8c21c";
  String tableName = "grocery_table";

  Devless devless = new Devless(devlessToken, appUrl, serviceName, this );
```
If this is done you've set Devless up.  Its so simple!

* Lets make a query(get some stuff from our database in devless and load it into our  app). You can do that with only the table name now lets see how its done.
*

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
