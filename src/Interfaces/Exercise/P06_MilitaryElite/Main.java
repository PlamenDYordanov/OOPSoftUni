package Interfaces.Exercise.P06_MilitaryElite;

import Interfaces.Exercise.P06_MilitaryElite.Enums.Corps;
import Interfaces.Exercise.P06_MilitaryElite.Interfaces.Engineer;
import Interfaces.Exercise.P06_MilitaryElite.entity.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputData = scanner.nextLine();
        Collection<PrivateImpl> privateSoldiersCollection = new ArrayList<>();
        //private, lieutenant, engineer, commando, spy
        while (!"End".equals(inputData)) {
            String[] inputDataArr = inputData.split("\\s+");
            switch (inputDataArr[0]) {
                case "Private":
                    addPrivateToCollection(privateSoldiersCollection, inputDataArr);
                    break;
                case "Engineer":
                    addEngineer(inputDataArr);
                    break;
                case "LieutenantGeneral":
                    addLieutenant(privateSoldiersCollection, inputDataArr);
                    break;
                case "Commando":
                    addCommando(inputDataArr);
                    break;
                case "Spy":
                    addSpy(inputDataArr);
                    break;
            }


            inputData = scanner.nextLine();
        }


    }

    private static void addSpy(String[] inputData) {
        SpyImpl spy = null;
        int id = Integer.parseInt(inputData[1]);
        String firstName = inputData[2];
        String lastName = inputData[3];
        String codeNumber = inputData[4];
        spy = new SpyImpl(firstName, lastName, id, codeNumber);
        System.out.println(spy);

    }

    private static void addEngineer(String[] inputData) {
        EngineerImpl engineer = null;
        int id = Integer.parseInt(inputData[1]);
        String firstName = inputData[2];
        String lastName = inputData[3];
        double salary = Double.parseDouble(inputData[4]);
        String curCorps = inputData[5];
        if (curCorps.equals("Airforces") || curCorps.equals("Marines")) {
            Corps corps = Corps.valueOf(curCorps);
            engineer = new EngineerImpl(firstName, lastName, id, salary, corps);

        } else {
            return;
        }
        if (inputData.length > 6) {
            for (int i = 6; i < inputData.length; i += 2) {
                String currentPart = inputData[i];
                int workingHours = Integer.parseInt(inputData[i + 1]);
                RepairImpl repair = new RepairImpl(currentPart, workingHours);
                engineer.addRepair(repair);
            }
        }
        System.out.println(engineer);

    }

    private static void addLieutenant(Collection<PrivateImpl> privateSoldiersCollection, String[] inputData) {
        LieutenantGeneralImpl lieutenant = null;
        int id = Integer.parseInt(inputData[1]);
        String firstName = inputData[2];
        String lastName = inputData[3];
        double salary = Double.parseDouble(inputData[4]);
        lieutenant = new LieutenantGeneralImpl(firstName, lastName, id, salary);
        if (inputData.length > 5) {
            for (int i = 5; i < inputData.length; i++) {
                int currentId = Integer.parseInt(inputData[i]);
                for (PrivateImpl currentPrivate : privateSoldiersCollection) {
                    if (currentPrivate.getId() == currentId) {
                        lieutenant.addPrivate(currentPrivate);
                    }
                }
            }
        }
        System.out.print(lieutenant);
    }

    private static void addCommando(String[] inputData) {
        CommandoImpl commando = null;
        int id = Integer.parseInt(inputData[1]);
        String firstName = inputData[2];
        String lastName = inputData[3];
        double salary = Double.parseDouble(inputData[4]);
        String curCorps = inputData[5];
        if (curCorps.equals("Airforces") || curCorps.equals("Marines")) {
            Corps corps = Corps.valueOf(curCorps);
            commando = new CommandoImpl(firstName, lastName, id, salary, corps);

        } else {
            return;
        }
        if (inputData.length > 6) {
            for (int i = 6; i < inputData.length; i += 2) {
                String codeName = inputData[i];
                String state = inputData[i + 1];
                if (state.equals("inProgress") || state.equals("finished")) {
                    MissionImpl mission = new MissionImpl(codeName, state);
                    commando.addMission(mission);
                }

            }
        }
        System.out.print(commando);
    }

    private static void addPrivateToCollection(Collection<PrivateImpl> privateCollection, String[] inputData) {
        int id = Integer.parseInt(inputData[1]);
        String firstName = inputData[2];
        String lastName = inputData[3];
        double salary = Double.parseDouble(inputData[4]);
        PrivateImpl currentSoldier = new PrivateImpl(firstName, lastName, id, salary);
        privateCollection.add(currentSoldier);
        System.out.print(currentSoldier);
    }
}
