# CS3312_Proj
Canine Concierge is an app that helps users to walk their canine companions by arrangement.

## Release Notes
*New Features:*
- Login/Register
- Dog Owner Portal
  - Schedule Walk - select date/time and pack leader
  - Make Payment through PayPal Services
  - View Upcoming Walks - functionality to cancel walk
  - View Completed Walks - functionality to view walk’s map
- Pack Leader Portal
  - Set Available Hours
  - View Upcoming Walks - functionality to cancel walk
  - View Completed Walks - functionality to view walk’s map
- Live Asset Tracking - Pack Leader creates the map by tapping “Start Walk” in upcoming walks page when walk begins

*Bug Fixes:* N/A

*Known Bugs/Defects:*
- Pack Leaders unable to set vacation days
- Dog Owners and Pack Leaders unable to reset password
- Dog Owners and Pack Leader unable to edit account information (name/address/phone)


## Installation Information
#### Dog Owners and Pack Leaders:
Install the Canine Concierge app through the Google Play Store

#### Future Software Maintainers:
*Pre-requisites:*
- Install Android Studio
- Install & Download Android Device Emulator (if no access to Android phone)

*Dependent Libraries:*
- Paypal Android SDK 2.15
- Google Play Services 11.6.2

*Download Instructions:*
- Clone/Checkout this repository using Android Studio

*Build Instructions:*
1. Run gradle build
2. Install by using Android Studio's Run/Debug feature
3. Run Canine Concierge app from the device/emulator

*Installation of actual application:*
- Install application on device by connecting with USB cord
- Install application on emulator by simply clicking Run -> Select device emulator

*Run Instructions:*
- To run the application, tap the Canine Concierge icon on the device/emulator.

*Troubleshooting:*
- If application does not run, ensure you have Google Play version 11.6.2 or higher installed on your phone/emulator.
- If live tracking is not updating during dog walk, ensure the app is allowed to detect your location by going to Settings -> General -> Apps -> Configure Apps or App Settings -> App Permissions -> Your Location. Once here, find the Canine Concierge app and toggle location permissions to be allowed.
