#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_data_di_NetworkModule_apiKeyFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string apiKey = "rc5Jljnq92X5JKGCWVGg3ebvlGFH5QZe";
    return env->NewStringUTF(apiKey.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_data_di_NetworkModule_baseURLFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string baseUrl = "rc5Jljnq92X5JKGCWVGg3ebvlGFH5QZe";
    return env->NewStringUTF(baseUrl.c_str());
}