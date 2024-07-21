# Overview
Crypton is a library for encrypting and decrypting strings.

# Usage

## Encrypting
```java
de.cehlers88.utils.crypton.Encoder encoder = new de.cehlers88.utils.crypton.Encoder();
String original = "Hallo Welt!";
String encrypted = encoder.encrypt(original); // encrypted = sowas wie "4:%22ujkhvu%22%3E%7C66;%3F%22gzvgpfgfGpet%20rvkqp%22%3Evtwg%3F%22xcnwg%22%3E%22Unyy1](ry!%60qo0;B]%3C31ZB0u49*3;,oQ%22,"
```
## Decrypting
```java
de.cehlers88.utils.crypton.Encoder encoder = new de.cehlers88.utils.crypton.Encoder();
String original = "Hallo Welt!";
String encrypted = encoder.encrypt(original); // encrypted = "..."
String decrypted = encoder.decrypt(encrypted); // decrypted = "Hallo Welt!"
```

## Installation

### Gradle
```gradle
repositories {
    maven { url 'https://jitpack.io' }
}
dependencies {
    implementation 'com.github.cehlers88:crypton_java:#####'
}
```
(replace ##### with the desired version)