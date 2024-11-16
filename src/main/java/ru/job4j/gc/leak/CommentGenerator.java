package ru.job4j.gc.leak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommentGenerator implements Generate {

    private static final String PATH_PHRASES = "data/gc/leak/phrases.txt";
    private static final int COUNT = 50;

    private final List<Comment> comments = new ArrayList<>();
    private List<String> phrases;
    private UserGenerator userGenerator;
    private Random random;

    public CommentGenerator(Random random, UserGenerator userGenerator) throws IOException {
        this.userGenerator = userGenerator;
        this.random = random;
        read();
    }

    private void read() throws IOException {
            phrases = read(PATH_PHRASES);
    }

    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public void generate() {
        comments.clear();
        if (phrases != null) {
            for (int i = 0; i < COUNT; i++) {
                String comment = "%s %s %s".formatted(
                        phrases.get(random.nextInt(phrases.size())),
                        phrases.get(random.nextInt(phrases.size())),
                        phrases.get(random.nextInt(phrases.size())));
                comments.add(new Comment(comment,
                        userGenerator.randomUser()));
            }
        }
    }
}
