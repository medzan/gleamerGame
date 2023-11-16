package com.ezangui.gleamer.service.Question;

import com.ezangui.gleamer.configuration.GameConfiguration;
import com.ezangui.gleamer.service.player.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class QuestionService implements IQuestionService {
    private static final Logger logger = LoggerFactory.getLogger(QuestionService.class);
    private final IQuestionGenerator questionGenerator;
    private final GameConfiguration gameConfiguration;
    private Map<Integer, Category> positionMap;
    private Map<Category, Deque<Question>> questions;

    public QuestionService(IQuestionGenerator questionGenerator, GameConfiguration gameConfiguration) {
        this.questionGenerator = questionGenerator;
        this.gameConfiguration = gameConfiguration;

    }

    @Override
    public void init() {
        questions  = generateQuestions(gameConfiguration.getTotalNumberOfQuestions());
        positionMap = buildPositionMap(gameConfiguration.getBoardSize());
    }

    private Map<Category, Deque<Question>> generateQuestions(int numberOfQuestions) {
        Map<Category, Deque<Question>> questions = new HashMap<>();
        for (Category category : fetchCategories()) {
            questions.put(category, questionGenerator.generate(category, numberOfQuestions));
        }
        return questions;
    }

    //Building the position in advance during the game's initialization will enable
    //fast retrieval and scaling based on questions inputs and categories
    private Map<Integer, Category> buildPositionMap(int boardSize) {
        Map<Integer, Category> positionCategoryMap = new HashMap<>();
        int i = 0;
        while (i < boardSize) {
            for (Category category : fetchCategories()) {
                positionCategoryMap.put(i++, category);
            }
        }
        return positionCategoryMap;
    }

    @Override
    public String getQuestionForPlayer(Player player) {
        Category category = getQuestionCategoryForPlayer(player);
        Deque<Question> queue = questions.get(category);
        if (queue == null) {
            // Type of error to be captured by runtime monitoring tools
            logger.error("Null queue is not permissible for the question's queue; an error occurred during the initialization of questions.");
        }
        if (queue == null || queue.isEmpty()) {
            // The business is unclear in this scenario, we assume that we will generate a new list of questions.
            queue = questionGenerator.generate(category, gameConfiguration.getTotalNumberOfQuestions());
            questions.put(category, queue);
        }
        return queue.removeFirst().content();

    }

    @Override
    public Category getQuestionCategoryForPlayer(Player player) {
        return positionMap.get(player.getPosition());
    }

    private List<Category> fetchCategories() {
        return Arrays.asList(new Category(1, "Pop"), new Category(2, "Science"), new Category(3, "Sports"), new Category(4, "Rock"));
    }
}
