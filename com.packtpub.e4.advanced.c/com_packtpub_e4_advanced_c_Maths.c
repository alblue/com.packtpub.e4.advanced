#include "com_packtpub_e4_advanced_c_Maths.h"
#include "other.h"

JNIEXPORT jint JNICALL Java_com_packtpub_e4_advanced_c_Maths_add
	(JNIEnv *env, jclass c, jint a, jint b) {
	return otherAdd(a,b);
}

