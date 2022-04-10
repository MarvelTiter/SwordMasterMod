package SwordMaster.relics;

import SwordMaster.SwordMasterMod;
import SwordMaster.utils.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Bracelet extends CustomRelic {
    public static final String ID = SwordMasterMod.makeID("Bracelet");
    private static final Texture IMG = TextureLoader.getTexture(SwordMasterMod.makeRelicPath("Bracelet.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(SwordMasterMod.makeRelicOutlinePath("Bracelet.png"));
    private int TURNS = 0;

    public Bracelet() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    @Override
    public void atTurnStart() {
        addToBot(new DrawCardAction(1));
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

}
