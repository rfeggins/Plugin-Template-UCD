
def targetFile = "source.properties"
Properties properties = new Properties()
File propertiesFile = new File(targetFile)
propertiesFile.withInputStream {
    properties.load(it)
}

def runtimeString = 'a'
assert properties."$runtimeString" == '1'
assert properties.b == '2'

println properties


