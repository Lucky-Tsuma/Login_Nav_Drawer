Project illustrates the use of viewbinding and how to make a navigation drawer.
The following line should be added to the build.gradle(app mkodule) file:
  android {
    buildFeatures {
        viewBinding = true
    }
  }
  
To use the drawerlayout(for the navigation draweer), add the following dependancy:
  androidx.drawerlayout:drawerlayout:1.1.1(the version may change. It does)

While building the navigation drawer, remember to replace the Actionbar with a Toolbar which is more flexible(Check the style and the manifest file)

  
 
