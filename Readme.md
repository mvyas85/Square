Thank you for taking time to read my project :) 
# Demo
![Alt Text](gif.gif)

# Square
CodeChallenge
Square interview code challenge to read employee ( valid/malformed/empty) data from cloud
services and display them in application

# Square Project
## What is Included ?
1. Demo Video (GIF)
- Shows functionality of opening Square employee directory application
- Display three buttons on main screen given to perform different types of requests.
2. Project code ZIP folder with README included in it.

## How to load project in Android studio
- Step 1 : Download the Square.zip folder
- Step 2 : Extract Zip folder
- Step 3 : Open Android Studio
- Step 4 : select option “Open an Existing Project”
- Step 5: Select Square > build.gradle file
This may take couple of minutes to load and index the project

## How to run the application on Device/Simulator
Prerequisite :
Project is loaded in Android studio
Debug android device is connected to your computer or Simulator is running
- Step 1 : In top right corner of Android studio click on “Run app”

## How to run APK without Android studio setup
Prerequisite : You need to have ADB installed on your computer
- Step 1 : download APK on your computer and navigate to the folder in your terminal
- Step 2 : run the following command
```bash
adb install -r -f build/outputs/apk/debug/app-debug.apk
```

## Points to Highlight
1. Project completed in both Kotlin & Java uses Jetpack libraries
- Coroutines (kotlin)
- OkHttp (retrofit)
- Retrofit  
- Hilt dagger
- Glide
- Room
- Lifecycle viewmodel  (jetpack)
- Lifecycle livedata  (jetpack)  
- UI (jetpack)
2. Using MVVM architecture
3. Handling configuration changes using ViewModel lifecycle
4. Used ViewBinding
5. Retrofit2 to make API calls
6. Used LiveData to automatically refresh the UX when data is updated
7. Room DAO caching to store employee data
8. Good code review practice with inline comments
9. Added Logs to follow the flow from log perspective

## What is next ?
1. Better UX – Design improvements
2. Internationalization to support multiple language
3. Error handling for better user experience (wifi not connected and handle other scenarios)
4. Other features to save user's preferences can be added
6. Add multiple logs throughout so it can become production ready
7. Unit tests, Instrumentation tests, Automation tests.
