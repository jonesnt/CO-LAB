import java.util.ArrayList;
import java.time.ZonedDateTime;

public class Comment {
    private ArrayList<Comment> commentList;
    private Comment prevComment;
    private String commentData;
    private ZonedDateTime commentDate;
    private User author;
    private Comment headComment;

    public Comment(String commentData, User user) {
        // TODO
    }

    public addReply(String replyData, User user) {
        // TODO
    }

    public String getComment() {
        return commentData;
    }

    public ArrayList<Comment> getThread() {
        return commentList;
    }

    public changeThread(int selection) {
        // TODO rely on implementing class to recognize a null
        // pass as a comment without a thread (this will cause
        // a nullptr exception otherwise :))
    }

    private setPreviousComment(Comment prevComment) {
        // TODO
    }

    public String getCommentTime() {
        // TODO
    }

    public String getCommentAuthor() {
        // TODO Get User and use println() fn.
    }

    public boolean removeComment(int CommentNum, User currentUser) {
        // TODO
    }
}
