package utilities;

import constants.Constants;

public class Scheduling {

    public void schedule(Car[] car, Request[] r, int[][] loc){

        for(int i=0; i<r.length; i++){
            int carNumber=0;
            //TO FIND WHICH CAR WILL SERVE THE REQUEST
            for(int k=0; k<car.length; k++){        
                if(car[k].getCap() > 0){
                    carNumber = k;
                }
            }
            //TO SEND CAR TO SOURCE OF REQUEST
            if(car[carNumber].getLocation() != r[i].getSrc()){                      
                int srtDist=loc[(car[carNumber].getLocation())-1][(r[i].getSrc())-1];
                car[carNumber].setLocation(r[i].getSrc());
                car[carNumber].setCarTime(2*srtDist);
            }
            //CAR WAIT 
            if(car[carNumber].getCarTime() < r[i].getSrtTime()) {                    
                car[carNumber].setCarTime(r[i].getSrtTime());
            }
            //SCHEDULE REQUEST
            if(car[carNumber].getCap() > 0 && car[carNumber].getCarTime() >= r[i].getSrtTime() && car[carNumber].getCarTime() <= r[i].getEndTime()){
                car[carNumber].setRevenue(car[carNumber].getRevenue() + loc[(car[carNumber].getLocation())-1][(r[i].getDest())-1] );     
                car[carNumber].setCap(car[carNumber].getCap()-1);                                                       
                int[] indices=car[carNumber].getPassengerLocation();
                System.out.println((i+1) + Constants.space + Constants.requestServed + Constants.space + r[i].getSrc() + Constants.space + r[i].getDest() + Constants.space + r[i].getSrtTime() + Constants.space + r[i].getEndTime());
                try {
                    for (int j = 0; j < 5; j++) {
                        if (indices[j] == 0) {
                            indices[j] = r[i].getDest();                                                
                            break;
                        }
                    }
                } catch(NullPointerException e) {
                    System.out.println(Constants.notServedRequest + (i + 1));
                }
                car[carNumber].setPassengerLocation(indices);
            }
            //DROP PASSENGER
            if(car[carNumber].getCap() == 5){                   
                int[] dropLocations = car[carNumber].getPassengerLocation();
                for(int j=0; j<dropLocations.length; j++){
                    if(car[carNumber].getLocation() != dropLocations[j]){                  
                        int srtDist=loc[(car[carNumber].getLocation())-1][(dropLocations[j])-1];    
                        car[carNumber].setLocation(dropLocations[j]);                       
                        car[carNumber].setCarTime(car[carNumber].getCarTime() + srtDist);
                    }
                    car[carNumber].setCap(car[carNumber].getCap() + 1);
                }
                car[carNumber].passengerLocationReset();
            }
        }
    }
}
