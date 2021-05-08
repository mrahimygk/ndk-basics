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

target_link_libraries(
        helloworld-c
        android
        native_app_glue
        ${log-lib}
        EGL
        GLESv3
)

