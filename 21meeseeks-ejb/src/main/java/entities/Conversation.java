package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Conversation implements Serializable 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idConversationn;
	
	@ManyToMany
	private List<User> participants;
	
	@Column(nullable=true)
	private String subject;
	
	@OneToMany(mappedBy="conversation")
	private List<Message> messages;
	
	private ConversationType conversationType;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	public int getIdConversationn() {
		return idConversationn;
	}

	public void setIdConversationn(int idConversation) {
		this.idConversationn = idConversation;
	}

	public List<User> getParticipants() {
		return participants;
	}

	public void setParticipants(List<User> participants) {
		this.participants = participants;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
}
