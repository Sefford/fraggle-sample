fraggle-sample
==============

A demonstration of Fraggle capabilities. [Fraggle](http://fraggle.sefford.com/) is a wrapper to Android's FragmentManager that encapsulates
common logic used over the applications. 
 
This project is just a sample project demonstration of its capabilities

Usage
-----

Clone the repo and execute `mvn clean install`. It requires internet connection to download its
dependencies, Fraggle itself.

You can install it to an emulator or phone via `mvn android:deploy android:run`. It requires an
Android device with API 15 or superior.

Fraggle-Sample will not require any specific permissions

Explanation
-----------

Once you open the Sample you will be presented with an Activity that shows a Fragment. The Fragment
number will be incremental from the current Fragment. The name and the strip of the bottom serves
as identification of different Fragments.

Clicking on the button will create and add a new Fragment configurable via some options.

You are presented every time with four selectable options:

- Selecting `Make it return to First` will create a fragment whose onBackTarget returns the Fragment #1 to showcase the custom flow return.
- If `Is Single Instance` is selected will create a `Fragment #1` instance and will force Fraggle to pop back to the initial Fragment.
- `Show a Toast on Back` will open a Fragment that first will show a Toast, then go back to demonstrate the feature of custom actions on backPressed.
- If `Is Entry Fragment` a Fragment will create a Fragment that will trigger Fraggle to reach an Activity `finish()` termination by pressing the back buttons.

For more information about the concepts discussed, visit [Fraggle page](http://fraggle.sefford.com/)

License
-------
    Copyright 2014 Sefford.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.