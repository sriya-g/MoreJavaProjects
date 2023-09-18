import java.util.Scanner;
public class CoordinateConverter {
    static Scanner keyboard = new Scanner(System.in);
    public static boolean isValidCoord(String coordStr) {
        String[] coordBreak = coordStr.split(",");
        if (coordBreak.length != 3) {
            return false;
        }
        for (int i = 0; i < coordBreak.length; i++) {
            try {
                double d = Double.parseDouble(coordBreak[i]);
            } catch (NumberFormatException nfe) {
                return false;
            }
        }
        return true;
    }
    public static void formatPrint(double[] arr) {
        System.out.print("(");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.print(")");
    }
    public static double accurateAtan(double coord1, double coord2) {
        double theta = Math.atan(coord2/coord1);
        if (coord2 > 0 && coord1 < 0) {
            theta += Math.PI/2;
        }
        if (coord2 < 0 && coord1 > 0) {
            theta += Math.PI;
        }
        return theta;
    }
    public static double[] convRtoC(double[] coordArr) { //(x, y, z) to (r, theta, z)
        double r = Math.sqrt((coordArr[0]*coordArr[0])+(coordArr[1]*coordArr[1]));
        double theta = accurateAtan(coordArr[0], coordArr[1]);
        double[] returnArray = {r, theta, coordArr[2]};
        return returnArray;
    }
    public static double[] convRtoS(double[] coordArr) { //(x, y, z) to (p, theta, phi)
        double p = Math.sqrt((coordArr[0]*coordArr[0])+(coordArr[1]*coordArr[1])+(coordArr[2]*coordArr[2]));
        double theta = accurateAtan(coordArr[0], coordArr[1]);
        double phi = Math.acos(coordArr[2]/p);
        double[] returnArray = {p, theta, phi};
        return returnArray;
    }
    public static double[] convCtoR(double[] coordArr) { //(r, theta, z) to (x, y, z)
        double x = coordArr[0]*Math.cos(coordArr[1]);
        double y = coordArr[0]*Math.sin(coordArr[1]);
        double[] returnArray = {x, y, coordArr[2]};
        return returnArray;
    }
    public static double[] convCtoS(double[] coordArr) { //(r, theta, z) to (p, theta, phi)
        double p = Math.sqrt((coordArr[0]*coordArr[0])+(coordArr[2]*coordArr[2]));
        double phi = Math.acos(coordArr[2]/p);
        double[] returnArray = {p, coordArr[1], phi};
        return returnArray;
    }
    public static double[] convStoR(double[] coordArr) { //(p, theta, phi) to (x, y, z)
        double x = coordArr[0]*Math.cos(coordArr[1])*Math.sin(coordArr[2]);
        double y = coordArr[0]*Math.sin(coordArr[1])*Math.sin(coordArr[2]);
        double z = coordArr[0]*Math.cos(coordArr[2]);
        double[] returnArray = {x, y, z};
        return returnArray;
    }
    public static double[] convStoC(double[] coordArr) { //(p, theta, phi) to (r, theta, z)
        double r = coordArr[0]*Math.sin(coordArr[2]);
        double z = coordArr[0]*Math.cos(coordArr[2]);
        double[] returnArray = {r, coordArr[1], z};
        return returnArray;
    }
    public static void main(String[] args) {
        String coordStr = "";
        while (!isValidCoord(coordStr)) {
            System.out.println("Enter your coordinates below separated by ',' ");
            coordStr = keyboard.nextLine();
        }
        String typeCoord = "";
        while (!typeCoord.equalsIgnoreCase("R") && !typeCoord.equalsIgnoreCase("C") && !typeCoord.equalsIgnoreCase("s")) {
            System.out.println("What type of coordinates are they? (R, C, S): ");
            typeCoord = keyboard.next().toUpperCase();
        }
        String typeConv = "";
        while (!typeConv.equalsIgnoreCase("R") && !typeConv.equalsIgnoreCase("C") && !typeConv.equalsIgnoreCase("s")) {
            System.out.println("What do you want to convert to? (R, C, S):");
            typeConv = keyboard.next().toUpperCase();
        }
        if (typeCoord.equalsIgnoreCase(typeConv)) {
            System.out.println("("+coordStr+")");
        }
        String[] numStrs = coordStr.split(",");
        double[] numArr = new double[numStrs.length];
        for (int i = 0; i < numStrs.length; i++) {
            numArr[i] = Double.parseDouble(numStrs[i]);
        }
        if (typeCoord.equals("R")) {
            if (typeConv.equals("C")) {
                formatPrint(convRtoC(numArr));
            }
            if (typeConv.equals("S")) {
                formatPrint(convRtoS(numArr));
            }
        }
        if (typeCoord.equals("C")) {
            if (typeConv.equals("R")) {
                formatPrint(convCtoR(numArr));
            }
            if (typeConv.equals("S")) {
                formatPrint(convCtoS(numArr));
            }
        }
        if (typeCoord.equals("S")) {
            if (typeConv.equals("R")) {
                formatPrint(convCtoR(numArr));
            }
            if (typeConv.equals("C")) {
                formatPrint(convCtoS(numArr));
            }
        }
    }
}
