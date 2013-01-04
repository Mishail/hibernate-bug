package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OptimisticLock;

@Entity
@Table(name="server")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
public class Server {

	private Long id;
	
	private Storage storage;

	private Storage timeView;

	private Server parent;

	private List<Server> childServers;
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	
	@OneToOne(optional=false)
	@Cascade(CascadeType.SAVE_UPDATE)
	@OptimisticLock(excluded=true)
	public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

	
	@OneToOne
	@JoinColumn(name="time_view")
	@Cascade(CascadeType.SAVE_UPDATE)
	public Storage getTimeView() {
		return timeView;
	}
	
	public void setTimeView(Storage storage) {
		timeView = storage;
	}
	
	@ManyToOne
	@OptimisticLock(excluded=true)
	public Server getParent() {
		return parent;
	}

	public void setParent( Server parent ) {
		this.parent = parent;
	}
	

	@Column(insertable=false)
	@OneToMany(mappedBy="parent")	
	public List<Server> getChilds() {
		if(childServers == null)
		{
			childServers = new ArrayList<Server>();
		}
		return (childServers);
	}	
	
	@SuppressWarnings("unused")
	private void setChilds(List<Server> childs)
	{
		this.childServers = childs;
	}
	
}
