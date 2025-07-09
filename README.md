# SoleSync 👟✨

[![Kotlin](https://img.shields.io/badge/Kotlin-0095D5?style=for-the-badge&logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com/)
[![Firebase](https://img.shields.io/badge/Firebase-FFCA28?style=for-the-badge&logo=firebase&logoColor=black)](https://firebase.google.com/)
[![Firestore](https://img.shields.io/badge/Firestore-FF6D00?style=for-the-badge&logo=firebase&logoColor=white)](https://firebase.google.com/docs/firestore)
[![LiveData](https://img.shields.io/badge/LiveData-F57C00?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com/topic/libraries/architecture/livedata)
[![MVVM](https://img.shields.io/badge/Architecture-MVVM-orange?style=for-the-badge)](https://developer.android.com/topic/libraries/architecture/viewmodel)

SoleSync is a modern Android e-commerce application built with **Kotlin** that offers a seamless and real-time shopping experience for footwear. Leveraging the power of **Firebase Firestore** and **Live Data** within an **MVVM architecture**, SoleSync provides dynamic pricing, up-to-date inventory, and a highly responsive user interface.

## 🌟 Features

* **User Authentication:** Secure sign-up and login functionalities.
* **Curated Product Collection:** Browse a diverse range of footwear.
* **Real-time Updates:** Dynamic pricing and inventory updates powered by Firestore and Live Data.
* **Personalized Recommendations:** Discover products tailored to your preferences.
* **Intuitive Cart Management:** Easily add, remove, and adjust item quantities in your cart with real-time total calculations.
* **Product Details:** View comprehensive product information, including sizes and ratings.
* **User Profile Management:** Access and manage your personal details.
* **Aesthetic UI/UX:** Clean, modern, and user-friendly interface designed for a smooth shopping journey.

## 📸 Screenshots

| Splash Screen | Welcome Screen | Sign Up | Login |
| :-----------: | :------------: | :-----: | :---: |
| <img src="https://github.com/Lakshaydang47/SoleSync/blob/master/splashscreen.jpg?raw=true" alt="splash screen" width="200"/> |  <img src="https://github.com/Lakshaydang47/SoleSync/blob/master/intro.jpg?raw=true" alt="intro" width="200"/> | <img src="https://github.com/Lakshaydang47/SoleSync/blob/master/signup.jpg?raw=true" alt="signup" width="200"/> | <img src="https://github.com/Lakshaydang47/SoleSync/blob/master/login.jpg?raw=true" alt="login" width="200"/> |

| Home Screen | Home Screen (Loading) | Product Detail | Cart Screen |
| :---------: | :-------------------: | :------------: | :---------: |
| <img src="https://github.com/Lakshaydang47/SoleSync/blob/master/mainscreen.jpg?raw=true" alt="home screen" width="200"/> | <img src="https://github.com/Lakshaydang47/SoleSync/blob/master/loadinglivedata.jpg?raw=true" alt="loading live data" width="200"/> | <img src="https://github.com/Lakshaydang47/SoleSync/blob/master/loadinglivedata.jpg?raw=true" alt="product detail" width="200"/> | <img src="https://github.com/Lakshaydang47/SoleSync/blob/master/cart.jpg?raw=true" alt="cart screen" width="200"/> |

| Profile Screen |
| :------------: |
| <img src="https://github.com/Lakshaydang47/SoleSync/blob/master/profile.jpg?raw=true" alt="profile" width="200"/> |


## 🛠️ Technologies Used

* **Kotlin:** Primary programming language for Android development.
* **Android Jetpack:**
    * **MVVM Architecture:** Model-View-ViewModel for a robust, testable, and maintainable codebase.
    * **Live Data:** Lifecycle-aware observable data holder for real-time UI updates.
    * **ViewModel:** Stores and manages UI-related data in a lifecycle-conscious way.
* **Firebase:**
    * **Firestore:** NoSQL cloud database for real-time data synchronization and flexible data storage.
    * **Firebase Authentication:** Secure user authentication system.
* **Gradle:** Build automation tool.
* **XML Layouts:** For designing the user interface.

## 🚀 Getting Started

To get a local copy up and running, follow these simple steps.

### Prerequisites

* Android Studio Arctic Fox (or newer)
* Kotlin plugin for Android Studio
* A Firebase project configured for Android
* Google Play Services installed on your emulator/device

### Installation

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/your-username/SoleSync.git](https://github.com/your-username/SoleSync.git)
    cd SoleSync
    ```
2.  **Set up Firebase:**
    * Create a new Firebase project in the [Firebase Console](https://console.firebase.google.com/).
    * Add an Android app to your Firebase project.
    * Follow the instructions to download the `google-services.json` file.
    * Place the `google-services.json` file into your `app/` directory of the cloned project.
    * **Important:** If you're forking this repository and making it public, consider adding `app/google-services.json` to your `.gitignore` and instructing users to set up their own Firebase project. For private team development, it's often included.

3.  **Open in Android Studio:**
    * Open the project in Android Studio.
    * Let Gradle sync the project dependencies.

4.  **Run the app:**
    * Connect an Android device or start an emulator.
    * Click the `Run` button (▶️) in Android Studio.

## 🤝 Contributing

Contributions are what make the open-source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! ⭐ Thanks again!

1.  Fork the Project
2.  Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3.  Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4.  Push to the Branch (`git push origin feature/AmazingFeature`)
5.  Open a Pull Request

## 📄 License

Distributed under the MIT License. See `LICENSE` for more information.

## 📞 Contact

Lakshay Dang - [Your Email](mailto:lakshsaydang764@gmail.com)
Project Link: [https://github.com/your-username/SoleSync](https://github.com/your-username/SoleSync)

---
