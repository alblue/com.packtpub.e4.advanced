Normally the commons-email and javax.mail would be contributed by
external bundles, and the Import-Package would be adjusted as follows:

Import-Package:
 ...,
 org.apache.commons.mail

This will then work when both commons-email and javax.mail are
bundled into the same OSGi runtime. However, for the purpose of
making this easier code to import, they have both been defined
on the Bundle-ClassPath and a copy embedded into this JAR.

 