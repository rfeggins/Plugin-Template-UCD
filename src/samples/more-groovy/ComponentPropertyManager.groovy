package irvnet.ItemManager

class ComponentPropertyManager {                                    
    static void main(String... args) {          

        // prep for building the ucd cli command
        def ucdClient = "/home/vagrant/ucd-cli/udclient"
        def webUrl = "http://localhost:8080"
        def usr = "ADMIN"
        def pwd = "ADMIN"
        def cmd = "setComponentProperty"
        def cname = "target-component"

        // grab the property listing 
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


        // write out the list of items to a file
        for (Item i : propList) {
          println(i)

          def setProp = [ucdClient, "-weburl", webUrl, "-username", usr, "-password", pwd, \
                        cmd, "-component", cname, "-name", i.getId() , "-value", i.getDescription() ] as String[]
          def proc2 =  setProp.execute()
          proc2.waitFor()

        }

    }
}


