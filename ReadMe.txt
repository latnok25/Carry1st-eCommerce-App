Carry1st-eCommerce-App


Project Overview


Carry1st eCommerce App is an Android application designed to efficiently manage products and cart items. The application uses a multi-module architecture following the MVVM (Model-View-ViewModel) pattern. It fetches product data from a remote server using Retrofit and leverages Jetpack Compose for the UI.


Features


Product Listing: Display a list of products fetched from a server.


Cart Management: Add, remove, and clear items in the cart.


View Product Details: Navigate to detailed views of selected products.


Database Integration: Local storage of cart items using Room database.


Architecture


MVVM (Model-View-ViewModel)


Model: Manages data and business logic.


View: Handles UI and user interactions using Jetpack Compose.


ViewModel: Acts as a bridge between the View and Model, managing UI-related data in a lifecycle-conscious way.


Multi-Module Setup: The project is divided into multiple modules for better separation of concerns and to manage dependencies effectively.


Key Components and Third-Party Libraries


Retrofit: For network operations to fetch product data from the server.


Room: Provides local data storage for cart items.


Hilt: Used for dependency injection to manage the lifecycle and dependencies across modules.


Coroutines: Utilized for asynchronous operations and managing background tasks.


Coil: Image loading library used to display product images.


Mockk: Mocking library for testing purposes.


JUnit: Framework for writing and running tests.


Jetpack Compose: Modern toolkit for building native Android UI.
Setup


Clone the Repository:


git clone https://github.com/latnok25/Carry1st-eCommerce-App.git


Navigate to the Project Directory:


cd Carry1st-eCommerce-App


Build the Project:


./gradlew build


Run the Application:


Open the project in Android Studio.


Select an emulator or a connected device.


Click on the 'Run' button or use the following command:


./gradlew installDebug


Usage


Launching the App: Start the application to see the list of products fetched from the server.


Managing Cart Items: Add or remove items from the cart. Changes are reflected immediately.


Product Details: Click on a product to view detailed information.


Assumptions Made


The server endpoint is functional and returns product data in the expected format.


The Room database is properly configured, and schema migrations are handled.


Network connectivity is required for fetching product data from the server.


Choices Around Plugins and Libraries


Retrofit: For making API calls and handling network responses.


Room: For providing a local database to store cart items and manage offline data.


Hilt: For simplifying dependency injection and managing component lifecycles.


Coroutines: For efficient asynchronous programming and handling background tasks.


Coil: For image loading and display.


Mockk: For mocking objects in tests.


JUnit: For testing the application's business logic.


Testing


Unit Tests: To verify the correctness of business logic and use cases.


Integration Tests: To ensure that various components of the application work together as expected.