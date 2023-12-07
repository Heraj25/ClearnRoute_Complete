package Page1;

import java.util.Scanner;


//Strategy - Location Choice
abstract class DumpLocation {
    RecyclableLocation recyclableLocation;

    public DumpLocation() {
        this.recyclableLocation = null;
    }

    public DumpLocation(RecyclableLocation recyclableLocation) {
        this.recyclableLocation = recyclableLocation;
    }

    public void Jayanagar() {
        System.out.println("Location: Jayanagar");
    }

    public void Recyclable() {
        if (recyclableLocation == null) {
            System.out.println("You do not have access to this location");
        } else {
            recyclableLocation.recycle();
        }
    }

    public abstract void display();
}

interface RecyclableLocation {
    void recycle();
}

class JP_Nagar implements RecyclableLocation {
    public void recycle() {
        System.out.println("Location: JP Nagar");
    }
}

class Vijayanagar implements RecyclableLocation {
    public void recycle() {
        System.out.println("Location: glass recycling landfill");
    }
}

class K_cross implements RecyclableLocation {
    public void recycle() {
        System.out.println("Waste dumped at wet waste recycling landfill");
    }
}

class Driver extends DumpLocation {
    public Driver(RecyclableLocation recyclableLocation) {
        super(recyclableLocation);
    }

    public void display() {
        System.out.println("Driver1 dumped waste");
    }
}

//Chain of responsibility - Waste Segregation
abstract class WasteHandler {
    public Scanner scanner = new Scanner(System.in);
    protected WasteHandler nextHandler;
    public int[] weight = {0, 0, 0, 0};

    public void setNextHandler(WasteHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void segregateWaste(Waste waste, String specific_waste, String g, String e, String f, String s);
}

class HazardousHandler extends WasteHandler {
    public WasteHandler successor;

    public void setSuccessor(WasteHandler successor) {
        this.successor = successor;
    }

    public void segregateWaste(Waste waste, String specific_waste, String d, String e, String f, String g) {
        if (specific_waste.equalsIgnoreCase("hazard")) {
            System.out.println("Weight of waste before emptying hazardous waste: " + waste.getWeight());
            System.out.println("Enter the weight of the hazardous waste: ");
            weight[0] = Integer.parseInt(g);
            waste.setWeight(waste.getWeight() - weight[0]);
            System.out.println("Hazardous waste segregated.");
            System.out.println("Weight of waste after emptying hazardous waste: " + waste.getWeight());
        } else {
            successor.segregateWaste(waste, specific_waste, g, e, f, g);
        }
    }
}

class PlasticHandler extends WasteHandler {

    public void segregateWaste(Waste waste, String specific_waste, String d, String e, String f, String g) {
        System.out.println(waste+specific_waste+d+e+f+g);
        if (specific_waste.equalsIgnoreCase("plastic")) {
            System.out.println("Weight of waste before emptying plastic waste: " + waste.getWeight());
            System.out.println("Enter the weight of the plastic waste: ");
            weight[1] = Integer.parseInt(d);
            System.out.println(weight[1]);
            waste.setWeight(waste.getWeight() - weight[1]);
            System.out.println("Plastic waste segregated.");
            System.out.println("Weight of waste after emptying plastic waste: " + waste.getWeight());
        } else {
            System.out.println("Wrong input");
        }
    }
}

class WetwasteHandler extends WasteHandler {
    public WasteHandler successor;

    public void setSuccessor(WasteHandler successor) {
        this.successor = successor;
    }

    public void segregateWaste(Waste waste, String specific_waste, String d, String e, String f, String g) {
        if (specific_waste.equalsIgnoreCase("wet waste")) {
            System.out.println("Weight of waste before emptying wet waste: " + waste.getWeight());
            System.out.println("Enter the weight of the wet waste: ");
            weight[2] = Integer.parseInt(f);
            waste.setWeight(waste.getWeight() - weight[2]);
            System.out.println("Wet waste segregated.");
            System.out.println("Weight of waste after emptying wet waste: " + waste.getWeight());
        } else {
            successor.segregateWaste(waste, specific_waste, d, e, f, g);
        }
    }
}

class GlassHandler extends WasteHandler {
    public WasteHandler successor;

