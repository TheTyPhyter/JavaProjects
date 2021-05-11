public class PhoneBookManager {
    public ListNode list;
    public ListNode last;

    public void addContact(String name, String address, String city, String phoneNumber){//method that adds contacts
        ListNode node= new ListNode(name, address, city, phoneNumber);
        if(this.list == null){
            this.list = node;
            this.last = this.list;
        }else {
            ListNode temp = this.list;
            while (temp.next != null && name.compareTo(temp.name) >= 0){//we auto-sort by name when adding contacts
                temp = temp.next;
            }
            if(temp == this.list && name.compareTo(temp.name) < 0){
                node.next = this.list;
                this.list = node;

            }else{
                node.next = temp.next;
                temp.next = node;
                this.last = temp.next;
            }
        }
    }
    public String getContact(String name){
        ListNode temp = list;//we store a copy of the ListNode object and search the copy
        while (temp != null && !temp.name.equals(name)){
            temp = temp.next;
        }
        if (temp != null){
            return temp.toString();
        }
        return null;
    }
    public void delContact(String name){//method to remove contacts
        ListNode temp = this.list;
        while(temp.next != null && !temp.next.name.equals(name)){
            temp = temp.next;
        }
        if(temp.next != null){
            if(temp.next.next != null){
                temp.next = temp.next.next;//we remove an object from the list by changing the pointer of the
                // previous object to point to its pointer. If nothing is pointing to an object, its is "removed".
            }else{
                temp.next = null;//the last object's pointer is set to null.
            }
        }
    }
    public String toString(){//this prints the entire phonebook.
        String book = "";
        ListNode temp = this.list;
        while (temp != null){
            book += temp.toString();//this is ListNode.toString.
            temp = temp.next;
        }
        return book;
    }
}
