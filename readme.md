# Isolated IPC Demo (AIDL-based)

This project demonstrates **Inter-Process Communication (IPC)** in Android using **AIDL (Android Interface Definition Language)** between an app and an **isolated SDK running in a separate process**.

## Overview

- `RemoteService`: A `Service` defined in a separate process/module that implements `IRemoteService.aidl`.
- `IRemoteService.aidl`: Defines the AIDL interface used for IPC communication.
- `MainActivity`: The app component that **binds** to the `RemoteService`, invokes the remote `getMessage()` method, and displays the returned string in a `TextView`.

## Project Structure

```

io.matin.isolatedipc/       # Client app
└── MainActivity.kt         # Binds to the remote service

com.example.isolatedsdk/    # Isolated SDK
├── RemoteService.kt        # Implements the remote AIDL interface
└── IRemoteService.aidl     # AIDL file for IPC contract

```

## How to Run

1. Clone this repository into Android Studio.
2. Build and install the app.
3. Launch the app.
4. Click the **"Bind Service"** button to establish a connection with the isolated SDK and receive the message.

## Configurations

For **detailed setup and configuration instructions**, refer to [configurations.md](./configurations.md).

## License
This project is provided for educational purposes. You are free to modify and use it as per your needs.

