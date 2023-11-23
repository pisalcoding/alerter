# Alerter for Android
<div style="text-align:center; width: 100%;">
  <img src="demo.gif" width="300" height="auto" align="center"> 
</div>

## Usage

> Step 1: Add this your root build.gradle
```java
repositories {  
	...
    maven { url "https://jitpack.io" }
}

```
> Step 2: Add this your app build.gradle
```java
dependencies {
	implementation 'com.github.pisalcoding:alerter:Tag'
}
```

> Step 3: Show an alert dialog from your activity or fragment

### Simple 
```kotlin
Alerter.success()
    .withTitle("Success")
    .withMessage("Congratulations!")
    .show(supportFragmentManager, "Tag")
```

### More customization 
```kotlin
Alerter.info()
    .withTitle("Tips")
    .withMessage("Look away from your screens every 20 minutes.")
    .setIconDrawable(
        ResourcesCompat.getDrawable(resources, drawable.round_check_circle_24, theme)
    )
    .setTimeoutMillis(4000)
    .setOnDismissListener {
        // Do something after dialog is dismissed
    }
    .show(supportFragmentManager, "Tag")
```


