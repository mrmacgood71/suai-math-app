package it.macgood.mathanappkt.ui.handbook;

public class Handbook {

    private Long id;
    private int mDrawable;
    private String mPath;
    private String mTitle;
    private String mAuthor;

    public Handbook() {
    }

    public Handbook(Long id, int mDrawable, String mTitle, String mAuthor) {
        this.id = id;
        this.mDrawable = mDrawable;
        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
    }

    public Handbook(Long id, String mPath, String mTitle, String mAuthor) {
        this.id = id;
        this.mPath = mPath;
        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
    }

    public Handbook(Long id, int mDrawable, String mPath, String mTitle, String mAuthor) {
        this.id = id;
        this.mDrawable = mDrawable;
        this.mPath = mPath;
        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDrawable() {
        return mDrawable;
    }

    public void setDrawable(int mDrawable) {
        this.mDrawable = mDrawable;
    }

    public String getPath() {
        return mPath;
    }

    public void setPath(String mPath) {
        this.mPath = mPath;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }
}
