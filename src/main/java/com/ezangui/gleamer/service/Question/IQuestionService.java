package com.ezangui.gleamer.service.Question;

import com.ezangui.gleamer.service.player.Player;

public interface IQuestionService {
    void init();

    String getQuestionForPlayer(Player player);

    Category getQuestionCategoryForPlayer(Player player);
}