    public void setSuccessor(WasteHandler successor) {
        this.successor = successor;
    }

    public void segregateWaste(Waste waste, String specific_waste, String d, String e, String f, String g) {
        if (specific_waste.equalsIgnoreCase("glass")) {
            System.out.println("Weight of waste before emptying glass waste: " + waste.getWeight());
            System.out.println("Enter the weight of the glass waste: ");
            weight[3] = Integer.parseInt(e);
            waste.setWeight(waste.getWeight() - weight[3]);
            System.out.println("Glass waste segregated.");
            System.out.println("Weight of waste before emptying glass waste: " + waste.getWeight());
        } else {
            successor.segregateWaste(waste, specific_waste,d,e,f,g);
        }
    }
}

//Factory - Payment
class PaymentInfoFactory {
    public PaymentInfoModel createPaymentInfo(String wasteType) throws Exception {
        switch (wasteType) {
            case "hazard":
                return new HazardousPaymentInfo();
            case "plastic":
                return new PlasticPaymentInfo();
            case "glass":
                return new GlassPaymentInfo();
            case "wetwaste":
                return new WetWastePaymentInfo();
            default:
                throw new Exception("Invalid waste type: " + wasteType);
        }
    }
}

abstract class PaymentInfoModel {
    public static int sum;

    abstract public double calculatePayment(Waste waste, Truck truck, WasteHandler handler);
}

class HazardousPaymentInfo extends PaymentInfoModel {

    public double calculatePayment(Waste waste, Truck truck, WasteHandler handler) {
        return handler.weight[0] * 0.3;
    }
}

class PlasticPaymentInfo extends PaymentInfoModel {
    public double calculatePayment(Waste waste, Truck truck, WasteHandler handler) {
        return handler.weight[1] * 0.6;
    }
}

class GlassPaymentInfo extends PaymentInfoModel {
    public double calculatePayment(Waste waste, Truck truck, WasteHandler handler) {
        return handler.weight[2] * 0.5;
    }
}

class WetWastePaymentInfo extends PaymentInfoModel {
    public double calculatePayment(Waste waste, Truck truck, WasteHandler handler) {
        return handler.weight[3] * 0.4;
    }
}

//Factory
abstract class PaymentProcessor {
    public abstract void processPayment(double amount);
}

class CreditCardProcessor extends PaymentProcessor {
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment: $" + amount);
    }
}

class PayPalProcessor extends PaymentProcessor {
    public void processPayment(double amount) {
        System.out.println("Processing PayPal payment: $" + amount);
    }
}

class PaymentProcessorFactory {
    public PaymentProcessor createProcessor(String paymentMethod) {
        if (paymentMethod.equalsIgnoreCase("creditcard")) {
            return new CreditCardProcessor();
        } else if (paymentMethod.equalsIgnoreCase("paypal")) {
            return new PayPalProcessor();
        } else {
            throw new IllegalArgumentException("Invalid payment method: " + paymentMethod);
        }
    }
}

class Waste {
    private String type;
    private double weight = 0;

    public Waste(String type, double weight) {
        this.type = type;
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}

class Truck {
    private double initialWeight;
    private double finalWeight;

    public Truck(double initialWeight) {
        this.initialWeight = initialWeight;
    }

    public double getInitialWeight() {
        return initialWeight;
    }

    public void setInitialWeight(double initialWeight) {
        this.initialWeight = initialWeight;
    }

    public double getFinalWeight() {
        return finalWeight;
    }

    public void setFinalWeight(double finalWeight) {
        this.finalWeight = finalWeight;
    }
}

class PaymentInfo {
    private String paymentMethod;

    public PaymentInfo(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}

class Supervisor {
    public void weighTruck(Truck truck, double weight) {
        truck.setFinalWeight(weight);
    }

    public double calculateWasteWeight(Truck truck) {
        return truck.getInitialWeight() - truck.getFinalWeight();
    }
}

public class WasteController {
    private Truck truck;
    private Supervisor supervisor;
    private Waste waste;


