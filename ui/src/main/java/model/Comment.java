package model;
import java.util.ArrayList;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * @author jonesnt
 * This class provides a functional data strcture
 * with which to store threads and Comments on the
 * CO-LAB platform.
 * Comments are structued as objects containing
 * ArrayLists of other Comments. The instantiated Comment
 * object represents a single comment with replies which are
 * stored within said comment's ArrayList. As such, a reply
 * IS A comment.
 */
public class Comment {
    private UUID commentID;
    private String commentData;
    private UUID author;
    private ArrayList<Comment> replies;
    private ZonedDateTime time;
    private Comment commentBackEdge;

    public Comment(String commentData, UUID author, Comment backEdge) {
        // store commentData
        this.commentData = commentData;
        // store UUID of comment author
        this.author = author;
        // then, find out what time they made the comment
        // and then store it
        this.time = ZonedDateTime.now();
        // initialize the array list of 'threads'
        // a nullptr signifies that the comment
        // has no children
        this.replies = new ArrayList<Comment>();
        // assign new ID
        this.commentID = UUID.randomUUID();
        this.commentBackEdge = backEdge;
    }

    public Comment(UUID comemntID, String commentData, UUID author,
        ArrayList<Comment> replies, ZonedDateTime time, UUID commentBackEdge) {
            this.commentID = comemntID;
            this.commentData = commentData;
            this.author = author;
            this.replies = replies;
            this.time = time;
            //BROKEN
            // commentBackEdge = commentBackEdge;
    }

    public boolean editComment(String newComment) {
        if(newComment == null)
            return false;
        this.commentData = newComment;
        return true;
    }

    public String getCommentData() {
        return commentData;
    }

    public UUID getAuthor() {
        return author;
    }

    public Comment getBackEdge() {
        return commentBackEdge;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public boolean addReply(String reply, UUID author) {
        if(reply == null)
            return false;
        replies.add(new Comment(reply, author, this));
        return true;
    }

    public ArrayList<Comment> getReplies() {
        return replies;
    }

    public UUID getID() {
        return commentID;
    }

    public boolean equals(UUID commentAttempt) {
        return commentID == commentAttempt;
    }

    public boolean removeReply(int choice, UUID currentUser) {
        if(choice >= 0 && choice < replies.size()) {  // nested to prevent nullptr exception
            if(currentUser == replies.get(choice).getAuthor()) {
                replies.remove(choice);
                return true;
            }
        }
        return false;  
    } 

}
