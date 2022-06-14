# Github App

## 1. Introduction
Github Android app lets the user search for GitHub users and when clicking on one of the search results it opens a details screen for the selected result.
## 2. Libraries

- Retrofit
- Coroutines
- Paging3
- Navigation
- Glide
- Dagger-hilt
- MockK


## 3. Technical design
- App follows MVVM with Clean Architecture.
- Navigation component handles navigation in the app.
- Kotlin DSL for handling dependencies.
- Hilt for dependency injection.
- Coroutines & Retrofit for threading & consuming Apis.
- Paging3 for data pagination.
- Glide for ImageLoading.
- Stateflow to notify ui with updates.
- MockK & JUnit are used for testing.

**Note: please add your github token in system Environment variable GITHUB_TOKEN
