package ir.gooble.clinic.model;

public class Blog {

    private Post Posts[];

    public Post[] getPosts() {
        return Posts;
    }

    public void setPosts(Post[] posts) {
        Posts = posts;
    }

    public class Post {

        private int PID;
        private String PostContent;
        private String PostDate;
        private String PostPicture;
        private String PostTitle;

        public int getPID() {
            return PID;
        }

        public void setPID(int PID) {
            this.PID = PID;
        }

        public String getPostContent() {
            return PostContent;
        }

        public void setPostContent(String postContent) {
            PostContent = postContent;
        }

        public String getPostDate() {
            return PostDate;
        }

        public void setPostDate(String postDate) {
            PostDate = postDate;
        }

        public String getPostPicture() {
            return PostPicture;
        }

        public void setPostPicture(String postPicture) {
            PostPicture = postPicture;
        }

        public String getPostTitle() {
            return PostTitle;
        }

        public void setPostTitle(String postTitle) {
            PostTitle = postTitle;
        }
    }
}
