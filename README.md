# AutoDimension

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Platform](https://img.shields.io/badge/platform-android-green.svg)](https://developer.android.com)
[![](https://jitpack.io/v/Akaizz/AutoDimension.svg)](https://jitpack.io/#Akaizz/AutoDimension)

Scales `dp`/`sp` dimensions by the device's smallest width (`values-swXXXdp`), using a 360dp screen as the base.
On a `sw720dp` device `@dimen/_10dp` resolves to `20dp`.

> **Should you use this in a new app?** For new apps, especially Jetpack Compose apps, Google recommends
> [Window Size Classes](https://developer.android.com/develop/ui/compose/layouts/adaptive/use-window-size-classes)
> and adaptive layouts (show more content on bigger screens) with fixed `dp`/`sp` values, rather than
> scaling every dimension linearly. This library mainly targets View/XML projects that already rely on it,
> and designs that must keep exact proportions across screen sizes.

## Requirements

- minSdk 21
- Android Gradle Plugin 9.x, JDK 17+

## Install

**via JitPack**

`settings.gradle.kts`
```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}
```

`module/build.gradle.kts`
```kotlin
dependencies {
    implementation("com.github.Akaizz:AutoDimension:1.0.0")
}
```

## How to use

```xml
<TextView
    android:id="@+id/work_time_sheet"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:layout_margin="@dimen/_1dp"
    android:textSize="@dimen/_14sp" />
```

Available dimensions:

| Name | Range |
|---|---|
| `@dimen/_0_5dp` … `@dimen/_600dp` | step 0.5dp |
| `@dimen/minus_0_5dp` … `@dimen/minus_600dp` | step 0.5dp |
| `@dimen/_1sp` … `@dimen/_60sp` | step 1sp |

### Jetpack Compose

The same values can be read in Compose with `dimensionResource`, so mixed View/Compose screens stay consistent:

```kotlin
Modifier.padding(dimensionResource(R.dimen._16dp))
fontSize = dimensionResource(R.dimen._14sp).value.sp
```

`R` here is the library's R class (`gapo.autodimension.R`) because Android Gradle Plugin uses non-transitive R classes by default.

## Extensions

```kotlin
Context.dpToPx(dp: Number)
Context.pxToDp(px: Number)
```

## Foldables and flip phones

- The device's smallest width changes when a foldable is folded or unfolded. By default the activity is recreated and
  picks up the matching `values-swXXXdp` file automatically.
- If your activity declares `android:configChanges` with `screenSize|smallestScreenSize|screenLayout`, the activity is
  **not** recreated: views that are already inflated keep the old values until you re-inflate or re-apply them.
- Android picks the closest bucket that is **less than or equal to** the device's smallest width. Screens narrower
  than 360dp (e.g. some cover screens) use the `sw320dp` bucket, or the base 360dp values if they are narrower than 320dp.
  If you need an exact bucket for a specific device, check its smallest width (the sample app logs
  `smallestScreenWidthDp`), add it to the `dimens` list in [`autodimens.gradle`](autodimens.gradle) and regenerate.

## Regenerating the dimension files

The `values*/auto_dimens.xml` files are generated and committed. To change the buckets or ranges, edit the
`createDimension` task in [`autodimens.gradle`](autodimens.gradle), then run:

```bash
./gradlew :autodimens:createDimension
```

## Contribution

If you've found an error, please file an issue.

Patches and new samples are encouraged, and may be submitted by forking this project and submitting a pull request through GitHub.

### LICENCE

    Copyright 2026 Gapo

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
