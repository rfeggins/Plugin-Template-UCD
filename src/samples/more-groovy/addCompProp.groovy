
// http://www.joergm.com/2010/09/executing-shell-commands-in-groovy/
// https://www.ibm.com/support/knowledgecenter/en/SS4GSP_6.2.0/com.ibm.udeploy.api.doc/topics/udclient_setcomponentproperty.html


def ucdClient = "/home/vagrant/ucd-cli/udclient"
def setEnvProp = [ucdClient, "-weburl", "http://localhost:8080", "-username", "ADMIN", "-password", "ADMIN", "setComponentProperty", "-component", "target-component", "-name", "irvnetProp", "-value", "irvnetProp-val01" ] as String[]


def proc2 =  setEnvProp.execute()
proc2.waitFor()
println "return code: ${ proc2.exitValue()}" 
println "stdout: ${proc2.in.text}"
println "stderr: ${proc2.err.text}"
