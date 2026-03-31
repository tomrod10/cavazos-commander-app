package com.example.project;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;
import java.util.Scanner;

import org.json.simple.JSONArray;

public class App {

    // Stack for history of commands issued
    private static Deque<String> undoCommands = new ArrayDeque<>();
    private static Deque<String> redoCommands = new ArrayDeque<>();

    public static void main(String[] args) {
        String fileName = "/Users/tomasrodriguez/Documents/college/SJCC/Spring 2026/CIS-055 Data Structures & Algorithms/Module 8/projects/commander/src/main/java/com/example/project/commands.json";

        // read coammands
        JSONArray commandJSONArray = JSONFile.readArray(fileName);
        String[] commandArray = getCommandArray(commandJSONArray);
        // System.out.println(commandArray);

        // Open scanner
        String inputCommand = "";
        Scanner sc = new Scanner(System.in);

        // Commander app loop
        while (inputCommand != "q") {
            printAppName();
            printMenu();
            System.out.printf("Enter a command: ");
            inputCommand = sc.nextLine().toLowerCase();

            switch (inputCommand) {
                case "q":
                    inputCommand = "q";
                    break;
                case "l":
                    System.out.println();
                    print(commandArray);
                    break;
                case "i":
                    System.out.println();
                    randomCommand(commandArray, 1);
                    break;
                case "u":
                    undoCommand();
                    break;
                case "r":
                    redoCommand();
                    break;
                default:
                    System.out.println("Unknown command ");
                    break;
            }
            System.out.println();
        }
        sc.close();
    }

    // randomly issue commands from General Cavazos
    public static void randomCommand(String[] commandArray, int numCommand) {
        Random rand = new Random();
        // System.out.printf("Number\tCommand\n");
        // System.out.printf("------\t---------------\n");
        for (int i = 0; i < numCommand; i++) {
            int randIndex = rand.nextInt(commandArray.length);
            undoCommands.add(commandArray[randIndex]);
            // System.out.printf("%04d\t%s\n", i, commandArray[randIndex]);
            System.out.printf("[COMMAND ISSUED] General Cavazos orders the troops to do: %s\n",
                    commandArray[randIndex]);
        }
    }

    public static void undoCommand() {
        if (undoCommands.isEmpty()) {
            System.out.println("There are no commands to undo. Please issue or redo a command");
        } else {
            String cmd = undoCommands.removeLast();
            redoCommands.addLast(cmd);
            System.out.printf("[UNDO COMMAND] General Cavazos orders the troops to undo: %s\n", cmd);
        }
    }

    public static void redoCommand() {
        if (redoCommands.isEmpty()) {
            System.out.println("There are no commands to redo. Please issue a command");
        } else {
            String cmd = redoCommands.removeLast();
            undoCommands.addLast(cmd);
            System.out.printf("[REDO COMMAND] General Cavazos orders the troops to redo: %s\n", cmd);
        }
    }

    // print command array
    public static void print(String[] commandArray) {
        System.out.println("----- List of all commands -----");
        System.out.printf("Number\tCommand\n");
        System.out.printf("------\t---------------\n");
        for (int i = 0; i < commandArray.length; i++) {
            System.out.printf("%04d\t%s\n", i, commandArray[i]);
        }
    }

    // get array of commands
    public static String[] getCommandArray(JSONArray commandArray) {
        String[] arr = new String[commandArray.size()];

        // get names from json object
        for (int i = 0; i < commandArray.size(); i++) {
            String command = commandArray.get(i).toString();
            arr[i] = command;
        }
        return arr;
    }

    public static void printLineBreak() {
        System.out.println("----------------------------------------------------------");
    }

    public static void printAppName() {
        printLineBreak();
        System.out.println("General Cavazos Commander App");
    }

    public static void printMenu() {
        printLineBreak();
        System.out.printf("i\tIssue a command\n");
        System.out.printf("l\tList of all the commands\n");
        System.out.printf("i\tIssue a command\n");
        System.out.printf("u\tUndo the last command that was issued\n");
        System.out.printf("r\tRedo the last command that was issued\n");
        printLineBreak();
    }
}
