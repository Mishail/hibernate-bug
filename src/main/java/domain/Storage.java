package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
@Table(name="storages")
public class Storage {

	private Long id;

	private Storage parent;

	private List<StorageTimeMark> timemarks = new ArrayList<StorageTimeMark>(0);

	private StorageTimeMark timeviewmark;

	private List<Storage> childStorages = new ArrayList<Storage>();

	private Server server;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	@OneToMany(mappedBy="storage", orphanRemoval=true)
	@Cascade({CascadeType.SAVE_UPDATE})
	public List<StorageTimeMark> getTimeMarks() {
		return timemarks;
	}

	@SuppressWarnings("unused")
	private void setTimeMarks(List<StorageTimeMark> timemarks) {
		this.timemarks  = timemarks;
	}
	
	//TODO: could timeview mark exist without parent?

	//Here be dragons
	@OneToOne(mappedBy="timeview")
	//@Transient
	public StorageTimeMark getTimeViewMark() {
		return (timeviewmark);
	}

	public void setTimeViewMark(StorageTimeMark timemark) {
		this.timeviewmark  = timemark;
	}

	//Here be dragons
	@ManyToOne
	//@Transient
	public Storage getParent() {
		return parent;
	}

	public void setParent( Storage parent ) {
		this.parent = parent;
	}
	
	/**
	 * @return the list of VMs associated with the host. READ ONLY
	 */
	@Column(insertable=false)
	@OneToMany(mappedBy="parent")
//	@Transient
	public List<Storage> getChilds() {
		return (childStorages);
	}	

	@SuppressWarnings("unused")
	private void setChilds(List<Storage> childs)
	{
		this.childStorages = childs;
	}

	@OneToOne(mappedBy="storage", fetch=FetchType.LAZY)
	public Server getServer() {
		return server;
	}

	@SuppressWarnings("unused")
	private void setServer(Server server) {
		this.server = server;
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
		
		if(obj instanceof Storage)
		{
			Storage storage = (Storage)obj;

			return(storage.getId().equals(this.getId()));
		}
		return false;
	}
	
}