These bundles are required for the ServiceLoader examples, and can be
downloaded from Maven Central.

http://central.maven.org/maven2/org/ow2/asm/asm-all/4.0/asm-all-4.0.jar
http://central.maven.org/maven2/org/apache/aries/org.apache.aries.util/1.0.0/org.apache.aries.util-1.0.0.jar
http://central.maven.org/maven2/org/apache/aries/spifly/org.apache.aries.spifly.dynamic.bundle/1.0.0/org.apache.aries.spifly.dynamic.bundle-1.0.0.jar

To import these into Eclipse, go to File->Import->Project->Plug-in Development->
Plug-ins and Fragments, and then navigate to this directory. It will offer to
import the following bundles:

org.apache.aries.spifly.dynamic.bundle
org.apache.aries.util
org.objectweb.asm.all

Import these by selecting the 'Add All->' button from the import dialog. Three
projects will be created for the these bundles. These can be added to the
OSGi framework runtimes in order to allow the service code to run.

