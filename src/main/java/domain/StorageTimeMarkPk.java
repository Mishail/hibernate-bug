package domain;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class StorageTimeMarkPk implements Serializable {

	private static final long serialVersionUID = 1867194552804912342L;

	public StorageTimeMarkPk()
	{
		
	}

	@Column(nullable = false)
	private String uuid;

	@ManyToOne(optional = false)
	private Storage storage;

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUuid() {
		return uuid;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

	public Storage getStorage() {
		return storage;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null)
		{
			return false;
		}
		
		if(obj == this)
		{
			return true;
		}
		
		if(obj instanceof StorageTimeMarkPk)
		{
			StorageTimeMarkPk pk = (StorageTimeMarkPk) obj;

			return(pk.getStorage().equals(this.getStorage()) && 
					pk.getUuid().equals(this.getUuid()));
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 23;
		result = 31 * result + uuid.hashCode();
		result = 31 * result + ((storage != null)?storage.getId().hashCode():0);
		return result;
	}
}
