package cliapp;

public class App {

    public static void main(String[] args) {
	    System.out.println("This app is only a .jar");

        for (String arg:args) {
            System.out.println("And this was an arg: "+arg);
        }
    }
}
