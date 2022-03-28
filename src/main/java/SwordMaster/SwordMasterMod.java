package SwordMaster;

import SwordMaster.cards.Master_AbstractCard;
import SwordMaster.characters.swordMaster;
import SwordMaster.relics.Limiter;
import SwordMaster.utils.IDCheckDontTouchPls;
import SwordMaster.utils.KeywordWithProper;
import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@SpireInitializer
public class SwordMasterMod implements EditCardsSubscriber, EditRelicsSubscriber, EditStringsSubscriber, EditKeywordsSubscriber, EditCharactersSubscriber, PostInitializeSubscriber, AddAudioSubscriber, OnStartBattleSubscriber {
    private static String modID;
    public static final String ENABLE_PLACEHOLDER_SETTINGS = "enablePlaceholder";
    private static final String MODNAME = "The Sword Master";
    private static final String AUTHOR = "MarvelTiter";
    private static final String DESCRIPTION = "A base for Slay the Spire to start your own mod from, feat. the Default.";
    private static final String ATTACK_DEFAULT_GRAY = "swordMasterResources/images/512/bg_attack_default_gray.png";
    private static final String SKILL_DEFAULT_GRAY = "swordMasterResources/images/512/bg_skill_default_gray.png";
    private static final String POWER_DEFAULT_GRAY = "swordMasterResources/images/512/bg_power_default_gray.png";
    private static final String ENERGY_ORB_DEFAULT_GRAY = "swordMasterResources/images/512/card_default_gray_orb.png";
    private static final String CARD_ENERGY_ORB = "swordMasterResources/images/512/card_small_orb.png";
    private static final String ATTACK_DEFAULT_GRAY_PORTRAIT = "swordMasterResources/images/1024/bg_attack_default_gray.png";
    private static final String SKILL_DEFAULT_GRAY_PORTRAIT = "swordMasterResources/images/1024/bg_skill_default_gray.png";
    private static final String POWER_DEFAULT_GRAY_PORTRAIT = "swordMasterResources/images/1024/bg_power_default_gray.png";
    private static final String ENERGY_ORB_DEFAULT_GRAY_PORTRAIT = "swordMasterResources/images/1024/card_default_gray_orb.png";
    private static final String THE_DEFAULT_BUTTON = "swordMasterResources/images/charSelect/SwordMasterButton.png";
    private static final String THE_DEFAULT_PORTRAIT = "swordMasterResources/images/charSelect/SwordMasterSelect.png";
    public static final String THE_DEFAULT_IDLE = "swordMasterResources/images/char/swordMaster/idle.png";
    public static final String THE_DEFAULT_SHOULDER_1 = "swordMasterResources/images/char/swordMaster/shoulder.png";
    public static final String THE_DEFAULT_SHOULDER_2 = "swordMasterResources/images/char/swordMaster/shoulder2.png";
    public static final String THE_DEFAULT_CORPSE = "swordMasterResources/images/char/swordMaster/corpse.png";
    public static final String BADGE_IMAGE = "swordMasterResources/images/Badge.png";
    public static CardGroup deadList;
    public static boolean enablePlaceholder = true;
    public static boolean[] activeTutorials = {true};
    public static Properties HermitModDefaultSettings = new Properties();
    public static Properties theDefaultDefaultSettings = new Properties();
    public static boolean tackybypass = false;
    public static final Color DEFAULT_GRAY = CardHelper.getColor(64.0f, 70.0f, 70.0f);
    public static final Color CHARACTER_COLOR = CardHelper.getColor(129.0f, 232.0f, 223.0f);
    public static final Color PLACEHOLDER_POTION_LIQUID = CardHelper.getColor(209.0f, 53.0f, 18.0f);
    public static final Color PLACEHOLDER_POTION_HYBRID = CardHelper.getColor(255.0f, 230.0f, 230.0f);
    public static final Color PLACEHOLDER_POTION_SPOTS = CardHelper.getColor(100.0f, 25.0f, 10.0f);
    private static boolean alreadyInitialized = false;

    public static String makeCardPath(String resourcePath) {
        return getModID() + "Resources/images/cards/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return getModID() + "Resources/images/relics/" + resourcePath;
    }

    public static String makeRelicOutlinePath(String resourcePath) {
        return getModID() + "Resources/images/relics/outline/" + resourcePath;
    }

    public static String makeOrbPath(String resourcePath) {
        return getModID() + "Resources/orbs/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return getModID() + "Resources/images/powers/" + resourcePath;
    }

    public static String makeEventPath(String resourcePath) {
        return getModID() + "Resources/images/events/" + resourcePath;
    }

    public static String getModID() {
        return modID;
    }

