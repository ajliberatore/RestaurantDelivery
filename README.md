# RestaurantDelivery

Sample app to display a list of Restaurants and detailed page of each when clicked. 

When the app is launched, you will be see a list of restaurants.
Clicking on a list item will bring you into the details of that restaurant.

This app demonstrates the Clean Architecture + MVVM architectural pattern in which I outline here: https://medium.com/@ajliberatore/android-clean-architecture-mvvm-4df18933fa9

### Clean Architecture + MVVM
This pattern will consist of three modules - Data, Domain, and Application.

**Data** is responsible for interacting data sources. It'll fetch the data and then map the objects to a domain object.
**Domain** is responsible for handling business logic and housing the model object that we will perform the logic with.
**Application** is responsible for the presenation layer (**MVVM** will live here) as well as Android components. This module can be submoduled. For example, it may make sense to have a device module that handles camera, location, sensors, etc.

### Libraries
**Dagger** is used to handle dependency injection. 

**RxJava** is used to handle streams of data. Since this is a simple project, it is only lightly using RxJava. We use it as a Retrofit call adapter, and manipulate the data downstream. We also a BehaviorSubject wrapper called a StickyAction. This allows the View to observe changes in the ViewModel. A StickyAction handles nulls, and consumes the values when observed. A normal BehvaiorSubject will keep the latest value for any new observer to see.

**Mockito** is used to handle unit testing. 

More: **Databinding**, **Retrofit**, **GSON**, **Picasso**
