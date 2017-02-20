package irvnet.ItemManager


def propList = []

Properties properties = new Properties()
File propertiesFile = new File("bpm-boilerplate.properties")
propertiesFile.withInputStream {
   properties.load(it)
}

properties.each {
    String rtnStr = it.toString()
    def (propName, propVal) = rtnStr.tokenize("=")
    Item i = new Item(propName, propVal)
    propList.add(i)
}


for (Item i : propList) {
     println(i)
}
 

