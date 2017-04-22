# SignUp and Configure of Devless in an Android App

#### Step One
Get the devless backend working on your local or on heroku

#### Step Two
Launch your dashboard and navigate to the app section

![Alt text](/images/devless/step1.png)


#### Step Three
On the services tab, create a service



![Alt text](/images/devless/step2.png)
![Alt text](/images/devless/step3.png)
![Alt text](/images/devless/step4.png)



#### Step Four
Add a table and field of your choosing by clicking on your generated service

![Alt text](/images/devless/step5.png)
![Alt text](/images/devless/step7.png)
![Alt text](/images/devless/step10.png)


###### Take note of the service name, table name and token. These will be used for connecting an android app with a devless backend.

--------------------------

### Devless backend with existing App

### Step One
Download the arr file [here](https://raw.github.com/charlesagyemang/Devless_Android_SDK) which contains the devless package into your java project

### Step two
Enter android studio and Follow these steps.
![Alt text](/images/androider/step2.png)

![Alt text](/images/androider/step3.png)


![Alt text](/images/androider/step5.png)

![Alt text](/images/androider/step6.png)

![Alt text](/images/androider/step7.png)

![Alt text](/images/androider/step8.png)

![Alt text](/images/androider/step9.png)


#### Step Three
open your app build.gradle file and paste this code there under dependencies
```Java
  dependencies {
    ......
    compile 'com.mcxiaoke.volley:library:1.0.+'
    compile project(":devless")
  }
```

### Step Four
Sync the project and boom you're done. 
