// Demonstrates seat reservation using synchronization and Runnable.

class Bus implements Runnable {
    int availableSeats = 2;
    int requestedSeats;

    Bus(int seats) {
        this.requestedSeats = seats;
    }

    public synchronized void run() {
        String name = Thread.currentThread().getName();

        if (availableSeats >= requestedSeats) {
            System.out.println(name + " reserved seat...");
            availableSeats = availableSeats - requestedSeats;
        } else {
            System.out.println("Sorry! Seat not available for " + name);
        }
    }
}

class seat_reservation_demo {
    public static void main(String args[]) {
        Bus obj = new Bus(1);

        Thread t1 = new Thread(obj);
        Thread t2 = new Thread(obj);
        Thread t3 = new Thread(obj);

        t1.setName("A");
        t2.setName("B");
        t3.setName("C");

        t1.start();
        t2.start();
        t3.start();
    }
}