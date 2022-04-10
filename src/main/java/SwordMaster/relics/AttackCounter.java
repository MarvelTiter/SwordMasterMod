package SwordMaster.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import SwordMaster.SwordMasterMod;
import SwordMaster.utils.TextureLoader;
import basemod.abstracts.CustomRelic;

public class AttackCounter extends CustomRelic {
    public static final String ID = SwordMasterMod.makeID("AttackCounter");
    private static final Texture IMG = TextureLoader.getTexture(SwordMasterMod.makeRelicPath("AttackCounter.png"));
    private static final Texture OUTLINE = TextureLoader
            .getTexture(SwordMasterMod.makeRelicOutlinePath("AttackCounter.png"));

    public AttackCounter() {
        super(ID, IMG, OUTLINE, AbstractRelic.RelicTier.STARTER, LandingSound.HEAVY);
    }

    @Override
    public void onVictory() {
        counter = 0;
    }

    @Override
    public void atBattleStart() {
        reset();
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        counter++;
    }

    public void modifyCount(int count) {
        counter += count;
        flash();
    }

    public void reset(){
        counter = 0;
        flash();
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
}
