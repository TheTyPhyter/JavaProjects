public class ListNode {
    String name;
    String address;
    String city;
    String phoneNumber;
    ListNode next;
    public ListNode(String name,String address,String city,String phoneNumber){//
        this(name, address, city, phoneNumber, null);
    }
    public ListNode(String name,String address,String city,String phoneNumber, ListNode next){//our constructor
        // for new contacts
        this.name = name;
        this.address = address;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.next = next;//points to the next node in the list. points to null if this is the last object.
    }
    public String toString(){//the toString method returns the class attributes as a string.
        String ret = "";
        ret += this.name + "\n";
        ret += this.address + "\n";
        ret += this.city + "\n";
        ret += this.phoneNumber + "\n";
        return ret;
    }
}
