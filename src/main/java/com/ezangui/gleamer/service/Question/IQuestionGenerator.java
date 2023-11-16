package com.ezangui.gleamer.service.Question;

import java.util.Deque;

public interface IQuestionGenerator {
    Deque<Question> generate(Category categoryType, int numberOfQuestions);
}
