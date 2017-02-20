package irvnet.ItemManager

class Item {

    def Item(String _id, String _description ) {
       this.id = _id
       this.description = _description
    }

    private String id 
    String getId() { return id }
    void setName(String id) { 
        if (id == null) {
            this.id = "" 
        } else {
            this.id = id
        } 
    }

    private String description 
    String getDescription() { return description }
    void setDescription(String description ) { this.description = description }

    public String toString() {
       String rtnStr = "id: " + this.id + " desc: " + this.description
       return rtnStr
    }
}
