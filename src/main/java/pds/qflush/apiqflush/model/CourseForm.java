package pds.qflush.apiqflush.model;

import javax.persistence.Entity;
import java.util.ArrayList;


public class CourseForm {
    private ArrayList<Store> stores;

    public ArrayList<Store> getStores() {
        return stores;
    }

    public void setStores(ArrayList<Store> stores) {
        this.stores = stores;
    }
}
