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
            return decompile(PostContent);
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
            return decompile(PostTitle);
        }

        public void setPostTitle(String postTitle) {
            PostTitle = postTitle;
        }

        private String decompile(String text) {
            StringBuilder builder = new StringBuilder();
            boolean filter = false;
            for (char character : text.toCharArray()) {
                if (character == '<') {
                    filter = true;
                } else if (character == '>') {
                    filter = false;
                } else {
                    if (!filter) {
                        builder.append(character);
                    }
                }
            }
            return builder.toString();
        }
    }
}
