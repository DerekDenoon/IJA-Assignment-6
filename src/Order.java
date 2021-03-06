/*
Author: Denoon, Derek
Date: July 9 2021
Title: IJA Assignment 6
Overview: Order Management System
*/

import java.util.Scanner;

public class Order {
    public static final Scanner input = new Scanner(System.in);
    // private variables
    private boolean validDate = true;
    private boolean altValidDate = true;
    private boolean altDateChoose;
    private int productId;
    private int deliveryDay;
    private int deliveryMonth;
    private int deliveryYear;
    private int altDeliveryDay;
    private int altDeliveryMonth;
    private int altDeliveryYear;
    //Constructors
    public Order(int[] inputs){
        // takes the values from an array to input dates and the product id
        // date 1
       validDate = checkDate(inputs[0],inputs[1]);
       setDeliveryMonth(inputs[0]);
       setDeliveryDay(inputs[1]);
       setDeliveryYear(inputs[2]);

       // date 2
       altValidDate = checkDate(inputs[4],inputs[5]);
       setAltDeliveryMonth(inputs[4]);
       setAltDeliveryDay(inputs[5]);
       setAltDeliveryYear(inputs[6]);

       // product Id
       setProductId(inputs[3]);
       if(inputs[7] == 1){
           altDateChoose = true;
       }else{
           altDateChoose = false;
       }
    }
    public Order(){
        this.validDate = false;
        this.productId = 0;
    }
    //methods
    public boolean checkDate(int month, int day) {
        boolean valid;
        // checks if the day and month are valid days before running the blockout day checker
        int[][] daysInMonth = {{1,31},{2,28},{3,31},{4,30},{5,31},{6,30},{7,31},{8,31},{9,30},{10,31},{11,30},{12,31}};
        if ((month > 0 && month <= 12) && (day > 0 && day <= daysInMonth[month-1][1])) {
            valid = true;
            int i;
            int j;
            // the design of this allows the addition of multiple blockout days per month by either adding them to the
            // array as a new array or extending ie: {1,1}: Jan 1   {1,1,2,3]: Jan 1-3   {4,1,2,3,4,5,6}:  April 1-6
            // this simplifies adding multiple days per month because it keeps the max length of blockOutCalendar to 12
            int[][] blockOutCalendar = {{1, 1}, {3, 20}, {4, 22}, {5, 1}, {6, 5}, {8, 2}, {12, 31},};
            for (i = 0; i < blockOutCalendar.length; i++) {
                if (month == blockOutCalendar[i][0]) {
                    // j starts at one because the first element of the array(0) is used to store the month
                    for (j = 1; j < blockOutCalendar[i].length; j++) {
                        if (day == blockOutCalendar[i][j]) {
                            valid = false;
                        }
                    }

                }

            }
        }else{
            valid = false;
        }
        return valid;
    }
    public boolean getValidDate() {
        // returns true if the primary date is valid
        return validDate;
    }
    public boolean getAltValidDate(){
        // returns true if the alternate date is valid
        return altValidDate;
    }
    public void setProductId(int productId) {
        // sets the productId to the argument(1-5)
        this.productId = productId;
    }
    public String getProductName() {
        // returns the product name given the productID (must be used after productID is set)
        int temp = productId;
        if(temp > 0) {
            String[] names = {"Antivirus Software Standard Edition 2021", "Antivirus Software Enterprise Edition 2021", "Password Manager and 2FA Software",
                    "System Optimizer"};
            return names[temp - 1];
        }
        return "invalid";
    }
    public void setDeliveryDay(int deliveryDay){
        // sets primary day of delivery
        this.deliveryDay = deliveryDay;
    }
    public void setDeliveryMonth(int deliveryMonth){
        // sets primary month of delivery
        this.deliveryMonth = deliveryMonth;
    }
    public void setDeliveryYear(int deliveryYear){
        // sets primary year of delivery
        this.deliveryYear = deliveryYear;
    }
    public int getDeliveryDay() {
        // returns the primary delivery day
        return deliveryDay;
    }
    public int getDeliveryMonth() {
        // returns the primary delivery month
        return deliveryMonth;
    }
    public String getDeliveryMonthName(){
        // uses an array to return a string corresponding to the integer value of delivery month
        if(getDeliveryMonth() <= 12) {
            int temp = getDeliveryMonth();
            String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October",
                    "November", "December"};
            return months[getDeliveryMonth() - 1];
        }else{
            return "invalid month";
        }
    }
    public int getDeliveryYear() {
        // returns the primary delivery month
        return deliveryYear;
    }
    public void setAltDeliveryDay(int altDeliveryDay){
        // sets the alternate delivery day
        this.altDeliveryDay = altDeliveryDay;
    }
    public void setAltDeliveryMonth(int altDeliveryMonth){
        // sets the alternate delivery month
        this.altDeliveryMonth = altDeliveryMonth;
    }
    public void setAltDeliveryYear(int altDeliveryYear){
        // sets the alternate delivery year
        this.altDeliveryYear = altDeliveryYear;
    }
    public int getAltDeliveryDay(){
        // returns the alternate delivery day
        return altDeliveryDay;
    }
    public int getAltDeliveryMonth(){
        // returns the alternate delivery month
        return altDeliveryMonth;
    }
    public int getAltDeliveryYear(){
        // returns the alternate delivery year
        return altDeliveryYear;
    }
    public String getAltDeliveryMonthName(){
        // uses an array to return a string corresponding to the integer value of alt delivery month
        if(getAltDeliveryMonth() <= 12) {
            if(getAltDateChoose()){
            int temp = getAltDeliveryMonth();
            String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October",
                    "November", "December"};
            return months[getAltDeliveryMonth() - 1];
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
    public void setAltDateChoose(boolean altDateChoose){
        // sets altDateChoose (if the user chooses to enter an alternate date)
        this.altDateChoose = altDateChoose;
    }
    public boolean getAltDateChoose(){
        // returns true if the user has entered an alternate date
        return altDateChoose;
    }
    // user inputs and outputs
    public int[] userInputs(){
        // takes user inputs and outputs an integer array with values representing the inputs
        int month = 0;
        int day = 0;
        int year = 0;
        int altMonth = 0;
        int altDay = 0;
        int altYear = 0;
        int productId = 0;
        System.out.println("Please enter the number of the item you would like to purchase");
        productId = input.nextInt();
        System.out.println("Please enter the month of delivery");
        month = input.nextInt();
        System.out.println("Please enter the day of delivery" );
        day = input.nextInt();
        System.out.println("Please enter the year of delivery");
        year = input.nextInt();
        int choice;
        System.out.println("Would you like to enter an alternate delivery date? (1)Yes (2)No");
        choice = input.nextInt();
        int altDateChooseInt;
        if(choice == 1){
            System.out.println("Please enter the alternate month of delivery");
            altMonth = input.nextInt();
            System.out.println("Please enter the alternate day of delivery" );
            altDay = input.nextInt();
            System.out.println("Please enter the alternate year of delivery");
            altYear = input.nextInt();
            altDateChooseInt = 1;
        }else { altDateChooseInt = 2;
        }
        int[] userInputs = {month,day,year,productId,altMonth,altDay,altYear,altDateChooseInt};
        return userInputs;
    }
    public void summary(){
        // outputs a quick summary of the delivery dates depending on if they are valid or not
        System.out.println("\nProduct Name: " + getProductName());
        if (getValidDate()){
        System.out.print("\nDelivery Date: ");
        System.out.println(getDeliveryMonthName() + " " + getDeliveryDay() + "," + getDeliveryYear());
        }else{
            System.out.println("Unfortunately your requested primary delivery date is unavailable");
        }
        if(getAltValidDate()) {
            if (getAltDateChoose()) {
                System.out.print("Alternate Delivery Date: ");
                System.out.println(getAltDeliveryMonthName() + " " + getAltDeliveryDay() + "," + getAltDeliveryYear());
                System.out.println(" ");
            }
        }else{
            if(getAltDeliveryYear() != 0) {
                System.out.println("Unfortunately your requested alternative delivery date is unavailable");
            }
        }
    }
    public void congrats(){
        // outputs a final summary of the order and delivery date depending on the validity of each date
        if(getValidDate() || getAltValidDate()){
            System.out.println("Order Summary: \n ");
            System.out.println("Product: ");
            System.out.println(getProductName());
            System.out.println("\nDelivery Date: ");
            if(getValidDate()){
            System.out.println(getDeliveryMonthName() + " " + getDeliveryDay() + "," + getDeliveryYear());}else{
                System.out.println(getAltDeliveryMonthName() + " " + getAltDeliveryDay() + "," + getAltDeliveryYear());
            }
        }else{
            System.out.println("Your entered date(s) are unavailable . Please run the program again and " +
                    "select a new date");
            System.exit(1);
        }
        System.out.println("Enter to (1)Confirm or (2)Exit");
        int confirm;
        confirm = input.nextInt();
        if(getValidDate() && (confirm == 1)){
            System.out.println("Congratulations! Your delivery date of " + getDeliveryMonthName() + " " + getDeliveryDay()
            + "," + getDeliveryYear() + " has been confirmed ");
        }else if(getAltValidDate() && confirm == 1){
            System.out.println("\nCongratulations! Your delivery date of " + getAltDeliveryMonthName() + " " + getAltDeliveryDay() +
                    "," + getAltDeliveryYear() + " has been confirmed");
        }else{
            System.out.println("\nDelivery not confirmed, please check back later to schedule one.");
            System.exit(1);
        }
    }
}
class Main{
    // methods
    public static final Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        // outputs a menu
        initial();
        int counter;
        // do while loop of the creation of a myOrder object
        // allows the user to input as many orders as they would like
        do {
            Order myOrder = new Order();
            myOrder = new Order(myOrder.userInputs());
            myOrder.summary();
            if (reEnter() == 1) {
                myOrder = new Order(myOrder.userInputs());
                myOrder.summary();
            }
            myOrder.congrats();
            System.out.println("Enter (1) Exit  or (2) Place another order ");
            counter = input.nextInt();
        }while (counter != 1);
    }
    public static void initial(){
        // outputs initial menu
        System.out.println("Welcome to the Fusion Analytics Order Management System\n");
        System.out.println("\tProducts and Services ");
        System.out.println("(1) " + "Antivirus Software Standard Edition 2021" );
        System.out.println("(2) " + "Antivirus Software Enterprise Edition 2021" );
        System.out.println("(3) " + "Password Manager and 2FA Software"  );
        System.out.println("(4) " + "System Optimizer" );
    }
    public static int reEnter(){
        // logic for re-running if the user wants to make a change during runtime
        int choice = 0;
        System.out.println("Would you like to make a change to your selections  (1)Yes  (2)No");
        choice =  input.nextInt();
       return choice;
    }
}

