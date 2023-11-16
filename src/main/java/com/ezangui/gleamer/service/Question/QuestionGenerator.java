package com.ezangui.gleamer.service.Question;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Abstract the question generation process,
 * This is a simplified version that generates dummy questions.
 * Other implementations could be used to load questions from a database or external providers.
 */
public class QuestionGenerator implements IQuestionGenerator {
    public Deque<Question> generate(Category category, int numberOfQuestions) {
        // ArrayDeque is employed because we are using a FIFO queue, and ArrayDeque is widely recognized for its performance.
        Deque<Question> questions = new ArrayDeque<>(numberOfQuestions);
        for (int i = 0; i < numberOfQuestions; i++) {
            questions.addLast(new Question(category.id(), category.name() + " Question " + i));
        }
        return questions;
    }
}
