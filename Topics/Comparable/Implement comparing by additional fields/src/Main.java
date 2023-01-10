class Article implements Comparable<Article> {
    private String title;
    private int size;

    public Article(String title, int size) {
        this.title = title;
        this.size = size;
    }

    public String getTitle() {
        return this.title;
    }

    public int getSize() {
        return this.size;
    }

    @Override
    public int compareTo(Article otherArticle) {
        int res = Integer.compare(size, otherArticle.size);
        if (res != 0) {
            return res;
        } else {
            return title.compareTo(otherArticle.title);

        }
    }
}