FBLA "MarkAPPel" App

By Nick Li

Mark Keppel High School

Alhambra, CA

MarkAPPel is a mobile application for Android devices that is made for students to check information about their school (Mark Keppel). When the app is launched, the user must either login or signup using their email address. They may also connect and sign in through their Google Account. Once in the app, users have an option to add their current schedule, find extracurricular activities about their school, know about the school's lunch menu, email their teacher and staff, and check the up-to-date school calendar. This app is made for students of Mark Keppel High School where all the extracurricular activities, lunch menu, staff, and calendar are all based on the school. This app uses Firebase User Authentication to allow user login and signin and Firebase Realtime Database to store user information. This app has no code errors and is currently bug free, although if a user finds out about a bug, they can use the bug report function to report a bug.

Features
- Designed for Andorid devices with API 16+
- Users can login and signup through the app
- Users can connect to their Google Account and sign in using their Google Account
- Users able to upload their own schedule and check it anytime
- A list of extracurricular activities and advisors of each activity provided
- Users able to see current lunch menu for the school
- A list of the staff directory is provided and users are able to email staff through the application
- A updated calendar for the whole school year is accesible
- Bug Report function allowing users to report bugs found

Folder Layout
/app/src/main/ -> contains Android Studio solution file
/app/release/app-release.apk -> downloadable and deployable apk file

Build Instructions for Android Studio
- Android Studio with SDK Platform "Android 11.0" and "Android API 31"
In order to build you will need Android Studio 11.0 SDK Platform installed as well as Android API 31. In addition, if the app is not executed on a connected Android mobile device, an emulator with API 16+ must be installed. This app only works for Android devices with API 16+. Upon opening Android Studio set the Run/Debug configurations to "app" and click "run 'app'" to launch app onto emulator or connected Android device.

Application APK Install/Run Instructions (Android Devices)
/app/release/app-release.apk is a deployable .apk file that could be downloaded on to android devices for use. 

Resources Used
- Menu Icons made using Ucraft - https://www.ucraft.com/free-logo-maker
- MKHS Aztec Icon/Logo - https://scorebooklive.com/california/basketball/teams/37565-mark-keppel-aztecs
- Transparent Logo Maker - https://www.adobe.com/express/feature/image/transparent-background

Software and Services Used
- GitHub - https://github.com
GitHub was used to store code and make revisions.
- Firebase - https://firebase.google.com/
Firebase was used for User Authentication and was utilized for its Realtime Database to store information user creates.
- Android Studio - https://developer.android.com/studio
IDE for developing app using Java code.
Design was coded in XML code.

References
- FBLA-PBL, www.fbla-pbl.org/
- FBLA Topics, https://www.fbla-pbl.org/fbla-topics/

License
The MIT License (MIT)

Copyright (c) 2022 Nick Li

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software  without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit  persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR  PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR  OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
