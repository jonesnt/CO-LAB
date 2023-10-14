import java.util.ArrayList;
import java.time.ZonedDateTime;

/**
 * @author jonesnt
 */
public class Comment {
    private ArrayList<Comment> commentList;
    private String commentData;
    private ZonedDateTime commentDate;
    private User author;

    public Comment(String commentData, User author) {
        // start by storing comment data (the important part)
        commentData = commentData;
        // next, store the username of the person
        // who is making said comment
        author = author;
        // then, find out what time they made the comment
        // and then store it for posterity
        commentDate = ZonedDateTime.now();
        // initialize the array list of 'threads'
        // a nullptr signifies that the comment
        // has no children
        commentList = null;
    }

    public void addReply(String replyData, User user) {
        // Construct a new Comment object
        Comment reply = new Comment(replyData, user);
        // this Comment will be placed in the thread list
        commentList.add(reply);
    }

    public String getComment() {
        return commentData;
    }

    public ArrayList<Comment> getThread() {
        return commentList;
    }

    public String getCommentTime() {
        return commentDate.toString();
        // TODO May have to edit the formatting on this line
    }

    public String getCommentAuthor() {
        return author.println();
    }

    public boolean hasReplies() {
        return !commentList.isEmpty();
    }

    public int getNumReplies() {
        if (commentList.isEmpty())
            return 0;
        return commentList.size();
    }

    /**
     * NOTE: This method expects a non-zero indexed reply number
     * which it will be removing. If you want to delte the 7th reply,
     * actually pass in a 7 as the commentNum argument.
     * @param replyNum Positive integer representing which reply
     * in the sequence of rendered replies must be removed.
     * @param currentUser The logged-in user attempting to make
     * changes to the replies of a comment
     * @return boolean representing whether a change was sucessful
     */
    public boolean removeReply(int replyNum, User currentUser) {
        if(replyNum <= 0)
            return false;
        int indexNum = replyNum -1;
        // check if request valid
        if(!hasReplies() || replyNum > getNumReplies())
            return false;
        // check for appropriate user permissions
        if(commentList.get(indexNum).getCommentAuthor() == currentUser) {
            commentList.remove(indexNum);
            return true;
        }
        return false;
    }

}
