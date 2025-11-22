# Shopping List App

A modern, feature-rich Android application for managing shopping lists, built with **Jetpack Compose** and **Clean Architecture**.

## 📱 Features

-   **Unified Category View**: View all your shopping items grouped by their categories on a single screen.
-   **Category Management**: Create, edit, and delete custom categories.
-   **Item Management**: Add, edit, delete, and toggle shopping items directly within their categories.
-   **Theme Support**: Toggle between Light and Dark modes with persistent preference storage.
-   **Offline First**: All data is stored locally using Room Database.

## 🛠 Tech Stack

-   **Language**: [Kotlin](https://kotlinlang.org/)
-   **UI Toolkit**: [Jetpack Compose](https://developer.android.com/jetpack/compose) (Material 3)
-   **Architecture**: Clean Architecture (Presentation, Domain, Data layers)
-   **Pattern**: MVI (Model-View-Intent)
-   **Dependency Injection**: [Koin](https://insert-koin.io/)
-   **Local Storage**: [Room Database](https://developer.android.com/training/data-storage/room)
-   **Preferences**: [DataStore](https://developer.android.com/topic/libraries/architecture/datastore)
-   **Asynchrony**: Kotlin Coroutines & Flow
-   **Navigation**: [Jetpack Navigation Compose](https://developer.android.com/guide/navigation/navigation-compose) (Type-safe)

## 🏗 Architecture Overview

The project follows the principles of **Clean Architecture** to ensure separation of concerns and testability.

### Layers

1.  **Presentation Layer**:
    -   **UI**: Composable functions (`CategoryListScreen`, `ShoppingItemRow`).
    -   **ViewModel**: Handles UI logic and state management (`CategoryListViewModel`).
    -   **State/Event/Effect**: Implements the MVI pattern for predictable state updates.

2.  **Domain Layer**:
    -   **Models**: Pure Kotlin data classes (`Category`, `ShoppingItem`).
    -   **Use Cases**: Encapsulate business logic (`GetShoppingItemsUseCase`, `AddShoppingItemUseCase`, etc.).
    -   **Repositories**: Interfaces defining data operations.

3.  **Data Layer**:
    -   **Local**: Room Database implementation (`ShoppingDatabase`, `CategoryDao`, `ShoppingDao`).
    -   **Entities**: Database tables (`CategoryEntity`, `ShoppingItemEntity`).
    -   **Repository Implementation**: Concrete implementations of domain repositories.

## 🚀 Getting Started

1.  **Clone the repository**:
    ```bash
    git clone https://github.com/yourusername/ShoppingListApp.git
    ```
2.  **Open in Android Studio**:
    -   Open Android Studio and select "Open an existing Android Studio project".
    -   Navigate to the cloned directory.
3.  **Build and Run**:
    -   Wait for Gradle sync to complete.
    -   Select an emulator or connected device.
    -   Click the "Run" button (green play icon).

## 📂 Project Structure

```
com.example.shoppinglistapp
├── app                 # Application class and DI setup
├── core                # Common extensions and utilities
├── data                # Data layer (Room, DataStore, Repositories)
├── domain              # Domain layer (Models, UseCases, Repository Interfaces)
└── presentation        # UI layer (Screens, ViewModels, Theme, Components)
```
