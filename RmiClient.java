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