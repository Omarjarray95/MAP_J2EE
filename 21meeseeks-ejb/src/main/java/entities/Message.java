package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Message implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMessage;
	
	@ManyToOne
	private User sender;
	
	@ManyToMany
	private List<User> receivers;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date sendingDate;
	
	private String content;
	
	@ManyToOne
	private Conversation conversation;

	public int getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(int idMessage) {
		this.idMessage = idMessage;
	}

	public Date getSendingDate() {
		return sendingDate;
	}

	public void setSendingDate(Date sendingDate) {
		this.sendingDate = sendingDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