    public SwordMasterMod() {
        System.out.println("Subscribe to BaseMod hooks");
        BaseMod.subscribe(this);
        setModID("swordMaster");
        System.out.println("Done subscribing");
        System.out.println("Creating the color " + swordMaster.Enums.COLOR_LIGHT_BLUE.toString());
        BaseMod.addColor(swordMaster.Enums.COLOR_LIGHT_BLUE
                , CHARACTER_COLOR
                , CHARACTER_COLOR
                , CHARACTER_COLOR
                , CHARACTER_COLOR
                , CHARACTER_COLOR
                , CHARACTER_COLOR
                , CHARACTER_COLOR
                , ATTACK_DEFAULT_GRAY
                , SKILL_DEFAULT_GRAY
                , POWER_DEFAULT_GRAY
                , ENERGY_ORB_DEFAULT_GRAY
                , ATTACK_DEFAULT_GRAY_PORTRAIT
                , SKILL_DEFAULT_GRAY_PORTRAIT
                , POWER_DEFAULT_GRAY_PORTRAIT
                , ENERGY_ORB_DEFAULT_GRAY_PORTRAIT
                , CARD_ENERGY_ORB);
        System.out.println("Done creating the color");
        System.out.println("Adding mod settings");
        theDefaultDefaultSettings.setProperty(ENABLE_PLACEHOLDER_SETTINGS, "FALSE");
        try {
            SpireConfig config = new SpireConfig("defaultMod", "theDefaultConfig", theDefaultDefaultSettings);
            config.load();
            enablePlaceholder = config.getBool(ENABLE_PLACEHOLDER_SETTINGS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Done adding mod settings");
    }

    private void setModID(String id) {
        this.modID = id;
    }


    public static void initialize(){
        new SwordMasterMod();
    }


    @Override
    public void receiveAddAudio() {

    }

    @Override
    public void receiveEditCards() {
        new AutoAdd("SwordMaster").packageFilter(Master_AbstractCard.class).setDefaultSeen(true).cards();
        System.out.println("Done adding cards!");
//        BaseMod.addCard(new Strike_SwordMaster());
//        BaseMod.addCard(new Defend_SwordMaster());
    }

    private static void pathCheck() {
        IDCheckDontTouchPls EXCEPTION_STRINGS = (IDCheckDontTouchPls) new Gson().fromJson(new InputStreamReader(SwordMasterMod.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json"), StandardCharsets.UTF_8), IDCheckDontTouchPls.class);
        String packageName = SwordMasterMod.class.getPackage().getName();
        FileHandle resourcePathExists = Gdx.files.internal(getModID() + "Resources");
        if (modID.equals(EXCEPTION_STRINGS.DEVID)) {
            return;
        }
        if (!packageName.equals(getModID())) {
            throw new RuntimeException(EXCEPTION_STRINGS.PACKAGE_EXCEPTION + getModID());
        } else if (!resourcePathExists.exists()) {
            throw new RuntimeException(EXCEPTION_STRINGS.RESOURCE_FOLDER_EXCEPTION + getModID() + "Resources");
        }
    }


    @Override
    public void receiveEditCharacters() {
        System.out.println("Beginning to edit characters. Add " + swordMaster.Enums.SWORD_MASTER.toString());
        BaseMod.addCharacter(new swordMaster("the Sword Master", swordMaster.Enums.SWORD_MASTER), THE_DEFAULT_BUTTON, THE_DEFAULT_PORTRAIT, swordMaster.Enums.SWORD_MASTER);
        // receiveEditPotions();
        System.out.println("Added " + swordMaster.Enums.SWORD_MASTER.toString());

    }

    @Override
    public void receiveEditKeywords() {
        loadKeywords("zhs");
    }
    private void loadKeywords(String langKey) {
        if (!Gdx.files.internal(getModID() + "Resources/localization/" + langKey).exists()) {
            System.out.println("Hermit: Language not found: " + langKey);
            return;
        }
        KeywordWithProper[] keywordWithProperArr = BaseMod.gson.fromJson(GetLocString(langKey, "Keyword"), KeywordWithProper[].class);
        for (KeywordWithProper keyword : keywordWithProperArr) {
            BaseMod.addKeyword(modID, keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
//            if (keyword.ID.equals("rugged")) {
//                Tonic.keyword_name = keyword.PROPER_NAME;
//                Tonic.keyword_description = keyword.DESCRIPTION;
//            }
//            if (keyword.ID.equals("bruise")) {
//                BlackBile.keyword_name = keyword.PROPER_NAME;
//                BlackBile.keyword_description = keyword.DESCRIPTION;
//            }
        }
    }

    @Override
    public void receiveEditRelics() {
        System.out.println("Adding relics");
        BaseMod.addRelicToCustomPool(new Limiter(), swordMaster.Enums.COLOR_LIGHT_BLUE);
        UnlockTracker.markRelicAsSeen(Limiter.ID);
        System.out.println("Done adding relics!");

    }

    @Override
    public void receiveEditStrings() {
        loadStrings("zhs");
    }

    private void loadStrings(String langKey) {
        if (!Gdx.files.internal(getModID() + "Resources/localization/" + langKey).exists()) {
            System.out.println("SwordMaster: Language not found: " + langKey);
            return;
        }
        BaseMod.loadCustomStrings(CardStrings.class, GetLocString(langKey, "Card"));
        BaseMod.loadCustomStrings(RelicStrings.class, GetLocString(langKey, "Relic"));
        BaseMod.loadCustomStrings(PowerStrings.class, GetLocString(langKey, "Power"));
        BaseMod.loadCustomStrings(CharacterStrings.class, GetLocString(langKey, "Character"));
//        BaseMod.loadCustomStrings(UIStrings.class, GetLocString(langKey, "UI"));
//        BaseMod.loadCustomStrings(PotionStrings.class, GetLocString(langKey, "Potion"));
//        BaseMod.loadCustomStrings(TutorialStrings.class, GetLocString(langKey, "Tutorial"));
    }

    private static String GetLocString(String locCode, String name) {
        return Gdx.files.internal(getModID() + "Resources/localization/" + locCode + "/SwordMasterMod-" + name + ".json").readString(String.valueOf(StandardCharsets.UTF_8));
    }


    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {

    }

    @Override
    public void receivePostInitialize() {

    }

    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }

}
