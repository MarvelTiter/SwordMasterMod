package SwordMaster.relics;

import SwordMaster.SwordMasterMod;
import SwordMaster.utils.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Limiter extends CustomRelic {
    public static final String ID = SwordMasterMod.makeID("Limiter");
    private static final Texture IMG = TextureLoader.getTexture(SwordMasterMod.makeRelicPath("Limiter.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(SwordMasterMod.makeRelicOutlinePath("Limiter.png"));
    private int TURNS = 0;

    public Limiter() {
        super(ID, IMG, OUTLINE, AbstractRelic.RelicTier.STARTER, LandingSound.HEAVY);
    }

    public void atBattleStart() {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, 1), 1));
        addToBot(new ApplyPowerAction(p, p, new LoseDexterityPower(p, 1), 1));

    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

}
