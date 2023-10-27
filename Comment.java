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
    private UUID commentBackEdge;

    public Comment(String commentData, UUID author) {
        // store commentData
        commentData = commentData;
        // store UUID of comment author
        author = author;
        // then, find out what time they made the comment
        // and then store it
        time = ZonedDateTime.now();
        // initialize the array list of 'threads'
        // a nullptr signifies that the comment
        // has no children
        replies = null;
        // assign new ID
        commentID = UUID.randomUUID();
    }

    public Comment(UUID comemntID, String commentData, UUID author,
        ArrayList<Comment> replies, ZonedDateTime time, UUID commentBackEdge) {
            commentID = comemntID;
            commentData = commentData;
            author = author;
            replies = replies;
            time = time;
            commentBackEdge = commentBackEdge;
    }

    public boolean editComment(String newComment) {
        if(newComment == null)
            return false;
        commentData = newComment;
        return true;
    }

    public String getCommentData() {
        return commentData;
    }

    public UUID getAuthor() {
        return author;
    }

    public UUID getBackEdge() {
        return commentBackEdge;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public boolean addReply(String reply, UUID author) {
        if(reply == null)
            return false;
        replies.add(new Comment(reply, author));
        return true;
    }

    public ArrayList<Comment> getReplies() {
        return replies;
    }

    public UUID getID() {
        return commentID;
    }

    public boolean equls(UUID commentAttempt) {
        return commentID == commentAttempt;
    }

}
