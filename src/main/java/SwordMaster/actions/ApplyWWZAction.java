package SwordMaster.actions;

import java.util.Iterator;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ApplyWWZAction extends AbstractGameAction {
    public ApplyWWZAction() {
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        for (AbstractCard card : AbstractDungeon.player.hand.group) {
            if (card.type == CardType.ATTACK)
                card.setCostForTurn(-9);
        }
        this.isDone = true;
    }
}
