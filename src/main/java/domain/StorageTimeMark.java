package domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="storage_timemarks")
public class StorageTimeMark {
	
	@Id
	private StorageTimeMarkPk pk = new StorageTimeMarkPk();

	@Column(name="storage_id", nullable=false, insertable=false, updatable=false)
	private Long storage;

	@Column(nullable=false, insertable=false, updatable=false)
	private String uuid;

	@OneToOne(optional=true)
	private Storage timeview;

	public StorageTimeMarkPk getPk()
	{
		return pk;
	}
	
	@SuppressWarnings("unused")
	private void setPk(StorageTimeMarkPk pk)
	{
		this.pk = pk;
	}
	
	public String getUuid() {
		return pk.getUuid();
	}

	public void setUuid(String uuid) {
		this.pk.setUuid(uuid);
	}
	
	public Storage getStorage() {
		return pk.getStorage();
	}

	public void setStorage(Storage storage) {
		this.pk.setStorage(storage);
	}
	
	public Storage getTimeview() {
		return timeview;
	}

	public void setTimeview(Storage timeview) {
		this.timeview = timeview;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
		{
			return true;
		}
    	
		if(obj instanceof StorageTimeMark)
		{
			return this.getUuid().equals(((StorageTimeMark)obj).getUuid());				    		
		}
    		
		return false;
	}
}

