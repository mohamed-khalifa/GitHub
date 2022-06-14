object Libs {
    // kotlin
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.KOTLIN}"

    // Android Jetpack (UI & navigation)
    const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APPCOMPAT}"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${Versions.FRAGMENT_KTX}"
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
    const val CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
    const val CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
    const val VIEW_MODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.VIEW_MODEL}"
    const val LIFECYCLE_RUNTIME_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE_KTX}"

    const val NAVIGATION_CORE = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
    const val NAVIGATION_KTX = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"

    //Network
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val RETROFIT_GSON_CONVERTOR = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}"

    // Coroutines
    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES}"

    // Pagination
    const val PAGING_COMMON_KTX = "androidx.paging:paging-common-ktx:${Versions.PAGING}"
    const val PAGING_RUNTIME_KTX = "androidx.paging:paging-runtime-ktx:${Versions.PAGING}"

    //Dependency Injection
    const val HILT_ANDROID = "com.google.dagger:hilt-android:${Versions.HILT}"
    const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.HILT}"

    //Image Loading
    const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
    const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${Versions.GLIDE}"

    // Testing
    const val JUNIT = "junit:junit:${Versions.JUNIT}"
    const val MOCKK = "io.mockk:mockk:${Versions.MOCKK}"
    const val COROUTINES_TEST =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.COROUTINES}"
    const val ARCH_CORE_TESTING = "androidx.arch.core:core-testing:${Versions.ARCH_CORE_TESTING}"
    const val ANDROIDX_TEST = "androidx.test.ext:junit:${Versions.ANDROIDX_TEST}"

}

