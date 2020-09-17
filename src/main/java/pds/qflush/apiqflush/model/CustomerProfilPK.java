package pds.qflush.apiqflush.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CustomerProfilPK implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="idProfil")
    private long idProfil;
    
	@Column(name="customerId")
    private Long customerId;
	
    public long getIdProfil() {
		return idProfil;
	}

	public void setIdProfil(long idProfil) {
		this.idProfil = idProfil;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	


	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        CustomerProfilPK that = (CustomerProfilPK) o;
        return Objects.equals(idProfil, that.idProfil) &&
                Objects.equals(customerId, that.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProfil, customerId);
    }

}