   /* public void processWasteData(String initialWeight, String finalWeight, String garbagetypes,
                                 String plastic, String glass, String wet, String hazard) {
        // Process the data received from Page1ViewController
        // You can use the data here as needed.

        System.out.println(initialWeight+ finalWeight+garbagetypes+plastic+glass+wet+hazard);
    }*/


    public WasteController() {
        supervisor = new Supervisor();
    }
    private String msg1, msg2, msg3, msg4, msg5, msg6, msg7,msg8;

    public String getMsg1() {
        return msg1;
    }

    public String getMsg2() {
        return msg2;
    }

    public String getMsg3() {
        return msg3;
    }

    public String getMsg4() {
        return msg4;
    }

    public String getMsg5() {
        return msg5;
    }

    public String getMsg6() {
        return msg6;
    }
    public String getMsg7() {
        return msg7;
    }
    public String getMsg8() {
        return msg8;
    }


    public void startProcess(String a, String b, String c, String d, String e, String f, String g) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter truck's initial weight:");
        double initialWeight =Double.parseDouble(a);
        System.out.println(initialWeight);
        truck = new Truck(initialWeight);

        msg1="Give location access to dump the waste: \nLocation to dump the waste:";
        RecyclableLocation spcl_location = new JP_Nagar();
        Driver driver1 = new Driver(spcl_location);
        driver1.display();
        System.out.print("Location to dump the waste: ");
        driver1.Jayanagar();
        System.out.println();

        System.out.print("Enter truck's final weight:");
        double finalWeight = Double.parseDouble(b);
        System.out.println(finalWeight);
        supervisor.weighTruck(truck, finalWeight);
        System.out.println();

        double wasteWeight = supervisor.calculateWasteWeight(truck);
        waste = new Waste("glass", wasteWeight);
        System.out.println("Waste weight: " + waste.getWeight());

        msg2="Segregating the waste...";
        msg7="CleanRoute: Waste Segregation Management Project Running Successfully.";
        msg3=d+e+f+g;
        System.out.print("Enter types of waste present (space separated): ");
        String[] waste_present = c.split(" ");
        msg4=waste_present+"";
        HazardousHandler hazard = new HazardousHandler();
        PlasticHandler plastic = new PlasticHandler();
        WetwasteHandler wetwaste = new WetwasteHandler();
        GlassHandler glass = new GlassHandler();

        hazard.setSuccessor(wetwaste);
        wetwaste.setSuccessor(glass);
        glass.setSuccessor(plastic);
        msg5="Generating output*******************************************************";
        for (int i = 0; i < waste_present.length; i++) {
            String currentWaste = waste_present[i];
            switch (currentWaste) {
                case "hazard":
                    hazard.segregateWaste(waste, currentWaste, g, e, f, g);
                    break;
                case "plastic":
                    plastic.segregateWaste(waste, currentWaste, d, e, f, g);
                    break;
                case "wetwaste":
                    wetwaste.segregateWaste(waste, currentWaste, d, e, f, g);
                    break;
                case "glass":
                    glass.segregateWaste(waste, currentWaste, d, e, f, g);
                    break;
                default:
                    System.out.println("Invalid waste type: " + currentWaste);
            }
            System.out.println(d + e + f + g);
        }

        PaymentInfoFactory payment = new PaymentInfoFactory();

        double total_payment = 0;
        for (int j = 0; j < waste_present.length; j++) {
            String w = waste_present[j];
            PaymentInfoModel paymentInfoModel = payment.createPaymentInfo(w);
            if (w.equals("hazard")) total_payment += paymentInfoModel.calculatePayment(waste, truck, hazard);
            else if (w.equals("plastic")) total_payment += paymentInfoModel.calculatePayment(waste, truck, plastic);
            else if (w.equals("wetwaste")) total_payment += paymentInfoModel.calculatePayment(waste, truck, wetwaste);
            else if (w.equals("glass")) total_payment += paymentInfoModel.calculatePayment(waste, truck, glass);
        }

        msg6="" + total_payment;
        msg8="Executed Successfully!";
        scanner.close();

    }

}
