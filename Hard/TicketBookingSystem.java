import java.util.*;

class TicketBookingSystem {
    private static final int TOTAL_SEATS = 10;
    private final boolean[] seats = new boolean[TOTAL_SEATS];

    public synchronized boolean bookSeat(int seatNumber, String customerName) {
        if (seatNumber < 0 || seatNumber >= TOTAL_SEATS) {
            System.out.println(customerName + " attempted to book an invalid seat.");
            return false;
        }
        if (!seats[seatNumber]) {
            seats[seatNumber] = true;
            System.out.println(customerName + " successfully booked seat " + seatNumber);
            return true;
        } else {
            System.out.println(customerName + " attempted to book an already booked seat " + seatNumber);
            return false;
        }
    }
}

class Customer extends Thread {
    private final TicketBookingSystem system;
    private final int seatNumber;
    private final String customerName;

    public Customer(TicketBookingSystem system, int seatNumber, String customerName, int priority) {
        this.system = system;
        this.seatNumber = seatNumber;
        this.customerName = customerName;
        setPriority(priority);
    }

    @Override
    public void run() {
        system.bookSeat(seatNumber, customerName);
    }
}

public class Main {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem();
        List<Thread> customers = new ArrayList<>();

        customers.add(new Customer(system, 2, "VIP_Alice", Thread.MAX_PRIORITY));
        customers.add(new Customer(system, 2, "Regular_Bob", Thread.MIN_PRIORITY));
        customers.add(new Customer(system, 5, "VIP_Charlie", Thread.MAX_PRIORITY));
        customers.add(new Customer(system, 5, "Regular_Dave", Thread.MIN_PRIORITY));
        customers.add(new Customer(system, 8, "VIP_Eve", Thread.MAX_PRIORITY));
        customers.add(new Customer(system, 8, "Regular_Frank", Thread.MIN_PRIORITY));

        Collections.shuffle(customers); // Simulate random request order
        for (Thread customer : customers) {
            customer.start();
        }

        for (Thread customer : customers) {
            try {
                customer.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
