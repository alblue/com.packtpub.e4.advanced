#include "com_packtpub_e4_advanced_c_Maths.h"
#include "other.h"

JNIEXPORT jint JNICALL Java_com_packtpub_e4_advanced_c_Maths_nativeAdd
	(JNIEnv *env, jclass c, jint a, jint b) {
#ifdef USE_OTHER
	return otherAdd(a,b);
#else
	return a+b;
#endif
}

