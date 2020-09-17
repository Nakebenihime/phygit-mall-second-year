package pds.qflush.apiqflush.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LocationStorePk implements Serializable {
    @Column(name = "storeId")
    private Long storeId;

    @Column(name = "locationId")
    private Long locationId;

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        LocationStorePk that = (LocationStorePk) o;
        return Objects.equals(storeId, that.storeId) &&
                Objects.equals(locationId, that.locationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeId, locationId);
    }

}
