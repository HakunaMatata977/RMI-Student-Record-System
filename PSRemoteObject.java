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