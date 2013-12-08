This contains a demonstration of how to run Equinox with an SSH server.

To run it, execute 

  java -jar org.eclipse.osgi_3.9.1.v20130814-1242.jar

and then run

  ssh -p 1234 equinox@localhost
  plink -P 1234 -u equinox localhost (for Windows users)

The default password is equinox, which prompts the user for a log-in
to a different password after log-in complete. The file configuration/store
is used to store the passwords, and once changed this can be used to
update the content.

Note that the org.eclipse.equinox.console.jaas.fragment is required, as
this adds a DynamicImport-Package for the LoginModule provided by the
org.eclipse.equinox.console.ssh bundle to the org.apache.sshd.core bundle.
