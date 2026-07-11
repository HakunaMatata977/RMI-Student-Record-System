# RMI Student Record System

Simple Java RMI (Remote Method Invocation) program to manage student records over client-server communication. Made this for my Distributed Systems lab.

## What it does

Basic student record system with these options:

1. Student Registration (name, faculty, age, roll no)
2. Student Record Update
3. Student Record Listing
4. Student Record Search
5. Student Record Delete
6. Exit

Client and server run separately, client calls methods on server like they were local methods (that's the whole point of RMI).

## Files

- `RemoteMethods.java` - the interface, defines what remote methods are available
- `PSRemoteObject.java` - actual implementation of those methods (server side logic)
- `RmiServer.java` - starts the RMI registry and binds the object
- `RmiClient.java` - connects to server and gives menu to user

## How RMI works here (short version)

Server creates the registry and registers the object under a name (`StudentService`). Client looks up that name in the registry and gets a stub back. When client calls a method on the stub, it actually goes over network to server, runs there, and sends result back. Client doesn't need to know where the server physically is.

## Data storage

Used simple parallel arrays instead of ArrayList or a Student class, just to keep it beginner friendly:

```java
String[] names = new String[100];
String[] faculties = new String[100];
int[] ages = new int[100];
int[] rolls = new int[100];
int count = 0;
```

Same index across all arrays = same student. `count` keeps track of how many students are stored right now.

## How to run

1. Put all 4 java files in same folder
2. Compile everything

```
javac *.java
```

3. Start server first (leave this terminal running)

```
java RmiServer
```

4. Open a new terminal and run client

```
java RmiClient
```

5. Follow the menu

## Code

### RemoteMethods.java

```java
import java.rmi.Remote;
import java.rmi.RemoteException;

interface RemoteMethods extends Remote {
    String register(String name, String faculty, int age, int rollNo) throws RemoteException;
    String update(int rollNo, String name, String faculty, int age) throws RemoteException;
    String list() throws RemoteException;
    String search(int rollNo) throws RemoteException;
    String delete(int rollNo) throws RemoteException;
}
```

### PSRemoteObject.java

```java
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class PSRemoteObject extends UnicastRemoteObject implements RemoteMethods {

    String[] names = new String[100];
    String[] faculties = new String[100];
    int[] ages = new int[100];
    int[] rolls = new int[100];
    int count = 0;

    PSRemoteObject() throws RemoteException {
        super();
    }

    @Override
    public String register(String name, String faculty, int age, int rollNo) throws RemoteException {
        for (int i = 0; i < count; i++) {
            if (rolls[i] == rollNo) {
                return "Student with Roll No " + rollNo + " already exists!";
            }
        }
        names[count] = name;
        faculties[count] = faculty;
        ages[count] = age;
        rolls[count] = rollNo;
        count++;
        return "Student registered successfully!";
    }

    @Override
    public String update(int rollNo, String name, String faculty, int age) throws RemoteException {
        for (int i = 0; i < count; i++) {
            if (rolls[i] == rollNo) {
                names[i] = name;
                faculties[i] = faculty;
                ages[i] = age;
                return "Student record updated successfully!";
            }
        }
        return "Student with Roll No " + rollNo + " not found!";
    }

    @Override
    public String list() throws RemoteException {
        if (count == 0) {
            return "No student records found.";
        }
        String result = "";
        for (int i = 0; i < count; i++) {
            result += "Roll No: " + rolls[i] + ", Name: " + names[i] +
                      ", Faculty: " + faculties[i] + ", Age: " + ages[i] + "\n";
        }
        return result;
    }

    @Override
    public String search(int rollNo) throws RemoteException {
        for (int i = 0; i < count; i++) {
            if (rolls[i] == rollNo) {
                return "Roll No: " + rolls[i] + ", Name: " + names[i] +
                       ", Faculty: " + faculties[i] + ", Age: " + ages[i];
            }
        }
        return "Student not found!";
    }

    @Override
    public String delete(int rollNo) throws RemoteException {
        for (int i = 0; i < count; i++) {
            if (rolls[i] == rollNo) {
                for (int j = i; j < count - 1; j++) {
                    names[j] = names[j + 1];
                    faculties[j] = faculties[j + 1];
                    ages[j] = ages[j + 1];
                    rolls[j] = rolls[j + 1];
                }
                count--;
                return "Student record deleted successfully!";
            }
        }
        return "Student with Roll No " + rollNo + " not found!";
    }
}
```

### RmiServer.java

```java
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiServer {
    public static void main(String[] args) throws Exception {
        PSRemoteObject obj = new PSRemoteObject();
        Registry rg = LocateRegistry.createRegistry(1008);
        rg.rebind("StudentService", obj);
        System.out.println("Server Started");
    }
}
```

### RmiClient.java

```java
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class RmiClient {
    public static void main(String[] args) throws Exception {
        Registry rg = LocateRegistry.getRegistry("localhost", 1008);
        RemoteMethods stub = (RemoteMethods) rg.lookup("StudentService");
        Scanner sc = new Scanner(System.in);

        int choice;

        do {
            System.out.println("\n---Student Records---");
            System.out.println("1. Student Registration(name,faculty,age,Roll No)");
            System.out.println("2. Student Record Update");
            System.out.println("3. Student Record Listing");
            System.out.println("4. Student Record Search");
            System.out.println("5. Student Record Delete");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Faculty: ");
                    String faculty = sc.nextLine();
                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();
                    System.out.print("Enter Roll No: ");
                    int rollNo = sc.nextInt();
                    System.out.println(stub.register(name, faculty, age, rollNo));
                    break;

                case 2:
                    System.out.print("Enter Roll No to update: ");
                    int uRoll = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter New Name: ");
                    String uName = sc.nextLine();
                    System.out.print("Enter New Faculty: ");
                    String uFaculty = sc.nextLine();
                    System.out.print("Enter New Age: ");
                    int uAge = sc.nextInt();
                    System.out.println(stub.update(uRoll, uName, uFaculty, uAge));
                    break;

                case 3:
                    System.out.println("Result:\n" + stub.list());
                    break;

                case 4:
                    System.out.print("Enter Roll No to search: ");
                    int sRoll = sc.nextInt();
                    System.out.println("Result: " + stub.search(sRoll));
                    break;

                case 5:
                    System.out.print("Enter Roll No to delete: ");
                    int dRoll = sc.nextInt();
                    System.out.println("Result: " + stub.delete(dRoll));
                    break;

                case 6:
                    System.out.println("Exit");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 6);
    }
}
```

## Notes

- Fixed size array (100 students max), didn't bother with dynamic resizing since this was just for the lab
- No file/database storage, data is lost once server stops
- Port used is 1008, change it in both server and client if it's already in use on your machine
