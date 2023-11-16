package com.ezangui.gleamer.service.Question;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Deque;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class QuestionGeneratorTest {

    @Test
    void shouldGenerateExactNumberOfQuestion() {
        IQuestionGenerator questionGenerator = new QuestionGenerator();
        int numQuestionToGenerate = 10;
        Deque<Question> popQuestions = questionGenerator.generate(new Category(1, "pop"), numQuestionToGenerate);
        assertNotNull(popQuestions);
        Assertions.assertEquals(numQuestionToGenerate, popQuestions.size());
        while (!popQuestions.isEmpty()) {
            Question q = popQuestions.removeFirst();
            assertEquals(1, q.categoryId());
            assertNotNull(q);
            Assertions.assertFalse(q.content().isBlank());
        }
    }
}