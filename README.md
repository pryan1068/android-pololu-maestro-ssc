![Pololu Logo](https://raw.github.com/pryan1068/android-pololu-maestro-ssc/master/res/drawable-hdpi/ic_launcher.png)
Maestro Serial Servo Controller (SSC)
======

Example Code for accessing Pololu.com hardware

This project displays 6 sliders for controlling servos via a Pololu Maestro Serial Servo Controller. It should work with the 6, 12 or 24 board models. There is skeleton code for other commands, however setTarget() is the only one that is currently working. This code accesses the board via native USB calls. This eliminates issues with virtual serial port drivers (missing drivers, virtual device name conflicts, etc). The Pololu Maestro USB API is not currently published. This code is based off of reverse-engineering their C# example code.

This is intended to demonstrate how to use an Android device to control Maestro products that are attached via an On-The-Go (OTG) USB cable (Try EBay or Adafruit).

![Class Diagram](https://raw.github.com/pryan1068/android-pololu-maestro-ssc/master/Class%20Diagram.jpg)

## Requirements:

Android Device running Android 3.1 (Honeycomb) API Level 12 or later
OTG USB Cable to force Android into USB host mode
Pololu Maestro Serial Servo Controller Board

## Getting Started:

NOTE: Asof Jelly Bean (4.2.2), you need to plug your Android device into your PC via a USB cable to get it to prompt you for permission to access the device. Make sure you select the checkbox when you give it permission. This will allow it to work via ADB Konnect or similar.

Pull down the project, connect your Android device via a network based adb (ADB Konnect or similar), connect the Maestro board via an OTG cable, and run the project. Once it's downloaded, you should be able to reconnect the USB cable and it will ask if you want to run MaestroSSC by default: check yes and press ok. The GUI should come up. 

## Resources:

[Pololu Maestro Servo Controller Users Guide](http://www.pololu.com/docs/0J40)
