//written by Anthony Spears
//This is a simple phone book using linked lists.
//for extra credit, this program will auto-sort contacts as you add them alphabetically by name and city. I also made
//use of a hashmap to look up phonebooks.

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PhoneBookTest {
    public static void main(String[] args){//main functions as a menu for the user
        HashMap<String, PhoneBookManager> phoneBooks = new HashMap<>();//we create a hashmap and use it to look
        // up phonebooks.
        Scanner scanner = new Scanner(System.in);
        String menu = "What would you like to do?\n" +
                "enter \"a\" to add a contact\n" +
                "enter \"q\" to close the phonebook\n" +
                "enter \"p\" to print a contact\n" +
                "enter \"d\" to delete a contact\n" +
                "enter \"pa\" to print all contacts";
        System.out.println("Welcome to your phonebook, " + menu);
        String option = scanner.nextLine().toLowerCase();
        while (!option.equals("q")){//as long as the user doesn't quite, we are still running.
            if(option.equals("a")){
               phoneBooks = add(phoneBooks);

            }else if (option.equals("p")){
                System.out.println("What contact would you like to print?");
                Scanner print = new Scanner(System.in);
                String printContact = print.nextLine();
                printContact(phoneBooks, printContact);
            }else if (option.equals("d")){
                phoneBooks = del(phoneBooks);
            }else if (option.equals("pa")){
                printPB(phoneBooks);
            }
            System.out.println(menu);
            option = scanner.nextLine().toLowerCase();
        }
    }
    public static HashMap<String, PhoneBookManager> add(HashMap<String, PhoneBookManager> phoneBooks){//adds contacts
        //and returns the updated hashmap so that it can be searched for phonebooks.
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a contact name: ");
        String name = input.nextLine();
        System.out.println("Enter an address: ");
        String address = input.nextLine();
        System.out.println("Enter a city: ");
        String city = input.nextLine();
        System.out.println("Enter a phone number: ");
        String phonenumber = input.nextLine();
        if (!phoneBooks.containsKey(city)){//phonebooks are created by city, we check to see if the phonebook exists
            PhoneBookManager temp = new PhoneBookManager();
            temp.addContact(name, address, city, phonenumber);
            phoneBooks.put(city, temp);
        }else {
            PhoneBookManager phoneBook = phoneBooks.get(city);
            phoneBook.addContact(name, address, city, phonenumber);
            phoneBooks.put(city, phoneBook);
        }
        printContact(phoneBooks, name);//prints the newly added contact so the user can confirm accuracy
        return phoneBooks;
    }
    public static void printContact(HashMap<String, PhoneBookManager> phoneBooks, String name){//searches every phone
        // book for a specific contact and prints out the details
        for (PhoneBookManager phoneBook : phoneBooks.values()){
            String contact = phoneBook.getContact(name);
            if(contact != null){
                System.out.println(contact);
                break;
            }
        }
    }
    public static HashMap<String, PhoneBookManager> del(HashMap<String, PhoneBookManager> phoneBooks){//method to remove contacts
        System.out.println("What contact would you like to delete?");
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        for (Map.Entry<String, PhoneBookManager> entry : phoneBooks.entrySet()){
            PhoneBookManager phoneBook = entry.getValue();
            String contact = phoneBook.getContact(name);
            if(contact != null){
                phoneBook.delContact(name);
                phoneBooks.put(entry.getKey(), phoneBook);
                return phoneBooks;
            }
        }
        System.out.println("Contact not found!");
        return phoneBooks;
    }
    public static void printPB(HashMap<String, PhoneBookManager> phoneBooks){//prints an entire phonebook
        System.out.println("What phone book would you like to print? \n type \"all\" for all phone books type" +
                " \"quit\" to return to the main menu");
        System.out.println(phoneBooks.keySet());
        Scanner input = new Scanner(System.in);
        String city = input.nextLine();
        if(!city.equals("quit")){
            if (!city.equals("all")){
                if(phoneBooks.containsKey(city)){
                    System.out.println(phoneBooks.get(city).toString());
                }else{
                    System.out.println("Phonebook not found!");
                    printPB(phoneBooks);
                }
            }else{
                for (PhoneBookManager phoneBook : phoneBooks.values()){
                    System.out.println(phoneBook.toString());
                }
            }
        }
    }
}
