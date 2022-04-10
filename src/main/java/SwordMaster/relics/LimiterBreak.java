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

public class LimiterBreak extends CustomRelic {
    public static final String ID = SwordMasterMod.makeID("LimiterBreak");
    private static final Texture IMG = TextureLoader.getTexture(SwordMasterMod.makeRelicPath("LimiterBreak.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(SwordMasterMod.makeRelicOutlinePath("LimiterBreak.png"));
    private int TURNS = 0;

    public LimiterBreak() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.HEAVY);
    }

    public void atBattleStart() {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));
        addToBot(new ApplyPowerAction(p, p, new LoseStrengthPower(p, 1), 1));
        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, 2), 1));
        addToBot(new ApplyPowerAction(p, p, new LoseDexterityPower(p, 2), 1));
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(Limiter.ID);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

}
