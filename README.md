# DineoutRec
An Android application for Restaurant Venues near a particular location

## Overview
This project displays the list of Restaurant Venues near a particular location from the [Wolt Restaurants API](https://restaurant-api.wolt.com/)

API endpoints used:
- [https://restaurant-api.wolt.com/v1/pages/restaurants?lat=[LAT_VALUE]&lon=[LONG_VALUE]](https://restaurant-api.wolt.com/v1/pages/restaurants?lat=60.170187&lon=24.930599)

Important information:
 1. 'lat' query param representing the latitude coordinate of the location
 2. 'lon' query param representing the latitude coordinate of the location
 3. API used to show restaurant venues contains 4 sections having a common field 'template' which is used as the class discriminator from api response
   1. Below 4 values are possible for 'template', but for this assignment, only the first 3 are used, as 4. is for no-location but location always passed.
      Also, only 1. and 3. are shown to the user as per the requirement of the assignment
     * 1. venue-vertical-list (for Venue Item)
     * 2. banner-small (for Venue Category Item)
     * 3. no-content (for No Content near the location message title and description)
     * 4. no-location (for No location passed message title and description)
4. Error handling is done as follows:
   * 1. No Internet
   * 2. Request Timeout
   * 3. All other exceptions and errors are displayed as unknown
   * In all of the cases above, the user has the option to retry and refresh the restaurants' venue list
5. Location update is done every 10 seconds according to the assignment. The transition animation time is excluded from this consideration.
   So, it is a strict 10-second update. If the user refreshes the data within a certain time interval (12 seconds, for example), the corresponding
   location is used for that refresh. The same applies in case of an error i.e. if the user tries to retry to get the restaurants' venues list.

## ⚙️ Features
* Restaurants venues list near the location
* Updating the restaurants' venues list when the location changes
* Manual restaurants' venues list refresh button
* Favourite mark option for restaurant venues in the list

## 🚀 Technology Used

* DineoutRec is built using Kotlin and Native Android

## Setup and Installation

Follow the steps below to run the project. 

1. Download the zip file from the following link
```
$ Paste the link here
```
2. Open the unzipped project in Android Studio
```
$ File > Open > Select the unzipped project directory
```
3. Sync the project with Gradle:
```
$ Android Studio will prompt you to sync. Ensure all dependencies are downloaded.
  Make sure to use the same dependencies' versions
```
4. Run the app on an emulator or a physical device:
```
$ Turn on the USB Debugging and connect the Android device using a USB cable. 
  Alternatively, in the case of Android 11+, Wireless Debugging could be used as well.
  Click on the green play button or press `Shift + F10`.
```

## Architecture

MVI - Model View Intent  

## Tech Stack 🛠

- Native Android
- Kotlin
- Jetpack Compose
- Dagger-Hilt
- Material Design
- Kotlin Coroutines
- Kotlin Flows
- Retrofit2
- Coil
- Kotlinx Serialization
- Mockito
- JUnit

## Contact
For any questions, please reach out to me at dhiraj.chhabra.g@gmail.com
