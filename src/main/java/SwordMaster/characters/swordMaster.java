package SwordMaster.characters;

import SwordMaster.SwordMasterMod;
import SwordMaster.cards.BackStepCutter;
import SwordMaster.cards.Defend_SwordMaster;
import SwordMaster.cards.SecretSwordArt;
import SwordMaster.cards.Strike_SwordMaster;
import SwordMaster.relics.Limiter;
import basemod.abstracts.CustomPlayer;
import basemod.animations.AbstractAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;

import java.util.ArrayList;

public class swordMaster extends CustomPlayer {

    public static final int ENERGY_PER_TURN = 3;
    public static final int STARTING_HP = 75;
    public static final int MAX_HP = 75;
    public static final int STARTING_GOLD = 99;
    public static final int CARD_DRAW = 5;
    public static final int ORB_SLOTS = 0;
    public static float update_timer = 0.0f;
    public static boolean moon_fade = false;
    private static final String ID = SwordMasterMod.makeID("swordMaster");
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    private static final String[] NAMES = characterStrings.NAMES;
    private static final String[] TEXT = characterStrings.TEXT;
    public static final String[] orbTextures = {
            "swordMasterResources/images/char/swordMaster/orb/layer1.png",
            "swordMasterResources/images/char/swordMaster/orb/layer2.png",
            "swordMasterResources/images/char/swordMaster/orb/layer3.png",
            "swordMasterResources/images/char/swordMaster/orb/layer4.png",
            "swordMasterResources/images/char/swordMaster/orb/layer5.png",
            "swordMasterResources/images/char/swordMaster/orb/layer6.png",
            "swordMasterResources/images/char/swordMaster/orb/layer1d.png",
            "swordMasterResources/images/char/swordMaster/orb/layer2d.png",
            "swordMasterResources/images/char/swordMaster/orb/layer3d.png",
            "swordMasterResources/images/char/swordMaster/orb/layer4d.png",
            "swordMasterResources/images/char/swordMaster/orb/layer5d.png"};
    private float fireTimer = 0.0f;
    // public Slot eye = this.skeleton.findSlot("eye");

    public static class Enums {
        @SpireEnum
        public static AbstractPlayer.PlayerClass SWORD_MASTER;
        @SpireEnum(name = "MASTER_COLOR")
        public static AbstractCard.CardColor COLOR_LIGHT_BLUE;
        @SpireEnum(name = "MASTER_COLOR")
        public static CardLibrary.LibraryType LIBRARY_COLOR;
    }


    public swordMaster(String name, AbstractPlayer.PlayerClass setClass) {
        super(name, setClass, orbTextures,
                "swordMasterResources/images/char/swordMaster/orb/vfx.png", (float[]) null, new AbstractAnimation() { // from class: hermit.characters.hermit.1
            public AbstractAnimation.Type type() {
                return AbstractAnimation.Type.NONE;
            }
        });
        initializeClass(SwordMasterMod.THE_DEFAULT_IDLE
                , SwordMasterMod.THE_DEFAULT_SHOULDER_2
                , SwordMasterMod.THE_DEFAULT_SHOULDER_1
                , SwordMasterMod.THE_DEFAULT_CORPSE
                , getLoadout(), 20.0f, -10.0f, 220.0f, 290.0f
                , new EnergyManager(ENERGY_PER_TURN));
        this.dialogX = this.drawX + (0.0f * Settings.scale);
        this.dialogY = this.drawY + (220.0f * Settings.scale);
    }


    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        System.out.println("Begin loading starter Deck Strings");
        retVal.add(Strike_SwordMaster.ID);
        retVal.add(Strike_SwordMaster.ID);
        retVal.add(Strike_SwordMaster.ID);
        retVal.add(Strike_SwordMaster.ID);
        retVal.add(Defend_SwordMaster.ID);
        retVal.add(Defend_SwordMaster.ID);
        retVal.add(Defend_SwordMaster.ID);
        retVal.add(Defend_SwordMaster.ID);
        retVal.add(BackStepCutter.ID);
        retVal.add(SecretSwordArt.ID);
        return retVal;
    }

    @Override
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(Limiter.ID);
        return retVal;
    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0], STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, CARD_DRAW, this, getStartingRelics(), getStartingDeck(), false);
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return  NAMES[0];
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return Enums.COLOR_LIGHT_BLUE;
    }

    @Override
    public Color getCardRenderColor() {
        return CardHelper.getColor(129.0f, 232.0f, 223.0f);
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new Strike_SwordMaster();
    }

    @Override
    public Color getCardTrailColor() {
        return CardHelper.getColor(129.0f, 232.0f, 223.0f);
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 0;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, true);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return null;
    }

    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new swordMaster(this.name, this.chosenClass);
    }

    @Override
    public Color getSlashAttackColor() {
        return CardHelper.getColor(129.0f, 232.0f, 223.0f);
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[0];
    }

    @Override
    public String getSpireHeartText() {
        return TEXT[0];
    }

    @Override
    public String getVampireText() {
        return TEXT[1];
    }
}
