package utilities;

public class Car {

    private int cap, location, revenue, carTime;                                 
    private int[] passengerLocation;    
   
    public Car(){
        passengerLocation = new int[5];
        cap=5;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public void setPassengerLocation(int[] passengerLocation) {
        this.passengerLocation = passengerLocation;
    }

    public void setCarTime(int carTime) { 
        this.carTime = carTime; 
    }

    public int getCap() {
        return cap;
    }

    public int getLocation() {
        return location;
    }

    public int getRevenue() {
        return revenue;
    }

    public int[] getPassengerLocation() {
        return passengerLocation;
    }

    public int getCarTime() { 
        return carTime; 
    }

    public void passengerLocationReset() {
        for(int i=0; i<5; i++){
            passengerLocation[i]=0;
        }
    }
}
