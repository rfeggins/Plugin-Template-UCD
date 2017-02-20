package irvnet.ItemManager

class ComponentPropertyFaker {                                    
    static void main(String... args) {          

        // prep for building the ucd cli command
        def ucdClient = "/home/vagrant/ucd-cli/udclient"
        def webUrl = "http://localhost:8080"
        def usr = "ADMIN"
        def pwd = "ADMIN"
        def cmd = "setComponentProperty"
        def cname = "target-component"
        def pname = "IrvProperty"
        def pval = "IrvValue01"

        // prep an output file
        String outputFile = "/home/vagrant/work/output.fil"
        File file = new File(outputFile)

        // make some dummy data
        Item item1 = new Item("item001", "First Item")
        Item item2 = new Item("item002", "Second Item")
        Item item3 = new Item("item003", "Third Item")
        def itemList = []

        // populate the list 
        itemList.add(item1)
        itemList.add(item2)
        itemList.add(item3)

        // write out the list of items to a file
        for (Item i : itemList) {
          println(i)

          def setProp = [ucdClient, "-weburl", webUrl, "-username", usr, "-password", pwd, \
                        cmd, "-component", cname, "-name", i.getId() , "-value", i.getDescription() ] as String[]


          def proc2 =  setProp.execute()
          proc2.waitFor()

        }

    }
}


