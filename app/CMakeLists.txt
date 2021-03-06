cmake_minimum_required(VERSION 3.6.0)

add_library(
        native_app_glue STATIC
        ${ANDROID_NDK}/sources/android/native_app_glue/android_native_app_glue.c)

target_include_directories(
        native_app_glue PUBLIC
        ${ANDROID_NDK}/sources/android/native_app_glue)

find_library(
        log-lib
        log
)

set(CMAKE_SHARED_LINKER_FLAGS "${CMAKE_SHARED_LINKER_FLAGS} -u ANativeActivity_onCreate")
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -Wall -Werror -Wno-unused-function")

add_library(helloworld-c
    SHARED
    src/main/cpp/helloworld-c.cpp)

add_library(native-gl
    SHARED
    src/main/cpp/native-gl.cpp)

add_library(native-concat
    SHARED
    src/main/cpp/native-concat.cpp)

add_library(native-callme
    SHARED
    src/main/cpp/native-callme.cpp)

add_library(native-exception
    SHARED
    src/main/cpp/native-exception.cpp)

add_library(native-throw
        SHARED
        src/main/cpp/native-throw.cpp)

add_library(native-mutable
        SHARED
        src/main/cpp/native-mutable.cpp)

add_library(native-lock
        SHARED
        src/main/cpp/native-lock.cpp)

add_library(native-thread
        SHARED
        src/main/cpp/native-thread.cpp)

add_library(native-abi
        SHARED
        src/main/cpp/native-abi.cpp)

add_library(native-plasma
        SHARED
        src/main/cpp/plasma.cpp)

target_link_libraries(
        helloworld-c
        android
        native_app_glue
        ${log-lib}
        EGL
        GLESv3
)

target_link_libraries(native-plasma
        android
        jnigraphics
        log
        m)