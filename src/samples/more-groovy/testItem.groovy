package irvnet.ItemManager


class Main {                                    
    static void main(String... args) {          

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
          file << i.toString() + "\n"
        }

    }
}


