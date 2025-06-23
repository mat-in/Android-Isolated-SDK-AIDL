
# Configurations for Isolated IPC (AIDL-based) Demo

This document provides the setup, configurations, and build steps required to run the **Isolated IPC Demo** using AIDL and bound services.

---

## 📁 AIDL File Placement

- Place the `.aidl` file in:

```

src/main/aidl/<package directory structure>

```

Example:

```

src/main/aidl/com/example/isolatedsdk

````

- Must be present **in both:**
  - The **SDK module**
  - The **App module**
  
➡> **Both should have the same directory structure and identical `.aidl` file.**

---

## ⚙️ Gradle Configuration

### Module-level (`build.gradle`) — SDK & App Modules

```kotlin dsl
android {
    buildFeatures {
        aidl = true
    }
    sourceSets["main"].aidl.srcDir("src/main/aidl")
}
````

---

### Project-level (`settings.gradle`)

```kotlin dsl
include(":isolatedsdk")
```

---

## 📦 Dependencies Setup

* Add the SDK module to your app via:

```kotlin dsl
dependencies {
    implementation project(":isolatedsdk")
}
```

---

## ▶ Building AIDL Interfaces

Use the following Gradle tasks to generate AIDL stub classes:

```bash
# Generate AIDL stub classes for the SDK
./gradlew :isolatedsdk:compileDebugAidl --info

# Generate AIDL stub classes for the App
./gradlew :app:compileDebugAidl --info
```

The generated classes will be located in:

```
<module>/build/generated/aidl_source_output_dir/
```

---

## Verifying Service and Processes

To verify that the remote service is available and running:

```bash
adb shell pm query-services -a com.example.isolatedsdk.REMOTE_SERVICE
```

To check running processes:

```bash
adb shell ps -ef
```

---

## 📄 Reference
For **usage** and **code examples**, refer to [README.md](./README.md).

