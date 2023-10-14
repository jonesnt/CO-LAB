import java.util.ArrayList;
import java.time.ZonedDateTime;

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
    private ArrayList<Comment> commentList;
    private String commentData;
    private ZonedDateTime commentDate;
    private User author;

    /**
     * The Comment class constructor requires both the comment
     * that is to be created (text data) and the User creating
     * said comment to be passed as arguments.
     * @param commentData String containing desired comment
     * @param author User creating comment
     */
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

    /**
     * The addReply method adds a reply to the
     * instantiated Comment.
     * @param replyData String containing desired reply
     * @param user User making said reply
     */
    public void addReply(String replyData, User user) {
        // Construct a new Comment object
        Comment reply = new Comment(replyData, user);
        // this Comment will be placed in the thread list
        commentList.add(reply);
    }

    /**
     * This method returns a comment's text.
     * @return Sting containing comment text
     */
    public String getComment() {
        return commentData;
    }

    /**
     * This method returns an ArrayList containing
     * the instantiated comment's replies.
     * @return ArrayList of type Comment which contains
     * all replies to the current Comment.
     */
    public ArrayList<Comment> getThread() {
        return commentList;
    }

    /**
     * This method returns a String describing
     * the time the comment was made/changed.
     * @return String describing the time a
     * comment was made or changed.
     */
    public String getCommentTime() {
        return commentDate.toString();
        // TODO May have to edit the formatting on this line
    }

    /**
     * This method prints name of the Comment
     * author.
     * @return String containing user's first
     * and last name.
     */
    public String getCommentAuthor() {
        return author.getFirstName + " " + author.getLastName();
    }

    /**
     * This method is a helper-method which
     * will return a boolean describing whether or not
     * the Comment has replies.
     * @return boolean describing whether the Comment
     * has replies
     */
    public boolean hasReplies() {
        return !commentList.isEmpty();
    }

    /**
     * This method edits the text of the current Comment
     * if the Comment was made by the current User.
     * @param newComment String containing new Comment data
     * @param currentUser User representing the current User
     * @return boolean representing if the operation was
     * able to be completed
     */
    public boolean editComment(String newComment, User currentUser) {
        if(author != currentUser)
            return false;
        commentData = newComment;
        return true;
    }

    /**
     * This method returns the numebr of replies
     * to the current Comment.
     * @return int describing the number of
     * replies to this comment
     */
    public int getNumReplies() {
        if (commentList.isEmpty())
            return 0;
        return commentList.size();
    }

    /**
     * NOTE: This method expects a non-zero-indexed reply number
     * which it will be removing. If you want to delte the 7th reply,
     * actually pass in a 7 as the commentNum argument.
     * @param replyNum Positive integer representing which reply
     * in the sequence of rendered replies must be removed.
     * @param newData String containing new reply data. If the
     * user wishes to delete their reply, simply pass null as
     * an argument for this method and the reply will be deleted.
     * @param currentUser The logged-in user attempting to make
     * changes to the replies of a comment
     * @return boolean representing whether a change was sucessful
     */
    public boolean editReply(int replyNum, String newData, User currentUser) {
        if(replyNum <= 0)
            return false;
        int indexNum = replyNum -1;
        // check if request valid
        if(!hasReplies() || replyNum > getNumReplies())
            return false;
        // check for appropriate user permissions
        if(commentList.get(indexNum).getCommentAuthor() == currentUser) {
            if(newData == null) {  // if removing reply
                commentList.remove(indexNum);
                return true;
            } else {
                commentList.get(indexNum).editComment(newData, currentUser);
            }
        }
        return false;
    }

}
