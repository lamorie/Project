package ca.ualberta.cs.cmput301f14t14.questionapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class Answer extends Model implements Serializable {

	private static final long serialVersionUID = -237004584128041997L;

	private UUID id;
	private Image image;
	private String body;
	private List<UUID> commentList;
	private String author;
	private UUID parent;
	private Date date;
	private int upVotes;

	
	public Answer() {
		this.id = null;
		this.body = null;
		this.author = null;
		this.image = null;
		this.parent = null;
		this.commentList = new ArrayList<UUID>();
		setDate(new Date());
		upVotes = 0;
	}

	//Create answer with a parent, body, string and optional image
	public Answer(UUID parent, String body, String author, Image image) {
		setId(UUID.randomUUID());
		setBody(body);
		setAuthor(author);
		setImage(image);
		setParent(parent);
		setCommentList(new ArrayList<UUID>());
		setDate(new Date());
		upVotes = 0;
	}
	
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public String getBody() {
		return body;
	}
	
	//Make sure there is a body and trim the whitespace
	public void setBody(String body) {
		if (body == null || body.trim().length() == 0)
			throw new IllegalArgumentException("Answer body may not be blank.");
		this.body = body;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}

	public boolean hasComment(UUID comment) {
		return commentList.contains(comment);
	}

	//add comment if not already added
	public void addComment(UUID comment) {
		if (!hasComment(comment)) {
			commentList.add(comment);
			comment.setParent(this.id);
		}
	}

	public Integer getUpvotes() {
		return upVotes;
	}

	public void addUpvote() {
		upVotes++;
	}

	public List<UUID> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<UUID> commentList) {
		this.commentList = commentList;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
	public UUID getParent() {
		return parent;
	}
	
	public void setParent(UUID parent) {
		this.parent = parent;
	}

	//Checks the attributes of an answer against the object and this to make sure it is the same
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Answer)) return false;
		Answer a = (Answer) o;
		
		return a.id.equals(this.id) && a.body.equals(this.body) &&
				a.author.equals(this.author) && a.commentList.equals(this.commentList);
	}
	
	//Give the string representation of an answer
	@Override
	public String toString() {
		return String.format("Answer [%s - %s]", body, author);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
