package com.github.enid3.cardgames.game.impl.cardcomparsion;

import com.github.enid3.cardgames.game.lib.engine.entity.Player;
import com.github.enid3.cardgames.game.lib.engine.components.Hand;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardComparisonGamePlayer extends Player {
   private Hand hand;

   public CardComparisonGamePlayer(Player player) {
      super(player);
      this.hand = new Hand();
   }
}
