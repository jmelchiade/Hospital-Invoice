import javax.swing.JOptionPane;

public class Hospital {
    private String patientID;
    private String FirstName;
    private String LastName;
    private int numberOfDays;
    private char typeOfRoom;

    public Hospital(String patientID, String FirstName, String LastName, int numberOfDays, char typeOfRoom) {
        this.patientID = patientID;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.numberOfDays = numberOfDays;
        this.typeOfRoom = typeOfRoom;
    }

    public double calculateRoomCharge() {
        double roomCharge = 0.0;

        switch (typeOfRoom) {
            case 'P':
            case 'p':
                roomCharge = numberOfDays * 550.0;
                break;
            case 'S':
            case 's':
                roomCharge = numberOfDays * 350.0;
                break;
            case 'W':
            case 'w':
                roomCharge = numberOfDays * 105.0;
                break;
            default:
                System.out.println("Invalid room type");
        }

        return roomCharge;
    }

    public double calculateMedicationCost() {
        double baseMedicationFee = 275.0;
        double medicationCost = 0.0;

        switch (typeOfRoom) {
            case 'P':
            case 'p':
                medicationCost = baseMedicationFee * 2.0;
                break;
            case 'S':
            case 's':
                medicationCost = baseMedicationFee;
                break;
            case 'W':
            case 'w':
                medicationCost = baseMedicationFee / 2.0;
                break;
            default:
                System.out.println("Invalid room type");
        }

        return medicationCost;
    }

    public double calculateTotalAmountDue() {
        double roomCharge = calculateRoomCharge();
        double medicationCost = calculateMedicationCost();
        double telephoneCost = 0.0;
        double televisionCost = 0.0;

        if (typeOfRoom == 'P' || typeOfRoom == 'p') {
            telephoneCost = 4.50;
            televisionCost = 7.50;
        } else if (typeOfRoom == 'S' || typeOfRoom == 's') {
            telephoneCost = 0.0;
            televisionCost = 7.50;
        } else {
            telephoneCost = 0.0;
            televisionCost = 0.0;
        }

        return roomCharge + medicationCost + telephoneCost + televisionCost;
    }

    public void printBillingStatement() {
        System.out.println();
        System.out.println("ABC Community Hospital");
        System.out.println("Patient Billing Statement");
        System.out.println("Patient's First Name: " + FirstName);
        System.out.println("Patient's Surname: " + LastName);
        System.out.println("Number of days: " + numberOfDays);
        System.out.println();
        System.out.print("Type of room: ");
        switch (typeOfRoom) {
            case 'P':
            case 'p':
                System.out.println("Private");
                break;
            case 'S':
            case 's':
                System.out.println("Semi-Private");
                break;
            case 'W':
            case 'w':
                System.out.println("Ward");
                break;
            default:
                System.out.println("Invalid room type");
        }
        System.out.printf("Room charge...... $%.2f%n", calculateRoomCharge());

        double telephoneCost;
        if (typeOfRoom == 'P' || typeOfRoom == 'p') {
            telephoneCost = 4.50;
        } else {
            telephoneCost = 0.0;
        }
        System.out.printf("Telephone........ $%.2f%n", telephoneCost);

        double televisionCost;
        if (typeOfRoom == 'P' || typeOfRoom == 'p' || typeOfRoom == 'S' || typeOfRoom == 's') {
            televisionCost = 7.50;
        } else {
            televisionCost = 0.0;
        }
        System.out.printf("Television....... $%.2f%n", televisionCost);

        System.out.printf("Medication....... $%.2f%n", calculateMedicationCost());
        System.out.printf("Total amount due. $%.2f%n", calculateTotalAmountDue());
        System.out.println();

    }

    public static void main(String[] args) {
        String patientID = JOptionPane.showInputDialog("Enter patient identification number:");

        if (patientID == null || patientID.length() < 5) {
            JOptionPane.showMessageDialog(null,
                    "Invalid ID. Please provide a valid ID with a minimum length of 5 characters.");
            return; // Stop execution if ID is invalid
        }
        String FirstName = JOptionPane.showInputDialog("Enter patient's first name:");

        if (FirstName == null || FirstName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Patient name cannot be empty. Please provide a first name.");
            return; // Stop execution if last name is empty
        }

        String LastName = JOptionPane.showInputDialog("Enter patient's Surname:");

        if (FirstName == null || FirstName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Patient name cannot be empty. Please provide a last name.");
            return; // Stop execution if last name is empty
        }

        int numberOfDays = Integer
                .parseInt(JOptionPane.showInputDialog("Enter number of days the patient is in the hospital:"));
        String typeOfRoomInput = JOptionPane
                .showInputDialog("Enter room type (P = private, S = semi-private, W = ward):");
        char typeOfRoom = typeOfRoomInput.charAt(0);

        Hospital hospital = new Hospital(patientID, FirstName, LastName, numberOfDays, typeOfRoom);
        hospital.printBillingStatement();
    }
}
